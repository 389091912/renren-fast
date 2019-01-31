package io.renren.modules.product.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.product.dao.ProductOrderDao;
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

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ProductOrderEntity> page = this.selectPage(
                new Query<ProductOrderEntity>(params).getPage(),
                new EntityWrapper<ProductOrderEntity>()
        );

        return new PageUtils(page);
    }

}
