package io.renren.modules.product.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import io.renren.common.utils.Dict;
import io.renren.modules.product.service.ProductModelService;
import io.renren.modules.sys.controller.AbstractController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.product.entity.ProductModelOutEntity;
import io.renren.modules.product.service.ProductModelOutService;
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
@RequestMapping("product/productmodelout")
public class ProductModelOutController extends AbstractController {
    @Autowired
    private ProductModelOutService productModelOutService;

    @Autowired
    private ProductModelService productModelService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("product:productmodelout:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = productModelOutService.queryPage(params);
        List<Dict> modelVoList = productModelService.getModelVoList();

        return R.ok().put( "page", page ).put( "modelVoList", modelVoList );
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("product:productmodelout:info")
    public R info(@PathVariable("id") Integer id){
		ProductModelOutEntity productModelOut = productModelOutService.selectById(id);


        System.out.println(productModelOut.toString());
        return R.ok().put("productModelOut", productModelOut);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("product:productmodelout:save")
    public R save(@RequestBody ProductModelOutEntity productModelOut){
			productModelOutService.insert(productModelOut);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("product:productmodelout:update")
    public R update(@RequestBody ProductModelOutEntity productModelOut){
			productModelOutService.updateById(productModelOut);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("product:productmodelout:delete")
    public R delete(@RequestBody Integer[] ids){
			productModelOutService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
