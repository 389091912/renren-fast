package io.renren.modules.product.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.product.entity.ProductInfoEntity;

import java.util.Map;

/**
 * 
 *
 * @author wsy
 * @email 389091912@qq.com
 * @date 2019-01-14 00:02:12
 */
public interface ProductInfoService extends IService<ProductInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

