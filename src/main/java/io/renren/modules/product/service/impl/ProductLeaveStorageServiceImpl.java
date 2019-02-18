package io.renren.modules.product.service.impl;

import io.renren.modules.product.dao.ProductBoxDao;
import io.renren.modules.product.dao.ProductInfoDao;
import io.renren.modules.product.dao.ProductOrderDao;
import io.renren.modules.product.entity.ProductBoxEntity;
import io.renren.modules.product.entity.ProductInfoEntity;
import io.renren.modules.product.entity.ProductOrderEntity;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.product.dao.ProductLeaveStorageDao;
import io.renren.modules.product.entity.ProductLeaveStorageEntity;
import io.renren.modules.product.service.ProductLeaveStorageService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * @author wsy
 */
@Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
@Service("productLeaveStorageService")
public class ProductLeaveStorageServiceImpl extends ServiceImpl<ProductLeaveStorageDao, ProductLeaveStorageEntity> implements ProductLeaveStorageService {

    @Autowired
    private ProductInfoDao productInfoDao;

    @Autowired
    private ProductOrderDao productOrderDao;

    @Autowired
    private ProductBoxDao productBoxDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ProductLeaveStorageEntity> page = this.selectPage(
                new Query<ProductLeaveStorageEntity>(params).getPage(),
                new EntityWrapper<ProductLeaveStorageEntity>().orderBy( "create_time", false )
        );

        if (CollectionUtils.isNotEmpty( page.getRecords() )) {
            for (ProductLeaveStorageEntity productLeaveStorage : page.getRecords()) {
                ProductInfoEntity productInfo = productInfoDao.selectById( productLeaveStorage.getProductId() );
                productLeaveStorage.setProductName( StringUtils.isEmpty( productInfo )?null: productInfo.getProductName()); ;
                ProductOrderEntity productOrder = productOrderDao.selectById( productLeaveStorage.getOrderId() );
                productLeaveStorage.setOrderNo( StringUtils.isEmpty( productOrder ) ? null : productOrder.getOrderNo() );
                ProductBoxEntity productBoxEntity = productBoxDao.selectById( productLeaveStorage.getBoxId() );

                productLeaveStorage.setBoxNo( StringUtils.isEmpty( productBoxEntity ) ? null : productBoxEntity.getBoxNo() );


            }
        }


        return new PageUtils(page);
    }

}
