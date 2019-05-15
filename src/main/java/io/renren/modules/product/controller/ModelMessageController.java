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

import io.renren.modules.product.entity.ModelMessageEntity;
import io.renren.modules.product.service.ModelMessageService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 
 *
 * @author wsy
 * @email 389091912@qq.com
 * @date 2019-04-29 16:12:11
 */
@RestController
@RequestMapping("product/modelmessage")
public class ModelMessageController {
    @Autowired
    private ModelMessageService modelMessageService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("product:modelmessage:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = modelMessageService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("product:modelmessage:info")
    public R info(@PathVariable("id") Integer id){
			ModelMessageEntity modelMessage = modelMessageService.selectById(id);

        return R.ok().put("modelMessage", modelMessage);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("product:modelmessage:save")
    public R save(@RequestBody ModelMessageEntity modelMessage){
			modelMessageService.insert(modelMessage);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("product:modelmessage:update")
    public R update(@RequestBody ModelMessageEntity modelMessage){
			modelMessageService.updateById(modelMessage);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("product:modelmessage:delete")
    public R delete(@RequestBody Integer[] ids){
			modelMessageService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
