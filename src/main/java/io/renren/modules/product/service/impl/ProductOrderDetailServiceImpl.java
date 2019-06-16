package io.renren.modules.product.service.impl;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.sun.org.apache.bcel.internal.generic.NEW;
import io.renren.modules.product.dao.*;
import io.renren.modules.product.entity.*;
import io.renren.modules.sys.dao.SysUserDao;
import io.renren.modules.sys.entity.SysUserEntity;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.BeanWrapper;
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

    @Autowired
    private BoxFactoryDao boxFactoryDao;

    @Autowired
    private BoxAddLeaveDao boxAddLeaveDao;

    @Autowired
    private ProductBoxDao productBoxDao;


    @Autowired
    private OrderMessageDao orderMessageDao;

    @Autowired
    private OrderUserMessageDao orderUserMessageDao;

    @Autowired
    private ProductModelDao productModelDao;

    @Autowired
    private SysUserDao sysUserDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        SysUserEntity sysUserEntity = (SysUserEntity) SecurityUtils.getSubject().getPrincipal();

        String  productWeight = (String) params.get( "key" );
        String productId = (String) params.get( "productId" );
        Page<ProductOrderDetailEntity> page = new Page<>();
        List<Integer> orderIdList = new ArrayList<>();
        if (SysUserEntity.SALESMAN .equals( sysUserEntity.getType() ) ) {
            List<ProductOrderEntity> orderEntityList = productOrderDao.selectList( new EntityWrapper<ProductOrderEntity>().eq( "employee_id", sysUserEntity.getUserId() ) );

            orderIdList = orderEntityList.stream().map( ProductOrderEntity::getId ).collect( Collectors.toList() );

            if (CollectionUtils.isEmpty( orderIdList )) {
                return new PageUtils(page);
            }
        }


        if (!StringUtils.isEmpty( productWeight.trim() )) {
            if (SysUserEntity.SALESMAN .equals( sysUserEntity.getType() ) ) {
                if(!StringUtils.isEmpty( productId )){
                    page = this.selectPage(
                            new Query<ProductOrderDetailEntity>( params ).getPage(),
                            new EntityWrapper<ProductOrderDetailEntity>()
                                    .in( "order_id", orderIdList )
                                    .eq( "product_id", productId )
                                    .between( "product_weight", (Integer.parseInt( productWeight ) - 5), (Integer.parseInt( productWeight ) + 5) )
                                    .orderBy( "status", true )
                                    .orderBy( "create_time", false )
                                    .orderBy( "product_weight", false )


                    );
                }else {
                    page = this.selectPage(
                            new Query<ProductOrderDetailEntity>(params).getPage(),
                            new EntityWrapper<ProductOrderDetailEntity>()
                                    .in( "order_id",orderIdList )
                                    .between( "product_weight",(Integer.parseInt(productWeight )-5),(Integer.parseInt(productWeight  )+5) )
                                    .orderBy( "status",true )
                                    .orderBy( "create_time", false )
                                    .orderBy( "product_weight", false )


                    );
                }

            }
            if((SysUserEntity.SYSTEM_USER.equals( sysUserEntity.getType()))||(SysUserEntity.MANAGER.equals( sysUserEntity.getType()) )){
              if(!StringUtils.isEmpty( productId )){
                  page = this.selectPage(
                          new Query<ProductOrderDetailEntity>( params ).getPage(),
                          new EntityWrapper<ProductOrderDetailEntity>()
                                  .eq( "product_id", productId )
                                  .between( "product_weight", (Integer.parseInt( productWeight ) - 5), (Integer.parseInt( productWeight ) + 5) )
                                  .orderBy( "status", true )
                                  .orderBy( "create_time", false )
                                  .orderBy( "product_weight", false )


                  );
                }else {
                  page = this.selectPage(
                          new Query<ProductOrderDetailEntity>( params ).getPage(),
                          new EntityWrapper<ProductOrderDetailEntity>()
                                  .between( "product_weight", (Integer.parseInt( productWeight ) - 5), (Integer.parseInt( productWeight ) + 5) )
                                  .orderBy( "status", true )
                                  .orderBy( "create_time", false )
                                  .orderBy( "product_weight", false )


                  );
              }
            }

        }else {
            if (SysUserEntity.SALESMAN .equals( sysUserEntity.getType() )) {
                if (!StringUtils.isEmpty( productId )) {

                    page = this.selectPage(
                            new Query<ProductOrderDetailEntity>( params ).getPage(),
                            new EntityWrapper<ProductOrderDetailEntity>()
                                    .eq( "product_id", productId )
                                    .in( "order_id", orderIdList )
                                    .orderBy( "status", true )
                                    .orderBy( "create_time", false )
                                    .orderBy( "product_weight", false )


                    );
                }else {
                    page = this.selectPage(
                            new Query<ProductOrderDetailEntity>( params ).getPage(),
                            new EntityWrapper<ProductOrderDetailEntity>()
                                    .in( "order_id", orderIdList )
                                    .orderBy( "status", true )
                                    .orderBy( "create_time", false )
                                    .orderBy( "product_weight", false )


                    );
                }
            }

            if((SysUserEntity.SYSTEM_USER.equals( sysUserEntity.getType() ))||(SysUserEntity.MANAGER.equals(sysUserEntity.getType()  ))) {
                if (!StringUtils.isEmpty( productId )) {
                    page = this.selectPage(
                            new Query<ProductOrderDetailEntity>( params ).getPage(),
                            new EntityWrapper<ProductOrderDetailEntity>()
                                    .eq(  "product_id",productId)
                                    .orderBy( "status", true )
                                    .orderBy( "create_time", false )
                                    .orderBy( "product_weight", false )

                    );
                }else {
                    page = this.selectPage(
                            new Query<ProductOrderDetailEntity>( params ).getPage(),
                            new EntityWrapper<ProductOrderDetailEntity>()
                                    .orderBy( "status", true )
                                    .orderBy( "create_time", false )
                                    .orderBy( "product_weight", false )

                    );
                }
            }

        }


        if (CollectionUtils.isNotEmpty( page.getRecords() )) {
            for (ProductOrderDetailEntity productOrderDetail : page.getRecords()) {
                ProductInfoEntity productInfoEntity = productInfoDao.selectById( productOrderDetail.getProductId() );
                productOrderDetail.setProductName((!StringUtils.isEmpty( productInfoEntity ))?
                        productInfoEntity.getProductName():
                        null  );

                if (!StringUtils.isEmpty( productOrderDetail.getBoxFactoryId() )) {
                    BoxFactoryEntity boxFactoryEntity = boxFactoryDao.selectById( productOrderDetail.getBoxFactoryId() );
                    productOrderDetail.setFactoryName( !StringUtils.isEmpty( boxFactoryEntity ) ? boxFactoryEntity.getFactoryName() : null );
                }

                if (productOrderDetail.getStatus() .equals( ProductOrderDetailEntity.WAITER_PRODUCT ) ) {
                    if (!StringUtils.isEmpty( productOrderDetail.getPlanId() )) {
                        productOrderDetail.setStatus( ProductOrderDetailEntity.PROCESS_PRODUCT );
                    }
                }

                if (!StringUtils.isEmpty( productOrderDetail.getModelId() )) {
                    ProductModelEntity productModelEntity = productModelDao.selectById( productOrderDetail.getModelId() );
                    if (!StringUtils.isEmpty( productModelEntity )) {
                        productOrderDetail.setModelNo( productModelEntity.getModelNo() );

                    }
                }

                if(!StringUtils.isEmpty( productOrderDetail.getCreateUser() )){
                    SysUserEntity userEntity = sysUserDao.selectById( productOrderDetail.getCreateUser() );
                    if (!StringUtils.isEmpty( userEntity )) {
                        productOrderDetail.setEmployeeName( userEntity.getRealName() );

                    }
                }




                if((SysUserEntity.SYSTEM_USER.equals( sysUserEntity.getType()) )||(SysUserEntity.MANAGER.equals(sysUserEntity.getType())  )) {

                    List<OrderMessageEntity> orderMessageEntityList = orderMessageDao.selectList( new EntityWrapper<OrderMessageEntity>().eq( "order_detail_id", productOrderDetail.getId() ) );
                    if (CollectionUtils.isNotEmpty( orderMessageEntityList )) {
                        OrderMessageEntity orderMessageEntity = orderMessageEntityList.get( 0 );
                        if (!StringUtils.isEmpty( orderMessageEntity )) {
                            List<OrderUserMessageEntity> orderUserMessageEntities = orderUserMessageDao.selectList(
                                    new EntityWrapper<OrderUserMessageEntity>()
                                            .eq( "user_id", sysUserEntity.getUserId() )
                                            .eq( "order_msg_id", orderMessageEntity.getOrderDetailId() )
                            );
                            if (CollectionUtils.isEmpty( orderUserMessageEntities )) {
                                productOrderDetail.setIsRead( OrderUserMessageEntity.IS_NOT_READ );

                            }
                        }
                    }


                }

                    if(!StringUtils.isEmpty( productOrderDetail.getBoxSupplyWay() )){

                        if (productOrderDetail.getBoxSupplyWay().equals( ProductOrderDetailEntity.BOX_SUPPLY_SELF )) {

                            if (!StringUtils.isEmpty( productOrderDetail.getOrderId() ) && !StringUtils.isEmpty( productOrderDetail.getProductId() )) {
                                System.out.println( productOrderDetail.getOrderId() );
                                System.out.println( productOrderDetail.getProductId() );
                                Integer enterBoxNumber = boxAddLeaveDao.countAddBoxNumberByOrderIdAndProductId( productOrderDetail.getOrderId(), productOrderDetail.getProductId() );
                                System.out.println( enterBoxNumber );
                                if (!StringUtils.isEmpty( enterBoxNumber )) {
                                    productOrderDetail.setEntryBoxNumber(enterBoxNumber);
                                }else {
                                    productOrderDetail.setEntryBoxNumber(0);
                                }

                            }


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
