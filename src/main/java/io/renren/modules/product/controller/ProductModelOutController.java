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

            boolean modelSuccessMoNumFlag = productModelEntity.getModelSuccessMo() - productModelOut.getModelSuccessMo() <= 0;

            if (modelSuccessMoNumFlag) {
                modelShelfEntity.setIsEmpty( ModelShelfEntity.IS_EMPTY );
                modelShelfEntity.setModelId( null );
                modelShelfEntity.setUpdataTime( date );
                modelShelfService.updateAllColumnById( modelShelfEntity );

                productModelEntity.setId( productModelOut.getModelNo() );
                productModelEntity.setModelShelfId( null );
                productModelEntity.setDepotId( null );
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
