package io.renren.modules.finance.service.impl;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import io.renren.common.utils.DateUtils;
import io.renren.modules.app.dao.UserDao;
import io.renren.modules.finance.dao.TbUserDao;
import io.renren.modules.finance.entity.FinanceBorrowerInfoEntity;
import io.renren.modules.finance.entity.TbUserEntity;
import io.renren.modules.sys.entity.SysUserEntity;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.finance.dao.FinanceRepaymentInfoDao;
import io.renren.modules.finance.entity.FinanceRepaymentInfoEntity;
import io.renren.modules.finance.service.FinanceRepaymentInfoService;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;


@Service("financeRepaymentInfoService")
public class FinanceRepaymentInfoServiceImpl extends ServiceImpl<FinanceRepaymentInfoDao, FinanceRepaymentInfoEntity> implements FinanceRepaymentInfoService {


    @Autowired
    private TbUserDao tbUserDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        EntityWrapper<FinanceRepaymentInfoEntity> financeRepaymentInfoQuery =getFinanceRepaymentInfoQuery(params);


        Page<FinanceRepaymentInfoEntity> page = this.selectPage(
                new Query<FinanceRepaymentInfoEntity>(params).getPage(),
                financeRepaymentInfoQuery
        );
        List<FinanceRepaymentInfoEntity> repaymentInfoEntityList = page.getRecords();
        if (CollectionUtils.isNotEmpty(repaymentInfoEntityList)) {
            List<Long> userIdList = repaymentInfoEntityList.stream().map(FinanceRepaymentInfoEntity::getUserId).collect(Collectors.toList());
            EntityWrapper<TbUserEntity> userEntityQuery = new EntityWrapper<>();
            userEntityQuery.in("user_id", userIdList);
            List<TbUserEntity> userEntityList = tbUserDao.selectList(userEntityQuery);
            Map<Long, String> userListMap = userEntityList.stream().collect(Collectors.toMap(TbUserEntity::getUserId, TbUserEntity::getUsername));

            for (FinanceRepaymentInfoEntity financeRepaymentInfoEntity : repaymentInfoEntityList) {
                financeRepaymentInfoEntity.setUsername(userListMap.get(financeRepaymentInfoEntity.getUserId()));

            }
        }
        return new PageUtils(page);
    }

    @Override
    public List<String> importFinanceRepaymentInfo(MultipartFile file) {

        ImportParams params = new ImportParams();
        params.setTitleRows(1);
        params.setHeadRows(1);
        params.setNeedVerfiy(true);
        //xls和xlsx格式都可以
        ExcelImportResult<FinanceRepaymentInfoEntity> result =  null;
        try {
            result = ExcelImportUtil.importExcelMore(file.getInputStream(), FinanceRepaymentInfoEntity.class, params);

            Date date = new Date();
            SysUserEntity sysUserEntity  = (SysUserEntity) SecurityUtils.getSubject().getPrincipal();
            Long userId = sysUserEntity.getUserId();

            List<FinanceRepaymentInfoEntity> repaymentInfoList = result.getList();
            if (CollectionUtils.isNotEmpty(repaymentInfoList)) {

                List<TbUserEntity> tbUserEntityList = tbUserDao.selectList(null);

                Map<String, Long> userMapList = new HashMap<>();

                if (CollectionUtils.isNotEmpty(tbUserEntityList)) {
                    userMapList = tbUserEntityList.stream().collect(Collectors.toMap(TbUserEntity::getUsername, TbUserEntity::getUserId));
                }


                for (FinanceRepaymentInfoEntity financeRepayment : repaymentInfoList) {
                    String borrowerUserName = financeRepayment.getUsername();
                    if (!StringUtils.isEmpty(borrowerUserName)) {
                        Long borrowerUserId = userMapList.get(borrowerUserName);
                        if (StringUtils.isEmpty(borrowerUserId)) {
                            TbUserEntity tbUserEntity = new TbUserEntity();
                            tbUserEntity.setUsername(borrowerUserName);
                            tbUserEntity.setCreateTime(date);
                            tbUserDao.insert(tbUserEntity);
                            financeRepayment.setUserId(tbUserEntity.getUserId());
                            userMapList.put(borrowerUserName, tbUserEntity.getUserId());
                        }else {
                            financeRepayment.setUserId(borrowerUserId);
                        }
                    }
                    financeRepayment.setCreateUser(userId);
                    financeRepayment.setCreateTime(date);

                }
                this.insertBatch(repaymentInfoList);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    @Override
    public List<FinanceRepaymentInfoEntity> getFinanceRepaymentInfoList(Map<String, Object> params) {

        EntityWrapper<FinanceRepaymentInfoEntity> financeRepaymentInfoQuery = getFinanceRepaymentInfoQuery(params);

        List<FinanceRepaymentInfoEntity> financeRepaymentInfoEntityList = this.baseMapper.selectList(financeRepaymentInfoQuery);
        return financeRepaymentInfoEntityList;
    }

    private EntityWrapper<FinanceRepaymentInfoEntity> getFinanceRepaymentInfoQuery(Map<String, Object> params){

        EntityWrapper<FinanceRepaymentInfoEntity> financeRepaymentInfoQuery = new EntityWrapper<>();

        String startDateTime = (String) params.get("startDateTime");
        String endDateTime = (String) params.get("endDateTime");
        String userId = (String) params.get("userId");

        if (!StringUtils.isEmpty(startDateTime)) {
            financeRepaymentInfoQuery.between( "repayment_date", startDateTime+ DateUtils.DATE_TIME_BEFORE,endDateTime+DateUtils.DATE_TIME_AFTER);
        }

        if (!StringUtils.isEmpty(startDateTime)) {
            financeRepaymentInfoQuery.between( "repayment_date", startDateTime+ DateUtils.DATE_TIME_BEFORE,endDateTime+DateUtils.DATE_TIME_AFTER);
        }

        financeRepaymentInfoQuery.eq(!StringUtils.isEmpty(userId), "user_id", userId);

        financeRepaymentInfoQuery.orderBy("user_id");
        financeRepaymentInfoQuery.orderBy("repayment_date");

        return financeRepaymentInfoQuery;

    }




}
