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

import io.renren.modules.product.entity.ModelWarehouseEntity;
import io.renren.modules.product.service.ModelWarehouseService;
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
@RequestMapping("product/modelwarehouse")
public class ModelWarehouseController {
    @Autowired
    private ModelWarehouseService modelWarehouseService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("product:modelwarehouse:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = modelWarehouseService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("product:modelwarehouse:info")
    public R info(@PathVariable("id") Integer id){
			ModelWarehouseEntity modelWarehouse = modelWarehouseService.selectById(id);

        return R.ok().put("modelWarehouse", modelWarehouse);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("product:modelwarehouse:save")
    public R save(@RequestBody ModelWarehouseEntity modelWarehouse){
			modelWarehouseService.insert(modelWarehouse);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("product:modelwarehouse:update")
    public R update(@RequestBody ModelWarehouseEntity modelWarehouse){
			modelWarehouseService.updateById(modelWarehouse);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("product:modelwarehouse:delete")
    public R delete(@RequestBody Integer[] ids){
			modelWarehouseService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
