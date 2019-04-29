package io.renren.modules.product.controller;

import java.util.AbstractCollection;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import io.renren.common.utils.Dict;
import io.renren.modules.sys.controller.AbstractController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.product.entity.PartInfoEntity;
import io.renren.modules.product.service.PartInfoService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 
 *
 * @author wsy
 * @email 389091912@qq.com
 * @date 2019-04-27 16:15:54
 */
@RestController
@RequestMapping("product/partinfo")
public class PartInfoController  extends AbstractController {
    @Autowired
    private PartInfoService partInfoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("product:partinfo:list")
    public R list(@RequestParam Map<String, Object> params){



        PageUtils page = partInfoService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("product:partinfo:info")
    public R info(@PathVariable("id") Integer id){
			PartInfoEntity partInfo = partInfoService.selectById(id);

        return R.ok().put("partInfo", partInfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("product:partinfo:save")
    public R save(@RequestBody PartInfoEntity partInfo){
			partInfoService.insert(partInfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("product:partinfo:update")
    public R update(@RequestBody PartInfoEntity partInfo){
			partInfoService.updateById(partInfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("product:partinfo:delete")
    public R delete(@RequestBody Integer[] ids){
			partInfoService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }



    /**
     * 获取所有的配件信息列表
     */
    @RequestMapping("/getAllPartList")
    @RequiresPermissions("product:partinfo:list")
    public R getAllPartList(){

        List<Dict> selectAllPartList = partInfoService.selectAllPartList();

        return R.ok().put("partInfoList", selectAllPartList);
    }
}
