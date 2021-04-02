package io.renren.modules.finance.dao;

import io.renren.modules.finance.entity.FinanceBorrowerInfoEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 财务-个人借款
 * 
 * @author wsy
 * @email wsy@gmail.com
 * @date 2021-01-29 20:45:18
 */
@Mapper
public interface FinanceBorrowerInfoDao extends BaseMapper<FinanceBorrowerInfoEntity> {
	
}
