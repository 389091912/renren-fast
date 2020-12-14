package io.renren.modules.finance.service.impl;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import io.renren.modules.finance.dao.FinanceCategoryDao;
import io.renren.modules.finance.entity.FinanceCategoryEntity;
import io.renren.modules.finance.service.FinanceCategoryService;


@Service("financeCategoryService")
public class FinanceCategoryServiceImpl extends ServiceImpl<FinanceCategoryDao, FinanceCategoryEntity> implements FinanceCategoryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<FinanceCategoryEntity> page = this.selectPage(
                new Query<FinanceCategoryEntity>(params).getPage(),
                new EntityWrapper<FinanceCategoryEntity>()
        );

        return new PageUtils(page);
    }

}
