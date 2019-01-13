package io.renren.modules.product.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.product.dao.ProductPutInStorageDao;
import io.renren.modules.product.entity.ProductPutInStorageEntity;
import io.renren.modules.product.service.ProductPutInStorageService;


@Service("productPutInStorageService")
public class ProductPutInStorageServiceImpl extends ServiceImpl<ProductPutInStorageDao, ProductPutInStorageEntity> implements ProductPutInStorageService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ProductPutInStorageEntity> page = this.selectPage(
                new Query<ProductPutInStorageEntity>(params).getPage(),
                new EntityWrapper<ProductPutInStorageEntity>()
        );

        return new PageUtils(page);
    }

}
