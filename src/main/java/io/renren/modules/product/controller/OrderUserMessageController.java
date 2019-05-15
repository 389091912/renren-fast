package io.renren.modules.product.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import io.renren.modules.sys.controller.AbstractController;
import io.renren.modules.sys.entity.SysUserEntity;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.product.entity.OrderUserMessageEntity;
import io.renren.modules.product.service.OrderUserMessageService;
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
@RequestMapping("product/orderusermessage")
public class OrderUserMessageController  extends AbstractController {
    @Autowired
    private OrderUserMessageService orderUserMessageService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("product:orderusermessage:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = orderUserMessageService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("product:orderusermessage:info")
    public R info(@PathVariable("id") Integer id){
			OrderUserMessageEntity orderUserMessage = orderUserMessageService.selectById(id);

        return R.ok().put("orderUserMessage", orderUserMessage);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("product:orderusermessage:save")
    public R save(@RequestBody OrderUserMessageEntity orderUserMessage){
            orderUserMessage.setUserId( getUserId().intValue() );
            orderUserMessage.setCreateTime( new Date() );
            orderUserMessage.setCreateUser(getUserId().intValue());
			orderUserMessageService.insert(orderUserMessage);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("product:orderusermessage:update")
    public R update(@RequestBody OrderUserMessageEntity orderUserMessage){
			orderUserMessageService.updateById(orderUserMessage);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("product:orderusermessage:delete")
    public R delete(@RequestBody Integer[] ids){
			orderUserMessageService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }


    /**
     * 列表
     */
    @RequestMapping("/getUserMessageCount")
    @RequiresPermissions("product:orderusermessage:list")
    public R getUserMessageCount(){
        SysUserEntity user = getUser();
        if (user.getType() == SysUserEntity.SYSTEM_USER) {
            Integer userMessageCount = orderUserMessageService.selectUserMessageCount( user.getUserId().intValue() );
            System.out.println( userMessageCount );
            return R.ok().put("orderMsgCountNumber", userMessageCount);
        }else {
            return R.ok().put("orderMsgCountNumber", 0);
        }



    }
}
