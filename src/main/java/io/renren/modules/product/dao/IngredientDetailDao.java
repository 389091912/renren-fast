package io.renren.modules.product.dao;

import io.renren.modules.product.entity.IngredientDetailEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * 
 * @author wsy
 * @email 389091912@qq.com
 * @date 2019-04-25 23:43:32
 */
@Mapper
public interface IngredientDetailDao extends BaseMapper<IngredientDetailEntity> {

    Double countWeight(@Param( "ingredientId" )Integer ingredientId);

    Double outCountWeight(@Param( "ingredientId" )Integer ingredientId);
}
