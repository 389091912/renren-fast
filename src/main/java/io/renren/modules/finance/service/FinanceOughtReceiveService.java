package io.renren.modules.finance.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.finance.entity.FinanceOughtReceiveEntity;

import java.util.Map;

/**
 * 财务—收款信息
 *
 * @author wsy
 * @email wsy@gmail.com
 * @date 2020-12-13 03:01:58
 */
public interface FinanceOughtReceiveService extends IService<FinanceOughtReceiveEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

