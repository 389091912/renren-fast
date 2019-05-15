package io.renren.modules.product.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.product.dao.ModelMessageDao;
import io.renren.modules.product.entity.ModelMessageEntity;
import io.renren.modules.product.service.ModelMessageService;


@Service("modelMessageService")
public class ModelMessageServiceImpl extends ServiceImpl<ModelMessageDao, ModelMessageEntity> implements ModelMessageService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ModelMessageEntity> page = this.selectPage(
                new Query<ModelMessageEntity>(params).getPage(),
                new EntityWrapper<ModelMessageEntity>()
        );

        return new PageUtils(page);
    }

}
