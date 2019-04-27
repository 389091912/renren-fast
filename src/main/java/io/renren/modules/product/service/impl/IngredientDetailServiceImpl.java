package io.renren.modules.product.service.impl;

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
        Page<IngredientDetailEntity> page = this.selectPage(
                new Query<IngredientDetailEntity>( params ).getPage(),
                new EntityWrapper<IngredientDetailEntity>().orderBy( "create_time", false )
        );

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
