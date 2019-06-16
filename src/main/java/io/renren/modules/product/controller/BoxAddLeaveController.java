package io.renren.modules.product.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.renren.common.utils.*;
import io.renren.modules.product.entity.BoxFactoryEntity;
import io.renren.modules.product.entity.ProductBoxEntity;
import io.renren.modules.product.entity.ProductOrderDetailEntity;
import io.renren.modules.product.entity.vo.ExcelInfo;
import io.renren.modules.product.service.*;
import io.renren.modules.sys.controller.AbstractController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.aspectj.bridge.MessageWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.InstanceFilter;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.product.entity.BoxAddLeaveEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 
 *
 * @author wsy
 * @email 389091912@qq.com
 * @date 2019-01-30 02:41:40
 */
@RestController
@RequestMapping("product/boxaddleave")
public class BoxAddLeaveController  extends AbstractController {
    @Autowired
    private BoxAddLeaveService boxAddLeaveService;

    @Autowired
    private ProductBoxService productBoxService;

    @Autowired
    private BoxFactoryService boxFactoryService;

    @Autowired
    private ProductOrderService productOrderService;

    @Autowired
    private ProductOrderDetailService productOrderDetailService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("product:boxaddleave:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = boxAddLeaveService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("product:boxaddleave:info")
    public R info(@PathVariable("id") Integer id){
			BoxAddLeaveEntity boxAddLeave = boxAddLeaveService.selectById(id);

        return R.ok().put("boxAddLeave", boxAddLeave);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("product:boxaddleave:save")
    public R save(@RequestBody BoxAddLeaveEntity boxAddLeave){

        if (boxAddLeave.getType() == 1) {
            Date addBoxTime = boxAddLeave.getAddBoxTime();
            boxAddLeave.setCreateTime( addBoxTime );

            String batch = new SimpleDateFormat( "yyyy-MM-dd" ).format( addBoxTime );
            String boxNo = boxAddLeave.getBoxNo();
            ProductBoxEntity productBoxEntity = productBoxService.selectById( Integer.parseInt( boxNo ) );
            String boxAddBatch = productBoxEntity.getBoxAddBatch();
            StringBuilder sbBatch = new StringBuilder();
            if (!StringUtils.isEmpty( boxAddBatch )) {
                String boxAddBatchStr =sbBatch.append( boxAddBatch ).append( "," ).append( batch ).toString();
                productBoxEntity.setBoxAddBatch( boxAddBatchStr );
            }else {
                String boxAddBatchStr =sbBatch.append( batch ).toString();
                productBoxEntity.setBoxAddBatch( boxAddBatchStr );
            }
            String boxAddPrice = productBoxEntity.getBoxAddPrice();
            StringBuilder sbPrice = new StringBuilder();
            if (!StringUtils.isEmpty( boxAddPrice )) {
                String boxAddPriceStr = sbPrice.append( boxAddPrice ).append( "," ).append( boxAddLeave.getBoxPrice() ).toString();
                productBoxEntity.setBoxAddPrice( boxAddPriceStr );
            }else {
                String boxAddPriceStr = sbPrice.append( boxAddLeave.getBoxPrice() ).toString();
                productBoxEntity.setBoxAddPrice( boxAddPriceStr );
            }
            String boxAddFactoryId = productBoxEntity.getBoxAddFactoryId();
            StringBuilder sbFactory = new StringBuilder();
            if (!StringUtils.isEmpty( boxAddFactoryId )) {
                String boxAddFactoryIdStr = sbFactory.append( boxAddFactoryId ).append( "," ).append( boxAddLeave.getFactoryId() ).toString();
                productBoxEntity.setBoxAddFactoryId( boxAddFactoryIdStr );
            }else {
                String boxAddFactoryIdStr = sbFactory.append( boxAddLeave.getFactoryId() ).toString();
                productBoxEntity.setBoxAddFactoryId( boxAddFactoryIdStr );
            }


            productBoxService.updateById( productBoxEntity );
            
            System.out.println( batch );
        } else {
            Date outBoxTime = boxAddLeave.getOutBoxTime();
            boxAddLeave.setCreateTime( outBoxTime );

        }

			boxAddLeaveService.insert(boxAddLeave);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("product:boxaddleave:update")
    public R update(@RequestBody BoxAddLeaveEntity boxAddLeave){
			boxAddLeaveService.updateById(boxAddLeave);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("product:boxaddleave:delete")
    public R delete(@RequestBody Integer[] ids){
			boxAddLeaveService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }


    /**
     * 信息
     */
    @RequestMapping("/getAllBoxAddLeave")
    public R getAllBoxAddLeave(){
        List<Dict> allBoxAddLeave = boxAddLeaveService.getAllBoxAddLeave();

        return R.ok().put("boxAddLeaveList", allBoxAddLeave);
    }

    /**
     *
     */
    @RequestMapping("/getExcel")
    public void getExcel(@RequestParam Map<String, Object> params, HttpServletRequest request, HttpServletResponse response) {

        List<BoxAddLeaveEntity> boxAddLeaveEntities = boxAddLeaveService.selectList( new EntityWrapper<BoxAddLeaveEntity>().orderBy( "create_time",false ) );
        ExcelInfo excelInfo = new ExcelInfo();
        List<ExcelInfo> excelInfoList = new ArrayList<>();
        List<String > titleStrList = new ArrayList<>();
        Date date = new Date();
        String yyyyMMhh = DateUtils.format( date, "yyyyMMhh" );
        excelInfo.setSheetName( "纸箱出入库详情" + yyyyMMhh );

        titleStrList.add( "纸箱编号" );
        titleStrList.add( "箱体数量" );
        titleStrList.add( "格挡数量" );
        titleStrList.add( "垫片数量" );
        titleStrList.add( "入库时间" );
        titleStrList.add( "价格" );
        titleStrList.add( "供货商" );

        excelInfo.setTitles( titleStrList );
        List<List<Object>> objects = new ArrayList<>();


        for (BoxAddLeaveEntity boxAddLeaveEntity : boxAddLeaveEntities) {
            List object = new ArrayList();
            if (!StringUtils.isEmpty(boxAddLeaveEntity.getBoxNo()  )) {
                ProductBoxEntity productBoxEntity = productBoxService.selectById( Integer.parseInt( boxAddLeaveEntity.getBoxNo() ) );
                if (!StringUtils.isEmpty(productBoxEntity)) {
                    object.add( productBoxEntity.getBoxNo() );
                }else {
                    object.add( boxAddLeaveEntity.getBoxNo()  +" 系统未匹配纸箱编号,请看回执单");
                }
            }



            object.add( boxAddLeaveEntity.getBodyNumber() );
            object.add( boxAddLeaveEntity.getParryNumber() );
            object.add( boxAddLeaveEntity.getSpacerNumber() );

            String addBoxTimeStr = DateUtils.format( boxAddLeaveEntity.getAddBoxTime(), "yyyy-MM-dd" );
            object.add( addBoxTimeStr );
            object.add( boxAddLeaveEntity.getBoxPrice() );

            if (!StringUtils.isEmpty( boxAddLeaveEntity.getFactoryId() )) {
                BoxFactoryEntity boxFactoryEntity = boxFactoryService.selectById( boxAddLeaveEntity.getFactoryId() );
                object.add( boxFactoryEntity.getFactoryName() );
            }

            objects.add( object );
        }

        excelInfo.setLists( objects );
        excelInfoList.add( excelInfo );
        try {
            ExcelUtil.exportExcel( "纸箱出入库详情" + yyyyMMhh, excelInfoList, request, response );
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @RequestMapping("/getBoxNeedNumberListByOrderIdAndProductId")
    public R getBoxNeedNumberListByOrderIdAndProductId(@RequestParam Map<String, Object> params){
        System.out.println(params.get( "orderId" ));
        System.out.println(params.get( "productId" ));

        String orderId = (String) params.get( "orderId" );
        String productId = (String) params.get( "productId" );

        if (StringUtils.isEmpty( orderId )) {
            return R.error( "订单编号不能为空" );
        }

        if (StringUtils.isEmpty( productId )) {
            return R.error( "产品编号不能为空" );
        }

        ProductOrderDetailEntity productOrderDetailEntity = productOrderDetailService.selectOne( new EntityWrapper<ProductOrderDetailEntity>().eq( "order_id", orderId ).eq( "product_id", productId) );

        Integer entryBoxNumber = boxAddLeaveService.countAddBoxNumberByOrderIdAndProductId( Integer.parseInt( orderId ), Integer.parseInt( productId ) );
        if(StringUtils.isEmpty( entryBoxNumber )){
            productOrderDetailEntity.setEntryBoxNumber( 0 );
        }else {
            productOrderDetailEntity.setEntryBoxNumber( entryBoxNumber);
        }

        return R.ok().put( "productOrderDetailEntity", productOrderDetailEntity );
    }



}
