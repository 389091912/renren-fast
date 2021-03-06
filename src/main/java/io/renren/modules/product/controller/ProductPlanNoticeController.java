package io.renren.modules.product.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import io.renren.modules.product.entity.ProductOrderDetailEntity;
import io.renren.modules.product.service.ProductOrderDetailService;
import io.renren.modules.sys.controller.AbstractController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.product.entity.ProductPlanNoticeEntity;
import io.renren.modules.product.service.ProductPlanNoticeService;
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
@RequestMapping("product/productplannotice")
public class ProductPlanNoticeController extends AbstractController {
    @Autowired
    private ProductPlanNoticeService productPlanNoticeService;

    @Autowired
    private ProductOrderDetailService productOrderDetailService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("product:productplannotice:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = productPlanNoticeService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("product:productplannotice:info")
    public R info(@PathVariable("id") Integer id){
			ProductPlanNoticeEntity productPlanNotice = productPlanNoticeService.selectById(id);
        System.out.println( "ProductPlanNoticeController.info" );
        return R.ok().put("productPlanNotice", productPlanNotice);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("product:productplannotice:save")
    public R save(@RequestBody ProductPlanNoticeEntity productPlanNotice){

        Integer orderDetailId = productPlanNotice.getOrderId();
        Date date = new Date();

        ProductOrderDetailEntity productOrderDetailEntity = productOrderDetailService.selectById( orderDetailId );
        if(!StringUtils.isEmpty( productOrderDetailEntity )){
            productPlanNotice.setOrderId( productOrderDetailEntity.getOrderId() );
        }else {
            productPlanNotice.setOrderId( null );
        }
        productPlanNotice.setCreateTime( date );
        productPlanNotice.setCreateUser( getUserId().intValue() );

        productPlanNoticeService.insert(productPlanNotice);
        productOrderDetailEntity.setStatus(ProductOrderDetailEntity.PROCESS_PRODUCT  );
        productOrderDetailEntity.setPlanId( productPlanNotice.getId() );
        productOrderDetailService.updateById( productOrderDetailEntity );
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("product:productplannotice:update")
    public R update(@RequestBody ProductPlanNoticeEntity productPlanNotice){
			productPlanNoticeService.updateById(productPlanNotice);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("product:productplannotice:delete")
    public R delete(@RequestBody Integer[] ids){
			productPlanNoticeService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
