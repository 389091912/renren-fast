package io.renren.modules.finance.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.finance.entity.FinanceCategoryEntity;

import java.util.Map;

/**
 * 财务类别
 *
 * @author wsy
 * @email wsy@gmail.com
 * @date 2020-12-13 03:01:58
 */
public interface FinanceCategoryService extends IService<FinanceCategoryEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

