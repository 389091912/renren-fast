package io.renren.modules.product.entity.vo;

import lombok.Data;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: wsy
 * Date: 2019-05-05
 * Time: 20:28
 */
@Data
public class ExcelInfo {

    private String sheetName;
    private List<String> titles;

    private List<List<Object>> lists;

}
