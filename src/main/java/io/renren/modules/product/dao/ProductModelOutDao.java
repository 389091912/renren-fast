package io.renren.modules.product.dao;

import io.renren.modules.product.entity.ProductModelOutEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * 
 * @author wsy
 * @email 389091912@qq.com
 * @date 2019-01-25 23:35:04
 */
@Mapper
public interface ProductModelOutDao extends BaseMapper<ProductModelOutEntity> {

    /**
     *  计算模具入库记录
     * @param modelNo
     * @return
     */
    ProductModelOutEntity selectModelAddCount(@Param( "modelNo" )Integer modelNo);

    /**
     * 计算模具出库记录
     * @param modelNo
     * @return
     */
    ProductModelOutEntity selectModelOutCount(@Param( "modelNo" ) Integer modelNo);

}
