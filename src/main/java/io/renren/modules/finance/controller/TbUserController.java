package io.renren.modules.finance.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.renren.common.utils.Dict;
import io.renren.modules.product.entity.BoxFactoryEntity;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.renren.modules.finance.entity.TbUserEntity;
import io.renren.modules.finance.service.TbUserService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 用户
 *
 * @author wsy
 * @email wsy@gmail.com
 * @date 2021-01-29 21:20:29
 */
@RestController
@RequestMapping("finance/tbuser")
public class TbUserController {
    @Autowired
    private TbUserService tbUserService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("finance:tbuser:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = tbUserService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{userId}")
    @RequiresPermissions("finance:tbuser:info")
    public R info(@PathVariable("userId") Long userId){
			TbUserEntity tbUser = tbUserService.selectById(userId);

        return R.ok().put("tbUser", tbUser);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("finance:tbuser:save")
    public R save(@RequestBody TbUserEntity tbUser){
			tbUserService.insert(tbUser);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("finance:tbuser:update")
    public R update(@RequestBody TbUserEntity tbUser){
			tbUserService.updateById(tbUser);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("finance:tbuser:delete")
    public R delete(@RequestBody Long[] userIds){
			tbUserService.deleteBatchIds(Arrays.asList(userIds));

        return R.ok();
    }


    @GetMapping("/getAllTbUserList")
    public R getAllTbUserList() {
        EntityWrapper<TbUserEntity> userEntityWrapper = new EntityWrapper<>();

        List<TbUserEntity> tbUserEntityList = tbUserService.selectList(userEntityWrapper);

        if (CollectionUtils.isNotEmpty(tbUserEntityList)) {
            List<Dict> tbUserDictList = tbUserEntityList.stream().map(item -> {
                Dict dict = new Dict();
                dict.setId(item.getUserId().intValue());
                dict.setName(item.getUsername());
                return dict;
            }).collect(Collectors.toList());
            return R.ok().put("tbUserDictList", tbUserDictList);
        }
        return R.ok();
    }

}
