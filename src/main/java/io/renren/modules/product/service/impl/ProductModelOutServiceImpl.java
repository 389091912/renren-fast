package io.renren.modules.product.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.product.dao.ProductModelOutDao;
import io.renren.modules.product.entity.ProductModelOutEntity;
import io.renren.modules.product.service.ProductModelOutService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author wsy
 */
@Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
@Service("productModelOutService")
public class ProductModelOutServiceImpl extends ServiceImpl<ProductModelOutDao, ProductModelOutEntity> implements ProductModelOutService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ProductModelOutEntity> page = this.selectPage(
                new Query<ProductModelOutEntity>(params).getPage(),
                new EntityWrapper<ProductModelOutEntity>()
        );

        return new PageUtils(page);
    }

}
