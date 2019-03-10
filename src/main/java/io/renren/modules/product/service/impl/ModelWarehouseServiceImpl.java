package io.renren.modules.product.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.product.dao.ModelWarehouseDao;
import io.renren.modules.product.entity.ModelWarehouseEntity;
import io.renren.modules.product.service.ModelWarehouseService;


@Service("modelWarehouseService")
public class ModelWarehouseServiceImpl extends ServiceImpl<ModelWarehouseDao, ModelWarehouseEntity> implements ModelWarehouseService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ModelWarehouseEntity> page = this.selectPage(
                new Query<ModelWarehouseEntity>(params).getPage(),
                new EntityWrapper<ModelWarehouseEntity>()
        );

        return new PageUtils(page);
    }

}
