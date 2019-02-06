package io.renren.modules.product.service.impl;

import io.renren.common.utils.Dict;
import io.renren.modules.product.entity.ProductOrderEntity;
import org.apache.commons.collections.CollectionUtils;
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
import io.renren.modules.product.entity.ProductModelEntity;
import io.renren.modules.product.service.ProductModelService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author wsy
 */
@Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
@Service("productModelService")
public class ProductModelServiceImpl extends ServiceImpl<ProductModelDao, ProductModelEntity> implements ProductModelService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ProductModelEntity> page = this.selectPage(
                new Query<ProductModelEntity>(params).getPage(),
                new EntityWrapper<ProductModelEntity>().orderBy( "create_time", false )
        );

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
