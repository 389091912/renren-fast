package io.renren.modules.product.service.impl;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import io.renren.modules.product.dao.*;
import io.renren.modules.product.entity.*;
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

                if (productOrderDetail.getBoxSupplyWay().equals( ProductOrderDetailEntity.BOX_SUPPLY_SELF )) {
                    List<ProductBoxEntity> productBoxList = productBoxDao.selectList( new EntityWrapper<ProductBoxEntity>().eq( "product_id", productOrderDetail.getProductId() ) );
                    if (CollectionUtils.isNotEmpty( productBoxList )) {
                        int enterBoxNumber = 0;
                       // int outBoxNumber = 0;
                        for (ProductBoxEntity productBoxEntity : productBoxList) {
                            System.out.println(productBoxEntity.getId());
                            BoxAddLeaveEntity boxAdd = boxAddLeaveDao.addBoxNumberCount2( String.valueOf( productBoxEntity.getId()  ),productOrderDetail.getBoxFactoryId());
                         //   BoxAddLeaveEntity boxLeave = boxAddLeaveDao.leaveBoxNumberCount2( String.valueOf( productBoxEntity.getId()  ),productOrderDetail.getBoxFactoryId() );
                            if (!StringUtils.isEmpty(boxAdd )) {
                                enterBoxNumber+=boxAdd.getAddBoxNumberCount();
                                System.out.println(enterBoxNumber);

                            }
//                            if(!StringUtils.isEmpty( boxLeave)){
//                                System.out.println(outBoxNumber);
//                                outBoxNumber+= boxLeave.getOutBoxNumberCount();
//                            }
////                        List<BoxAddLeaveEntity> addLeaveList = boxAddLeaveDao.selectList( new EntityWrapper<BoxAddLeaveEntity>().eq( "box_no", productBoxEntity.getBoxNo() ) );
////                        for (BoxAddLeaveEntity boxAddLeave : addLeaveList) {
////
////                            if (!StringUtils.isEmpty( boxAddLeave.getAddBoxNumber() )) {
////                                enterBoxNumber += boxAddLeave.getAddBoxNumber();
////                            }
////                            if(!StringUtils.isEmpty( boxAddLeave.getOutBoxNumberCount() )){
////
////                            }
////                            outBoxNumber += boxAddLeave.getOutBoxNumber();
////
                        }

                        productOrderDetail.setEntryBoxNumber( enterBoxNumber  );
                    }else {
                        productOrderDetail.setEntryBoxNumber(0);
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
