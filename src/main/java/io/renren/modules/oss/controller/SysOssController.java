/**
 * Copyright 2018 人人开源 http://www.renren.io
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package io.renren.modules.oss.controller;

import com.google.gson.Gson;
import io.renren.common.exception.RRException;
import io.renren.common.utils.*;
import io.renren.common.validator.ValidatorUtils;
import io.renren.common.validator.group.AliyunGroup;
import io.renren.common.validator.group.QcloudGroup;
import io.renren.common.validator.group.QiniuGroup;
import io.renren.modules.oss.cloud.CloudStorageConfig;
import io.renren.modules.oss.cloud.OSSFactory;
import io.renren.modules.oss.entity.SysOssEntity;
import io.renren.modules.oss.service.SysOssService;
import io.renren.modules.sys.service.SysConfigService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * 文件上传
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-03-25 12:13:26
 */
@RestController
@RequestMapping("sys/oss")
public class SysOssController {
	@Autowired
	private SysOssService sysOssService;
    @Autowired
    private SysConfigService sysConfigService;

    private final static String KEY = ConfigConstant.CLOUD_STORAGE_CONFIG_KEY;

	@Value("${files.path}")
	private String filesPath;


	/**
	 * 列表
	 */
	@GetMapping("/list")
	@RequiresPermissions("sys:oss:all")
	public R list(@RequestParam Map<String, Object> params){
		PageUtils page = sysOssService.queryPage(params);

		return R.ok().put("page", page);
	}


    /**
     * 云存储配置信息
     */
    @GetMapping("/config")
    @RequiresPermissions("sys:oss:all")
    public R config(){
        CloudStorageConfig config = sysConfigService.getConfigObject(KEY, CloudStorageConfig.class);

        return R.ok().put("config", config);
    }


	/**
	 * 保存云存储配置信息
	 */
	@PostMapping("/saveConfig")
	@RequiresPermissions("sys:oss:all")
	public R saveConfig(@RequestBody CloudStorageConfig config){
		//校验类型
		ValidatorUtils.validateEntity(config);

		if(config.getType() == Constant.CloudService.QINIU.getValue()){
			//校验七牛数据
			ValidatorUtils.validateEntity(config, QiniuGroup.class);
		}else if(config.getType() == Constant.CloudService.ALIYUN.getValue()){
			//校验阿里云数据
			ValidatorUtils.validateEntity(config, AliyunGroup.class);
		}else if(config.getType() == Constant.CloudService.QCLOUD.getValue()){
			//校验腾讯云数据
			ValidatorUtils.validateEntity(config, QcloudGroup.class);
		}

        sysConfigService.updateValueByKey(KEY, new Gson().toJson(config));

		return R.ok();
	}
	

	/**
	 * 上传文件
	 */
	@PostMapping("/upload")
	@RequiresPermissions("sys:oss:all")
	public R upload(@RequestParam("file") MultipartFile file) throws Exception {
		if (file.isEmpty()) {
			throw new RRException("上传文件不能为空");
		}
		//上传文件
		String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		String url = OSSFactory.build().uploadSuffix(file.getBytes(), suffix);

		//保存文件信息
		SysOssEntity ossEntity = new SysOssEntity();
		ossEntity.setUrl(url);
		ossEntity.setCreateDate(new Date());
		sysOssService.insert(ossEntity);

		return R.ok().put("url", url);
	}


	@RequestMapping("/uploadImage")
	public R uploadImage(MultipartFile file) {
		if (file.isEmpty()) {
			return R.error("上传文件不能为空");
		}
		String fileOrigName = file.getOriginalFilename();
		if (!fileOrigName.contains( "." )) {
			return R.error( "缺少后缀名" );
		}

		fileOrigName = fileOrigName.substring( fileOrigName.lastIndexOf( "." ) );

		String uuid = UUID.randomUUID().toString().replaceAll( "-", "" );

		String imagePathName = filesPath +"/images"+ FileUtil.getPath() + uuid + fileOrigName;

		String imageUrl = "/images"+FileUtil.getPath() + uuid + fileOrigName;
		FileUtil.saveFile( file, imagePathName );

		SysOssEntity ossEntity = new SysOssEntity();
		ossEntity.setCreateDate( new Date() );
		ossEntity.setUrl( imageUrl );
		ossEntity.setOriginalName( file.getOriginalFilename() );
		sysOssService.insert(ossEntity);
		System.out.println( ossEntity.toString() );

		return R.ok().put( "imageId", ossEntity.getId() );
	}

	@RequestMapping("/uploadDesignFile")
	public R uploadDesignFile(MultipartFile file, HttpServletResponse response) {

//		if (file.isEmpty()) {
//			return R.error("上传文件不能为空");
//		}
		String fileOrigName = file.getOriginalFilename();

		if (!fileOrigName.contains( "." )) {
			return R.error( "缺少后缀名" );
		}
		fileOrigName = fileOrigName.substring( fileOrigName.lastIndexOf( "." ) );
		String uuid = UUID.randomUUID().toString().replaceAll( "-", "" );

		String designPathName = filesPath + "/design" + FileUtil.getPath() +uuid+ fileOrigName;
		String designFileUrl = "/design" + FileUtil.getPath()+uuid  + fileOrigName;

//		if (file.isEmpty()) {
//			String contentType = "text/plain;charset=UTF-8";
//			response.setContentType( contentType );
//			try {
//				response.getWriter().println( "请刷新浏览器重新尝试该操作！！！" );
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			return null;
//		}

		FileUtil.saveFile( file, designPathName );

		SysOssEntity ossEntity = new SysOssEntity();
		ossEntity.setCreateDate( new Date() );
		ossEntity.setUrl( designFileUrl );
		ossEntity.setOriginalName( file.getOriginalFilename() );
		sysOssService.insert(ossEntity);

		return R.ok().put( "drawingId", ossEntity.getId() );
	}

	@RequestMapping("/uploadBoxImage")
	public R uploadOtherImage(MultipartFile file) {
		if (file.isEmpty()) {
			return R.error("上传文件不能为空");
		}
		String fileOrigName = file.getOriginalFilename();
		if (!fileOrigName.contains( "." )) {
			return R.error( "缺少后缀名" );
		}

		fileOrigName = fileOrigName.substring( fileOrigName.lastIndexOf( "." ) );

		String uuid = UUID.randomUUID().toString().replaceAll( "-", "" );

		String imagePathName = filesPath +"/images/box"+ FileUtil.getPath() + uuid + fileOrigName;

		String imageUrl = "/images/box"+FileUtil.getPath() + uuid + fileOrigName;

		FileUtil.saveFile( file, imagePathName );

		return R.ok().put( "imageUrl", imageUrl );
	}

	@RequestMapping("/uploadProductLeaveImage")
	public R uploadProductLeaveImage(MultipartFile file) {
		if (file.isEmpty()) {
			return R.error("上传文件不能为空");
		}
		String fileOrigName = file.getOriginalFilename();
		if (!fileOrigName.contains( "." )) {
			return R.error( "缺少后缀名" );
		}

		fileOrigName = fileOrigName.substring( fileOrigName.lastIndexOf( "." ) );

		String uuid = UUID.randomUUID().toString().replaceAll( "-", "" );

		String imagePathName = filesPath +"/images/productLeave"+ FileUtil.getPath() + uuid + fileOrigName;

		String imageUrl = "/images/productLeave"+FileUtil.getPath() + uuid + fileOrigName;

		FileUtil.saveFile( file, imagePathName );

		return R.ok().put( "imageUrl", imageUrl );
	}


	@RequestMapping("/uploadSupplierImage")
	public R uploadSupplierImage(MultipartFile file) {
		if (file.isEmpty()) {
			return R.error("上传文件不能为空");
		}
		String fileOrigName = file.getOriginalFilename();
		if (!fileOrigName.contains( "." )) {
			return R.error( "缺少后缀名" );
		}

		fileOrigName = fileOrigName.substring( fileOrigName.lastIndexOf( "." ) );

		String uuid = UUID.randomUUID().toString().replaceAll( "-", "" );

		String imagePathName = filesPath +"/images/supplier"+ FileUtil.getPath() + uuid + fileOrigName;

		String imageUrl = "/images/supplier"+FileUtil.getPath() + uuid + fileOrigName;

		FileUtil.saveFile( file, imagePathName );

		return R.ok().put( "imageUrl", imageUrl );
	}


	@RequestMapping("/uploadPartImage")
	public R uploadPlanImage(MultipartFile file) {
		if (file.isEmpty()) {
			return R.error("上传文件不能为空");
		}
		String fileOrigName = file.getOriginalFilename();
		if (!fileOrigName.contains( "." )) {
			return R.error( "缺少后缀名" );
		}

		fileOrigName = fileOrigName.substring( fileOrigName.lastIndexOf( "." ) );

		String uuid = UUID.randomUUID().toString().replaceAll( "-", "" );

		String imagePathName = filesPath +"/images/part"+ FileUtil.getPath() + uuid + fileOrigName;

		String imageUrl = "/images/part"+FileUtil.getPath() + uuid + fileOrigName;

		FileUtil.saveFile( file, imagePathName );

		return R.ok().put( "imageUrl", imageUrl );
	}
	/**
	 * 删除
	 */
	@PostMapping("/delete")
	@RequiresPermissions("sys:oss:all")
	public R delete(@RequestBody Long[] ids){
		sysOssService.deleteBatchIds(Arrays.asList(ids));

		return R.ok();
	}

}
