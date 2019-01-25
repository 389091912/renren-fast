package io.renren.modules.product.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.product.dao.ProductBoxDao;
import io.renren.modules.product.entity.ProductBoxEntity;
import io.renren.modules.product.service.ProductBoxService;


@Service("productBoxService")
public class ProductBoxServiceImpl extends ServiceImpl<ProductBoxDao, ProductBoxEntity> implements ProductBoxService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ProductBoxEntity> page = this.selectPage(
                new Query<ProductBoxEntity>(params).getPage(),
                new EntityWrapper<ProductBoxEntity>()
        );

        return new PageUtils(page);
    }

}
