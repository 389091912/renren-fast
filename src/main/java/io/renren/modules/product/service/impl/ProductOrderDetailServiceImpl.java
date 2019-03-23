package io.renren.modules.product.service.impl;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import io.renren.modules.product.dao.*;
import io.renren.modules.product.entity.ProductOrderEntity;
import io.renren.modules.sys.entity.SysUserEntity;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        SysUserEntity sysUserEntity = (SysUserEntity) SecurityUtils.getSubject().getPrincipal();

        String  productWeight = (String) params.get( "key" );
        String productId = (String) params.get( "productId" );
        Page<ProductOrderDetailEntity> page = new Page<>();
        List<Integer> orderIdList = new ArrayList<>();
        if (SysUserEntity.SALESMAN == sysUserEntity.getType()) {
            List<ProductOrderEntity> orderEntityList = productOrderDao.selectList( new EntityWrapper<ProductOrderEntity>().eq( "employee_id", sysUserEntity.getUserId() ) );
            orderIdList = orderEntityList.stream().map( ProductOrderEntity::getId ).collect( Collectors.toList() );

        }


        if (!StringUtils.isEmpty( productWeight.trim() )) {
            if (SysUserEntity.SALESMAN == sysUserEntity.getType()) {
                if(!StringUtils.isEmpty( productId )){
                    page = this.selectPage(
                            new Query<ProductOrderDetailEntity>( params ).getPage(),
                            new EntityWrapper<ProductOrderDetailEntity>()
                                    .in( "order_id", orderIdList )
                                    .eq( "product_id", productId )
                                    .between( "product_weight", (Integer.parseInt( productWeight ) - 5), (Integer.parseInt( productWeight ) + 5) )
                                    .orderBy( "status", true )
                                    .orderBy( "create_time", false )


                    );
                }else {
                    page = this.selectPage(
                            new Query<ProductOrderDetailEntity>(params).getPage(),
                            new EntityWrapper<ProductOrderDetailEntity>()
                                    .in( "order_id",orderIdList )
                                    .between( "product_weight",(Integer.parseInt(productWeight )-5),(Integer.parseInt(productWeight  )+5) )
                                    .orderBy( "status",true )
                                    .orderBy( "create_time", false )


                    );
                }

            }
            if((SysUserEntity.SYSTEM_USER==sysUserEntity.getType())||(SysUserEntity.MANAGER==sysUserEntity.getType())){
              if(!StringUtils.isEmpty( productId )){
                  page = this.selectPage(
                          new Query<ProductOrderDetailEntity>( params ).getPage(),
                          new EntityWrapper<ProductOrderDetailEntity>()
                                  .eq( "product_id", productId )
                                  .between( "product_weight", (Integer.parseInt( productWeight ) - 5), (Integer.parseInt( productWeight ) + 5) )
                                  .orderBy( "status", true )
                                  .orderBy( "create_time", false )


                  );
                }else {
                  page = this.selectPage(
                          new Query<ProductOrderDetailEntity>( params ).getPage(),
                          new EntityWrapper<ProductOrderDetailEntity>()
                                  .between( "product_weight", (Integer.parseInt( productWeight ) - 5), (Integer.parseInt( productWeight ) + 5) )
                                  .orderBy( "status", true )
                                  .orderBy( "create_time", false )


                  );
              }
            }

        }else {
            if (SysUserEntity.SALESMAN == sysUserEntity.getType()) {
                if (!StringUtils.isEmpty( productId )) {

                    page = this.selectPage(
                            new Query<ProductOrderDetailEntity>( params ).getPage(),
                            new EntityWrapper<ProductOrderDetailEntity>()
                                    .eq( "product_id", productId )
                                    .in( "order_id", orderIdList )
                                    .orderBy( "status", true )
                                    .orderBy( "create_time", false )


                    );
                }else {
                    page = this.selectPage(
                            new Query<ProductOrderDetailEntity>( params ).getPage(),
                            new EntityWrapper<ProductOrderDetailEntity>()
                                    .in( "order_id", orderIdList )
                                    .orderBy( "status", true )
                                    .orderBy( "create_time", false )


                    );
                }
            }

            if((SysUserEntity.SYSTEM_USER==sysUserEntity.getType())||(SysUserEntity.MANAGER==sysUserEntity.getType())) {
                if (!StringUtils.isEmpty( productId )) {
                    page = this.selectPage(
                            new Query<ProductOrderDetailEntity>( params ).getPage(),
                            new EntityWrapper<ProductOrderDetailEntity>()
                                    .eq(  "product_id",productId)
                                    .orderBy( "status", true )
                                    .orderBy( "create_time", false )

                    );
                }else {
                    page = this.selectPage(
                            new Query<ProductOrderDetailEntity>( params ).getPage(),
                            new EntityWrapper<ProductOrderDetailEntity>()
                                    .orderBy( "status", true )
                                    .orderBy( "create_time", false )

                    );
                }
            }

        }


        if (CollectionUtils.isNotEmpty( page.getRecords() )) {
            for (ProductOrderDetailEntity productOrderDetail : page.getRecords()) {
                productOrderDetail.setProductName((!StringUtils.isEmpty( productInfoDao.selectById( productOrderDetail.getProductId() ) ))?
                        productInfoDao.selectById( productOrderDetail.getProductId() ).getProductName():
                        null  );

                if (productOrderDetail.getStatus() .equals( ProductOrderDetailEntity.WAITER_PRODUCT ) ) {
                    if (!StringUtils.isEmpty( productOrderDetail.getPlanId() )) {
                        productOrderDetail.setStatus( ProductOrderDetailEntity.PROCESS_PRODUCT );
                    }
                }
                ProductOrderEntity productOrderEntity = productOrderDao.selectById( productOrderDetail.getOrderId() );
                if(!StringUtils.isEmpty(productOrderEntity)){
                    productOrderDetail.setOrderNo( productOrderEntity.getOrderNo() );
                    productOrderDetail.setOrderStatus( productOrderEntity.getStatus() );
                    productOrderDetail.setRemark( productOrderEntity.getRemark() );

                }

            }
        }
        return new PageUtils(page);
    }

}
