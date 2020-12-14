package io.renren.modules.product.dao;

import io.renren.modules.product.entity.BoxAddLeaveEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.renren.modules.product.entity.vo.DictVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

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

    BoxAddLeaveEntity  addBoxNumberCount1(@Param("params")Map<String,Object> params);

    BoxAddLeaveEntity addBoxNumberCount2(@Param("boxNo") String boxNo, @Param("factoryId") Integer factoryId);

    BoxAddLeaveEntity leaveBoxNumberCount(@Param( "boxNo" ) String boxNo);

    BoxAddLeaveEntity leaveBoxNumberCount1(@Param("params")Map<String,Object> params);

    BoxAddLeaveEntity leaveBoxNumberCount2(@Param( "boxNo" )  String boxNo, @Param("factoryId") Integer factoryId);

    Integer countAddBoxNumberByOrderIdAndProductId(@Param("orderId") Integer orderId, @Param("productId") Integer productId);



}
