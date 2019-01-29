package io.renren.modules.product.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.product.entity.ProductRequireEntity;

import java.util.Map;

/**
 * 产品需求

 *
 * @author wsy
 * @email 389091912@qq.com
 * @date 2019-01-30 02:41:40
 */
public interface ProductRequireService extends IService<ProductRequireEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

