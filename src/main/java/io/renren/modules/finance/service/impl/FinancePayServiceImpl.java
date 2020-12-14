package io.renren.modules.finance.service.impl;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import io.renren.modules.finance.dao.FinancePayDao;
import io.renren.modules.finance.entity.FinancePayEntity;
import io.renren.modules.finance.service.FinancePayService;


@Service("financePayService")
public class FinancePayServiceImpl extends ServiceImpl<FinancePayDao, FinancePayEntity> implements FinancePayService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<FinancePayEntity> page = this.selectPage(
                new Query<FinancePayEntity>(params).getPage(),
                new EntityWrapper<FinancePayEntity>()
        );

        return new PageUtils(page);
    }

}
