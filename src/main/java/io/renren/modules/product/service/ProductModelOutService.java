package io.renren.modules.product.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.product.entity.ProductModelOutEntity;

import java.util.Map;

/**
 * 
 *
 * @author wsy
 * @email 389091912@qq.com
 * @date 2019-01-25 23:35:04
 */
public interface ProductModelOutService extends IService<ProductModelOutEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 获取模具所有的入库数量
     * @param modelNo
     * @return
     */
    ProductModelOutEntity getAllModelAddCountByModelNo(Integer modelNo);

    /**
     * 获取模具 所有的出库数量
     * @param modelNo
     * @return
     */
    ProductModelOutEntity getAllModelOutCountByModelNo(Integer modelNo);


}

