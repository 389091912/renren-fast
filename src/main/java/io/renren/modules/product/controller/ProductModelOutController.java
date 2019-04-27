package io.renren.modules.product.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.renren.common.utils.Dict;
import io.renren.modules.product.entity.ModelShelfEntity;
import io.renren.modules.product.entity.ProductModelEntity;
import io.renren.modules.product.service.ModelShelfService;
import io.renren.modules.product.service.ProductModelService;
import io.renren.modules.sys.controller.AbstractController;
import javassist.expr.NewExpr;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.aspectj.bridge.MessageWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.product.entity.ProductModelOutEntity;
import io.renren.modules.product.service.ProductModelOutService;
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
@RequestMapping("product/productmodelout")
public class ProductModelOutController extends AbstractController {
    @Autowired
    private ProductModelOutService productModelOutService;

    @Autowired
    private ProductModelService productModelService;

    @Autowired
    private ModelShelfService modelShelfService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("product:productmodelout:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = productModelOutService.queryPage(params);
        List<Dict> modelVoList = productModelService.getModelVoList();

        return R.ok().put( "page", page ).put( "modelVoList", modelVoList );
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("product:productmodelout:info")
    public R info(@PathVariable("id") Integer id){
		ProductModelOutEntity productModelOut = productModelOutService.selectById(id);


        System.out.println(productModelOut.toString());
        return R.ok().put("productModelOut", productModelOut);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("product:productmodelout:save")
    public R save(@RequestBody ProductModelOutEntity productModelOut){
        ModelShelfEntity modelShelfEntity = modelShelfService.selectById( productModelOut.getModelShelfId() );

        String modelName = productModelOut.getModelName();
        Date date = new Date();

        if(productModelOut.getModelType()==ProductModelOutEntity.ADD_MODEL_TYPE){
            List<ProductModelEntity> productModelEntityList = productModelService.selectList( new EntityWrapper<ProductModelEntity>().eq( "model_no", modelName ) );
            if (CollectionUtils.isNotEmpty( productModelEntityList )) {
                ProductModelEntity productModelEntity = productModelEntityList.get( 0 );

                productModelOut.setModelNo( productModelEntity.getId() );
                productModelEntity.setUpdateTime( date );
                productModelEntity.setDepotId( productModelOut.getDepotId() );
                productModelEntity.setModelShelfId( productModelOut.getModelShelfId() );


                productModelService.updateAllColumnById( productModelEntity );


            }else {
                ProductModelEntity productModelEntity = new ProductModelEntity();
                productModelEntity.setModelNo( productModelOut.getModelName() );
                productModelEntity.setDepotId( productModelOut.getDepotId() );
                productModelEntity.setModelShelfId( productModelOut.getModelShelfId() );
                productModelEntity.setCreateTime( date );
                productModelEntity.setCreateUser( getUserId().intValue() );
                productModelEntity.setModelType( productModelOut.getModelType() );
                productModelEntity.setProductId( productModelOut.getProductId() );
                productModelEntity.setCustomerModelNo( productModelOut.getCustomerModelNo() );

                productModelService.insert( productModelEntity );

                productModelOut.setModelNo( productModelEntity.getId() );

            }

            modelShelfEntity.setIsEmpty( ModelShelfEntity.IS_NOT_EMPTY );
            modelShelfEntity.setModelId( productModelOut.getModelNo() );
            modelShelfEntity.setUpdataTime( date );
            modelShelfService.insertOrUpdate( modelShelfEntity );
        }
        if (productModelOut.getModelType() == ProductModelOutEntity.OUT_MODEL_TYPE) {
            ProductModelEntity productModelEntity = productModelService.selectById( productModelOut.getModelNo() );


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

            boolean modelSuccessMoNumFlag = productModelEntity.getModelSuccessMo() - productModelOut.getModelSuccessMo() <= 0;
            boolean modelPrimaryMoNumFlag = productModelEntity.getModelPrimaryMo() - productModelOut.getModelPrimaryMo() <= 0;
            boolean modelMouthMoNumFlag = productModelEntity.getModelMouthMo() - productModelOut.getModelMouthMo() <= 0;
            boolean modelMenTouNumFlag = productModelEntity.getModelMenTou() - productModelOut.getModelMenTou() <= 0;
            boolean modelFunnelNumFlag = productModelEntity.getModelFunnel() - productModelOut.getModelFunnel() <= 0;
            boolean modelCoreNumFlag = productModelEntity.getModelCore() - productModelOut.getModelCore() <= 0;
            boolean modelAirTouNumFlag = productModelEntity.getModelAirTou() - productModelOut.getModelAirTou() <= 0;
            boolean modelCoolingNumFlag = productModelEntity.getModelCooling() - productModelOut.getModelCooling() <= 0;
            boolean modelClampNumFlag = productModelEntity.getModelClamp() - productModelOut.getModelClamp() <= 0;

            boolean flag = modelSuccessMoNumFlag && modelPrimaryMoNumFlag &&
                    modelMouthMoNumFlag && modelMenTouNumFlag &&
                    modelFunnelNumFlag && modelCoreNumFlag &&
                    modelAirTouNumFlag && modelCoolingNumFlag && modelClampNumFlag;

            if (flag) {
                modelShelfEntity.setIsEmpty( ModelShelfEntity.IS_EMPTY );
                modelShelfEntity.setModelId( null );
                modelShelfEntity.setUpdataTime( date );
                modelShelfService.updateAllColumnById( modelShelfEntity );

                productModelEntity.setId( productModelOut.getModelNo() );
                productModelEntity.setModelShelfId( null );
                productModelEntity.setDepotId( null );
                productModelEntity.setModelSuccessMo( 0 );
                productModelEntity.setModelPrimaryMo( 0 );
                productModelEntity.setModelMouthMo( 0 );
                productModelEntity.setModelMenTou( 0 );
                productModelEntity.setModelFunnel( 0 );
                productModelEntity.setModelCore( 0 );
                productModelEntity.setModelAirTou( 0 );
                productModelEntity.setModelCooling( 0 );
                productModelEntity.setModelClamp( 0 );

                productModelService.updateAllColumnById( productModelEntity );
            }
        }

        productModelOut.setCreateTime( date );
        productModelOut.setCreateUser( getUserId().intValue() );
        productModelOutService.insert(productModelOut);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("product:productmodelout:update")
    public R update(@RequestBody ProductModelOutEntity productModelOut){
			productModelOutService.updateById(productModelOut);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("product:productmodelout:delete")
    public R delete(@RequestBody Integer[] ids){
			productModelOutService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
