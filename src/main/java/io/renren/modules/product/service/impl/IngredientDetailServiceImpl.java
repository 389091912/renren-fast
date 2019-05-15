package io.renren.modules.product.service.impl;

import io.renren.common.utils.DateUtils;
import io.renren.modules.product.dao.IngredientDao;
import io.renren.modules.product.dao.SupplierInfoDao;
import io.renren.modules.product.entity.IngredientEntity;
import io.renren.modules.product.entity.SupplierInfoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.product.dao.IngredientDetailDao;
import io.renren.modules.product.entity.IngredientDetailEntity;
import io.renren.modules.product.service.IngredientDetailService;
import org.springframework.util.StringUtils;


@Service("ingredientDetailService")
public class IngredientDetailServiceImpl extends ServiceImpl<IngredientDetailDao, IngredientDetailEntity> implements IngredientDetailService {

    @Autowired
    private SupplierInfoDao supplierInfoDao;

    @Autowired
    private IngredientDao ingredientDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String  key = (String) params.get( "key" );
        String rangeBefore = (String) params.get( "rangeBefore" );
        String rangeAfter = (String) params.get( "rangeAfter" );
        Page<IngredientDetailEntity> page = new Page<>();
        EntityWrapper<IngredientDetailEntity> ingredientDetailEntityEntityWrapper = new EntityWrapper<>();
        if (StringUtils.isEmpty( key )) {
            if (!StringUtils.isEmpty( rangeAfter )) {
                page= this.selectPage(
                        new Query<IngredientDetailEntity>( params ).getPage(),
                        ingredientDetailEntityEntityWrapper
                                .between( "detail_time",rangeBefore+ DateUtils.DATE_TIME_BEFORE,rangeAfter+DateUtils.DATE_TIME_AFTER )
                                .orderBy( "create_time", false )
                );
            }else {
                page= this.selectPage(
                        new Query<IngredientDetailEntity>( params ).getPage(),
                        ingredientDetailEntityEntityWrapper
                                .orderBy( "create_time", false )
                );
            }

        }else {
            if(!StringUtils.isEmpty( rangeAfter )){
                page= this.selectPage(
                        new Query<IngredientDetailEntity>( params ).getPage(),
                        ingredientDetailEntityEntityWrapper
                                .eq( "ingredient_id",key )
                                .between( "detail_time",rangeBefore+DateUtils.DATE_TIME_BEFORE,rangeAfter+DateUtils.DATE_TIME_AFTER )
                                .orderBy( "create_time", false )
                );
            }else {
                page= this.selectPage(
                        new Query<IngredientDetailEntity>( params ).getPage(),
                        ingredientDetailEntityEntityWrapper
                                .eq( "ingredient_id",key )
                                .orderBy( "create_time", false )
                );
            }

        }


        for (IngredientDetailEntity ingredientDetail : page.getRecords()) {
            if (!StringUtils.isEmpty( ingredientDetail.getIngredientId() )) {
                IngredientEntity ingredientEntity = ingredientDao.selectById( ingredientDetail.getIngredientId() );
                ingredientDetail.setMaterialName( ingredientEntity.getMaterialName() );
            }

        }

        for (IngredientDetailEntity ingredientDetail : page.getRecords()) {
            if (!StringUtils.isEmpty( ingredientDetail.getIngredientId() )) {
                IngredientEntity ingredientEntity = ingredientDao.selectById( ingredientDetail.getIngredientId() );
                if(!StringUtils.isEmpty(ingredientEntity  )){
                  ingredientDetail.setMaterialName( ingredientEntity.getMaterialName() );
                }
            }

            if (!StringUtils.isEmpty( ingredientDetail.getSupplierId() )) {
                SupplierInfoEntity supplierInfoEntity = supplierInfoDao.selectById( ingredientDetail.getSupplierId() );
                if (!StringUtils.isEmpty( supplierInfoEntity )) {
                   ingredientDetail.setSupplierName( supplierInfoEntity.getSupplierName() );
                }
            }

        }




        return new PageUtils(page);
    }

}
