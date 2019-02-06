package io.renren.modules.product.dao;

import io.renren.modules.product.entity.BoxAddLeaveEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * 
 * @author wsy
 * @email 389091912@qq.com
 * @date 2019-01-30 02:41:40
 */
@Mapper
public interface BoxAddLeaveDao extends BaseMapper<BoxAddLeaveEntity> {

    BoxAddLeaveEntity  addBoxNumberCount(@Param( "boxNo" ) String boxNo);

    BoxAddLeaveEntity leaveBoxNumberCount(@Param( "boxNo" ) String boxNo);
}
