package io.renren.modules.product.entity.vo;

import io.swagger.models.auth.In;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * @author : wsy
 * Date: 2019-02-03
 * Time: 19:41
 */
@Data
public class ProductDetailVo implements Serializable {

    private Integer productId;
    private String zhiNumber;
    private String remark;
}
