package io.renren.modules.product.service.impl;

import io.renren.common.utils.Dict;
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

import io.renren.modules.product.dao.SupplierInfoDao;
import io.renren.modules.product.entity.SupplierInfoEntity;
import io.renren.modules.product.service.SupplierInfoService;


@Service("supplierInfoService")
public class SupplierInfoServiceImpl extends ServiceImpl<SupplierInfoDao, SupplierInfoEntity> implements SupplierInfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<SupplierInfoEntity> page = this.selectPage(
                new Query<SupplierInfoEntity>(params).getPage(),
                new EntityWrapper<SupplierInfoEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<Dict> selectAllSupplierInfo() {

        List<SupplierInfoEntity> supplierInfoEntityList = baseMapper.selectList( new EntityWrapper<>() );

        List<Dict> dictList = new ArrayList<>();

        if (CollectionUtils.isNotEmpty( supplierInfoEntityList )) {
            for (SupplierInfoEntity supplierInfoEntity : supplierInfoEntityList) {
                Dict dict = new Dict();
                dict.setId( supplierInfoEntity.getId() );
                dict.setName( supplierInfoEntity.getSupplierName() );
                dictList.add( dict );
            }
        }

        return dictList;

    }

}
