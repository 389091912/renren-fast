package io.renren.modules.product.entity.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 * User: wsy
 * Date: 2019-05-30
 * Time: 0:12
 */
@Data
public class IngredientDetailVo {

    @Excel(name = "序号" )
    private Integer id;
    @Excel( name = "辅料名称")
    private String materialName;

    public IngredientDetailVo() {

    }
    public IngredientDetailVo(String materialName) {
        this.materialName = materialName;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }
}
