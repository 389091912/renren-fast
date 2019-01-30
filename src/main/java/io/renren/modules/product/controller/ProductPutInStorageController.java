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

import io.renren.modules.product.entity.ProductPutInStorageEntity;
import io.renren.modules.product.service.ProductPutInStorageService;
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
@RequestMapping("product/productputinstorage")
public class ProductPutInStorageController extends AbstractController {
    @Autowired
    private ProductPutInStorageService productPutInStorageService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("product:productputinstorage:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = productPutInStorageService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("product:productputinstorage:info")
    public R info(@PathVariable("id") Integer id){
			ProductPutInStorageEntity productPutInStorage = productPutInStorageService.selectById(id);

        return R.ok().put("productPutInStorage", productPutInStorage);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("product:productputinstorage:save")
    public R save(@RequestBody ProductPutInStorageEntity productPutInStorage){
			productPutInStorageService.insert(productPutInStorage);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("product:productputinstorage:update")
    public R update(@RequestBody ProductPutInStorageEntity productPutInStorage){
			productPutInStorageService.updateById(productPutInStorage);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("product:productputinstorage:delete")
    public R delete(@RequestBody Integer[] ids){
			productPutInStorageService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
