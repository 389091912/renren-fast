package io.renren.modules.product.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.product.dao.ProductDeviceDao;
import io.renren.modules.product.entity.ProductDeviceEntity;
import io.renren.modules.product.service.ProductDeviceService;


@Service("productDeviceService")
public class ProductDeviceServiceImpl extends ServiceImpl<ProductDeviceDao, ProductDeviceEntity> implements ProductDeviceService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ProductDeviceEntity> page = this.selectPage(
                new Query<ProductDeviceEntity>(params).getPage(),
                new EntityWrapper<ProductDeviceEntity>()
        );

        return new PageUtils(page);
    }

}
