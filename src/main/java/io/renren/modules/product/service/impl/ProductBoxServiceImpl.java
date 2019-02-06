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

import io.renren.modules.product.dao.ProductBoxDao;
import io.renren.modules.product.entity.ProductBoxEntity;
import io.renren.modules.product.service.ProductBoxService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * @author wsy
 */
@Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
@Service("productBoxService")
public class ProductBoxServiceImpl extends ServiceImpl<ProductBoxDao, ProductBoxEntity> implements ProductBoxService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ProductBoxEntity> page = this.selectPage(
                new Query<ProductBoxEntity>(params).getPage(),
                new EntityWrapper<ProductBoxEntity>().orderBy( "create_time", false )
        );

        return new PageUtils(page);
    }

    @Override
    public List<Dict> getAllProductBoxList() {
        List<ProductBoxEntity> productBoxList = baseMapper.selectList( new EntityWrapper<ProductBoxEntity>() );
        List<Dict> allProductBoxList = new ArrayList<>();
        if (!CollectionUtils.isEmpty( productBoxList )) {
            for (ProductBoxEntity productBox : productBoxList) {
                Dict dict = new Dict();
                dict.setId( productBox.getId() );
                dict.setName( productBox.getBoxNo() );
                allProductBoxList.add( dict );
            }
        }

        return allProductBoxList;
    }

}
