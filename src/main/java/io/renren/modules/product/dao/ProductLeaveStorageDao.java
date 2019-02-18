package io.renren.modules.product.dao;

import io.renren.modules.product.entity.ProductLeaveStorageEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * 
 * 
 * @author wsy
 * @email 389091912@qq.com
 * @date 2019-01-25 23:35:04
 */
@Mapper
public interface ProductLeaveStorageDao extends BaseMapper<ProductLeaveStorageEntity> {

    /**
     * 统计商品出口数量
     * @param params
     * @return
     */
    Integer productLeaveNumberCount(@Param("params") Map<String, Object> params);

}
