package io.renren.modules.product.controller;

import java.util.*;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.sun.org.apache.bcel.internal.generic.NEW;
import io.renren.common.utils.Dict;
import io.renren.common.utils.IntegerUtil;
import io.renren.modules.product.entity.*;
import io.renren.modules.product.entity.vo.ProductDetailVo;
import io.renren.modules.product.entity.vo.ProductOrderVo;
import io.renren.modules.product.service.*;
import io.renren.modules.sys.controller.AbstractController;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 
 *
 * @author wsy
 * @email 389091912@qq.com
 * @date 2019-01-25 23:35:04
 */
@RestController
@RequestMapping("product/productorder")
public class ProductOrderController extends AbstractController {
    @Autowired
    private ProductOrderService productOrderService;

    @Autowired
    private ProductOrderDetailService productOrderDetailService;

    @Autowired
    private ProductRequireService productRequireService;

    @Autowired
    private ProductBoxService productBoxService;

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private ProductModelService productModelService;

    @Autowired
    private OrderMessageService orderMessageService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("product:productorder:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = productOrderService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("product:productorder:info")
    public R info(@PathVariable("id") Integer id){
			ProductOrderEntity productOrder = productOrderService.selectById(id);
        List<ProductOrderDetailEntity> productOrderDetailList = productOrderDetailService.selectList( new EntityWrapper<ProductOrderDetailEntity>().eq( "order_id", productOrder.getId() ) );
        productOrder.setProductOrderDetailList( productOrderDetailList );
        return R.ok().put("productOrder", productOrder);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("product:productorder:save")
    public R save(@RequestBody ProductOrderEntity productOrder){
        Date date = new Date();
        productOrder.setEmployeeId( getUserId().intValue() );
        productOrder.setCreateTime( date );
        productOrder.setCreateUser( getUserId().intValue() );
        productOrder.setUpdateId( getUserId().intValue() );
        productOrder.setUpdateTime( date);
        productOrderService.insert(productOrder);


        if (!StringUtils.isEmpty( productOrder.getProductList() )) {
            List<ProductOrderDetailEntity> productDetailVos = JSONArray.parseArray( productOrder.getProductList(), ProductOrderDetailEntity.class );
            if (CollectionUtils.isNotEmpty( productDetailVos )) {

                for (ProductOrderDetailEntity productOrderDetail : productDetailVos) {
                    productOrderDetail.setOrderId( productOrder.getId() );
                    productOrderDetail.setCreateTime( date );
                    productOrderDetail.setCreateUser( getUserId().intValue() );
                    productOrderDetail.setUpdateTime( date );
                    productOrderDetail.setUpdateUser( getUserId().intValue() );
                    productOrderDetail.setStatus( ProductOrderDetailEntity.WAITER_PRODUCT);
                    //  ProductOrderDetailEntity productOrderDetail = new ProductOrderDetailEntity();
                    productOrderDetailService.insertOrUpdate( productOrderDetail );

                    OrderMessageEntity orderMessageEntity = new OrderMessageEntity();
                    orderMessageEntity.setUserId( getUserId().intValue() );
                    orderMessageEntity.setIsRead( OrderMessageEntity.IS_NOT_READ );
                    orderMessageEntity.setOrderDetailId( productOrderDetail.getId() );
                    orderMessageEntity.setCreateTime( date );
                    orderMessageEntity.setCreateUser( getUserId().intValue() );
                    orderMessageService.insert( orderMessageEntity );


                    List<ProductRequireEntity> productRequireList = productRequireService.selectList( new EntityWrapper<ProductRequireEntity>().eq( "product_id", productOrderDetail.getProductId() ) );
                    if (CollectionUtils.isNotEmpty( productRequireList )) {
                        ProductRequireEntity productRequire = productRequireList.get( 0 );
                        /**
                         * 计算实际需求的产品数量
                         */
                        Double count = productRequire.getProductRequireNumber();
                        Double productRequireNumber = count + productOrderDetail.getProductNumber();
                        productRequire.setProductRequireNumber(productRequireNumber );

                        /**
                         * 计算实际需求的纸箱数量
                         */
                        ProductBoxEntity productBox = productBoxService.selectById( productRequire.getBoxId() );
                        if (!StringUtils.isEmpty( productBox)) {
                            productRequire.setBoxId( productBox.getId());
                            if (productBox.getZhiShu() != 0) {
                                Integer boxNumber = IntegerUtil.integerNumber( productRequireNumber.intValue(), productBox.getZhiShu() );
                                productRequire.setBoxRequireNumber( boxNumber );
                            }
                        }

                        /**
                         * 拼接 订单
                         */


                        productRequireService.updateById( productRequire );

                    }else {
                        ProductInfoEntity productInfo = productInfoService.selectById( productOrderDetail.getProductId() );
                        ProductRequireEntity productRequire = new ProductRequireEntity();
                        productRequire.setProductId( productOrderDetail.getProductId() );
                        productRequire.setProductRequireNumber( productOrderDetail.getProductNumber() );
                        System.out.println(productInfo.toString());
                        /**
                         * 纸箱id
                         */

                        ProductBoxEntity productBox = productBoxService.selectById( productInfo.getCartonId() );

                        if (!StringUtils.isEmpty(productBox)) {
                        //    System.out.println(productBox);
                            productRequire.setBoxId( productBox.getId() );
                            if ("0".equals( productBox.getZhiShu() )) {
                                System.out.println( "ProductOrderController.save" );
                            }
                         /*   Integer boxNumber = IntegerUtil.integerNumber( productOrderDetail.getProductNumber(), productBox.getZhiShu() );
                            productRequire.setBoxRequireNumber( boxNumber );*/
                        }
                        //productRequire.setModelId( productInfo.get );
                        productRequireService.insert( productRequire );



                    }

                }
            }

        }


        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("product:productorder:update")
    public R update(@RequestBody ProductOrderEntity productOrder){

        if (!StringUtils.isEmpty( productOrder.getProductList() )) {

            List<ProductOrderDetailEntity> productDetailVos = JSONArray.parseArray( productOrder.getProductList(), ProductOrderDetailEntity.class );
            for (ProductOrderDetailEntity productDetail : productDetailVos) {
                System.out.println(productDetail.toString());
                productDetail.setOrderId( productOrder.getId() );
                if (StringUtils.isEmpty( productDetail.getId() )) {
                    productOrder.setStatus( ProductOrderDetailEntity.WAITER_PRODUCT );
                }
                productOrderDetailService.insertOrUpdate( productDetail );
            }
        }
			productOrderService.updateById(productOrder);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("product:productorder:delete")
    public R delete(@RequestBody Integer[] ids){

        for (Integer orderId : ids) {
            List<ProductOrderDetailEntity> productOrderDetailEntityList = productOrderDetailService.selectList( new EntityWrapper<ProductOrderDetailEntity>().eq( "order_id", orderId ) );

            if (CollectionUtils.isNotEmpty( productOrderDetailEntityList )) {
                List<Integer> orderDetailIdList = productOrderDetailEntityList.stream().map( ProductOrderDetailEntity::getId ).collect( Collectors.toList() );
                List<OrderMessageEntity> orderMessageEntityList = orderMessageService.selectList( new EntityWrapper<OrderMessageEntity>().in( "order_detail_id", orderDetailIdList ) );

                if (CollectionUtils.isNotEmpty( orderMessageEntityList )) {
                    List<Integer> orderIdList = orderMessageEntityList.stream().map( OrderMessageEntity::getId ).collect( Collectors.toList() );
                    orderMessageService.deleteBatchIds( orderIdList );
                }
            }


            productOrderDetailService.delete( new EntityWrapper<ProductOrderDetailEntity>().eq( "order_id", orderId ) );


        }
        productOrderService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

    /**
     *
     */
    @RequestMapping("/getProductOrder")
    public R getProductOrder(Integer productId){

        List<ProductOrderDetailEntity> productOrderDetailList = productOrderDetailService.selectList( new EntityWrapper<ProductOrderDetailEntity>().eq( "product_id", productId ) );

        List<ProductOrderVo> productOrderVoList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty( productOrderDetailList )) {
            for (ProductOrderDetailEntity productOrderDetail : productOrderDetailList) {
                ProductOrderVo productOrderVo = new ProductOrderVo();
                Integer boxSupplyWay = productOrderDetail.getBoxSupplyWay();
                ProductOrderEntity productOrderEntity = productOrderService.selectById( productOrderDetail.getOrderId() );
                productOrderVo.setBoxSupplyWay( boxSupplyWay );
                productOrderVo.setOrderId( productOrderEntity.getId() );
                productOrderVo.setOrderNo( productOrderEntity.getOrderNo() );
                productOrderVoList.add( productOrderVo );
            }
        }

        return R.ok().put( "productOrderList", productOrderVoList );
    }
    @RequestMapping("/getAllOrderList")
    public R getAllOrderList() {
        List<Dict> productOrderList = productOrderService.getProductOrderList();

        return R.ok().put( "productOrderList", productOrderList );
    }

    @RequestMapping("/getProductIdListByOrderId/{orderId}")
    public R getProductIdListByOrderId(@PathVariable Integer orderId) {
        System.out.println( orderId );
        List<ProductOrderDetailEntity> productOrderDetailList = productOrderDetailService.selectList( new EntityWrapper<ProductOrderDetailEntity>().eq( "order_id",orderId ) );
        List<Dict> productList = new ArrayList<>();

        if (CollectionUtils.isNotEmpty( productOrderDetailList )) {
            for (ProductOrderDetailEntity productOrderDetailEntity : productOrderDetailList) {
                Integer productId = productOrderDetailEntity.getProductId();
                if (!StringUtils.isEmpty( productId )) {
                    ProductInfoEntity productInfoEntity = productInfoService.selectById( productId );
                    if (!StringUtils.isEmpty( productInfoEntity )) {
                        Dict dict = new Dict();
                        dict.setId( productInfoEntity.getId() );
                        dict.setName( productInfoEntity.getProductName() );

                        productList.add( dict );
                    }
                }

            }

        }
        return R.ok().put( "productList", productList );
    }





}
