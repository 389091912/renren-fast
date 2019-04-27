package io.renren.modules.product.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.Dict;
import io.renren.common.utils.PageUtils;
import io.renren.modules.product.entity.IngredientEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author wsy
 * @email 389091912@qq.com
 * @date 2019-04-25 23:43:32
 */
public interface IngredientService extends IService<IngredientEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 获取所有的辅料信息
     * @return
     */
    List<Dict> selectAllIngredientList();

    /**
     * 获取剩余的数量
     * @param ingredientId
     * @return
     */
    Double residueWeightByIngredientId(Integer ingredientId);
}

