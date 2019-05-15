package io.renren.modules.product.service.impl;

import io.renren.modules.product.dao.ModelMessageDao;
import io.renren.modules.product.entity.ModelMessageEntity;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.product.dao.ModelUserMessageDao;
import io.renren.modules.product.entity.ModelUserMessageEntity;
import io.renren.modules.product.service.ModelUserMessageService;
import org.springframework.util.StringUtils;


@Service("modelUserMessageService")
public class ModelUserMessageServiceImpl extends ServiceImpl<ModelUserMessageDao, ModelUserMessageEntity> implements ModelUserMessageService {

    @Autowired
    private ModelMessageDao modelMessageDao;





    @Override

    public PageUtils queryPage(Map<String, Object> params) {
        Page<ModelUserMessageEntity> page = this.selectPage(
                new Query<ModelUserMessageEntity>( params ).getPage(),
                new EntityWrapper<ModelUserMessageEntity>()
        );

        return new PageUtils( page );
    }

    @Override
    public Integer selectNewModelMsgByUserId(Integer userId) {

        List<ModelMessageEntity> modelMessageEntityList = modelMessageDao.selectList(
                new EntityWrapper<>() );

        if (CollectionUtils.isNotEmpty( modelMessageEntityList )) {
            List<ModelUserMessageEntity> userMessageEntityList = baseMapper.selectList( new EntityWrapper<ModelUserMessageEntity>()
                    .eq( "user_id", userId )
                    .eq( "is_read", ModelUserMessageEntity.IS_READ )
            );

            if (CollectionUtils.isNotEmpty( userMessageEntityList )) {
                return modelMessageEntityList.size() - userMessageEntityList.size();
            }else {
                return modelMessageEntityList.size();
            }

        }else {
            return 0;
        }

    }

}
