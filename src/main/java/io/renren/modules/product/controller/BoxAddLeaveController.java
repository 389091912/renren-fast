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

import io.renren.modules.product.entity.BoxAddLeaveEntity;
import io.renren.modules.product.service.BoxAddLeaveService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 
 *
 * @author wsy
 * @email 389091912@qq.com
 * @date 2019-01-30 02:41:40
 */
@RestController
@RequestMapping("product/boxaddleave")
public class BoxAddLeaveController {
    @Autowired
    private BoxAddLeaveService boxAddLeaveService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("product:boxaddleave:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = boxAddLeaveService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("product:boxaddleave:info")
    public R info(@PathVariable("id") Integer id){
			BoxAddLeaveEntity boxAddLeave = boxAddLeaveService.selectById(id);

        return R.ok().put("boxAddLeave", boxAddLeave);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("product:boxaddleave:save")
    public R save(@RequestBody BoxAddLeaveEntity boxAddLeave){
			boxAddLeaveService.insert(boxAddLeave);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("product:boxaddleave:update")
    public R update(@RequestBody BoxAddLeaveEntity boxAddLeave){
			boxAddLeaveService.updateById(boxAddLeave);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("product:boxaddleave:delete")
    public R delete(@RequestBody Integer[] ids){
			boxAddLeaveService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
