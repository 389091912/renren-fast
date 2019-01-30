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

import io.renren.modules.product.entity.ProductOrderEntity;
import io.renren.modules.product.service.ProductOrderService;
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
@RequestMapping("product/productorder")
public class ProductOrderController extends AbstractController {
    @Autowired
    private ProductOrderService productOrderService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("product:productorder:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = productOrderService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("product:productorder:info")
    public R info(@PathVariable("id") Integer id){
			ProductOrderEntity productOrder = productOrderService.selectById(id);

        return R.ok().put("productOrder", productOrder);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("product:productorder:save")
    public R save(@RequestBody ProductOrderEntity productOrder){
			productOrderService.insert(productOrder);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("product:productorder:update")
    public R update(@RequestBody ProductOrderEntity productOrder){
			productOrderService.updateById(productOrder);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("product:productorder:delete")
    public R delete(@RequestBody Integer[] ids){
			productOrderService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
