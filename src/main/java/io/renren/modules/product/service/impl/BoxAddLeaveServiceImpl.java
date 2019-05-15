package io.renren.modules.product.service.impl;

import io.renren.common.utils.DateUtils;
import io.renren.common.utils.Dict;
import io.renren.modules.product.dao.ProductBoxDao;
import io.renren.modules.product.entity.ProductBoxEntity;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
import org.springframework.util.StringUtils;

/**
 * @author wsy
 */
@Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
@Service("boxAddLeaveService")
public class BoxAddLeaveServiceImpl extends ServiceImpl<BoxAddLeaveDao, BoxAddLeaveEntity> implements BoxAddLeaveService {

    @Autowired
    private ProductBoxDao productBoxDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        String key = (String) params.get( "key" );
        String rangeBefore = (String) params.get( "rangeBefore" );
        String rangeAfter = (String) params.get( "rangeAfter" );
        String type = (String) params.get( "type" );
        System.out.println(type);
        Page<BoxAddLeaveEntity> page = new Page<>();
        EntityWrapper<BoxAddLeaveEntity> boxAddLeaveEntityWrapper = new EntityWrapper<>();
        if(!StringUtils.isEmpty( key )){
            boxAddLeaveEntityWrapper.eq( "box_no", key );
        }
        if (!StringUtils.isEmpty( type )) {
            if (BoxAddLeaveEntity.ADD.equals( type )&&!StringUtils.isEmpty( rangeBefore )) {
                boxAddLeaveEntityWrapper.between( "add_box_time", rangeBefore+ DateUtils.DATE_TIME_BEFORE ,rangeAfter+DateUtils.DATE_TIME_AFTER );

            }
            if (BoxAddLeaveEntity.LEAVE.equals( type )&&!StringUtils.isEmpty( rangeBefore )) {
                boxAddLeaveEntityWrapper.between( "out_box_time", rangeBefore+DateUtils.DATE_TIME_BEFORE ,rangeAfter+DateUtils.DATE_TIME_AFTER);
            }
            boxAddLeaveEntityWrapper.eq( "type", type );
        }else {
            if(!StringUtils.isEmpty( rangeBefore )){
                boxAddLeaveEntityWrapper.between( "create_time", rangeBefore+DateUtils.DATE_TIME_BEFORE,rangeAfter+DateUtils.DATE_TIME_AFTER);
            }
        }


        page = this.selectPage(
                new Query<BoxAddLeaveEntity>( params ).getPage(),
                boxAddLeaveEntityWrapper
                        .orderBy( "box_no",true )
                        .orderBy( "create_time", false )
        );

        if (CollectionUtils.isNotEmpty( page.getRecords() )) {
            for (BoxAddLeaveEntity boxAddLeaveEntity : page.getRecords()) {

                boxAddLeaveEntity.setBoxNoName(
                        (!StringUtils.isEmpty( productBoxDao.selectById( boxAddLeaveEntity.getBoxNo()) ))?
                                productBoxDao.selectById( boxAddLeaveEntity.getBoxNo()).getBoxNo():
                        null);

            }
        }


        return new PageUtils( page );
    }

    @Override
    public List<Dict> getAllBoxAddLeave() {

        List<BoxAddLeaveEntity> boxAddLeaveList = baseMapper.selectList( new EntityWrapper<BoxAddLeaveEntity>().setSqlSelect( "distinct(box_no)" ));
        System.out.println( boxAddLeaveList.size() );
        List<Dict> boxAddList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty( boxAddLeaveList )) {
            for (BoxAddLeaveEntity boxAddLeaveEntity : boxAddLeaveList) {
                Dict dict = new Dict();
                if(!StringUtils.isEmpty( Integer.parseInt(boxAddLeaveEntity.getBoxNo()  ) )){
                    dict.setId( Integer.parseInt(boxAddLeaveEntity.getBoxNo()  ));
                    ProductBoxEntity productBoxEntity = productBoxDao.selectById( Integer.parseInt( boxAddLeaveEntity.getBoxNo() ) );
                    if (!StringUtils.isEmpty( productBoxEntity )) {
                        dict.setName( productBoxEntity.getBoxNo() );
                    }
                }
                boxAddList.add( dict );
            }
        }

        return boxAddList;
    }

}
