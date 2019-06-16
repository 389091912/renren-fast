package io.renren.modules.product.service.impl;

import io.renren.modules.product.dao.ProductBoxDao;
import io.renren.modules.product.dao.ProductInfoDao;
import io.renren.modules.product.dao.ProductOrderDao;
import io.renren.modules.product.entity.ProductBoxEntity;
import io.renren.modules.product.entity.ProductInfoEntity;
import io.renren.modules.product.entity.ProductOrderEntity;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.util.StringUtils;

/**
 * @author wsy
 */
@Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
@Service("productLeaveStorageService")
public class ProductLeaveStorageServiceImpl extends ServiceImpl<ProductLeaveStorageDao, ProductLeaveStorageEntity> implements ProductLeaveStorageService {

    @Autowired
    private ProductInfoDao productInfoDao;

    @Autowired
    private ProductOrderDao productOrderDao;

    @Autowired
    private ProductBoxDao productBoxDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        String  key = (String) params.get( "key" );
        EntityWrapper<ProductLeaveStorageEntity> entityWrapper = new EntityWrapper<>();

        if (StringUtils.isEmpty(key)) {
            entityWrapper.orderBy( "salesman", false )
                         .orderBy( "out_time", false );
        }else {
            entityWrapper.like( "product_name", key )
                    .or().like( "salesman", key )
                    .or().like( "customer", key )
                    .orderBy( "salesman", false )
                    .orderBy( "out_time", false );
        }


        Page<ProductLeaveStorageEntity> page = this.selectPage(
                new Query<ProductLeaveStorageEntity>( params ).getPage(),
                entityWrapper
        );




        return new PageUtils(page);
    }

}
