package io.renren.modules.product.service.impl;

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
        Page<BoxAddLeaveEntity> page = new Page<>();
        if(StringUtils.isEmpty( rangeBefore )){
            if (StringUtils.isEmpty( key )) {
                page = this.selectPage(
                        new Query<BoxAddLeaveEntity>( params ).getPage(),
                        new EntityWrapper<BoxAddLeaveEntity>().orderBy( "create_time", false )
                );
            }else {
                page = this.selectPage(
                        new Query<BoxAddLeaveEntity>( params ).getPage(),
                        new EntityWrapper<BoxAddLeaveEntity>()
                                .or().like( "box_no",key )
                                .orderBy( "create_time", false )
                );
            }
        }else {
            page = this.selectPage(
                    new Query<BoxAddLeaveEntity>( params ).getPage(),
                    new EntityWrapper<BoxAddLeaveEntity>()
                            .or().like( "box_no",key ).or()
                            .between( "add_box_time",rangeBefore,rangeAfter ).or()
                            .between( "out_box_time",rangeBefore,rangeAfter )
                            .orderBy( "create_time", false )
            );
        }
        if (CollectionUtils.isNotEmpty( page.getRecords() )) {
            for (BoxAddLeaveEntity boxAddLeaveEntity : page.getRecords()) {

                boxAddLeaveEntity.setBoxNoName(
                        (!StringUtils.isEmpty( boxAddLeaveEntity.getBoxNo() ))?
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
