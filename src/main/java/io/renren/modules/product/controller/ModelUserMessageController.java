package io.renren.modules.product.controller;

import java.util.AbstractCollection;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import com.sun.org.apache.bcel.internal.generic.NEW;
import io.renren.modules.sys.controller.AbstractController;
import io.renren.modules.sys.entity.SysUserEntity;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.product.entity.ModelUserMessageEntity;
import io.renren.modules.product.service.ModelUserMessageService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import org.springframework.web.server.session.InMemoryWebSessionStore;


/**
 * 
 *
 * @author wsy
 * @email 389091912@qq.com
 * @date 2019-04-29 16:12:11
 */
@RestController
@RequestMapping("product/modelusermessage")
public class ModelUserMessageController   extends AbstractController {
    @Autowired
    private ModelUserMessageService modelUserMessageService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("product:modelusermessage:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = modelUserMessageService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("product:modelusermessage:info")
    public R info(@PathVariable("id") Integer id){
			ModelUserMessageEntity modelUserMessage = modelUserMessageService.selectById(id);

        return R.ok().put("modelUserMessage", modelUserMessage);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("product:modelusermessage:save")
    public R save(@RequestBody ModelUserMessageEntity modelUserMessage){

        modelUserMessage.setUserId( getUserId().intValue() );
        modelUserMessage.setIsRead( ModelUserMessageEntity.IS_READ );
        modelUserMessage.setCreateTime( new Date() );
        modelUserMessage.setCreateUser( getUserId().intValue() );
		modelUserMessageService.insert(modelUserMessage);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("product:modelusermessage:update")
    public R update(@RequestBody ModelUserMessageEntity modelUserMessage){
			modelUserMessageService.updateById(modelUserMessage);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("product:modelusermessage:delete")
    public R delete(@RequestBody Integer[] ids){
			modelUserMessageService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }
    /**
     * 列表
     */
    @RequestMapping("/getModelMsgUserNumber")
    @RequiresPermissions("product:modelusermessage:list")
    public R getModelMsgUserNumber( ){
        SysUserEntity user = getUser();
        if(user.getType()==SysUserEntity.SYSTEM_USER||user.getType()==SysUserEntity.SALESMAN){
            Integer msgCountNumber = modelUserMessageService.selectNewModelMsgByUserId( user.getUserId().intValue() );
            System.out.println( msgCountNumber );
            return R.ok().put("msgCountNumber", msgCountNumber);
        }else {
            return R.ok().put("msgCountNumber", 0);
        }
    }

}
