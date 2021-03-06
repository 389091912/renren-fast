package io.renren.modules.product.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.product.entity.ModelMessageEntity;

import java.util.Map;

/**
 * 
 *
 * @author wsy
 * @email 389091912@qq.com
 * @date 2019-04-29 16:12:11
 */
public interface ModelMessageService extends IService<ModelMessageEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

