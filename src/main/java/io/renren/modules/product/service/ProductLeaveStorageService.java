package io.renren.modules.product.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.product.entity.ProductLeaveStorageEntity;

import java.util.Map;

/**
 * 
 *
 * @author wsy
 * @email 389091912@qq.com
 * @date 2019-01-25 23:35:04
 */
public interface ProductLeaveStorageService extends IService<ProductLeaveStorageEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

