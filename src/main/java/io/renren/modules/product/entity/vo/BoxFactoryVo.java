package io.renren.modules.product.entity.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by IntelliJ IDEA.
 * @author : wsy
 * Date: 2019-02-09
 * Time: 22:26
 */
@Data
public class BoxFactoryVo implements Serializable {

    private Integer factoryId;

    private String boxBatch;

    private BigDecimal boxPrice;

}
