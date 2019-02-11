package io.renren.modules.product.service.impl;

import io.renren.common.utils.Dict;
import io.renren.modules.product.dao.*;
import io.renren.modules.product.entity.ProductOrderDetailEntity;
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

import io.renren.modules.product.entity.ProductOrderEntity;
import io.renren.modules.product.service.ProductOrderService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
    private ProductRequireDao productRequireDao;




    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ProductOrderEntity> page = this.selectPage(
                new Query<ProductOrderEntity>( params ).getPage(),
                new EntityWrapper<ProductOrderEntity>()
                        .orderBy( "status", true )
                        .orderBy( "update_time", false )
        );

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

}
