package io.renren.modules.product.service.impl;

import io.renren.common.utils.Dict;
import io.renren.modules.product.dao.*;
import io.renren.modules.product.entity.ProductOrderDetailEntity;
import io.renren.modules.sys.dao.SysUserDao;
import io.renren.modules.sys.entity.SysUserEntity;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.product.entity.ProductOrderEntity;
import io.renren.modules.product.service.ProductOrderService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * @author wsy
 */
@Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
@Service("productOrderService")
public class ProductOrderServiceImpl extends ServiceImpl<ProductOrderDao, ProductOrderEntity> implements ProductOrderService {

    @Autowired
    private ProductInfoDao productInfoDao;

    @Autowired
    private ProductOrderDao productOrderDao;

    @Autowired
    private ProductOrderDetailDao productOrderDetailDao;

    @Autowired
    private ProductModelDao productModelDao;


    @Autowired
    private SysUserDao sysUserDao;

    @Autowired
    private ProductRequireDao productRequireDao;




    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        SysUserEntity sysUserEntity = (SysUserEntity) SecurityUtils.getSubject().getPrincipal();
        String key = (String) params.get( "key" );
        EntityWrapper<ProductOrderEntity> productOrderWrapper = new EntityWrapper<>();
        Page<ProductOrderEntity> page = new Page<>();
        if (SysUserEntity.SALESMAN == sysUserEntity.getType()) {
            if (StringUtils.isEmpty( key )) {
                page= this.selectPage(
                        new Query<ProductOrderEntity>( params ).getPage(),
                        productOrderWrapper
                                .eq( "employee_id", sysUserEntity.getUserId() )
                                .orderBy( "status", true )
                                .orderBy( "update_time", false )
                );
            }else {
                page= this.selectPage(
                        new Query<ProductOrderEntity>( params ).getPage(),
                        productOrderWrapper
                                .eq( "employee_id", sysUserEntity.getUserId() )
                                .or().like( "order_no",key )
                                .or().like( "customer",key )
                                .orderBy( "status", true )
                                .orderBy( "update_time", false )
                );
            }


        }
        if((SysUserEntity.SYSTEM_USER==sysUserEntity.getType())||(SysUserEntity.MANAGER==sysUserEntity.getType())){
            if(StringUtils.isEmpty( key )){
                page = this.selectPage(
                        new Query<ProductOrderEntity>( params ).getPage(),
                        new EntityWrapper<ProductOrderEntity>()
                                .orderBy( "status", true )
                                .orderBy( "update_time", false )
                );
            }else {
             page = this.selectPage(
                    new Query<ProductOrderEntity>( params ).getPage(),
                    new EntityWrapper<ProductOrderEntity>()
                            .or().like( "order_no",key )
                            .or().like( "customer",key )
                            .orderBy( "status", true )
                            .orderBy( "update_time", false )
            );
            }

        }


        if (CollectionUtils.isNotEmpty( page.getRecords() )) {
            for (ProductOrderEntity productOrderEntity : page.getRecords()) {

                productOrderEntity.setEmployeeName( StringUtils.isEmpty(  sysUserDao.selectById( productOrderEntity.getEmployeeId() ) ) ?null:sysUserDao.selectById( productOrderEntity.getEmployeeId() ).getRealName() );

                List<ProductOrderDetailEntity> productOrderDetailEntityList = productOrderDetailDao.selectList( new EntityWrapper<ProductOrderDetailEntity>().eq( "order_id", productOrderEntity.getId() ) );
                if (CollectionUtils.isNotEmpty( productOrderDetailEntityList )) {
                    Set<Integer> orderStatus = new HashSet<>();
                    orderStatus =productOrderDetailEntityList.stream().map( ProductOrderDetailEntity::getStatus ).collect( Collectors.toSet() );

                    System.out.println(orderStatus.size());
                    if (orderStatus.size() == 1) {
                        for (Integer i : orderStatus) {
                            if (!StringUtils.isEmpty( i )) {
                                if (i == 2) {
                                    productOrderEntity.setStatus( 4 );
                                    productOrderDao.updateById( productOrderEntity );
                                }
                            }

                        }
                    }


                }


            }
        }

        return new PageUtils(page);
    }

    @Override
    public void addOrderDetailAndRequire(ProductOrderEntity productOrderEntity) {

    }

    @Override
    public List<Dict> selectOrderIdByProductId(Integer productId) {
        List<ProductOrderDetailEntity> productOrderDetailList = productOrderDetailDao.selectList( new EntityWrapper<ProductOrderDetailEntity>().eq( "product_id", productId ) );
        List<Dict> dictList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty( productOrderDetailList )) {
            for (ProductOrderDetailEntity productOrderDetail : productOrderDetailList) {
                Dict dict = new Dict();
                ProductOrderEntity productOrderEntity = productOrderDao.selectById( productOrderDetail.getOrderId() );
                dict.setId( productOrderEntity.getId() );
                dict.setName( productOrderEntity.getOrderNo() );
//                Integer boxSupplyWay = productOrderDetail.getBoxSupplyWay();
                dictList.add( dict );
            }
        }
        return dictList;
    }

    @Override
    public List<Dict> getProductOrderList() {
        List<ProductOrderEntity> productOrderEntities = productOrderDao.selectList( new EntityWrapper<ProductOrderEntity>() );
        List<Dict> dictList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty( productOrderEntities )) {
            for (ProductOrderEntity productOrderEntity : productOrderEntities) {
                Dict dict = new Dict();
                dict.setId( productOrderEntity.getId() );
                dict.setName( productOrderEntity.getOrderNo() );
                dictList.add( dict );
            }


        }
        return dictList;
    }

}
