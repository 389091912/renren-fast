package io.renren.modules.product.service.impl;

import io.renren.common.utils.Dict;
import io.renren.modules.product.dao.ModelShelfDao;
import io.renren.modules.product.dao.ProductInfoDao;
import io.renren.modules.product.dao.ProductModelOutDao;
import io.renren.modules.product.entity.*;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.product.dao.ProductModelDao;
import io.renren.modules.product.service.ProductModelService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * @author wsy
 */
@Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
@Service("productModelService")
public class ProductModelServiceImpl extends ServiceImpl<ProductModelDao, ProductModelEntity> implements ProductModelService {

    @Autowired
    private ProductModelOutDao productModelOutDao;

    @Autowired
    private ProductInfoDao productInfoDao;

    @Autowired
    private ModelShelfDao modelShelfDao;


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String  key = (String) params.get( "key" );
        EntityWrapper<ProductModelEntity> objectEntityWrapper = new EntityWrapper<>();
        if (!StringUtils.isEmpty( key )) {
            objectEntityWrapper
                    .or().like( "model_no", key )
                    .or().like( "customer_name",key)
                    .or().like( "customer_model_no", key )
                    .or().like( "product_name", key )
                    .orderBy( "create_time", false )
            ;
        }else {
            objectEntityWrapper.orderBy( "create_time", false );
        }
        Page<ProductModelEntity> page = this.selectPage(
                new Query<ProductModelEntity>(params).getPage(),
                objectEntityWrapper
        );

        if (CollectionUtils.isNotEmpty( page.getRecords() )) {
            for (ProductModelEntity productModelEntity : page.getRecords()) {
                ProductModelOutEntity resultProductModelAdd = productModelOutDao.selectModelAddCount( productModelEntity.getId() );
                ProductModelOutEntity resultProductModelOut = productModelOutDao.selectModelOutCount( productModelEntity.getId() );
                if (!StringUtils.isEmpty( productModelEntity.getProductId() )) {
                    ProductInfoEntity productInfoEntity = productInfoDao.selectById( productModelEntity.getProductId() );
                    if (!StringUtils.isEmpty( productInfoEntity )) {
                        productModelEntity.setProductName( productInfoEntity.getProductName() );
                    }
                }

                if (!StringUtils.isEmpty( productModelEntity.getModelShelfId() )) {
                    ModelShelfEntity modelShelfEntity = modelShelfDao.selectById( productModelEntity.getModelShelfId() );
                    if (!StringUtils.isEmpty( modelShelfEntity )) {
                        productModelEntity.setSiteNo( modelShelfEntity.getShelfNo() );
                    }
                }

                //成模
                Integer modelSuccessMo = productModelEntity.getModelSuccessMo();
                //初模
                Integer modelPrimaryMo = productModelEntity.getModelPrimaryMo();
                //口模
                Integer modelMouthMo = productModelEntity.getModelMouthMo();
                //闷头
                Integer modelMenTou = productModelEntity.getModelMenTou();
                //漏斗
                Integer modelFunnel = productModelEntity.getModelFunnel();
                //芯子
                Integer modelCore = productModelEntity.getModelCore();
                //气头
                Integer modelAirTou = productModelEntity.getModelAirTou();
                //冷却
                Integer modelCooling = productModelEntity.getModelCooling();
                //钳片
                Integer modelClamp = productModelEntity.getModelClamp();

                if (!StringUtils.isEmpty( resultProductModelAdd )) {
                    productModelEntity.setModelSuccessMo( modelSuccessMo + resultProductModelAdd.getModelSuccessMoCount()  );
                    productModelEntity.setModelPrimaryMo( modelPrimaryMo + resultProductModelAdd.getModelPrimaryMoCount() );
                    productModelEntity.setModelMouthMo( modelMouthMo + resultProductModelAdd.getModelMouthMoCount() );
                    productModelEntity.setModelMenTou( modelMenTou + resultProductModelAdd.getModelMenTouCount() );
                    productModelEntity.setModelFunnel( modelFunnel + resultProductModelAdd.getModelFunnelCount()  );
                    productModelEntity.setModelCore( modelCore + resultProductModelAdd.getModelCoreCount()  );
                    productModelEntity.setModelAirTou( modelAirTou + resultProductModelAdd.getModelAirTouCount() );
                    productModelEntity.setModelCooling( modelCooling + resultProductModelAdd.getModelCoolingCount() );
                    productModelEntity.setModelClamp( modelClamp + resultProductModelAdd.getModelClampCount()  );
                }
                if (!StringUtils.isEmpty( resultProductModelOut )) {
                    productModelEntity.setModelSuccessMo( modelSuccessMo  - resultProductModelOut.getModelSuccessMoCount() );
                    productModelEntity.setModelPrimaryMo( modelPrimaryMo  - resultProductModelOut.getModelPrimaryMoCount() );
                    productModelEntity.setModelMouthMo( modelMouthMo - resultProductModelOut.getModelMouthMoCount() );
                    productModelEntity.setModelMenTou( modelMenTou  - resultProductModelOut.getModelMenTouCount() );
                    productModelEntity.setModelFunnel( modelFunnel  - resultProductModelOut.getModelFunnelCount() );
                    productModelEntity.setModelCore( modelCore  - resultProductModelOut.getModelCoreCount() );
                    productModelEntity.setModelAirTou( modelAirTou - resultProductModelOut.getModelAirTouCount() );
                    productModelEntity.setModelCooling( modelCooling  - resultProductModelOut.getModelCoolingCount() );
                    productModelEntity.setModelClamp( modelClamp - resultProductModelOut.getModelClampCount() );
                }
                if ((!StringUtils.isEmpty( resultProductModelAdd )) && (!StringUtils.isEmpty( resultProductModelOut ))) {

                    productModelEntity.setModelSuccessMo( modelSuccessMo + resultProductModelAdd.getModelSuccessMoCount() - resultProductModelOut.getModelSuccessMoCount() );
                    productModelEntity.setModelPrimaryMo( modelPrimaryMo + resultProductModelAdd.getModelPrimaryMoCount() - resultProductModelOut.getModelPrimaryMoCount() );
                    productModelEntity.setModelMouthMo( modelMouthMo + resultProductModelAdd.getModelMouthMoCount() - resultProductModelOut.getModelMouthMoCount() );
                    productModelEntity.setModelMenTou( modelMenTou + resultProductModelAdd.getModelMenTouCount() - resultProductModelOut.getModelMenTouCount() );
                    productModelEntity.setModelFunnel( modelFunnel + resultProductModelAdd.getModelFunnelCount() - resultProductModelOut.getModelFunnelCount() );
                    productModelEntity.setModelCore( modelCore + resultProductModelAdd.getModelCoreCount() - resultProductModelOut.getModelCoreCount() );
                    productModelEntity.setModelAirTou( modelAirTou + resultProductModelAdd.getModelAirTouCount() - resultProductModelOut.getModelAirTouCount() );
                    productModelEntity.setModelCooling( modelCooling + resultProductModelAdd.getModelCoolingCount() - resultProductModelOut.getModelCoolingCount() );
                    productModelEntity.setModelClamp( modelClamp + resultProductModelAdd.getModelClampCount() - resultProductModelOut.getModelClampCount() );
                }


            }
        }
        return new PageUtils(page);
    }

    @Override
    public List<Dict> getModelVoList() {

        List<ProductModelEntity> productModelList= this.selectList( new EntityWrapper<ProductModelEntity>() );
        List<Dict> arrayDictList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty( productModelList )) {
            for (ProductModelEntity productModel : productModelList) {
                Dict dict = new Dict();
                dict.setId( productModel.getId() );
                dict.setName( productModel.getModelNo() );
                arrayDictList.add( dict );
            }
        }

        return arrayDictList;
    }

}
