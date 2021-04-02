package io.renren.common.utils;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class ExcelUtiles {
    public static void exportExcel(List<?> list, String title, String sheetName, Class<?> pojoClass,
                                   String fileName, boolean isCreateHeader, HttpServletResponse response){
        ExportParams exportParams = new ExportParams(title, sheetName);
        exportParams.setCreateHeadRows(isCreateHeader);
        defaultExport(list, pojoClass, fileName, response, exportParams);
    }

    public static void exportExcel(List<?> list, String title, String sheetName, Class<?> pojoClass,String fileName,
                                   HttpServletResponse response){
        defaultExport(list, pojoClass, fileName, response, new ExportParams(title, sheetName));
    }

    public static void exportExcel(List<Map<String, Object>> list, String fileName, HttpServletResponse response){
        defaultExport(list, fileName, response);
    }

    private static void defaultExport(List<?> list, Class<?> pojoClass, String fileName,
                                      HttpServletResponse response, ExportParams exportParams) {
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams,pojoClass,list);

        if (workbook != null); downLoadExcel(fileName, response, workbook);
    }

    public static void downLoadExcel(String fileName, HttpServletResponse response, Workbook workbook) {
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/msexcel");
         //   response.setHeader("content-Type", "application/vnd.ms-excel");
            response.setHeader( "Access-Control-Expose-Headers", "Content-Disposition" );
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            //throw new NormalException(e.getMessage());
        }
    }

    private static void defaultExport(List<Map<String, Object>> list, String fileName, HttpServletResponse response) {
        Workbook workbook = ExcelExportUtil.exportExcel(list, ExcelType.HSSF);

        if (workbook != null);
        downLoadExcel(fileName, response, workbook);
    }

    public static <T> List<T> importExcel(String filePath,Integer titleRows,Integer headerRows, Class<T> pojoClass){
        if (StringUtils.isBlank(filePath)){
            return null;
        }
        ImportParams params = new ImportParams();
        params.setTitleRows(titleRows);
        params.setHeadRows(headerRows);
        List<T> list = null;
        try {
            list = ExcelImportUtil.importExcel(new File(filePath), pojoClass, params);
        }catch (NoSuchElementException e){
            //throw new NormalException("模板不能为空");
        } catch (Exception e) {
            e.printStackTrace();
            //throw new NormalException(e.getMessage());
        } return list;
    }

    public static <T> List<T> importExcel(MultipartFile file, Integer titleRows, Integer headerRows, Class<T> pojoClass){
        if (file == null){ return null;
        }
        ImportParams params = new ImportParams();
        params.setTitleRows(titleRows);
        params.setHeadRows(headerRows);
        List<T> list = null;
        try {
            list = ExcelImportUtil.importExcel(file.getInputStream(), pojoClass, params);
        }catch (NoSuchElementException e){
            // throw new NormalException("excel文件不能为空");
        } catch (Exception e) {
            //throw new NormalException(e.getMessage());
            System.out.println(e.getMessage());
        }
        return list;
    }
    public static void outPutStream(HttpServletResponse response, Workbook workbook, String fileName) throws IOException {
        OutputStream output = response.getOutputStream();
        response.reset();
        response.setHeader("Content-disposition", "attachment; filename=" + new String(fileName.getBytes("utf-8"), "ISO8859-1") + ".xlsx");
        response.setContentType("application/msexcel");
        workbook.write(output);
        output.close();
    }

    public static void outPutStream(HttpServletResponse response, XSSFWorkbook workbook, String fileName) throws IOException {
        OutputStream output = response.getOutputStream();
        response.reset();
        response.setHeader("Content-disposition", "attachment; filename=" + new String(fileName.getBytes("utf-8"), "ISO8859-1") + ".xlsx");
        response.setContentType("application/msexcel");
        workbook.write(output);
        output.close();
    }

    public static XSSFDataValidation getXSSFDataValidation(XSSFSheet sheet, String[] datas, Integer firstRow, Integer lastRow, Integer firstCol, Integer lastCol){

        XSSFDataValidationHelper dvHelper = new XSSFDataValidationHelper(sheet);
        //下拉框可选的数据，作为约束
        XSSFDataValidationConstraint dvConstraint = (XSSFDataValidationConstraint) dvHelper
                .createExplicitListConstraint(datas);
        CellRangeAddressList addressList = new CellRangeAddressList(firstRow,lastRow,firstCol,lastCol);
        XSSFDataValidation validation = (XSSFDataValidation) dvHelper.createValidation(dvConstraint,addressList);

        return validation;

    }
    public static void setXSSFDataValidation(XSSFWorkbook workbook, String[] datas, Integer firstRow, Integer lastRow, Integer firstCol, Integer lastCol){
        XSSFSheet sheet = workbook.getSheetAt(0);
        XSSFSheet hidden = workbook.createSheet("hiddenSheet");
        for (int i = 0, length = datas.length; i < length; i++)
        {
            hidden.createRow(i).createCell(0).setCellValue(datas[i]);
        }
        // 代表一系列单元格的定义名称
        // 单元格引用
        Name category1Name = workbook.createName();
        category1Name.setNameName("hidden");
        category1Name.setRefersToFormula("hiddenSheet" + "!$A$1:$A$" + datas.length);

        // 获取上文名称内数据
        DataValidationHelper helper = sheet.getDataValidationHelper();
        DataValidationConstraint constraint = helper.createFormulaListConstraint("hidden");
        // 设置下拉框位置
        CellRangeAddressList addressList = new CellRangeAddressList(firstRow, lastRow, firstCol, lastCol);
        DataValidation validation = helper.createValidation(constraint, addressList);
        // 处理Excel兼容性问题
        if (validation instanceof XSSFDataValidation) {
            // 数据校验
            validation.setSuppressDropDownArrow(true);
            validation.setShowErrorBox(true);
        } else {
            validation.setSuppressDropDownArrow(false);
        }

        // 数据多了会显示不了
//		XSSFDataValidationHelper dvHelper = new XSSFDataValidationHelper(sheet);
//		// 下拉框可选的数据，作为约束
//		XSSFDataValidationConstraint dvConstraint = (XSSFDataValidationConstraint) dvHelper.createExplicitListConstraint(datas);
//		CellRangeAddressList addressList = new CellRangeAddressList(firstRow,lastRow,firstCol,lastCol);
//		XSSFDataValidation validation = (XSSFDataValidation) dvHelper.createValidation(dvConstraint,addressList);

        workbook.setSheetHidden(1, true);
        sheet.addValidationData(validation);
    }

    public static void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}