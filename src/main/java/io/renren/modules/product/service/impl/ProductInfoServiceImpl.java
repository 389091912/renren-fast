package io.renren.modules.product.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.product.dao.ProductInfoDao;
import io.renren.modules.product.entity.ProductInfoEntity;
import io.renren.modules.product.service.ProductInfoService;


@Service("productInfoService")
public class ProductInfoServiceImpl extends ServiceImpl<ProductInfoDao, ProductInfoEntity> implements ProductInfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ProductInfoEntity> page = this.selectPage(
                new Query<ProductInfoEntity>(params).getPage(),
                new EntityWrapper<ProductInfoEntity>()
        );

        return new PageUtils(page);
    }

}
