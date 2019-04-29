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

import io.renren.modules.product.entity.OrderMessageEntity;
import io.renren.modules.product.service.OrderMessageService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 
 *
 * @author wsy
 * @email 389091912@qq.com
 * @date 2019-04-27 16:30:52
 */
@RestController
@RequestMapping("product/ordermessage")
public class OrderMessageController {
    @Autowired
    private OrderMessageService orderMessageService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("product:ordermessage:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = orderMessageService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("product:ordermessage:info")
    public R info(@PathVariable("id") Integer id){
			OrderMessageEntity orderMessage = orderMessageService.selectById(id);

        return R.ok().put("orderMessage", orderMessage);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("product:ordermessage:save")
    public R save(@RequestBody OrderMessageEntity orderMessage){

			orderMessageService.insert(orderMessage);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("product:ordermessage:update")
    public R update(@RequestBody OrderMessageEntity orderMessage){
			orderMessageService.updateById(orderMessage);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("product:ordermessage:delete")
    public R delete(@RequestBody Integer[] ids){
			orderMessageService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
