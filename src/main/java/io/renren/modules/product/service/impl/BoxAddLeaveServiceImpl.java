package io.renren.modules.product.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.product.dao.BoxAddLeaveDao;
import io.renren.modules.product.entity.BoxAddLeaveEntity;
import io.renren.modules.product.service.BoxAddLeaveService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author wsy
 */
@Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
@Service("boxAddLeaveService")
public class BoxAddLeaveServiceImpl extends ServiceImpl<BoxAddLeaveDao, BoxAddLeaveEntity> implements BoxAddLeaveService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<BoxAddLeaveEntity> page = this.selectPage(
                new Query<BoxAddLeaveEntity>( params ).getPage(),
                new EntityWrapper<BoxAddLeaveEntity>().orderBy( "create_time", false )
        );

        return new PageUtils( page );
    }

}
