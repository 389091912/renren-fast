package io.renren.modules.finance.service.impl;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import io.renren.modules.finance.dao.FinanceOughtReceiveDao;
import io.renren.modules.finance.entity.FinanceOughtReceiveEntity;
import io.renren.modules.finance.service.FinanceOughtReceiveService;


@Service("financeOughtReceiveService")
public class FinanceOughtReceiveServiceImpl extends ServiceImpl<FinanceOughtReceiveDao, FinanceOughtReceiveEntity> implements FinanceOughtReceiveService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<FinanceOughtReceiveEntity> page = this.selectPage(
                new Query<FinanceOughtReceiveEntity>(params).getPage(),
                new EntityWrapper<FinanceOughtReceiveEntity>()
        );

        return new PageUtils(page);
    }

}
