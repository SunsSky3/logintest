package com.zhang.mrbs.service.impl;

import java.io.File;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zhang.mrbs.dao.IFileDeleteDao;
import com.zhang.mrbs.service.IFileDeleteService;

/**
 * @Service
 * 相当于在spring容器中定义：
 * <bean id="com.zhang.mrbs.service.impl.FileDeleteServiceImpl" class="com.zhang.mrbs.service.impl.FileDeleteServiceImpl">
 */
@Service(IFileDeleteService.SERVICE_NAME)
@Transactional(readOnly=true)
public class FileDeleteServiceImpl implements IFileDeleteService {

	@Resource(name=IFileDeleteDao.SERVICE_NAME)
	IFileDeleteDao fileDeleteDao;

	
	/**  
	* @Name: deleteObjectById
	* @Description: 删除上传文件数据
	* @Author: 汪志文（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2016-9-17（创建日期）
	* @Parameters: Integer：主键ID
	* @Return: 无
	*/
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public void deleteObjectById(Integer fileId,String fileName) {
		String path = "C:\\conference\\file\\" + "upload\\" + fileName;
		if(StringUtils.isNotBlank(path)){
			File file = new File(path);
			if(file.exists()){
				// 1:删除本地的文件
				file.delete();  
			}
		}
		//2：删除数据库的数据
		fileDeleteDao.deleteObjectByIds(fileId);
	}
}
