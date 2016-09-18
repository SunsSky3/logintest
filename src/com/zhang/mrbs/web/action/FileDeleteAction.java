package com.zhang.mrbs.web.action;



import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.zhang.dao.MysqlAction;
import com.zhang.javabean.File;
import com.zhang.mrbs.domain.MrbsText;
import com.zhang.mrbs.service.IFileDeleteService;

@SuppressWarnings("serial")
@Controller("fileDeleteAction")
@Scope(value="prototype")
public class FileDeleteAction extends BaseAction<File>{
	File file = this.getModel();

	/**注入Service*/
	@Resource(name=IFileDeleteService.SERVICE_NAME)
	IFileDeleteService fileDeleteService;
	
	/**  
	* @Name: deleteObjectById
	* @Description: 删除上传文件数据
	* @Author: 汪志文（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2016-9-17（创建日期）
	* @Parameters: Integer：主键ID
	* @Return: String，重定向到/documentInfo.jsp
	*/
	public String delete(){
		//获取主键和文件名
		String fileName = request.getParameter("text");
		Integer fileId = Integer.parseInt(request.getParameter("fileInfoId"));	
		fileDeleteService.deleteObjectById(fileId,fileName);
		return "delete";
	}
	
}
