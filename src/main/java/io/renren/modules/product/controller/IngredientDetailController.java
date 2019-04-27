package io.renren.modules.product.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.renren.modules.product.entity.IngredientEntity;
import io.renren.modules.product.service.IngredientService;
import io.renren.modules.sys.controller.AbstractController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.product.entity.IngredientDetailEntity;
import io.renren.modules.product.service.IngredientDetailService;
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
@RequestMapping("product/ingredientdetail")
public class IngredientDetailController extends AbstractController {
    @Autowired
    private IngredientDetailService ingredientDetailService;

    @Autowired
    private IngredientService ingredientService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("product:ingredientdetail:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = ingredientDetailService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("product:ingredientdetail:info")
    public R info(@PathVariable("id") Integer id){
			IngredientDetailEntity ingredientDetail = ingredientDetailService.selectById(id);
        IngredientEntity ingredientEntity = ingredientService.selectById( ingredientDetail.getIngredientId() );
        if (!StringUtils.isEmpty( ingredientEntity )) {
            ingredientDetail.setMaterialName( ingredientEntity.getMaterialName() );

        }
        return R.ok().put("ingredientDetail", ingredientDetail);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("product:ingredientdetail:save")
    public R save(@RequestBody IngredientDetailEntity ingredientDetail){

        IngredientEntity ingredient = ingredientService.selectOne( new EntityWrapper<IngredientEntity>().eq( "material_name", ingredientDetail.getMaterialName() ) );
        Date date = new Date();

        if (StringUtils.isEmpty(ingredient)) {
            IngredientEntity ingredientEntity = new IngredientEntity();
            ingredientEntity.setMaterialName( ingredientDetail.getMaterialName() );
            ingredientEntity.setCreateTime( date );
            ingredientEntity.setCreateUser( getUserId().intValue() );
            ingredientService.insertOrUpdate( ingredientEntity );

            ingredientDetail.setIngredientId( ingredientEntity.getId() );
        }else {
            ingredientDetail.setIngredientId( ingredient.getId() );
        }

            ingredientDetail.setCreateTime( date );
            ingredientDetail.setCreateUser( getUserId().intValue() );
			ingredientDetailService.insert(ingredientDetail);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("product:ingredientdetail:update")
    public R update(@RequestBody IngredientDetailEntity ingredientDetail){
			ingredientDetailService.updateById(ingredientDetail);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("product:ingredientdetail:delete")
    public R delete(@RequestBody Integer[] ids){
			ingredientDetailService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
