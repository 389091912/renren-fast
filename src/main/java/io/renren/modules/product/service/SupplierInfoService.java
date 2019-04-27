package io.renren.modules.product.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.Dict;
import io.renren.common.utils.PageUtils;
import io.renren.modules.product.entity.SupplierInfoEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author wsy
 * @email 389091912@qq.com
 * @date 2019-04-26 00:15:25
 */
public interface SupplierInfoService extends IService<SupplierInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 查询所有供货商信息
     * @return
     */
    List<Dict> selectAllSupplierInfo();
}

