package io.renren.modules.product.service.impl;

import io.renren.modules.product.dao.ModelShelfDao;
import io.renren.modules.product.dao.ProductInfoDao;
import io.renren.modules.product.dao.ProductModelDao;
import io.renren.modules.product.entity.ModelShelfEntity;
import io.renren.modules.product.entity.ProductInfoEntity;
import io.renren.modules.product.entity.ProductModelEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.product.dao.ProductModelOutDao;
import io.renren.modules.product.entity.ProductModelOutEntity;
import io.renren.modules.product.service.ProductModelOutService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * @author wsy
 */
@Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
@Service("productModelOutService")
public class ProductModelOutServiceImpl extends ServiceImpl<ProductModelOutDao, ProductModelOutEntity> implements ProductModelOutService {

    @Autowired
    private ProductInfoDao productInfoDao;

    @Autowired
    private ModelShelfDao modelShelfDao;

    @Autowired
    private ProductModelDao productModelDao;


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ProductModelOutEntity> page = this.selectPage(
                new Query<ProductModelOutEntity>(params).getPage(),
                new EntityWrapper<ProductModelOutEntity>().orderBy( "create_time", false )
        );

        for (ProductModelOutEntity productModelOutEntity : page.getRecords()) {
            if (!StringUtils.isEmpty( productModelOutEntity.getProductId() )) {
                ProductInfoEntity productInfoEntity = productInfoDao.selectById( productModelOutEntity.getProductId() );
                if (!StringUtils.isEmpty( productInfoEntity )) {
                    productModelOutEntity.setProductName( productInfoEntity.getProductName() );
                }
            }

            if (!StringUtils.isEmpty( productModelOutEntity.getModelShelfId() )) {
                ModelShelfEntity modelShelfEntity = modelShelfDao.selectById( productModelOutEntity.getModelShelfId() );
                if (!StringUtils.isEmpty( modelShelfEntity )) {
                    productModelOutEntity.setSiteNo( modelShelfEntity.getShelfNo() );
                }
            }

            if (!StringUtils.isEmpty( productModelOutEntity.getModelNo() )) {
                ProductModelEntity productModelEntity = productModelDao.selectById( productModelOutEntity.getModelNo() );
                if (!StringUtils.isEmpty( productModelEntity )) {
                    productModelOutEntity.setModelName( productModelEntity.getModelNo() );
                }
            }
        }

        return new PageUtils(page);
    }

}
