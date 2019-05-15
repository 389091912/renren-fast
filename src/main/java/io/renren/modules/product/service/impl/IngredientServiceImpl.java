package io.renren.modules.product.service.impl;

import io.renren.common.utils.Dict;
import io.renren.modules.product.dao.IngredientDetailDao;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.product.dao.IngredientDao;
import io.renren.modules.product.entity.IngredientEntity;
import io.renren.modules.product.service.IngredientService;
import org.springframework.util.StringUtils;


@Service("ingredientService")
public class IngredientServiceImpl extends ServiceImpl<IngredientDao, IngredientEntity> implements IngredientService {

    @Autowired
    private IngredientDetailDao ingredientDetailDao;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        String key = (String) params.get( "key" );
        Page<IngredientEntity> page = new Page<>();
        if (!StringUtils.isEmpty( key )) {
            page = this.selectPage(
                    new Query<IngredientEntity>( params ).getPage(),
                    new EntityWrapper<IngredientEntity>()
                            .eq( "id", key )
                            .orderBy( "create_time", false )
            );
        }else {
            page = this.selectPage(
                    new Query<IngredientEntity>(params).getPage(),
                    new EntityWrapper<IngredientEntity>()
                            .orderBy( "create_time",false )
            );
        }

        if (CollectionUtils.isNotEmpty( page.getRecords() )) {
            for (IngredientEntity ingredient : page.getRecords()) {
                Double countWeight = ingredientDetailDao.countWeight( ingredient.getId() );
                Double outCountWeight = ingredientDetailDao.outCountWeight( ingredient.getId() );
                ingredient.setCountWeight( StringUtils.isEmpty(  countWeight)?0:countWeight );
                ingredient.setOutWeight( StringUtils.isEmpty(outCountWeight)?0:outCountWeight );
                ingredient.setResidueWeight( ingredient.getCountWeight() - ingredient.getOutWeight() );
            }
        }


        return new PageUtils(page);
    }

    @Override
    public List<Dict> selectAllIngredientList() {

        List<IngredientEntity> ingredientEntityList = baseMapper.selectList( new EntityWrapper<>() );

        List<Dict> dictList = new ArrayList<>();

        if (CollectionUtils.isNotEmpty( ingredientEntityList )) {
            for (IngredientEntity ingredientEntity : ingredientEntityList) {
                Dict dict = new Dict();
                dict.setId( ingredientEntity.getId() );
                dict.setName( ingredientEntity.getMaterialName() );
                dictList.add( dict );
            }
        }

        return dictList;
    }

    @Override
    public Double residueWeightByIngredientId(Integer ingredientId) {

        Double countWeight = ingredientDetailDao.countWeight( ingredientId );
        Double outCountWeight = ingredientDetailDao.outCountWeight( ingredientId );

        return  (StringUtils.isEmpty(  countWeight)?0:countWeight)-(StringUtils.isEmpty(outCountWeight)?0:outCountWeight );
    }

}
