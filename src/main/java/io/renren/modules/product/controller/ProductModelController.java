package io.renren.modules.product.controller;

import java.util.Arrays;
import java.util.Map;

import io.renren.modules.sys.controller.AbstractController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.product.entity.ProductModelEntity;
import io.renren.modules.product.service.ProductModelService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 
 *
 * @author wsy
 * @email 389091912@qq.com
 * @date 2019-01-25 23:35:04
 */
@RestController
@RequestMapping("product/productmodel")
public class ProductModelController extends AbstractController {
    @Autowired
    private ProductModelService productModelService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("product:productmodel:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = productModelService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("product:productmodel:info")
    public R info(@PathVariable("id") Integer id){
			ProductModelEntity productModel = productModelService.selectById(id);

        return R.ok().put("productModel", productModel);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("product:productmodel:save")
    public R save(@RequestBody ProductModelEntity productModel){
			productModelService.insert(productModel);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("product:productmodel:update")
    public R update(@RequestBody ProductModelEntity productModel){
			productModelService.updateById(productModel);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("product:productmodel:delete")
    public R delete(@RequestBody Integer[] ids){
			productModelService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
