package io.renren.modules.product.service.impl;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import io.renren.modules.product.dao.*;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.product.entity.ProductOrderDetailEntity;
import io.renren.modules.product.service.ProductOrderDetailService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * @author wsy
 */
@Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
@Service("productOrderDetailService")
public class ProductOrderDetailServiceImpl extends ServiceImpl<ProductOrderDetailDao, ProductOrderDetailEntity> implements ProductOrderDetailService {

    @Autowired
    private ProductInfoDao productInfoDao;

    @Autowired
    private ProductOrderDao productOrderDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String  productWeight = (String) params.get( "key" );
        Page<ProductOrderDetailEntity> page = new Page<>();
        if (!StringUtils.isEmpty( productWeight.trim() )) {
            page = this.selectPage(
                    new Query<ProductOrderDetailEntity>(params).getPage(),
                    new EntityWrapper<ProductOrderDetailEntity>()
                            .eq("product_weight",productWeight).or()
                            .between( "product_weight",(Integer.parseInt(productWeight )-5),(Integer.parseInt(productWeight  )+5) )
                            .orderBy( "create_time", false )
            );

        }else {
            page = this.selectPage(
                    new Query<ProductOrderDetailEntity>(params).getPage(),
                    new EntityWrapper<ProductOrderDetailEntity>()
                            .orderBy( "create_time", false )
            );
        }


        if (CollectionUtils.isNotEmpty( page.getRecords() )) {
            for (ProductOrderDetailEntity productOrderDetail : page.getRecords()) {
                productOrderDetail.setProductName((!StringUtils.isEmpty( productInfoDao.selectById( productOrderDetail.getProductId() ) ))?
                        productInfoDao.selectById( productOrderDetail.getProductId() ).getProductName():
                        null  );

                productOrderDetail.setOrderNo( (!StringUtils.isEmpty(  productOrderDao.selectById( productOrderDetail.getOrderId() ) ))?
                        productOrderDao.selectById( productOrderDetail.getOrderId() ).getOrderNo():
                        null );
            }
        }
        return new PageUtils(page);
    }

}
