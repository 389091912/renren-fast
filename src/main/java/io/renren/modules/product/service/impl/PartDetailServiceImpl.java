package io.renren.modules.product.service.impl;

import cn.hutool.core.getter.OptNullBasicTypeFromObjectGetter;
import io.renren.modules.product.dao.PartInfoDao;
import io.renren.modules.product.entity.PartInfoEntity;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.product.dao.PartDetailDao;
import io.renren.modules.product.entity.PartDetailEntity;
import io.renren.modules.product.service.PartDetailService;
import org.springframework.util.StringUtils;


@Service("partDetailService")
public class PartDetailServiceImpl extends ServiceImpl<PartDetailDao, PartDetailEntity> implements PartDetailService {

    @Autowired
    private PartInfoDao partInfoDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        Page<PartDetailEntity> page = this.selectPage(
                new Query<PartDetailEntity>(params).getPage(),
                new EntityWrapper<PartDetailEntity>()
                .orderBy( "create_time",false )
        );
        if (CollectionUtils.isNotEmpty( page.getRecords() )) {
            for (PartDetailEntity partDetail : page.getRecords()) {
                if (!StringUtils.isEmpty( partDetail.getPartId() )) {
                    PartInfoEntity partInfo = partInfoDao.selectById( partDetail.getPartId() );
                    if (!StringUtils.isEmpty( partInfo )) {
                        partDetail.setPartName( partInfo.getPartName() );

                    }

                }
            }
        }
        return new PageUtils(page);
    }

}
