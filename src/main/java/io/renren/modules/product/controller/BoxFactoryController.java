package io.renren.modules.product.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import io.renren.common.utils.Dict;
import io.renren.modules.sys.controller.AbstractController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.product.entity.BoxFactoryEntity;
import io.renren.modules.product.service.BoxFactoryService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 
 *
 * @author wsy
 * @email 389091912@qq.com
 * @date 2019-02-09 17:04:54
 */
@RestController
@RequestMapping("product/boxfactory")
public class BoxFactoryController extends AbstractController {
    @Autowired
    private BoxFactoryService boxFactoryService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("product:boxfactory:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = boxFactoryService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("product:boxfactory:info")
    public R info(@PathVariable("id") Integer id){
			BoxFactoryEntity boxFactory = boxFactoryService.selectById(id);

        return R.ok().put("boxFactory", boxFactory);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("product:boxfactory:save")
    public R save(@RequestBody BoxFactoryEntity boxFactory){
			boxFactoryService.insert(boxFactory);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("product:boxfactory:update")
    public R update(@RequestBody BoxFactoryEntity boxFactory){
			boxFactoryService.updateById(boxFactory);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("product:boxfactory:delete")
    public R delete(@RequestBody Integer[] ids){
			boxFactoryService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

    /**
     * 获取所有纸箱信息
     */
    @RequestMapping("/getAllProductBoxList")
    //s @RequiresPermissions("product:productbox:delete")
    public R getAllProductBoxList(){
        List<Dict> allProductBoxList = boxFactoryService.getAllBoxFactoryList();

        return R.ok().put( "productBoxList", allProductBoxList );
    }
}
