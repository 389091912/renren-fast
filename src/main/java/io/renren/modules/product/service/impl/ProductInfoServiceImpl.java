package io.renren.modules.product.service.impl;

import io.renren.modules.oss.dao.SysOssDao;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.product.dao.ProductInfoDao;
import io.renren.modules.product.entity.ProductInfoEntity;
import io.renren.modules.product.service.ProductInfoService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * @author wsy
 */
@Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
@Service("productInfoService")
public class ProductInfoServiceImpl extends ServiceImpl<ProductInfoDao, ProductInfoEntity> implements ProductInfoService {

    @Autowired
    private SysOssDao sysOssDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ProductInfoEntity> page = this.selectPage(
                new Query<ProductInfoEntity>(params).getPage(),
                new EntityWrapper<ProductInfoEntity>()
        );
        if (CollectionUtils.isNotEmpty( page.getRecords() )) {
            for (ProductInfoEntity productInfo : page.getRecords()) {

               productInfo.setProductImageUrl(
                       (!StringUtils.isEmpty( productInfo.getProductImageId() )) ?
                               sysOssDao.selectById( productInfo.getProductImageId() ).getUrl() :
                               null );
            }
        }
        return new PageUtils(page);
    }

}
