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

import io.renren.modules.product.entity.ProductDeviceEntity;
import io.renren.modules.product.service.ProductDeviceService;
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
@RequestMapping("product/productdevice")
public class ProductDeviceController extends AbstractController {
    @Autowired
    private ProductDeviceService productDeviceService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("product:productdevice:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = productDeviceService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("product:productdevice:info")
    public R info(@PathVariable("id") Integer id){
			ProductDeviceEntity productDevice = productDeviceService.selectById(id);

        return R.ok().put("productDevice", productDevice);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("product:productdevice:save")
    public R save(@RequestBody ProductDeviceEntity productDevice){
			productDeviceService.insert(productDevice);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("product:productdevice:update")
    public R update(@RequestBody ProductDeviceEntity productDevice){
			productDeviceService.updateById(productDevice);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("product:productdevice:delete")
    public R delete(@RequestBody Integer[] ids){
			productDeviceService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
