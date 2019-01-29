package io.renren.modules.product.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.product.dao.ProductRequireDao;
import io.renren.modules.product.entity.ProductRequireEntity;
import io.renren.modules.product.service.ProductRequireService;


@Service("productRequireService")
public class ProductRequireServiceImpl extends ServiceImpl<ProductRequireDao, ProductRequireEntity> implements ProductRequireService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ProductRequireEntity> page = this.selectPage(
                new Query<ProductRequireEntity>(params).getPage(),
                new EntityWrapper<ProductRequireEntity>()
        );

        return new PageUtils(page);
    }

}
