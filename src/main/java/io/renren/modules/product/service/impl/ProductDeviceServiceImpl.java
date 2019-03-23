package io.renren.modules.product.service.impl;

import io.renren.common.utils.Dict;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.product.dao.ProductDeviceDao;
import io.renren.modules.product.entity.ProductDeviceEntity;
import io.renren.modules.product.service.ProductDeviceService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author wsy
 */
@Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
@Service("productDeviceService")
public class ProductDeviceServiceImpl extends ServiceImpl<ProductDeviceDao, ProductDeviceEntity> implements ProductDeviceService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ProductDeviceEntity> page = this.selectPage(
                new Query<ProductDeviceEntity>(params).getPage(),
                new EntityWrapper<ProductDeviceEntity>().orderBy( "create_time", false )
        );

        return new PageUtils(page);
    }

    @Override
    public List<Dict> selectDriverList() {

        List<ProductDeviceEntity> productDeviceEntityList = baseMapper.selectList( new EntityWrapper<ProductDeviceEntity>() );
        List<Dict> dictArrayList = new ArrayList<>();

        for(ProductDeviceEntity productDeviceEntity:productDeviceEntityList) {
            Dict dict = new Dict();

            dict.setId( productDeviceEntity.getId() );
            dict.setName( productDeviceEntity.getDeciveName() );
            dictArrayList.add( dict );
        }
        return dictArrayList;
    }

}
