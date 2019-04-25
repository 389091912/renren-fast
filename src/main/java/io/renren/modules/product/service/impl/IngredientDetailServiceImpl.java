package io.renren.modules.product.service.impl;

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


@Service("ingredientDetailService")
public class IngredientDetailServiceImpl extends ServiceImpl<IngredientDetailDao, IngredientDetailEntity> implements IngredientDetailService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<IngredientDetailEntity> page = this.selectPage(
                new Query<IngredientDetailEntity>(params).getPage(),
                new EntityWrapper<IngredientDetailEntity>()
        );

        return new PageUtils(page);
    }

}
