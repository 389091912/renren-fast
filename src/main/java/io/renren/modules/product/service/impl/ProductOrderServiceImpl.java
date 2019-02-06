package io.renren.modules.product.service.impl;

import io.renren.modules.product.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.product.entity.ProductOrderEntity;
import io.renren.modules.product.service.ProductOrderService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author wsy
 */
@Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
@Service("productOrderService")
public class ProductOrderServiceImpl extends ServiceImpl<ProductOrderDao, ProductOrderEntity> implements ProductOrderService {

    @Autowired
    private ProductInfoDao productInfoDao;

    @Autowired
    private ProductOrderDao productOrderDao;

    @Autowired
    private ProductOrderDetailDao productOrderDetailDao;

    @Autowired
    private ProductModelDao productModelDao;


    @Autowired
    private ProductRequireDao productRequireDao;




    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ProductOrderEntity> page = this.selectPage(
                new Query<ProductOrderEntity>(params).getPage(),
                new EntityWrapper<ProductOrderEntity>().orderBy( "update_time", false )
        );

        return new PageUtils(page);
    }

    @Override
    public void addOrderDetailAndRequire(ProductOrderEntity productOrderEntity) {

    }

}
