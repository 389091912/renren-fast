package io.renren.modules.product.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.product.entity.ModelShelfEntity;
import io.renren.modules.product.service.ModelShelfService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 
 *
 * @author wsy
 * @email 389091912@qq.com
 * @date 2019-03-02 11:00:17
 */
@RestController
@RequestMapping("product/modelshelf")
public class ModelShelfController {
    @Autowired
    private ModelShelfService modelShelfService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("product:modelshelf:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = modelShelfService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("product:modelshelf:info")
    public R info(@PathVariable("id") Integer id){
			ModelShelfEntity modelShelf = modelShelfService.selectById(id);

        return R.ok().put("modelShelf", modelShelf);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("product:modelshelf:save")
    public R save(@RequestBody ModelShelfEntity modelShelf){
			modelShelfService.insert(modelShelf);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("product:modelshelf:update")
    public R update(@RequestBody ModelShelfEntity modelShelf){
			modelShelfService.updateById(modelShelf);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("product:modelshelf:delete")
    public R delete(@RequestBody Integer[] ids){
			modelShelfService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
