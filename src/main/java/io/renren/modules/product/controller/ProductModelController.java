package io.renren.modules.product.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import io.renren.common.utils.Dict;
import io.renren.modules.product.entity.ModelShelfEntity;
import io.renren.modules.product.entity.ProductModelOutEntity;
import io.renren.modules.product.service.ModelShelfService;
import io.renren.modules.product.service.ProductModelOutService;
import io.renren.modules.sys.controller.AbstractController;
import org.apache.shiro.authz.annotation.RequiresPermissions;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import io.renren.modules.product.entity.ProductModelEntity;
import io.renren.modules.product.service.ProductModelService;
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
@RequestMapping("product/productmodel")
public class ProductModelController extends AbstractController {
    @Autowired
    private ProductModelService productModelService;

    @Autowired
    private ProductModelOutService productModelOutService;

    @Autowired
    private ModelShelfService modelShelfService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("product:productmodel:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = productModelService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("product:productmodel:info")
    public R info(@PathVariable("id") Integer id){
        ProductModelEntity productModelEntity = productModelService.selectById(id);
        ProductModelOutEntity addCountByModelNo = productModelOutService.getAllModelAddCountByModelNo( productModelEntity.getId() );
        ProductModelOutEntity outCountByModelNo = productModelOutService.getAllModelOutCountByModelNo( productModelEntity.getId() );

        if (!StringUtils.isEmpty( addCountByModelNo )) {
            productModelEntity.setModelSuccessMo(  addCountByModelNo.getModelSuccessMoCount()  );
            productModelEntity.setModelPrimaryMo( addCountByModelNo.getModelPrimaryMoCount() );
            productModelEntity.setModelMouthMo( addCountByModelNo.getModelMouthMoCount() );
            productModelEntity.setModelMenTou( addCountByModelNo.getModelMenTouCount() );
            productModelEntity.setModelFunnel(  addCountByModelNo.getModelFunnelCount()  );
            productModelEntity.setModelCore(  addCountByModelNo.getModelCoreCount()  );
            productModelEntity.setModelAirTou( addCountByModelNo.getModelAirTouCount() );
            productModelEntity.setModelCooling(  addCountByModelNo.getModelCoolingCount() );
            productModelEntity.setModelClamp(  addCountByModelNo.getModelClampCount()  );
        }
        if (!StringUtils.isEmpty( outCountByModelNo )&& (!StringUtils.isEmpty( addCountByModelNo ))) {

            productModelEntity.setModelSuccessMo(  addCountByModelNo.getModelSuccessMoCount() - outCountByModelNo.getModelSuccessMoCount() );
            productModelEntity.setModelPrimaryMo(  addCountByModelNo.getModelPrimaryMoCount() - outCountByModelNo.getModelPrimaryMoCount() );
            productModelEntity.setModelMouthMo(   addCountByModelNo.getModelMouthMoCount() - outCountByModelNo.getModelMouthMoCount() );
            productModelEntity.setModelMenTou(  addCountByModelNo.getModelMenTouCount() - outCountByModelNo.getModelMenTouCount() );
            productModelEntity.setModelFunnel(   addCountByModelNo.getModelFunnelCount() - outCountByModelNo.getModelFunnelCount() );
            productModelEntity.setModelCore(   addCountByModelNo.getModelCoreCount() - outCountByModelNo.getModelCoreCount() );
            productModelEntity.setModelAirTou(   addCountByModelNo.getModelAirTouCount() - outCountByModelNo.getModelAirTouCount() );
            productModelEntity.setModelCooling(   addCountByModelNo.getModelCoolingCount() - outCountByModelNo.getModelCoolingCount() );
            productModelEntity.setModelClamp(   addCountByModelNo.getModelClampCount() - outCountByModelNo.getModelClampCount() );

        }
        if (!StringUtils.isEmpty( productModelEntity.getModelShelfId() )) {
            ModelShelfEntity modelShelfEntity = modelShelfService.selectById( productModelEntity.getModelShelfId() );
            productModelEntity.setShelfNo( modelShelfEntity.getShelfNo() );

        }



        return R.ok().put("productModel", productModelEntity);
    }


    /**
     * 信息
     */
    @RequestMapping("/infoByModelNo/{modelNo}")
    @RequiresPermissions("product:productmodel:info")
    public R infoByModelNo(@PathVariable("modelNo") String modelNo) {

        List<ProductModelEntity> productModelEntities = productModelService.selectList( new EntityWrapper<ProductModelEntity>().eq( "model_no", modelNo ) );

        if(CollectionUtils.isNotEmpty(  productModelEntities)){

            ProductModelEntity productModelEntity = productModelEntities.get( 0 );
            if (!StringUtils.isEmpty( productModelEntity.getModelShelfId() )) {
                ModelShelfEntity modelShelfEntity = modelShelfService.selectById( productModelEntity.getModelShelfId() );

                if(!StringUtils.isEmpty(modelShelfEntity  )){
                    productModelEntity.setShelfNo( modelShelfEntity.getShelfNo() );
                }



            }

            return R.ok().put( "productModel", productModelEntity);
        }else {
            return R.ok().put( "productModel", new ProductModelEntity() );
        }

    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("product:productmodel:save")
    public R save(@RequestBody ProductModelEntity productModel){

        ModelShelfEntity modelShelfEntity = modelShelfService.selectById( productModel.getModelShelfId() );
        if (ModelShelfEntity.IS_NOT_EMPTY.equals( modelShelfEntity.getIsEmpty() )) {
            return R.error( "改货架已经有货物了" );
        }


        productModelService.insert(productModel);
        ProductModelOutEntity productModelOutEntity = new ProductModelOutEntity();
        productModelOutEntity.setModelNo( productModel.getId() );
        productModelOutEntity.setProductName( productModel.getProductName() );


        productModelOutService.insert( productModelOutEntity );

        modelShelfEntity.setIsEmpty( ModelShelfEntity.IS_NOT_EMPTY );
        modelShelfEntity.setModelId( productModel.getId() );
        modelShelfEntity.setUpdataTime( new Date() );
        modelShelfService.insertOrUpdate( modelShelfEntity );
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("product:productmodel:update")
    public R update(@RequestBody ProductModelEntity productModel){
			productModelService.updateById(productModel);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("product:productmodel:delete")
    public R delete(@RequestBody Integer[] ids){
			productModelService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

    /**
     * 获取磨具列表
     * @return
     */
    @RequestMapping("/getModelVoList")
    @RequiresPermissions("product:productmodel:info")
    public R getModelVoList(){
        List<Dict> modelVoList = productModelService.getModelVoList();
        return R.ok().put("modelVoList", modelVoList);
    }

    /**
     * 获取磨具列表
     * @return
     */
    @RequestMapping("/getCustomerModelNo/{modelId}")
    @RequiresPermissions("product:productmodel:info")
    public R getCustomerModelNo(@PathVariable(value = "modelId",required = false) Integer modelId){

        if (modelId == 0) {

            return R.ok().put( "customerModelNo","" );
        }

        ProductModelEntity productModel = productModelService.selectById( modelId );

        return R.ok().put("customerModelNo", productModel.getCustomerModelNo());
    }

}
