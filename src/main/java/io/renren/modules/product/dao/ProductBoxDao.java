package io.renren.modules.product.dao;

import io.renren.modules.product.entity.ProductBoxEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.renren.modules.product.entity.vo.DictVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 
 * 
 * @author wsy
 * @email 389091912@qq.com
 * @date 2019-01-25 23:35:04
 */
@Mapper
public interface ProductBoxDao extends BaseMapper<ProductBoxEntity> {

    List<DictVo> selectBoxDictVoList();

}
