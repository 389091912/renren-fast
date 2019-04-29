package io.renren.modules.product.service.impl;

import com.sun.org.apache.bcel.internal.generic.NEW;
import io.renren.common.utils.Dict;
import io.renren.modules.product.dao.PartDetailDao;
import io.renren.modules.product.service.PartDetailService;
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

import io.renren.modules.product.dao.PartInfoDao;
import io.renren.modules.product.entity.PartInfoEntity;
import io.renren.modules.product.service.PartInfoService;
import org.springframework.util.StringUtils;


@Service("partInfoService")
public class PartInfoServiceImpl extends ServiceImpl<PartInfoDao, PartInfoEntity> implements PartInfoService {

    @Autowired
    private PartDetailDao partDetailDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        List<PartInfoEntity> partInfoEntityList= baseMapper.selectList( new EntityWrapper<>() );

        for (PartInfoEntity partInfoEntity : partInfoEntityList) {

            Double countNumber = partDetailDao.countNumber( partInfoEntity.getId() );

            Double outCountNumber = partDetailDao.outCountNumber( partInfoEntity.getId() );

            if (!StringUtils.isEmpty( countNumber )) {
                partInfoEntity.setResidueNumber(countNumber  );
            }
            if (!StringUtils.isEmpty( countNumber ) && !StringUtils.isEmpty( outCountNumber )) {
                partInfoEntity.setResidueNumber( countNumber - outCountNumber );
            }
            baseMapper.updateById( partInfoEntity );
        }


        Page<PartInfoEntity> page = this.selectPage(
                new Query<PartInfoEntity>( params ).getPage(),
                new EntityWrapper<PartInfoEntity>().orderBy( "residue_number", true )
        );

        return new PageUtils(page);
    }

    @Override
    public List<Dict> selectAllPartList() {

        List<PartInfoEntity> partInfoEntityList = baseMapper.selectList( new EntityWrapper<>() );
        List<Dict> dictList = new ArrayList<>();
        for (PartInfoEntity partInfoEntity : partInfoEntityList) {
            Dict dict = new Dict();
            dict.setName( partInfoEntity.getPartName() );
            dict.setId( partInfoEntity.getId() );
            dictList.add( dict );
        }

        return dictList;
    }

}
