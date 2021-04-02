package io.renren.modules.finance.controller;

import java.io.File;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.renren.common.utils.DateUtils;
import io.renren.common.utils.ExcelUtiles;
import io.renren.modules.finance.entity.FinanceOughtReceiveEntity;
import io.renren.modules.finance.entity.TbUserEntity;
import io.renren.modules.finance.service.TbUserService;
import io.renren.modules.sys.controller.AbstractController;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import io.renren.modules.finance.entity.FinanceBorrowerInfoEntity;
import io.renren.modules.finance.service.FinanceBorrowerInfoService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;


/**
 * 财务-个人借款
 *
 * @author wsy
 * @email wsy@gmail.com
 * @date 2021-01-29 20:45:18
 */
@Slf4j
@RestController
@RequestMapping("finance/financeborrowerinfo")
public class FinanceBorrowerInfoController extends AbstractController {
    @Autowired
    private FinanceBorrowerInfoService financeBorrowerInfoService;

    @Autowired
    private TbUserService tbUserService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("finance:financeborrowerinfo:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = financeBorrowerInfoService.queryPage(params);


        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("finance:financeborrowerinfo:info")
    public R info(@PathVariable("id") Integer id){
			FinanceBorrowerInfoEntity financeBorrowerInfo = financeBorrowerInfoService.selectById(id);

        return R.ok().put("financeBorrowerInfo", financeBorrowerInfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("finance:financeborrowerinfo:save")
    public R save(@RequestBody FinanceBorrowerInfoEntity financeBorrowerInfo){
			financeBorrowerInfoService.insert(financeBorrowerInfo);



        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("finance:financeborrowerinfo:update")
    public R update(@RequestBody FinanceBorrowerInfoEntity financeBorrowerInfo){
			financeBorrowerInfoService.updateById(financeBorrowerInfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("finance:financeborrowerinfo:delete")
    public R delete(@RequestBody Integer[] ids){
			financeBorrowerInfoService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }


    /**
     * 模板导出
     * */
    @GetMapping("/templateDownloadFinanceBorrower")
    public void downloadModel(HttpServletResponse response) throws Exception {
        InputStream ins = this.getClass().getClassLoader().getResourceAsStream("templates/hxbl-FinanceBorrower.xlsx");
        File f=new File("hxbl-FinanceBorrower.xlsx");
        ExcelUtiles.inputStreamToFile(ins, f);

        XSSFWorkbook workbook = new XSSFWorkbook(f);

        //XSSFSheet sheet = workbook.getSheetAt(0);

        // sheet.createFreezePane(3, 2, 3, 2);//设置冰冻列

        ExcelUtiles.outPutStream(response, workbook, "宏鑫财务个人借款导入模版");

        f.delete();
    }


    /**
     * 导入文件
     */
    @RequestMapping("/importFinanceBorrower")
    public R importFinanceBorrower(MultipartFile file) throws Exception{
        System.out.println(file.getOriginalFilename());

        if(file == null || file.isEmpty()){
            return R.ok().put("msg","请刷新浏览器重新尝试该操作!");
        }

        List<String> errorMsgList = financeBorrowerInfoService.importFinanceBorrower(file);

        return CollectionUtils.isNotEmpty(errorMsgList) ? R.error().put("msg", errorMsgList) : R.ok();

    }


    //getFinanceOutReceiveExport



    /**
     * 导出借款明细
     * */
    @GetMapping("/getFinanceBorrowerListExport")
    public void getFinanceBorrowerListExport(@RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {

        Map<String, Object> map = new HashMap<>();
        String startDateTime = (String) params.get("startDateTime");
        String endDateTime = (String) params.get("endDateTime");
        String userId = (String) params.get("userId");

        logger.error("userId"  + userId);

        List<FinanceBorrowerInfoEntity> financeBorrowerInfoList = financeBorrowerInfoService.getFinanceBorrowerInfoList(params);


        TemplateExportParams param = new TemplateExportParams("templates/hxbl-FinanceBorrowerListModel.xlsx" );
        if (CollectionUtils.isNotEmpty(financeBorrowerInfoList)) {


            EntityWrapper<TbUserEntity> userQuery = new EntityWrapper<>();

            List<Long> userIdList = financeBorrowerInfoList.stream().map(FinanceBorrowerInfoEntity::getUserId).collect(Collectors.toList());

            userQuery.in("user_id", userIdList);
            List<TbUserEntity> tbUserEntityList = tbUserService.selectList(userQuery);

            Map<Long, String> userMap = tbUserEntityList.stream().collect(Collectors.toMap(TbUserEntity::getUserId, TbUserEntity::getUsername));


            BigDecimal countAmount = financeBorrowerInfoList.stream().map(FinanceBorrowerInfoEntity::getBorrowerMoney).reduce(BigDecimal.ZERO, BigDecimal::add);

            int countIndex = 1;
            for (FinanceBorrowerInfoEntity financeBorrowerInfoEntity : financeBorrowerInfoList) {

                financeBorrowerInfoEntity.setId(countIndex);
                financeBorrowerInfoEntity.setUsername(userMap.get(financeBorrowerInfoEntity.getUserId()));
                financeBorrowerInfoEntity.setBorrowerDateStr(DateUtils.format(financeBorrowerInfoEntity.getBorrowerDate()));

                countIndex++;

                log.info(financeBorrowerInfoEntity.toString());
            }
            map.put("countAmount",countAmount);
            map.put("borrowInfoList", financeBorrowerInfoList);

        }
        map.put("startDateTime", StringUtils.isEmpty(startDateTime) ? "（默认开始时间）" : startDateTime.substring(0,11));
        map.put("endDateTime", StringUtils.isEmpty(endDateTime) ? "（默认结束时间）" : endDateTime.substring(0,11));

        Workbook workbook = ExcelExportUtil.exportExcel(param, map);

        String fileDate = new SimpleDateFormat("MM-dd_HH-mm").format(new Date());

        if (!StringUtils.isEmpty(workbook)) {
            ExcelUtiles.downLoadExcel("宏鑫财务个人借款单" + fileDate + ".xlsx", response, workbook);
        }

    }
}
