package io.renren.modules.product.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.product.dao.IngredientDao;
import io.renren.modules.product.entity.IngredientEntity;
import io.renren.modules.product.service.IngredientService;


@Service("ingredientService")
public class IngredientServiceImpl extends ServiceImpl<IngredientDao, IngredientEntity> implements IngredientService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<IngredientEntity> page = this.selectPage(
                new Query<IngredientEntity>(params).getPage(),
                new EntityWrapper<IngredientEntity>()
        );

        return new PageUtils(page);
    }

}
