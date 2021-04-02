package io.renren.modules.finance.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.finance.entity.FinanceBorrowerInfoEntity;
import io.renren.modules.finance.entity.FinanceRepaymentInfoEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * 财务-个人还款
 *
 * @author wsy
 * @email wsy@gmail.com
 * @date 2021-01-29 20:45:18
 */
public interface FinanceRepaymentInfoService extends IService<FinanceRepaymentInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);


    List<String> importFinanceRepaymentInfo(MultipartFile file);


    List<FinanceRepaymentInfoEntity> getFinanceRepaymentInfoList(Map<String, Object> params);

}

