package io.renren.modules.product.dao;

import io.renren.modules.product.entity.PartDetailEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * 
 * @author wsy
 * @email 389091912@qq.com
 * @date 2019-04-27 16:30:52
 */
@Mapper
public interface PartDetailDao extends BaseMapper<PartDetailEntity> {



    Double countNumber(@Param( "partId" )Integer partId);

    Double outCountNumber(@Param( "partId" )Integer partId);
}
