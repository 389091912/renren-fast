package io.renren.common.utils;

import io.renren.modules.product.entity.vo.ExcelInfo;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: wsy
 * Date: 2019-05-05
 * Time: 20:26
 */
public class ExcelUtil {

    public static void exportExcel(String fileName, List<ExcelInfo> excelInfos, HttpServletRequest request, HttpServletResponse response) throws IOException {
        /** 创建工作簿 */
        XSSFWorkbook workbook = new XSSFWorkbook();
        for (ExcelInfo excelInfo : excelInfos) {
            String sheetName = excelInfo.getSheetName();
            List<String> titles = excelInfo.getTitles();
            List<List<Object>> lists = excelInfo.getLists();

            /** 创建工作单 */
            XSSFSheet sheet = workbook.createSheet( sheetName );
            /** 创建第一行作为标题行 */
            XSSFRow row = sheet.createRow( 0 );
            /** 循环创建第一行中所有的列 */
            for (int i = 0; i < titles.size(); i++) {
                /** 创建列 */
                XSSFCell cell = row.createCell( i );
                /** 设置列中的值 */
                cell.setCellValue( titles.get( i ) );
            }
            /** 把集合data中的数据写入Excel */

            for (int i = 0; i < lists.size(); i++) {
                /** 创建行 */
                row = sheet.createRow( i + 1 );
                List<Object> list = lists.get( i );
//            /** 获取集合中的元素 */
    //       Object obj =  lists.get(i);
//            /** 利用反射获取java对象中所有的Field */
         //   Field[] fields = obj.getClass().getDeclaredFields();
                /** 循环所有的Field创建列 */
                for (int j = 0; j < list.size(); j++) {
                    /** 创建列 */
                    XSSFCell cell = row.createCell( j );
//                /** 获取字段名id */
//                String fieldName = fields[j].getName();
//                /** 把它转化成getId */
 //               String getMethodName = "get" + StringUtils.capitalize(fieldName);
//                /** 获取get方法 */
//                    try {
//                        Method method = obj.getClass().getMethod(getMethodName);
//                    } catch (NoSuchMethodException e) {
//                        e.printStackTrace();
//                    }
//                /** 调用方法 */
//                Object res = method.invoke(obj);
                    /** 设置列中的内容 */
                    cell.setCellValue( list.get( j ) != null ? list.get( j ).toString() : null );

                }
            }
        }
        /** 获取浏览器 */
        String userAgent = request.getHeader( "user-agent" );
        String encoding = "utf-8";
        /** 浏览器类型判断 */
        if (userAgent.toLowerCase().indexOf( "msie" ) != -1) {
            encoding = "gbk";
        }
        /** 响应过程:
         *  服务器： utf-8 -- iso8859-1
         *  浏览器：iso8859-1 -- utf-8 (firefox、chrome) msie iso8859-1 -- gbk
         */
        fileName = new String( fileName.getBytes( encoding ), "iso8859-1" );
        /** 告诉浏览器输出文件(响应内容的性质) */

        response.setContentType( "application/octet-stream" );
        response.setHeader( "Content-disposition", "attachment;filename=" + fileName + ".xls" );
        /** 向浏览器输出 */
        workbook.write( response.getOutputStream() );

        workbook.close();
    }

}

