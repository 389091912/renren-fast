package io.renren.modules.product.service.impl;

import io.renren.modules.product.dao.ProductBoxDao;
import io.renren.modules.product.dao.ProductInfoDao;
import io.renren.modules.product.entity.ProductInfoEntity;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.product.dao.ProductPutInStorageDao;
import io.renren.modules.product.entity.ProductPutInStorageEntity;
import io.renren.modules.product.service.ProductPutInStorageService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * @author wsy
 */
@Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
@Service("productPutInStorageService")
public class ProductPutInStorageServiceImpl extends ServiceImpl<ProductPutInStorageDao, ProductPutInStorageEntity> implements ProductPutInStorageService {

    @Autowired
    private ProductInfoDao productInfoDao;

    @Autowired
    private ProductBoxDao productBoxDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        String  key = (String) params.get( "key" );
        EntityWrapper<ProductPutInStorageEntity> productPutInStorageEntity = new EntityWrapper<>();
        if (!StringUtils.isEmpty( key )) {
            productPutInStorageEntity
                    .eq( "product_id", key )
                    .orderBy( "create_time", false );
        }else {
            productPutInStorageEntity.orderBy( "create_time", false );
        }

        Page<ProductPutInStorageEntity> page = this.selectPage(
                new Query<ProductPutInStorageEntity>( params ).getPage(),
                productPutInStorageEntity
        );
        if (CollectionUtils.isNotEmpty( page.getRecords() )) {
            for (ProductPutInStorageEntity productPutInStorage : page.getRecords()) {
                if (!StringUtils.isEmpty( productPutInStorage.getProductId() )) {
                    productPutInStorage.setProductName( StringUtils.isEmpty( productInfoDao.selectById( productPutInStorage.getProductId() ) ) ? null : productInfoDao.selectById( productPutInStorage.getProductId() ).getProductName() );
                }
                if (!StringUtils.isEmpty( productPutInStorage.getBoxId() )) {
                    productPutInStorage.setBoxNo( StringUtils.isEmpty( productBoxDao.selectById( productPutInStorage.getBoxId() ) )?null: productBoxDao.selectById( productPutInStorage.getBoxId()).getBoxNo());
                }
            }
        }

        return new PageUtils(page);
    }

}
