package io.renren.modules.product.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.renren.common.utils.DateUtils;
import io.renren.common.utils.EasyPoiUtil;
import io.renren.modules.product.entity.IngredientEntity;
import io.renren.modules.product.entity.SupplierInfoEntity;
import io.renren.modules.product.entity.vo.IngredientDetailVo;
import io.renren.modules.product.service.IngredientService;
import io.renren.modules.product.service.SupplierInfoService;
import io.renren.modules.sys.controller.AbstractController;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.product.entity.IngredientDetailEntity;
import io.renren.modules.product.service.IngredientDetailService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 
 *
 * @author wsy
 * @email 389091912@qq.com
 * @date 2019-04-25 23:43:32
 */
@RestController
@RequestMapping("product/ingredientdetail")
public class IngredientDetailController extends AbstractController {
    @Autowired
    private IngredientDetailService ingredientDetailService;

    @Autowired
    private IngredientService ingredientService;

    @Autowired
    private SupplierInfoService supplierInfoService;

    @Value("${files.path}")
    private String filesPath;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("product:ingredientdetail:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = ingredientDetailService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("product:ingredientdetail:info")
    public R info(@PathVariable("id") Integer id){
			IngredientDetailEntity ingredientDetail = ingredientDetailService.selectById(id);
        IngredientEntity ingredientEntity = ingredientService.selectById( ingredientDetail.getIngredientId() );
        if (!StringUtils.isEmpty( ingredientEntity )) {
            ingredientDetail.setMaterialName( ingredientEntity.getMaterialName() );

        }
        return R.ok().put("ingredientDetail", ingredientDetail);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("product:ingredientdetail:save")
    public R save(@RequestBody IngredientDetailEntity ingredientDetail){

        IngredientEntity ingredient = ingredientService.selectOne( new EntityWrapper<IngredientEntity>().eq( "material_name", ingredientDetail.getMaterialName() ) );
        Date date = new Date();

        if (StringUtils.isEmpty(ingredient)) {
            IngredientEntity ingredientEntity = new IngredientEntity();
            ingredientEntity.setMaterialName( ingredientDetail.getMaterialName() );
            ingredientEntity.setCreateTime( date );
            ingredientEntity.setCreateUser( getUserId().intValue() );
            ingredientService.insertOrUpdate( ingredientEntity );

            ingredientDetail.setIngredientId( ingredientEntity.getId() );
        }else {
            ingredientDetail.setIngredientId( ingredient.getId() );
        }

            ingredientDetail.setCreateTime( date );
            ingredientDetail.setCreateUser( getUserId().intValue() );
			ingredientDetailService.insert(ingredientDetail);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("product:ingredientdetail:update")
    public R update(@RequestBody IngredientDetailEntity ingredientDetail){
			ingredientDetailService.updateById(ingredientDetail);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("product:ingredientdetail:delete")
    public R delete(@RequestBody Integer[] ids){
			ingredientDetailService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

    @RequestMapping("/getExcel")
    public void getExcel(@RequestParam Map<String, Object> params, HttpServletRequest request, HttpServletResponse response) {
        List<IngredientDetailEntity> ingredientDetailEntityList = ingredientDetailService.selectList( new EntityWrapper<IngredientDetailEntity>()
                .orderBy( "ingredient_id", true )
                .orderBy( "detail_time", false )
        );
        System.out.println( ingredientDetailEntityList.size() );
        int id = 1;
        for (IngredientDetailEntity ingredientDetailEntity : ingredientDetailEntityList) {
            if (!StringUtils.isEmpty( ingredientDetailEntity.getSupplierId() )) {
                SupplierInfoEntity supplierInfoEntity = supplierInfoService.selectById( ingredientDetailEntity.getSupplierId() );
                ingredientDetailEntity.setSupplierName( supplierInfoEntity.getSupplierName() );
            }
            if (!StringUtils.isEmpty( ingredientDetailEntity.getIngredientId() )) {
                IngredientEntity ingredientEntity = ingredientService.selectById( ingredientDetailEntity.getIngredientId() );
               ingredientDetailEntity.setMaterialName( ingredientEntity.getMaterialName() );
            }
            if (!StringUtils.isEmpty( ingredientDetailEntity.getImageUrl() )) {
                String imageUrl = ingredientDetailEntity.getImageUrl();
                ingredientDetailEntity.setImageUrl( filesPath + imageUrl );

            }
            ingredientDetailEntity.setId( id );
            id++;
        }
       // Workbook workbook = ExcelExportUtil.exportExcel(  params1, IngredientDetailVo.class, ingredientDetailVoList);
        Date date = new Date();
        String format = DateUtils.format( date,"YY-MM-dd-hhmmss" );

        EasyPoiUtil.exportExcel(ingredientDetailEntityList,"辅料订单统计","辅料订单统计",IngredientDetailEntity.class,"辅料订单明细统计"+format+".xls",response);
//        // 告诉浏览器用什么软件可以打开此文件
//        response.setHeader("content-Type", "application/vnd.ms-excel");
//        // 下载文件的默认名称
//        try {
//            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("用户数据表","UTF-8") + ".xls");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        //编码
//        response.setCharacterEncoding("UTF-8");
//        try {
//            workbook.write(response.getOutputStream());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


    }
}
