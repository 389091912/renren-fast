package io.renren.modules.product.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.Dict;
import io.renren.common.utils.PageUtils;
import io.renren.modules.product.entity.ProductInfoEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author wsy
 * @email 389091912@qq.com
 * @date 2019-01-25 23:35:04
 */
public interface ProductInfoService extends IService<ProductInfoEntity> {

    /**
     * 查询分页
     * @param params
     * @return
     */
    PageUtils queryPage(Map<String, Object> params);


    /**
     * 获取 全部产品信息
     * @return
     */
    List<Dict> getAllProductVoList();

    /**
     * updateProductInfoIsNullByDesignId
     * @param designId
     */
    void updateProductInfoIsNullByDesignId(Integer designId);
}

