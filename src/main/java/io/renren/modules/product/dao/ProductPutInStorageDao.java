package io.renren.modules.product.dao;

import io.renren.modules.product.entity.ProductPutInStorageEntity;
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
public interface ProductPutInStorageDao extends BaseMapper<ProductPutInStorageEntity> {

    /**
     * 统计入库总数
     * @param params
     * @return
     */
    Integer productNumberCount(@Param("params") Map<String, Object> params);


}
