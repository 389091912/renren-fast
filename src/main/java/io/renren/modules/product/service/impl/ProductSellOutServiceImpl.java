package io.renren.modules.product.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.product.dao.ProductSellOutDao;
import io.renren.modules.product.entity.ProductSellOutEntity;
import io.renren.modules.product.service.ProductSellOutService;


@Service("productSellOutService")
public class ProductSellOutServiceImpl extends ServiceImpl<ProductSellOutDao, ProductSellOutEntity> implements ProductSellOutService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ProductSellOutEntity> page = this.selectPage(
                new Query<ProductSellOutEntity>(params).getPage(),
                new EntityWrapper<ProductSellOutEntity>()
        );

        return new PageUtils(page);
    }

}
