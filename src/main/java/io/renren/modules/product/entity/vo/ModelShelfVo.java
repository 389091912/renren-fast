package io.renren.modules.product.entity.vo;

import com.baomidou.mybatisplus.annotations.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * @author : wsy
 * Date: 2019-03-13
 * Time: 0:46
 */
@Data
public class ModelShelfVo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId
    private Integer id;
    /**
     * 仓库号
     */
    private Integer wId;
    /**
     * 架号
     */
    private String sNo;
    /**
     * 是否有货
     */
    private String empty;
}
