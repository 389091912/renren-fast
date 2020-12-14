package io.renren.modules.finance.controller;

import java.util.Arrays;
import java.util.Map;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.finance.entity.FinancePayEntity;
import io.renren.modules.finance.service.FinancePayService;



/**
 * 财务付款信息
 *
 * @author wsy
 * @email wsy@gmail.com
 * @date 2020-12-13 03:01:58
 */
@RestController
@RequestMapping("finance/financepay")
public class FinancePayController {
    @Autowired
    private FinancePayService financePayService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("finance:financepay:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = financePayService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("finance:financepay:info")
    public R info(@PathVariable("id") Integer id){
			FinancePayEntity financePay = financePayService.selectById(id);

        return R.ok().put("financePay", financePay);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("finance:financepay:save")
    public R save(@RequestBody FinancePayEntity financePay){
			financePayService.insert(financePay);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("finance:financepay:update")
    public R update(@RequestBody FinancePayEntity financePay){
			financePayService.updateById(financePay);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("finance:financepay:delete")
    public R delete(@RequestBody Integer[] ids){
			financePayService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
