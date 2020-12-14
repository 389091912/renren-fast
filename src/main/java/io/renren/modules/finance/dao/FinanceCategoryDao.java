package io.renren.modules.finance.dao;

import io.renren.modules.finance.entity.FinanceCategoryEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 财务类别
 * 
 * @author wsy
 * @email wsy@gmail.com
 * @date 2020-12-13 03:01:58
 */
@Mapper
public interface FinanceCategoryDao extends BaseMapper<FinanceCategoryEntity> {
	
}
