package io.renren.modules.finance.dao;

import io.renren.modules.finance.entity.FinanceRepaymentInfoEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 财务-个人还款
 * 
 * @author wsy
 * @email wsy@gmail.com
 * @date 2021-01-29 20:45:18
 */
@Mapper
public interface FinanceRepaymentInfoDao extends BaseMapper<FinanceRepaymentInfoEntity> {
	
}
