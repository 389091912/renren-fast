package io.renren.modules.product.service.impl;

import io.renren.common.utils.Dict;
import io.renren.modules.product.dao.BoxAddLeaveDao;
import io.renren.modules.product.entity.BoxAddLeaveEntity;
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

import io.renren.modules.product.dao.ProductBoxDao;
import io.renren.modules.product.entity.ProductBoxEntity;
import io.renren.modules.product.service.ProductBoxService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * @author wsy
 */
@Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
@Service("productBoxService")
public class ProductBoxServiceImpl extends ServiceImpl<ProductBoxDao, ProductBoxEntity> implements ProductBoxService {

    @Autowired
    private BoxAddLeaveDao boxAddLeaveDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String) params.get( "key" );
        Page<ProductBoxEntity> page = new Page<>();
        if(!StringUtils.isEmpty( key )){
            page = this.selectPage(
                    new Query<ProductBoxEntity>( params ).getPage(),
                    new EntityWrapper<ProductBoxEntity>()
                            .eq( "id", key )
                            .orderBy( "create_time", false )
            );
       }else {
            page = this.selectPage(
                    new Query<ProductBoxEntity>(params).getPage(),
                    new EntityWrapper<ProductBoxEntity>().orderBy( "create_time", false )
            );
        }


        if (CollectionUtils.isNotEmpty( page.getRecords() )) {
            for (ProductBoxEntity productBox : page.getRecords()) {

                BoxAddLeaveEntity addBoxNumberCount = boxAddLeaveDao.addBoxNumberCount( productBox.getId().toString() );

                BoxAddLeaveEntity leaveBoxNumberCount = boxAddLeaveDao.leaveBoxNumberCount( productBox.getId().toString() );

                productBox.setBoxNumber( (StringUtils.isEmpty( addBoxNumberCount)?0:addBoxNumberCount.getAddBoxNumberCount()) -  (StringUtils.isEmpty(leaveBoxNumberCount)?0:leaveBoxNumberCount.getOutBoxNumberCount()) );

                productBox.setBodyNumber( (StringUtils.isEmpty( addBoxNumberCount )?0:addBoxNumberCount.getBodyNumberCount()) -  (StringUtils.isEmpty(leaveBoxNumberCount)?0:leaveBoxNumberCount.getBodyNumberCount()) );

                productBox.setParryNumber( (StringUtils.isEmpty( addBoxNumberCount )?0:addBoxNumberCount.getParryNumberCount()) -  (StringUtils.isEmpty(leaveBoxNumberCount)?0:leaveBoxNumberCount.getParryNumberCount()) );

                productBox.setSpacerNumber( (StringUtils.isEmpty( addBoxNumberCount )?0:addBoxNumberCount.getSpacerNumberCount()) -  (StringUtils.isEmpty(leaveBoxNumberCount)?0:leaveBoxNumberCount.getSpacerNumberCount()) );

                productBox.setLeaveNumber(  (StringUtils.isEmpty(leaveBoxNumberCount)?0:leaveBoxNumberCount.getOutBoxNumberCount()));
                baseMapper.updateById( productBox );
            }
        }


        return new PageUtils(page);
    }

    @Override
    public List<Dict> getAllProductBoxList() {
        List<ProductBoxEntity> productBoxList = baseMapper.selectList( new EntityWrapper<ProductBoxEntity>() );
        List<Dict> allProductBoxList = new ArrayList<>();
        if (!CollectionUtils.isEmpty( productBoxList )) {
            for (ProductBoxEntity productBox : productBoxList) {
                Dict dict = new Dict();
                dict.setId( productBox.getId() );
                dict.setName( productBox.getBoxNo() );
                allProductBoxList.add( dict );
            }
        }

        return allProductBoxList;
    }

}
