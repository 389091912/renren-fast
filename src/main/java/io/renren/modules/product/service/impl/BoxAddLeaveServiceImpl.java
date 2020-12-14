package io.renren.modules.product.service.impl;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import io.renren.common.utils.*;
import io.renren.modules.job.utils.ScheduleRunnable;
import io.renren.modules.product.dao.ProductBoxDao;
import io.renren.modules.product.entity.ProductBoxEntity;
import io.renren.modules.product.entity.vo.DictVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import io.renren.modules.product.dao.BoxAddLeaveDao;
import io.renren.modules.product.entity.BoxAddLeaveEntity;
import io.renren.modules.product.service.BoxAddLeaveService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author wsy
 */
@Slf4j
@Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
@Service("boxAddLeaveService")
public class BoxAddLeaveServiceImpl extends ServiceImpl<BoxAddLeaveDao, BoxAddLeaveEntity> implements BoxAddLeaveService {

    @Autowired
    private ProductBoxDao productBoxDao;

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        String key = (String) params.get( "key" );
        String rangeBefore = (String) params.get( "rangeBefore" );
        String rangeAfter = (String) params.get( "rangeAfter" );
        String type = (String) params.get( "type" );
        System.out.println(type);
        Page<BoxAddLeaveEntity> page = new Page<>();
        EntityWrapper<BoxAddLeaveEntity> boxAddLeaveEntityWrapper = new EntityWrapper<>();
        if(!StringUtils.isEmpty( key )){
            boxAddLeaveEntityWrapper.eq( "box_no", key );
        }
        if (!StringUtils.isEmpty( type )) {
            if (BoxAddLeaveEntity.ADD.equals( type )&&!StringUtils.isEmpty( rangeBefore )) {
                boxAddLeaveEntityWrapper.between( "add_box_time", rangeBefore+ DateUtils.DATE_TIME_BEFORE ,rangeAfter+DateUtils.DATE_TIME_AFTER );

            }
            if (BoxAddLeaveEntity.LEAVE.equals( type )&&!StringUtils.isEmpty( rangeBefore )) {
                boxAddLeaveEntityWrapper.between( "out_box_time", rangeBefore+DateUtils.DATE_TIME_BEFORE ,rangeAfter+DateUtils.DATE_TIME_AFTER);
            }
            boxAddLeaveEntityWrapper.eq( "type", type );
        }else {
            if(!StringUtils.isEmpty( rangeBefore )){
                boxAddLeaveEntityWrapper.between( "create_time", rangeBefore+DateUtils.DATE_TIME_BEFORE,rangeAfter+DateUtils.DATE_TIME_AFTER);
            }
        }


        page = this.selectPage(
                new Query<BoxAddLeaveEntity>( params ).getPage(),
                boxAddLeaveEntityWrapper
                        .orderBy( "box_no",true )
        );

        if (CollectionUtils.isNotEmpty( page.getRecords() )) {
            for (BoxAddLeaveEntity boxAddLeaveEntity : page.getRecords()) {

                boxAddLeaveEntity.setBoxNoName(
                        (!StringUtils.isEmpty( productBoxDao.selectById( boxAddLeaveEntity.getBoxNo()) ))?
                                productBoxDao.selectById( boxAddLeaveEntity.getBoxNo()).getBoxNo():
                        null);

            }
        }


        return new PageUtils( page );
    }

    @Override
    public List<Dict> getAllBoxAddLeave() {

        List<BoxAddLeaveEntity> boxAddLeaveList = baseMapper.selectList( new EntityWrapper<BoxAddLeaveEntity>().setSqlSelect( "distinct(box_no)" ));
        System.out.println( boxAddLeaveList.size() );
        List<Dict> boxAddList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty( boxAddLeaveList )) {
            for (BoxAddLeaveEntity boxAddLeaveEntity : boxAddLeaveList) {
                Dict dict = new Dict();
                if(!StringUtils.isEmpty( Integer.parseInt(boxAddLeaveEntity.getBoxNo()  ) )){
                    dict.setId( Integer.parseInt(boxAddLeaveEntity.getBoxNo()  ));
                    ProductBoxEntity productBoxEntity = productBoxDao.selectById( Integer.parseInt( boxAddLeaveEntity.getBoxNo() ) );
                    if (!StringUtils.isEmpty( productBoxEntity )) {
                        dict.setName( productBoxEntity.getBoxNo() );
                    }
                }
                boxAddList.add( dict );
            }
        }

        return boxAddList;
    }

    @Override
    public Integer countAddBoxNumberByOrderIdAndProductId(Integer orderId, Integer productId) {

        return  baseMapper.countAddBoxNumberByOrderIdAndProductId( orderId, productId );

    }

    @Override
    public List<String> upload(MultipartFile file, Long userId) {
        ImportParams params = new ImportParams();
        params.setTitleRows(1);
        params.setHeadRows(1);
        params.setNeedVerfiy(true);

        try {

            ExcelImportResult<BoxAddLeaveEntity> result = ExcelImportUtil.importExcelMore(file.getInputStream(), BoxAddLeaveEntity.class, params);

            List<BoxAddLeaveEntity> boxAddLeaveEntityList = result.getList();

            List<DictVo> dictVoList = productBoxDao.selectBoxDictVoList();
            Map<Integer, String > hashMap = new HashMap<>();
            if (CollectionUtils.isNotEmpty(dictVoList)) {
                dictVoList.forEach(item->{
                    hashMap.put(item.getId(), item.getName());
                });
            }
            StringBuilder sb = new StringBuilder();

            log.info(boxAddLeaveEntityList.size() + "  数量");

        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;
    }

}
