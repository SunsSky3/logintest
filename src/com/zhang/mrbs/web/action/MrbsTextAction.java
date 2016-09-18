package com.zhang.mrbs.web.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.zhang.mrbs.domain.MrbsText;
import com.zhang.mrbs.service.IMrbsTextService;


/**
 * @Controller("mrbsTextAction")
 * 相当于spring容器中定义
 * <bean id="mrbsTextAction" class="com.zhang.mrbs.web.action.MrbsTextAction" scope="prototype">
 */
@SuppressWarnings("serial")
@Controller("mrbsTextAction")
@Scope(value="prototype")
public class MrbsTextAction extends BaseAction<MrbsText> {
	
	MrbsText mrbsText = this.getModel();
	
		
	/**注入Service*/
	@Resource(name=IMrbsTextService.SERVICE_NAME)
	IMrbsTextService mrbsTextService;
	
	/**  
	* @Name: save
	* @Description: 保存
	* @Author: 汪志文（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2016-9-15（创建日期）
	* @Parameters: 无
	* @Return: String：跳转到textAdd.jsp
	*/
	public String save(){
		mrbsTextService.saveMrbsText(mrbsText);
		String textDate = request.getParameter("textDate");
		return "save";
	}



	
}
