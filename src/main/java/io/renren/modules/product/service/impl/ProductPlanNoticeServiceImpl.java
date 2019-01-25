package io.renren.modules.product.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.product.dao.ProductPlanNoticeDao;
import io.renren.modules.product.entity.ProductPlanNoticeEntity;
import io.renren.modules.product.service.ProductPlanNoticeService;


@Service("productPlanNoticeService")
public class ProductPlanNoticeServiceImpl extends ServiceImpl<ProductPlanNoticeDao, ProductPlanNoticeEntity> implements ProductPlanNoticeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ProductPlanNoticeEntity> page = this.selectPage(
                new Query<ProductPlanNoticeEntity>(params).getPage(),
                new EntityWrapper<ProductPlanNoticeEntity>()
        );

        return new PageUtils(page);
    }

}
