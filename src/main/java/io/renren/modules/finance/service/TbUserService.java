package io.renren.modules.finance.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.finance.entity.TbUserEntity;

import java.util.Map;

/**
 * 用户
 *
 * @author wsy
 * @email wsy@gmail.com
 * @date 2021-01-29 21:20:29
 */
public interface TbUserService extends IService<TbUserEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

