package io.renren.modules.product.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.product.entity.IngredientDetailEntity;

import java.util.Map;

/**
 * 
 *
 * @author wsy
 * @email 389091912@qq.com
 * @date 2019-04-25 23:43:32
 */
public interface IngredientDetailService extends IService<IngredientDetailEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

