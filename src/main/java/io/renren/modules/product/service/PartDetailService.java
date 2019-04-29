package io.renren.modules.product.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.product.entity.PartDetailEntity;

import java.util.Map;

/**
 * 
 *
 * @author wsy
 * @email 389091912@qq.com
 * @date 2019-04-27 16:30:52
 */
public interface PartDetailService extends IService<PartDetailEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

