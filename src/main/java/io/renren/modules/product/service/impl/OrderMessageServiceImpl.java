package io.renren.modules.product.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.product.dao.OrderMessageDao;
import io.renren.modules.product.entity.OrderMessageEntity;
import io.renren.modules.product.service.OrderMessageService;


@Service("orderMessageService")
public class OrderMessageServiceImpl extends ServiceImpl<OrderMessageDao, OrderMessageEntity> implements OrderMessageService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<OrderMessageEntity> page = this.selectPage(
                new Query<OrderMessageEntity>(params).getPage(),
                new EntityWrapper<OrderMessageEntity>()
        );

        return new PageUtils(page);
    }

}
