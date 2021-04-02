package io.renren.modules.finance.service.impl;

import io.renren.modules.finance.controller.FinanceRepaymentInfoController;
import io.renren.modules.finance.dao.FinanceBorrowerInfoDao;
import io.renren.modules.finance.dao.FinanceRepaymentInfoDao;
import io.renren.modules.finance.entity.FinanceBorrowerInfoEntity;
import io.renren.modules.finance.entity.FinanceOughtReceiveEntity;
import io.renren.modules.finance.entity.FinanceRepaymentInfoEntity;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.finance.dao.TbUserDao;
import io.renren.modules.finance.entity.TbUserEntity;
import io.renren.modules.finance.service.TbUserService;
import org.springframework.util.StringUtils;


@Service("tbUserService")
public class TbUserServiceImpl extends ServiceImpl<TbUserDao, TbUserEntity> implements TbUserService {

    @Autowired
    private FinanceBorrowerInfoDao borrowerInfoDao;

    @Autowired
    private FinanceRepaymentInfoDao repaymentInfoDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<TbUserEntity> page = this.selectPage(
                new Query<TbUserEntity>(params).getPage(),
                new EntityWrapper<TbUserEntity>()
        );

        List<TbUserEntity> tbUserEntityList = page.getRecords();
        if (CollectionUtils.isNotEmpty(tbUserEntityList)) {
            List<Long> userIdList = tbUserEntityList.stream().map(TbUserEntity::getUserId).collect(Collectors.toList());
            EntityWrapper<FinanceBorrowerInfoEntity> borrowerInfoWrapper = new EntityWrapper<>();
            borrowerInfoWrapper.in("user_id", userIdList);

            List<FinanceBorrowerInfoEntity> borrowerInfoEntityList = borrowerInfoDao.selectList(borrowerInfoWrapper);
            Map<Long, BigDecimal> borrowerUserMap = new HashMap<>();

            if (CollectionUtils.isNotEmpty(borrowerInfoEntityList)) {
                Map<Long, List<FinanceBorrowerInfoEntity>> userBorrowerMap = borrowerInfoEntityList
                        .stream()
                        .collect(Collectors.groupingBy(FinanceBorrowerInfoEntity::getUserId));
                for (Map.Entry<Long, List<FinanceBorrowerInfoEntity>> userBorrower : userBorrowerMap.entrySet()) {
                    Long userId = userBorrower.getKey();
                    BigDecimal borrowerMoneyCount= userBorrower.getValue().stream().map(FinanceBorrowerInfoEntity::getBorrowerMoney).reduce(BigDecimal.ZERO, BigDecimal::add);
                    borrowerUserMap.put(userId, borrowerMoneyCount);
                }
            }

            Map<Long, BigDecimal> repaymentUserMap = new HashMap<>();

            EntityWrapper<FinanceRepaymentInfoEntity> repaymentInfoWrapper = new EntityWrapper<>();
            repaymentInfoWrapper.in("user_id", userIdList);
            List<FinanceRepaymentInfoEntity> repaymentInfoEntityList = repaymentInfoDao.selectList(repaymentInfoWrapper);
            if (CollectionUtils.isNotEmpty(repaymentInfoEntityList)) {
                Map<Long, List<FinanceRepaymentInfoEntity>> userBorrowerMap = repaymentInfoEntityList
                        .stream()
                        .collect(Collectors.groupingBy(FinanceRepaymentInfoEntity::getUserId));
                for (Map.Entry<Long, List<FinanceRepaymentInfoEntity>> userBorrower : userBorrowerMap.entrySet()) {
                    Long userId = userBorrower.getKey();
                    BigDecimal repaymentMoneyCount= userBorrower.getValue().stream().map(FinanceRepaymentInfoEntity::getRepaymentMoney).reduce(BigDecimal.ZERO, BigDecimal::add);
                    repaymentUserMap.put(userId, repaymentMoneyCount);
                }
            }

            for (TbUserEntity tbUserEntity : tbUserEntityList) {
                Long userId = tbUserEntity.getUserId();
                BigDecimal borrowerMoney = borrowerUserMap.get(userId);
                if (StringUtils.isEmpty(borrowerMoney)) {
                    borrowerMoney = BigDecimal.ZERO;
                }
                tbUserEntity.setBorrowerMoney(borrowerMoney);
                BigDecimal repaymentMoney = repaymentUserMap.get(userId);
                if (StringUtils.isEmpty(repaymentMoney)) {
                    repaymentMoney = BigDecimal.ZERO;
                }
                tbUserEntity.setRepaymentMoney(repaymentMoney);

                tbUserEntity.setDebtAmount(repaymentMoney.subtract(borrowerMoney));

            }



        }


        return new PageUtils(page);
    }

}
