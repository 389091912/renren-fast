package io.renren.modules.product.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.product.entity.ModelWarehouseEntity;

import java.util.Map;

/**
 * 
 *
 * @author wsy
 * @email 389091912@qq.com
 * @date 2019-03-02 11:00:17
 */
public interface ModelWarehouseService extends IService<ModelWarehouseEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

