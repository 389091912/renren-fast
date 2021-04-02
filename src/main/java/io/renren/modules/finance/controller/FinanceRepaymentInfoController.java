package io.renren.modules.finance.controller;

import java.io.File;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Map;

import io.renren.common.utils.ExcelUtiles;
import io.renren.modules.sys.controller.AbstractController;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.renren.modules.finance.entity.FinanceRepaymentInfoEntity;
import io.renren.modules.finance.service.FinanceRepaymentInfoService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;

import javax.servlet.http.HttpServletResponse;


/**
 * 财务-个人还款
 *
 * @author wsy
 * @email wsy@gmail.com
 * @date 2021-01-29 20:45:18
 */
@RestController
@RequestMapping("finance/financerepaymentinfo")
public class FinanceRepaymentInfoController extends AbstractController {
    @Autowired
    private FinanceRepaymentInfoService financeRepaymentInfoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("finance:financerepaymentinfo:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = financeRepaymentInfoService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("finance:financerepaymentinfo:info")
    public R info(@PathVariable("id") Integer id){
			FinanceRepaymentInfoEntity financeRepaymentInfo = financeRepaymentInfoService.selectById(id);

        return R.ok().put("financeRepaymentInfo", financeRepaymentInfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("finance:financerepaymentinfo:save")
    public R save(@RequestBody FinanceRepaymentInfoEntity financeRepaymentInfo){
			financeRepaymentInfoService.insert(financeRepaymentInfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("finance:financerepaymentinfo:update")
    public R update(@RequestBody FinanceRepaymentInfoEntity financeRepaymentInfo){
			financeRepaymentInfoService.updateById(financeRepaymentInfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("finance:financerepaymentinfo:delete")
    public R delete(@RequestBody Integer[] ids){
			financeRepaymentInfoService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }


    /**
     * 模板导出
     * */
    @GetMapping("/templateDownloadFinanceRepaymentInfo")
    public void downloadModel(HttpServletResponse response) throws Exception {
        InputStream ins = this.getClass().getClassLoader().getResourceAsStream("templates/hxbl-FinanceBorrower.xlsx");
        File f=new File("hxbl-FinanceRepaymentInfo.xlsx");
        ExcelUtiles.inputStreamToFile(ins, f);

        XSSFWorkbook workbook = new XSSFWorkbook(f);

        //XSSFSheet sheet = workbook.getSheetAt(0);

        // sheet.createFreezePane(3, 2, 3, 2);//设置冰冻列

        ExcelUtiles.outPutStream(response, workbook, "宏鑫财务-个人还款导入模版");

        f.delete();
    }
}
