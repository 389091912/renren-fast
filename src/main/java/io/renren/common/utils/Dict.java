package io.renren.common.utils;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: wsy
 * Date: 2019-01-31
 */
@Data
public class Dict implements Serializable {

    /**
     * id
     */
    private Integer id;

    /**
     * name
     */
    private String name;

}
