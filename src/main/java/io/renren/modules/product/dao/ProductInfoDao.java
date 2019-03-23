package io.renren.modules.product.dao;

import io.renren.modules.product.entity.ProductInfoEntity;
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
public interface ProductInfoDao extends BaseMapper<ProductInfoEntity> {
    void updateProductInfoIsNullByDesignId(@Param("designId") Integer designId);
}
