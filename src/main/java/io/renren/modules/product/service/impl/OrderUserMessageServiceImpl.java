package io.renren.modules.product.service.impl;

import io.renren.modules.product.dao.OrderMessageDao;
import io.renren.modules.product.entity.OrderMessageEntity;
import javassist.expr.NewArray;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.product.dao.OrderUserMessageDao;
import io.renren.modules.product.entity.OrderUserMessageEntity;
import io.renren.modules.product.service.OrderUserMessageService;


@Service("orderUserMessageService")
public class OrderUserMessageServiceImpl extends ServiceImpl<OrderUserMessageDao, OrderUserMessageEntity> implements OrderUserMessageService {

    @Autowired
    private OrderMessageDao orderMessageDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<OrderUserMessageEntity> page = this.selectPage(
                new Query<OrderUserMessageEntity>(params).getPage(),
                new EntityWrapper<OrderUserMessageEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public Integer selectUserMessageCount(Integer userId) {

        List<OrderMessageEntity> orderMessageEntityList = orderMessageDao.selectList( new EntityWrapper<>() );

       // List<Integer> orderMsgList =orderMessageEntityList.stream().map( OrderMessageEntity::getId ).collect( Collectors.toList() );

        List<OrderUserMessageEntity> orderUserMessageList = baseMapper.selectList(
                        new EntityWrapper<OrderUserMessageEntity>()
                        .eq( "user_id", userId )
                        .eq( "is_read",OrderUserMessageEntity.IS_READ));

        if (CollectionUtils.isNotEmpty( orderMessageEntityList )) {

            if (CollectionUtils.isNotEmpty( orderUserMessageList )) {

                return orderMessageEntityList.size() - orderUserMessageList.size();
            }else {

                return orderMessageEntityList.size();
            }

        }else {
            return 0;
        }
    }

}
