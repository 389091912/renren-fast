package io.renren.modules.finance.controller;

import java.io.File;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import io.renren.common.utils.ExcelUtiles;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.renren.modules.finance.entity.FinanceOughtReceiveEntity;
import io.renren.modules.finance.service.FinanceOughtReceiveService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;


/**
 * 财务—收款信息
 *
 * @author wsy
 * @email wsy@gmail.com
 * @date 2020-12-13 03:01:58
 */
@RestController
@RequestMapping("finance/financeoughtreceive")
public class FinanceOughtReceiveController {
    @Autowired
    private FinanceOughtReceiveService financeOughtReceiveService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("finance:financeoughtreceive:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = financeOughtReceiveService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("finance:financeoughtreceive:info")
    public R info(@PathVariable("id") Integer id){
			FinanceOughtReceiveEntity financeOughtReceive = financeOughtReceiveService.selectById(id);

        return R.ok().put("financeOughtReceive", financeOughtReceive);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("finance:financeoughtreceive:save")
    public R save(@RequestBody FinanceOughtReceiveEntity financeOughtReceive){
			financeOughtReceiveService.insert(financeOughtReceive);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("finance:financeoughtreceive:update")
    public R update(@RequestBody FinanceOughtReceiveEntity financeOughtReceive){
			financeOughtReceiveService.updateById(financeOughtReceive);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("finance:financeoughtreceive:delete")
    public R delete(@RequestBody Integer[] ids){
			financeOughtReceiveService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }



    /**
     * 导入文件
     */
    @RequestMapping("/importFinancePay")
    public R importFinancePay(MultipartFile file) throws Exception{
        System.out.println(file.getOriginalFilename());

        if(file == null || file.isEmpty()){
            return R.ok().put("msg","请刷新浏览器重新尝试该操作!");
        }

        List<String> errorMsgList =null;

        return CollectionUtils.isNotEmpty(errorMsgList) ? R.error().put("msg", errorMsgList) : R.ok();

    }

}
