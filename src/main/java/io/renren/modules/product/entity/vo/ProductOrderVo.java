package io.renren.modules.product.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * @author : wsy
 * Date: 2019-02-09
 * Time: 1:59
 */
@Data
public class ProductOrderVo implements Serializable {

    private Integer orderId;

    private Integer boxSupplyWay;

    private String orderNo;


}
