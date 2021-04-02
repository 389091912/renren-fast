package io.renren.modules.finance.dao;

import io.renren.modules.finance.entity.TbUserEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户
 * 
 * @author wsy
 * @email wsy@gmail.com
 * @date 2021-01-29 21:20:29
 */
@Mapper
public interface TbUserDao extends BaseMapper<TbUserEntity> {
	
}
