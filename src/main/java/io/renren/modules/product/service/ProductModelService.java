package io.renren.modules.product.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.Dict;
import io.renren.common.utils.PageUtils;
import io.renren.modules.product.entity.ProductModelEntity;
import io.renren.modules.product.entity.ProductOrderEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author wsy
 * @email 389091912@qq.com
 * @date 2019-01-25 23:35:04
 */
public interface ProductModelService extends IService<ProductModelEntity> {

    /**
     * 查询 信息   分页
     * @param params
     * @return
     */
    PageUtils queryPage(Map<String, Object> params);

    /**
     * 查询 全部模具列表 封装成 dict
     * @return
     */
    List<Dict> getModelVoList();



}

