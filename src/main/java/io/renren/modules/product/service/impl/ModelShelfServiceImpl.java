package io.renren.modules.product.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.product.dao.ModelShelfDao;
import io.renren.modules.product.entity.ModelShelfEntity;
import io.renren.modules.product.service.ModelShelfService;


@Service("modelShelfService")
public class ModelShelfServiceImpl extends ServiceImpl<ModelShelfDao, ModelShelfEntity> implements ModelShelfService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ModelShelfEntity> page = this.selectPage(
                new Query<ModelShelfEntity>(params).getPage(),
                new EntityWrapper<ModelShelfEntity>()
        );

        return new PageUtils(page);
    }

}
