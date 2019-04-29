package io.renren.modules.product.controller;

import java.util.*;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.renren.modules.product.entity.PartInfoEntity;
import io.renren.modules.product.service.PartInfoService;
import io.renren.modules.sys.controller.AbstractController;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.product.entity.PartDetailEntity;
import io.renren.modules.product.service.PartDetailService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 
 *
 * @author wsy
 * @email 389091912@qq.com
 * @date 2019-04-27 16:30:52
 */
@RestController
@RequestMapping("product/partdetail")
public class PartDetailController extends AbstractController {
    @Autowired
    private PartDetailService partDetailService;

    @Autowired
    private PartInfoService partInfoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("product:partdetail:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = partDetailService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("product:partdetail:info")
    public R info(@PathVariable("id") Integer id){
			PartDetailEntity partDetail = partDetailService.selectById(id);

        return R.ok().put("partDetail", partDetail);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("product:partdetail:save")
    public R save(@RequestBody PartDetailEntity partDetail){

        String partName = partDetail.getPartName();

        List<PartInfoEntity> partInfoEntityList = partInfoService.selectList( new EntityWrapper<PartInfoEntity>().eq( "part_name", partName ) );


        Date date = new Date();

        if (CollectionUtils.isEmpty(partInfoEntityList  )) {
            PartInfoEntity partInfo = new PartInfoEntity();
            partInfo.setPartName( partDetail.getPartName() );
            partInfo.setCreateTime( date );
            partInfo.setCreateUser(  getUserId().intValue());
            partInfoService.insert( partInfo );

            partDetail.setPartId( partInfo.getId() );

        }else {
            partDetail.setPartId( partInfoEntityList.get( 0 ).getId() );
        }
        partDetail.setCreateTime( date );
        partDetail.setCreateUser( getUserId().intValue() );
        partDetailService.insert(partDetail);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("product:partdetail:update")
    public R update(@RequestBody PartDetailEntity partDetail){
			partDetailService.updateById(partDetail);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("product:partdetail:delete")
    public R delete(@RequestBody Integer[] ids){
			partDetailService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
