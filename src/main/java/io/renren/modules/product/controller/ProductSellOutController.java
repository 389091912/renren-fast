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

import io.renren.modules.product.entity.ProductSellOutEntity;
import io.renren.modules.product.service.ProductSellOutService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 
 *
 * @author wsy
 * @email 389091912@qq.com
 * @date 2019-01-14 00:02:12
 */
@RestController
@RequestMapping("product/productsellout")
public class ProductSellOutController {
    @Autowired
    private ProductSellOutService productSellOutService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("product:productsellout:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = productSellOutService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("product:productsellout:info")
    public R info(@PathVariable("id") Integer id){
			ProductSellOutEntity productSellOut = productSellOutService.selectById(id);

        return R.ok().put("productSellOut", productSellOut);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("product:productsellout:save")
    public R save(@RequestBody ProductSellOutEntity productSellOut){
			productSellOutService.insert(productSellOut);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("product:productsellout:update")
    public R update(@RequestBody ProductSellOutEntity productSellOut){
			productSellOutService.updateById(productSellOut);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("product:productsellout:delete")
    public R delete(@RequestBody Integer[] ids){
			productSellOutService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
