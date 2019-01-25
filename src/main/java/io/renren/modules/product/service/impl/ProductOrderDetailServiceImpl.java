package io.renren.modules.product.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.product.dao.ProductOrderDetailDao;
import io.renren.modules.product.entity.ProductOrderDetailEntity;
import io.renren.modules.product.service.ProductOrderDetailService;


@Service("productOrderDetailService")
public class ProductOrderDetailServiceImpl extends ServiceImpl<ProductOrderDetailDao, ProductOrderDetailEntity> implements ProductOrderDetailService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ProductOrderDetailEntity> page = this.selectPage(
                new Query<ProductOrderDetailEntity>(params).getPage(),
                new EntityWrapper<ProductOrderDetailEntity>()
        );

        return new PageUtils(page);
    }

}
