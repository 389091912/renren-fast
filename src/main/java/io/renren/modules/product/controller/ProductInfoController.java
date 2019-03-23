package io.renren.modules.product.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.renren.common.utils.Dict;
import io.renren.modules.oss.entity.SysOssEntity;
import io.renren.modules.oss.service.SysOssService;
import io.renren.modules.product.service.ProductBoxService;
import io.renren.modules.sys.controller.AbstractController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import io.renren.modules.product.entity.ProductInfoEntity;
import io.renren.modules.product.service.ProductInfoService;
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
@RequestMapping("product/productinfo")
public class ProductInfoController extends AbstractController {
    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private ProductBoxService productBoxService;

    @Autowired
    private SysOssService sysOssService;

    @RequestMapping(value = "/*",method = RequestMethod.OPTIONS)
    public ResponseEntity.HeadersBuilder<?> handleOptions(){
        return ResponseEntity.noContent();
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("product:productinfo:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = productInfoService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("product:productinfo:info")
    public R info(@PathVariable("id") Integer id){
			ProductInfoEntity productInfo = productInfoService.selectById(id);

        productInfo.setProductImageUrl( StringUtils.isEmpty( productInfo.getProductImageId() )?
                null:
                sysOssService.selectById( productInfo.getProductImageId() ).getUrl());

        if (!StringUtils.isEmpty( productInfo.getProductDrawingId() )) {
            SysOssEntity sysOssEntity = sysOssService.selectById( productInfo.getProductDrawingId() );
            productInfo.setProductDrawingFileName( sysOssEntity.getOriginalName() );
            productInfo.setProductDrawingUrl( sysOssEntity.getUrl() );
            return R.ok().put("productInfo", productInfo).put( "fileName", sysOssEntity.getOriginalName());
        }
        System.out.println(productInfo.toString());
        return R.ok().put("productInfo", productInfo).put( "fileName","" );
    }

    /**
     * 信息Two
     */
    @GetMapping("/infoTwo/{id}")
   // @RequiresPermissions("product:productinfo:info")
    public R infoTwo(@PathVariable(value = "id",required = false) Integer id){
        if (id == 0) {
            return R.ok().put( "productInfo",new ProductInfoEntity() );
        }

        ProductInfoEntity productInfo = productInfoService.selectById(id);

        if (!StringUtils.isEmpty( productInfo.getCartonId() )) {
            productInfo.setBoxNo( productBoxService.selectById( productInfo.getCartonId() ).getBoxNo() );
        }
        System.out.println(productInfo.toString());
        return R.ok().put( "productInfo", productInfo );
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("product:productinfo:save")
    public R save(@RequestBody ProductInfoEntity productInfo){
        productInfo.setCreateUser( getUserId().intValue() );
        productInfo.setCreateTime( new Date() );
        productInfo.setStatus( ProductInfoEntity.STATUS_ON );
        productInfoService.insert(productInfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("product:productinfo:update")
    public R update(@RequestBody ProductInfoEntity productInfo){

        productInfo.setUpdateUser( getUserId().intValue() );
        productInfo.setUpdateTime( new Date() );

        productInfoService.updateById(productInfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("product:productinfo:delete")
    public R delete(@RequestBody Integer[] ids){
			productInfoService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }
    @RequestMapping("/deleteDesignId/{designId}")
    @RequiresPermissions("product:productinfo:delete")
    public R deleteDesignId(@PathVariable("designId") Integer designId){

        productInfoService.updateProductInfoIsNullByDesignId( designId );

        return R.ok();
    }
    /**
     * 根据Model ID 查询产品名称
     */
    @RequestMapping("/getProductName/{modelNo}")
  //  @RequiresPermissions("product:productinfo:info")
    public R getProductName(@PathVariable("modelNo") Integer modelNo){
        if (StringUtils.isEmpty( modelNo )) {
            return R.error( "模具编号不存在" );
        }
        ProductInfoEntity productInfoEntity = productInfoService.selectOne( new EntityWrapper<ProductInfoEntity>().eq( "model_no", modelNo ) );

        return R.ok().put("productName", StringUtils.isEmpty( productInfoEntity )?null:productInfoEntity.getProductName());
    }


    /**
     *  获取所有产品id 和 name
     */
    @RequestMapping("/getAllProductVoList")

    public R getAllProductVoList(){

        List<Dict> allProductVoList = productInfoService.getAllProductVoList();

        return R.ok().put("productList", allProductVoList);
    }

}
