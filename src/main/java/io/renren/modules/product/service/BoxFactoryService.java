package io.renren.modules.product.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.Dict;
import io.renren.common.utils.PageUtils;
import io.renren.modules.product.entity.BoxFactoryEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author wsy
 * @email 389091912@qq.com
 * @date 2019-02-09 17:04:54
 */
public interface BoxFactoryService extends IService<BoxFactoryEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<Dict> getAllBoxFactoryList();
}

