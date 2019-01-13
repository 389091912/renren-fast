package io.renren.modules.product.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.product.dao.ProductModelDao;
import io.renren.modules.product.entity.ProductModelEntity;
import io.renren.modules.product.service.ProductModelService;


@Service("productModelService")
public class ProductModelServiceImpl extends ServiceImpl<ProductModelDao, ProductModelEntity> implements ProductModelService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ProductModelEntity> page = this.selectPage(
                new Query<ProductModelEntity>(params).getPage(),
                new EntityWrapper<ProductModelEntity>()
        );

        return new PageUtils(page);
    }

}
