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

import io.renren.modules.product.entity.ProductRequireEntity;
import io.renren.modules.product.service.ProductRequireService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 产品需求

 *
 * @author wsy
 * @email 389091912@qq.com
 * @date 2019-01-30 02:41:40
 */
@RestController
@RequestMapping("product/productrequire")
public class ProductRequireController extends AbstractController {
    @Autowired
    private ProductRequireService productRequireService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("product:productrequire:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = productRequireService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("product:productrequire:info")
    public R info(@PathVariable("id") Integer id){
			ProductRequireEntity productRequire = productRequireService.selectById(id);
        System.out.println( productRequire.toString() );
        return R.ok().put("productRequire", productRequire);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("product:productrequire:save")
    public R save(@RequestBody ProductRequireEntity productRequire){
			productRequireService.insert(productRequire);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("product:productrequire:update")
    public R update(@RequestBody ProductRequireEntity productRequire){
			productRequireService.updateById(productRequire);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("product:productrequire:delete")
    public R delete(@RequestBody Integer[] ids){
			productRequireService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
