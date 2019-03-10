package io.renren.modules.product.service.impl;

import io.renren.modules.product.dao.ProductDeviceDao;
import io.renren.modules.product.dao.ProductInfoDao;
import io.renren.modules.product.entity.ProductDeviceEntity;
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

import io.renren.modules.product.dao.ProductPlanNoticeDao;
import io.renren.modules.product.entity.ProductPlanNoticeEntity;
import io.renren.modules.product.service.ProductPlanNoticeService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ProductPlanNoticeEntity> page = this.selectPage(
                new Query<ProductPlanNoticeEntity>(params).getPage(),
                new EntityWrapper<ProductPlanNoticeEntity>().orderBy( "create_time", false )
        );
        if (CollectionUtils.isNotEmpty( page.getRecords() )) {
            for (ProductPlanNoticeEntity planNoticeEntity : page.getRecords()) {
                ProductDeviceEntity productDeviceEntity = productDeviceDao.selectById( planNoticeEntity.getDeviceId() );
                planNoticeEntity.setDeviceName( productDeviceEntity.getDeciveName() );

                ProductInfoEntity productInfoEntity = productInfoDao.selectById( planNoticeEntity.getProductId() );
                planNoticeEntity.setProductName( productInfoEntity.getProductName() );

            }
        }
        return new PageUtils(page);
    }

}
