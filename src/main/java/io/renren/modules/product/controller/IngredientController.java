package io.renren.modules.product.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.renren.common.utils.Dict;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.product.entity.IngredientEntity;
import io.renren.modules.product.service.IngredientService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 
 *
 * @author wsy
 * @email 389091912@qq.com
 * @date 2019-04-25 23:43:32
 */
@RestController
@RequestMapping("product/ingredient")
public class IngredientController {
    @Autowired
    private IngredientService ingredientService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("product:ingredient:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = ingredientService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("product:ingredient:info")
    public R info(@PathVariable("id") Integer id){
			IngredientEntity ingredient = ingredientService.selectById(id);

        return R.ok().put("ingredient", ingredient);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("product:ingredient:save")
    public R save(@RequestBody IngredientEntity ingredient){
			ingredientService.insert(ingredient);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("product:ingredient:update")
    public R update(@RequestBody IngredientEntity ingredient){
			ingredientService.updateById(ingredient);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("product:ingredient:delete")
    public R delete(@RequestBody Integer[] ids){
			ingredientService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

    /**
     * 列表
     */
    @RequestMapping("/getAllIngredientList")
    @RequiresPermissions("product:ingredient:list")
    public R getAllIngredientList(){
        List<Dict> ingredientList = ingredientService.selectAllIngredientList();

        return R.ok().put("ingredientList", ingredientList);
    }

    /**
     * 信息
     */
    @RequestMapping("/residueWeightByMaterialName")
    public R residueWeightByIngredientId(@RequestBody IngredientEntity ingredient){

        IngredientEntity ingredientEntity = ingredientService.selectOne( new EntityWrapper<IngredientEntity>().eq( "material_name", ingredient.getMaterialName() ) );

        if (!StringUtils.isEmpty( ingredientEntity )) {
            Double residueWeight = ingredientService.residueWeightByIngredientId( ingredientEntity.getId() );
            return R.ok().put("residueWeight", residueWeight);
        }else {
            return R.ok().put( "residueWeight", 0 );

        }

    }


}
