package io.renren.modules.product.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.Dict;
import io.renren.common.utils.PageUtils;
import io.renren.modules.product.entity.BoxAddLeaveEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author wsy
 * @email 389091912@qq.com
 * @date 2019-01-30 02:41:40
 */
public interface BoxAddLeaveService extends IService<BoxAddLeaveEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<Dict> getAllBoxAddLeave();
}

