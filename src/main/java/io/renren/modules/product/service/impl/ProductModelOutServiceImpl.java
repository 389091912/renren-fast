package io.renren.modules.product.service.impl;

import com.sun.org.apache.bcel.internal.generic.NEW;
import io.renren.modules.product.dao.*;
import io.renren.modules.product.entity.*;
import io.renren.modules.sys.entity.SysUserEntity;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.product.service.ProductModelOutService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.util.StringUtils;

/**
 * @author wsy
 */
@Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
@Service("productModelOutService")
public class ProductModelOutServiceImpl extends ServiceImpl<ProductModelOutDao, ProductModelOutEntity> implements ProductModelOutService {

    @Autowired
    private ProductInfoDao productInfoDao;

    @Autowired
    private ModelShelfDao modelShelfDao;

    @Autowired
    private ProductModelDao productModelDao;

    @Autowired
    private ModelMessageDao modelMessageDao;

    @Autowired
    private ModelUserMessageDao modelUserMessageDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        SysUserEntity sysUserEntity = (SysUserEntity) SecurityUtils.getSubject().getPrincipal();
        String key = (String) params.get( "key" );
        Page<ProductModelOutEntity> page = new Page<>();
        if (StringUtils.isEmpty( key )) {
            page = this.selectPage(
                    new Query<ProductModelOutEntity>(params).getPage(),
                    new EntityWrapper<ProductModelOutEntity>()
                            .orderBy( "create_time", false )
            );
        }else {
            page = this.selectPage(
                    new Query<ProductModelOutEntity>(params).getPage(),
                    new EntityWrapper<ProductModelOutEntity>()
                            .eq( "model_no",key )
                            .orderBy( "create_time", false )
            );
        }


        for (ProductModelOutEntity productModelOutEntity : page.getRecords()) {
            if (!StringUtils.isEmpty( productModelOutEntity.getProductId() )) {
                ProductInfoEntity productInfoEntity = productInfoDao.selectById( productModelOutEntity.getProductId() );
                if (!StringUtils.isEmpty( productInfoEntity )) {
                    productModelOutEntity.setProductName( productInfoEntity.getProductName() );
                }
            }

            if (!StringUtils.isEmpty( productModelOutEntity.getModelShelfId() )) {
                ModelShelfEntity modelShelfEntity = modelShelfDao.selectById( productModelOutEntity.getModelShelfId() );
                if (!StringUtils.isEmpty( modelShelfEntity )) {
                    productModelOutEntity.setSiteNo( modelShelfEntity.getShelfNo() );
                }
            }


            if (sysUserEntity.getType() == SysUserEntity.SYSTEM_USER || sysUserEntity.getType() == SysUserEntity.SALESMAN) {
                List<ModelMessageEntity> modelMessageEntityList = modelMessageDao.selectList( new EntityWrapper<ModelMessageEntity>()
                        .eq( "model_detail_id", productModelOutEntity.getId() ) );
                if (CollectionUtils.isNotEmpty( modelMessageEntityList )) {
                    ModelMessageEntity modelMessageEntity = modelMessageEntityList.get( 0 );
                    List<ModelUserMessageEntity> modelUserMessageEntities = modelUserMessageDao.selectList( new EntityWrapper<ModelUserMessageEntity>()
                            .eq( "model_msg_id", productModelOutEntity.getId() )
                            .eq( "user_id", sysUserEntity.getUserId() )
                    );
                    if (CollectionUtils.isEmpty( modelUserMessageEntities )) {
                        productModelOutEntity.setIsRead( ModelUserMessageEntity.IS_NOT_READ );
                    }

                }
            }



            if (!StringUtils.isEmpty( productModelOutEntity.getModelNo() )) {
                ProductModelEntity productModelEntity = productModelDao.selectById( productModelOutEntity.getModelNo() );
                if (!StringUtils.isEmpty( productModelEntity )) {
                    productModelOutEntity.setModelName( productModelEntity.getModelNo() );
                    productModelOutEntity.setCustomerModelNo( productModelEntity.getCustomerModelNo() );

                    if (!StringUtils.isEmpty( productModelEntity.getProductId() )) {
                        ProductInfoEntity productInfoEntity = productInfoDao.selectById( productModelEntity.getProductId() );
                        if (!StringUtils.isEmpty( productInfoEntity )) {
                            productModelOutEntity.setProductName( productInfoEntity.getProductName() );
                        }
                    }

                }

            }
        }

        return new PageUtils(page);
    }

    @Override
    public ProductModelOutEntity getAllModelAddCountByModelNo(Integer modelNo) {
        return baseMapper.selectModelAddCount( modelNo );
    }

    @Override
    public ProductModelOutEntity getAllModelOutCountByModelNo(Integer modelNo) {
        return baseMapper.selectModelOutCount( modelNo );
    }

}
