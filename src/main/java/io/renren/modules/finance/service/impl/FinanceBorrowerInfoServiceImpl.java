package io.renren.modules.finance.service.impl;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import io.renren.common.utils.ConfigConstant;
import io.renren.common.utils.DateUtils;
import io.renren.modules.finance.dao.TbUserDao;
import io.renren.modules.finance.entity.FinanceCategoryEntity;
import io.renren.modules.finance.entity.FinancePayEntity;
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

import io.renren.modules.finance.dao.FinanceBorrowerInfoDao;
import io.renren.modules.finance.entity.FinanceBorrowerInfoEntity;
import io.renren.modules.finance.service.FinanceBorrowerInfoService;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;


@Service("financeBorrowerInfoService")
public class FinanceBorrowerInfoServiceImpl extends ServiceImpl<FinanceBorrowerInfoDao, FinanceBorrowerInfoEntity> implements FinanceBorrowerInfoService {

    @Autowired
    private FinanceBorrowerInfoDao financeBorrowerInfoDao;


    @Autowired
    private TbUserDao tbUserDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        EntityWrapper<FinanceBorrowerInfoEntity> financeBorrowerQuery = getFinanceBorrowerQuery(params);

        Page<FinanceBorrowerInfoEntity> page = this.selectPage(
                new Query<FinanceBorrowerInfoEntity>(params).getPage(),
                financeBorrowerQuery
        );

        List<FinanceBorrowerInfoEntity> borrowerInfoEntityList = page.getRecords();

        if (CollectionUtils.isNotEmpty(borrowerInfoEntityList)) {
            List<Long> userIdList = borrowerInfoEntityList.stream().map(FinanceBorrowerInfoEntity::getUserId).collect(Collectors.toList());
            EntityWrapper<TbUserEntity> userEntityQuery = new EntityWrapper<>();
            userEntityQuery.in("user_id", userIdList);
            List<TbUserEntity> userEntityList = tbUserDao.selectList(userEntityQuery);
            Map<Long, String> userListMap = userEntityList.stream().collect(Collectors.toMap(TbUserEntity::getUserId, TbUserEntity::getUsername));

            for (FinanceBorrowerInfoEntity financeBorrowerInfoEntity : borrowerInfoEntityList) {
                financeBorrowerInfoEntity.setUsername(userListMap.get(financeBorrowerInfoEntity.getUserId()));
            }
        }


        return new PageUtils(page);
    }

    @Override
    public List<String> importFinanceBorrower(MultipartFile file) {
        ImportParams params = new ImportParams();
        params.setTitleRows(1);
        params.setHeadRows(1);
        params.setNeedVerfiy(true);
        //xls和xlsx格式都可以
        ExcelImportResult<FinanceBorrowerInfoEntity> result =  null;
        try {
            result = ExcelImportUtil.importExcelMore(file.getInputStream(), FinanceBorrowerInfoEntity.class, params);

            Date date = new Date();
            SysUserEntity sysUserEntity  = (SysUserEntity) SecurityUtils.getSubject().getPrincipal();
            Long userId = sysUserEntity.getUserId();

            List<FinanceBorrowerInfoEntity> financeBorrowerInfoEntityList = result.getList();
            if (CollectionUtils.isNotEmpty(financeBorrowerInfoEntityList)) {

                List<TbUserEntity> tbUserEntityList = tbUserDao.selectList(null);

                Map<String, Long> userMapList = new HashMap<>();

                if (CollectionUtils.isNotEmpty(tbUserEntityList)) {
                     userMapList = tbUserEntityList.stream().collect(Collectors.toMap(TbUserEntity::getUsername, TbUserEntity::getUserId));
                }


                for (FinanceBorrowerInfoEntity financeBorrowerInfoEntity : financeBorrowerInfoEntityList) {
                    String borrowerUserName = financeBorrowerInfoEntity.getUsername();
                    if (!StringUtils.isEmpty(borrowerUserName)) {
                        Long borrowerUserId = userMapList.get(borrowerUserName);
                        if (StringUtils.isEmpty(borrowerUserId)) {
                            TbUserEntity tbUserEntity = new TbUserEntity();
                            tbUserEntity.setUsername(borrowerUserName);
                            tbUserEntity.setCreateTime(date);
                            tbUserDao.insert(tbUserEntity);
                            financeBorrowerInfoEntity.setUserId(tbUserEntity.getUserId());
                            userMapList.put(borrowerUserName, tbUserEntity.getUserId());
                        }else {
                            financeBorrowerInfoEntity.setUserId(borrowerUserId);
                        }
                    }
                    financeBorrowerInfoEntity.setCreateUser(userId);
                    financeBorrowerInfoEntity.setCreateTime(date);

                }
                this.insertBatch(financeBorrowerInfoEntityList);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ArrayList<>();

    }

    @Override
    public List<FinanceBorrowerInfoEntity> getFinanceBorrowerInfoList(Map<String, Object> params) {


        EntityWrapper<FinanceBorrowerInfoEntity> financeBorrowerQuery = getFinanceBorrowerQuery(params);

        List<FinanceBorrowerInfoEntity> financeBorrowerInfoEntityList = this.baseMapper.selectList(financeBorrowerQuery);

        return financeBorrowerInfoEntityList;
    }


    private EntityWrapper<FinanceBorrowerInfoEntity> getFinanceBorrowerQuery(Map<String, Object> params) {
        EntityWrapper<FinanceBorrowerInfoEntity> financeBorrowerQuery = new EntityWrapper<>();

        String startDateTime = (String) params.get("startDateTime");
        String endDateTime = (String) params.get("endDateTime");
        String userId = (String) params.get("userId");

        if (!StringUtils.isEmpty(startDateTime)) {
            financeBorrowerQuery.between( "borrower_date", startDateTime+ DateUtils.DATE_TIME_BEFORE,endDateTime+DateUtils.DATE_TIME_AFTER);
        }

        if (!StringUtils.isEmpty(startDateTime)) {
            financeBorrowerQuery.between( "borrower_date", startDateTime+ DateUtils.DATE_TIME_BEFORE,endDateTime+DateUtils.DATE_TIME_AFTER);
        }

        financeBorrowerQuery.eq(!StringUtils.isEmpty(userId), "user_id", userId);

        financeBorrowerQuery.orderBy("user_id");
        financeBorrowerQuery.orderBy("borrower_date");
        return financeBorrowerQuery;
    }


}
