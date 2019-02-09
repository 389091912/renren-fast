package io.renren.modules.product.service.impl;

import io.renren.common.utils.Dict;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.product.dao.BoxFactoryDao;
import io.renren.modules.product.entity.BoxFactoryEntity;
import io.renren.modules.product.service.BoxFactoryService;


@Service("boxFactoryService")
public class BoxFactoryServiceImpl extends ServiceImpl<BoxFactoryDao, BoxFactoryEntity> implements BoxFactoryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<BoxFactoryEntity> page = this.selectPage(
                new Query<BoxFactoryEntity>(params).getPage(),
                new EntityWrapper<BoxFactoryEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<Dict> getAllBoxFactoryList() {
        List<BoxFactoryEntity> boxFactoryEntities = baseMapper.selectList( new EntityWrapper<BoxFactoryEntity>() );
        List<Dict> boxFactoryList = new ArrayList<>();
        for (BoxFactoryEntity boxFactoryEntity : boxFactoryEntities) {
            Dict dict = new Dict();
            Integer id = boxFactoryEntity.getId();
            String factoryName = boxFactoryEntity.getFactoryName();
            dict.setId( id );
            dict.setName( factoryName );
            boxFactoryList.add( dict );
        }
        return boxFactoryList;
    }

}
