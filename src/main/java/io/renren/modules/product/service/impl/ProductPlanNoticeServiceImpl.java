package io.renren.modules.product.service.impl;

import io.renren.modules.product.dao.*;
import io.renren.modules.product.entity.*;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.product.service.ProductPlanNoticeService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * @author wsy
 */
@Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
@Service("productPlanNoticeService")
public class ProductPlanNoticeServiceImpl extends ServiceImpl<ProductPlanNoticeDao, ProductPlanNoticeEntity> implements ProductPlanNoticeService {

    @Autowired
    private ProductDeviceDao productDeviceDao;

    @Autowired
    private ProductInfoDao productInfoDao;

    @Autowired
    private ProductModelDao productModelDao;

    @Autowired
    private ProductOrderDao productOrderDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ProductPlanNoticeEntity> page = this.selectPage(
                new Query<ProductPlanNoticeEntity>( params ).getPage(),
                new EntityWrapper<ProductPlanNoticeEntity>()
                        .orderBy( "create_time", false )
                        .orderBy( "is_priority", false )

        );
        if (CollectionUtils.isNotEmpty( page.getRecords() )) {
            for (ProductPlanNoticeEntity planNoticeEntity : page.getRecords()) {
                ProductDeviceEntity productDeviceEntity = productDeviceDao.selectById( planNoticeEntity.getDeviceId() );
                planNoticeEntity.setDeviceName( productDeviceEntity.getDeciveName() );

                ProductInfoEntity productInfoEntity = productInfoDao.selectById( planNoticeEntity.getProductId() );
                if (!StringUtils.isEmpty( productInfoEntity )) {
                    planNoticeEntity.setProductName( productInfoEntity.getProductName() );
                    if (!StringUtils.isEmpty( productInfoEntity.getModelNo() )) {
                        ProductModelEntity productModelEntity = productModelDao.selectById( productInfoEntity.getModelNo() );
                        if (!StringUtils.isEmpty( productModelEntity )) {
                            planNoticeEntity.setModelNo( productModelEntity.getModelNo() );
                        }
                    }

                }

                if (!StringUtils.isEmpty( planNoticeEntity.getModelId() )) {
                    ProductModelEntity productModelEntity = productModelDao.selectById( planNoticeEntity.getModelId() );
                    if (!StringUtils.isEmpty( productModelEntity )) {
                        planNoticeEntity.setModelNo( productModelEntity.getModelNo() );

                    }
                }

                if (!StringUtils.isEmpty( planNoticeEntity.getOrderId() )) {
                    ProductOrderEntity productOrderEntity = productOrderDao.selectById( planNoticeEntity.getOrderId() );
                    if (!StringUtils.isEmpty(productOrderEntity)) {
                        planNoticeEntity.setOrderNo( productOrderEntity.getOrderNo() );
                    }
                }


            }
        }
        return new PageUtils(page);
    }

}
