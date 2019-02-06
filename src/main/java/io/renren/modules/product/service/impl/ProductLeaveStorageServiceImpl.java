package io.renren.modules.product.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.product.dao.ProductLeaveStorageDao;
import io.renren.modules.product.entity.ProductLeaveStorageEntity;
import io.renren.modules.product.service.ProductLeaveStorageService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author wsy
 */
@Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
@Service("productLeaveStorageService")
public class ProductLeaveStorageServiceImpl extends ServiceImpl<ProductLeaveStorageDao, ProductLeaveStorageEntity> implements ProductLeaveStorageService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ProductLeaveStorageEntity> page = this.selectPage(
                new Query<ProductLeaveStorageEntity>(params).getPage(),
                new EntityWrapper<ProductLeaveStorageEntity>().orderBy( "create_time", false )
        );

        return new PageUtils(page);
    }

}
