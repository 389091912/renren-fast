package io.renren.modules.product.service.impl;

import io.renren.common.utils.Dict;
import io.renren.modules.oss.dao.SysOssDao;
import io.renren.modules.product.dao.ProductLeaveStorageDao;
import io.renren.modules.product.dao.ProductPutInStorageDao;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.product.dao.ProductInfoDao;
import io.renren.modules.product.entity.ProductInfoEntity;
import io.renren.modules.product.service.ProductInfoService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * @author wsy
 */
@Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
@Service("productInfoService")
public class ProductInfoServiceImpl extends ServiceImpl<ProductInfoDao, ProductInfoEntity> implements ProductInfoService {

    @Autowired
    private SysOssDao sysOssDao;

    @Autowired
    private ProductPutInStorageDao productPutInStorageDao;

    @Autowired
    private ProductLeaveStorageDao productLeaveStorageDao;



    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String) params.get( "key" );
        String rangeBefore = (String) params.get( "rangeBefore" );
        String rangeAfter = (String) params.get( "rangeAfter" );
        Page<ProductInfoEntity> page = new Page<>();
            if(StringUtils.isEmpty( rangeBefore )){
                if (StringUtils.isEmpty( key )) {
                    page = this.selectPage(
                            new Query<ProductInfoEntity>( params ).getPage(),
                            new EntityWrapper<ProductInfoEntity>().orderBy( "create_time", false )
                    );
                }else {
                    page = this.selectPage(
                            new Query<ProductInfoEntity>( params ).getPage(),
                            new EntityWrapper<ProductInfoEntity>()
                                    .or().like( "product_name",key )
                                    .or().like( "model_no",key )
                                    .or().like( "customer_product_no",key )
                                    .orderBy( "create_time", false )
                    );
                }
            }else {
                page = this.selectPage(
                        new Query<ProductInfoEntity>( params ).getPage(),
                        new EntityWrapper<ProductInfoEntity>()
                                .or().like( "product_name",key )
                                .or().like( "model_no",key )
                                .or().like( "customer_product_no",key )
                                .between( "product_batch",rangeBefore,rangeAfter )
                                .orderBy( "create_time", false )
                );
            }



        if (CollectionUtils.isNotEmpty( page.getRecords() )) {
            for (ProductInfoEntity productInfo : page.getRecords()) {

               productInfo.setProductImageUrl(
                       (!StringUtils.isEmpty( productInfo.getProductImageId() )) ?
                               sysOssDao.selectById( productInfo.getProductImageId() ).getUrl() :
                               null );

               productInfo.setProductDrawingUrl(
                       (!StringUtils.isEmpty( productInfo.getProductDrawingId() )) ?
                             sysOssDao.selectById( productInfo.getProductDrawingId() ).getUrl() :
                             null );


                params.replace( "key", productInfo.getId() );
                Integer productNumberCount = productPutInStorageDao.productNumberCount( params );
                if (!StringUtils.isEmpty( productNumberCount )) {
                    Integer leaveNumberCount = productLeaveStorageDao.productLeaveNumberCount( params );
                    if (!StringUtils.isEmpty( leaveNumberCount )) {
                        productInfo.setProductNum( productNumberCount -leaveNumberCount);
                    }else {
                        productInfo.setProductNum( productNumberCount );
                    }


                }


            }
        }
        return new PageUtils(page);
    }

    @Override
    public List<Dict> getAllProductVoList() {
        List<Dict> allProductList = new ArrayList<>();
        List<ProductInfoEntity> productInfoList = baseMapper.selectList( new EntityWrapper<>() );
        if (CollectionUtils.isNotEmpty( productInfoList )) {
            for (ProductInfoEntity productInfo : productInfoList) {
                Dict dict = new Dict();
                dict.setId( productInfo.getId() );
                dict.setName( productInfo.getProductName() );
                allProductList.add( dict );
            }
        }

        return allProductList;
    }

}
