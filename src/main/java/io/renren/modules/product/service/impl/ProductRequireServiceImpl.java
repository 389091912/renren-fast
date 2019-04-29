package io.renren.modules.product.service.impl;

import io.renren.modules.product.dao.ProductInfoDao;
import io.renren.modules.product.dao.ProductOrderDetailDao;
import io.renren.modules.product.entity.ProductInfoEntity;
import io.renren.modules.product.entity.ProductOrderDetailEntity;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.product.dao.ProductRequireDao;
import io.renren.modules.product.entity.ProductRequireEntity;
import io.renren.modules.product.service.ProductRequireService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * @author wsy
 */
@Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
@Service("productRequireService")
public class ProductRequireServiceImpl extends ServiceImpl<ProductRequireDao, ProductRequireEntity> implements ProductRequireService {

    @Autowired
    private ProductInfoDao productInfoDao;

    @Autowired
    private ProductOrderDetailDao productOrderDetailDao;


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ProductRequireEntity> page = this.selectPage(
                new Query<ProductRequireEntity>(params).getPage(),
                new EntityWrapper<ProductRequireEntity>().orderBy( "create_time", false )
        );
        if (CollectionUtils.isNotEmpty( page.getRecords() )) {
            for (ProductRequireEntity productRequire : page.getRecords()) {

                if (!StringUtils.isEmpty( productRequire.getProductId() )) {
                    ProductInfoEntity productInfoEntity = productInfoDao.selectById( productRequire.getProductId() );

                    if(!StringUtils.isEmpty(  productInfoEntity)){
                        productRequire.setProductName( StringUtils.isEmpty(productInfoEntity.getProductName()  )?null:productInfoEntity.getProductName() );
                    }


                }
                /**
                 * 统计订单份数
                 */
                Integer productOrderCount = productOrderDetailDao.selectCount( new EntityWrapper<ProductOrderDetailEntity>().eq( "product_id", productRequire.getProductId() ) );

                productRequire.setOrderCount( productOrderCount );

            }
        }
        return new PageUtils(page);
    }

}
