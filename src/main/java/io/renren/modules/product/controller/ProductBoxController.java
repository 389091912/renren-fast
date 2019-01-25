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

import io.renren.modules.product.entity.ProductBoxEntity;
import io.renren.modules.product.service.ProductBoxService;
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
@RequestMapping("product/productbox")
public class ProductBoxController {
    @Autowired
    private ProductBoxService productBoxService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("product:productbox:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = productBoxService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("product:productbox:info")
    public R info(@PathVariable("id") Integer id){
			ProductBoxEntity productBox = productBoxService.selectById(id);

        return R.ok().put("productBox", productBox);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("product:productbox:save")
    public R save(@RequestBody ProductBoxEntity productBox){
			productBoxService.insert(productBox);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("product:productbox:update")
    public R update(@RequestBody ProductBoxEntity productBox){
			productBoxService.updateById(productBox);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("product:productbox:delete")
    public R delete(@RequestBody Integer[] ids){
			productBoxService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
