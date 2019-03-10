package io.renren.modules.product.controller;

import java.util.Arrays;
import java.util.Map;

import io.renren.modules.product.dao.ProductInfoDao;
import io.renren.modules.product.entity.ProductInfoEntity;
import io.renren.modules.product.entity.ProductOrderEntity;
import io.renren.modules.product.service.ProductInfoService;
import io.renren.modules.product.service.ProductOrderService;
import io.renren.modules.sys.controller.AbstractController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.product.entity.ProductOrderDetailEntity;
import io.renren.modules.product.service.ProductOrderDetailService;
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
@RequestMapping("product/productorderdetail")
public class ProductOrderDetailController extends AbstractController {
    @Autowired
    private ProductOrderDetailService productOrderDetailService;

    @Autowired
    private ProductOrderService productOrderService;

    @Autowired
    private ProductInfoService productInfoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("product:productorderdetail:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = productOrderDetailService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("product:productorderdetail:info")
    public R info(@PathVariable("id") Integer id){
			ProductOrderDetailEntity productOrderDetail = productOrderDetailService.selectById(id);
			if(!StringUtils.isEmpty( productOrderDetail.getOrderId() )){
                ProductOrderEntity productOrderEntity = productOrderService.selectById( productOrderDetail.getOrderId() );
                if (!StringUtils.isEmpty( productOrderDetail )) {
                    productOrderDetail.setOrderNo( productOrderEntity.getOrderNo() );
                }
            }
        if (!StringUtils.isEmpty( productOrderDetail.getProductId() )) {
            ProductInfoEntity productInfoEntity = productInfoService.selectById( productOrderDetail.getProductId() );
            if (!StringUtils.isEmpty( productInfoEntity )) {
                productOrderDetail.setProductName( productInfoEntity.getProductName() );
            }

        }

        return R.ok().put("productOrderDetail", productOrderDetail);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("product:productorderdetail:save")
    public R save(@RequestBody ProductOrderDetailEntity productOrderDetail){
			productOrderDetailService.insert(productOrderDetail);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("product:productorderdetail:update")
    public R update(@RequestBody ProductOrderDetailEntity productOrderDetail){
			productOrderDetailService.updateById(productOrderDetail);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("product:productorderdetail:delete")
    public R delete(@RequestBody Integer[] ids){
			productOrderDetailService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

    /**
     * updateOrderDetailStatus
     */
    @RequestMapping("/updateOrderDetailStatus")
    public R updateOrderDetailStatus(@RequestBody ProductOrderDetailEntity productOrderDetail){

        System.out.println(productOrderDetail.toString());
        ProductOrderDetailEntity productOrderDetailEntity = productOrderDetailService.selectById( productOrderDetail.getId() );
        productOrderDetailEntity.setStatus( productOrderDetail.getStatus() );

        productOrderDetailService.updateById( productOrderDetailEntity );

        return R.ok();
    }


}
