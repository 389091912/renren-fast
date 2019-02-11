package io.renren.modules.product.controller;

import java.text.SimpleDateFormat;
import java.util.*;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.renren.common.utils.Dict;
import io.renren.modules.product.entity.BoxAddLeaveEntity;
import io.renren.modules.product.entity.vo.BoxFactoryVo;
import io.renren.modules.product.service.BoxAddLeaveService;
import io.renren.modules.product.service.BoxFactoryService;
import io.renren.modules.sys.controller.AbstractController;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.product.entity.ProductBoxEntity;
import io.renren.modules.product.service.ProductBoxService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 
 *
 * @author wsy
 * @email 389091912@qq.com
 * @date 2019-01-25 23:35:04
 */
@RestController
@RequestMapping("product/productbox")
public class ProductBoxController extends AbstractController {
    @Autowired
    private ProductBoxService productBoxService;

    @Autowired
    private BoxAddLeaveService boxAddLeaveService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("product:productbox:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = productBoxService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("product:productbox:info")
    public R info(@PathVariable("id") Integer id){
        ProductBoxEntity productBox = productBoxService.selectById(id);
        List<BoxFactoryVo> boxFactoryVoList = new ArrayList<>();

        List<BoxAddLeaveEntity> addLeaveEntityList = boxAddLeaveService.selectList( new EntityWrapper<BoxAddLeaveEntity>()
                .eq( "box_no", productBox.getId() )
                .eq( "type", BoxAddLeaveEntity.ADD )
        );
        if (CollectionUtils.isNotEmpty( addLeaveEntityList )) {
            for (BoxAddLeaveEntity boxAddLeaveEntity : addLeaveEntityList) {
                BoxFactoryVo boxFactoryVo = new BoxFactoryVo();
                boxFactoryVo.setFactoryId( boxAddLeaveEntity.getFactoryId() );
                boxFactoryVo.setBoxPrice( boxAddLeaveEntity.getBoxPrice() );
                String format = new SimpleDateFormat( "yyyy-MM-dd" ).format( boxAddLeaveEntity.getAddBoxTime() );
                boxFactoryVo.setBoxBatch( format );
                boxFactoryVoList.add( boxFactoryVo );

            }
        }
        productBox.setBoxFactoryVoList( boxFactoryVoList );

        return R.ok().put("productBox", productBox);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("product:productbox:save")
    public R save(@RequestBody ProductBoxEntity productBox){
        if (StringUtils.isEmpty( productBox.getBoxFactoryVoStr() )) {
            List<BoxFactoryVo> boxFactoryVos = JSON.parseArray( productBox.getBoxFactoryVoStr(), BoxFactoryVo.class );
            Date date = new Date();

            for (BoxFactoryVo boxFactoryVo : boxFactoryVos) {

            }
        }

			productBoxService.insert(productBox);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("product:productbox:update")
    public R update(@RequestBody ProductBoxEntity productBox){
			productBoxService.updateById(productBox);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("product:productbox:delete")
    public R delete(@RequestBody Integer[] ids){
			productBoxService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

    /**
     * 获取所有纸箱信息
     */
    @RequestMapping("/getAllProductBoxList")
   //s @RequiresPermissions("product:productbox:delete")
    public R getAllProductBoxList(){
        List<Dict> allProductBoxList = productBoxService.getAllProductBoxList();

        return R.ok().put( "productBoxList", allProductBoxList );
    }

}
