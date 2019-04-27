package io.renren.modules.product.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import io.renren.common.utils.Dict;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.product.entity.SupplierInfoEntity;
import io.renren.modules.product.service.SupplierInfoService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 
 *
 * @author wsy
 * @email 389091912@qq.com
 * @date 2019-04-26 00:15:25
 */
@RestController
@RequestMapping("product/supplierinfo")
public class SupplierInfoController {
    @Autowired
    private SupplierInfoService supplierInfoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("product:supplierinfo:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = supplierInfoService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("product:supplierinfo:info")
    public R info(@PathVariable("id") Integer id){
			SupplierInfoEntity supplierInfo = supplierInfoService.selectById(id);

        return R.ok().put("supplierInfo", supplierInfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("product:supplierinfo:save")
    public R save(@RequestBody SupplierInfoEntity supplierInfo){
			supplierInfoService.insert(supplierInfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("product:supplierinfo:update")
    public R update(@RequestBody SupplierInfoEntity supplierInfo){
			supplierInfoService.updateById(supplierInfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("product:supplierinfo:delete")
    public R delete(@RequestBody Integer[] ids){
			supplierInfoService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

    /**
     * 获取所有供应商信息
     */
    @RequestMapping("/getAllSupplierList")
    @RequiresPermissions("product:supplierinfo:list")
    public R getAllSupplierInfo(){

        List<Dict> dictList = supplierInfoService.selectAllSupplierInfo();
        return R.ok().put("supplierList", dictList);
    }

}
