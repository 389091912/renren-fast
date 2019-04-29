package io.renren.modules.product.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.Dict;
import io.renren.common.utils.PageUtils;
import io.renren.modules.product.entity.PartInfoEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author wsy
 * @email 389091912@qq.com
 * @date 2019-04-27 16:15:54
 */
public interface PartInfoService extends IService<PartInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 查询所有的配件信息
     * @return
     */
    List<Dict> selectAllPartList();

}

