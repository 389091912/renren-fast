package io.renren.modules.product.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.Dict;
import io.renren.common.utils.PageUtils;
import io.renren.modules.product.entity.ProductBoxEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author wsy
 * @email 389091912@qq.com
 * @date 2019-01-25 23:35:04
 */
public interface ProductBoxService extends IService<ProductBoxEntity> {
    /**
     *  获取 纸箱分页
     * @param params
     * @return
     */
    PageUtils queryPage(Map<String, Object> params);


    List<Dict> getAllProductBoxList();

}

