package io.renren.modules.finance.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.common.utils.R;
import io.renren.modules.product.entity.vo.DictVo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.renren.modules.finance.entity.FinanceCategoryEntity;
import io.renren.modules.finance.service.FinanceCategoryService;




/**
 * 财务类别
 *
 * @author wsy
 * @email wsy@gmail.com
 * @date 2020-12-13 03:01:58
 */
@RestController
@RequestMapping("finance/financecategory")
public class FinanceCategoryController {
    @Autowired
    private FinanceCategoryService financeCategoryService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("finance:financecategory:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = financeCategoryService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("finance:financecategory:info")
    public R info(@PathVariable("id") Integer id){
			FinanceCategoryEntity financeCategory = financeCategoryService.selectById(id);

        return R.ok().put("financeCategory", financeCategory);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("finance:financecategory:save")
    public R save(@RequestBody FinanceCategoryEntity financeCategory){
			financeCategoryService.insert(financeCategory);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("finance:financecategory:update")
    public R update(@RequestBody FinanceCategoryEntity financeCategory){
			financeCategoryService.updateById(financeCategory);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("finance:financecategory:delete")
    public R delete(@RequestBody Integer[] ids){
			financeCategoryService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }
    /**
     * 列表
     */
    @GetMapping("/getAllFinanceList")
    @RequiresPermissions("finance:financecategory:list")
    public R getAllFinanceList(){

        EntityWrapper<FinanceCategoryEntity> categoryWrapper = new EntityWrapper<>();

        List<FinanceCategoryEntity> categoryEntityList = financeCategoryService.selectList(categoryWrapper);


        return R.ok().put("financeCategoryList", categoryEntityList);
    }


}

