package io.renren.modules.product.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import io.renren.common.utils.Dict;
import io.renren.modules.product.service.ProductOrderService;
import io.renren.modules.sys.controller.AbstractController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.product.entity.ProductLeaveStorageEntity;
import io.renren.modules.product.service.ProductLeaveStorageService;
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
@RequestMapping("product/productleavestorage")
public class ProductLeaveStorageController extends AbstractController {
    @Autowired
    private ProductLeaveStorageService productLeaveStorageService;

    @Autowired
    private ProductOrderService productOrderService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("product:productleavestorage:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = productLeaveStorageService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("product:productleavestorage:info")
    public R info(@PathVariable("id") Integer id){
			ProductLeaveStorageEntity productLeaveStorage = productLeaveStorageService.selectById(id);

        return R.ok().put("productLeaveStorage", productLeaveStorage);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("product:productleavestorage:save")
    public R save(@RequestBody ProductLeaveStorageEntity productLeaveStorage){
			productLeaveStorageService.insert(productLeaveStorage);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("product:productleavestorage:update")
    public R update(@RequestBody ProductLeaveStorageEntity productLeaveStorage){
			productLeaveStorageService.updateById(productLeaveStorage);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("product:productleavestorage:delete")
    public R delete(@RequestBody Integer[] ids){
			productLeaveStorageService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

    /**
     *
     */
    @RequestMapping("/selectOrderIdByProductId/{productId}")
    public R selectOrderIdByProductId(@PathVariable("productId") Integer productId){
        if(productId==0){
            return R.ok().put( "orderList",null );
        }else {
            List<Dict> dictList = productOrderService.selectOrderIdByProductId( productId );
            return R.ok().put( "orderList",dictList );
        }

    }
}
