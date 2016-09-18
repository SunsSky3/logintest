package com.zhang.mrbs.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zhang.mrbs.dao.IMrbsTextDao;
import com.zhang.mrbs.domain.MrbsText;
import com.zhang.mrbs.service.IMrbsTextService;

/**
 * @Service
 * 相当于在spring容器中定义：
 * <bean id="com.itheima.elec.service.impl.ElecTextServiceImpl" class="com.itheima.elec.service.impl.ElecTextServiceImpl">
 */
@Service(IMrbsTextService.SERVICE_NAME)
@Transactional(readOnly=true)
public class MrbsTextServiceImpl implements IMrbsTextService {

	@Resource(name=IMrbsTextDao.SERVICE_NAME)
	IMrbsTextDao mrbsTextDao;
	
	/**保存测试表*/
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public void saveMrbsText(MrbsText mrbsText) {
		mrbsTextDao.save(mrbsText);
	}
	
	/**指定查询条件，查询列表*/
	/**
	 * SELECT * FROM elec_text o WHERE 1=1     #Dao层
		AND o.textName LIKE '%张%'   #Service层
		AND o.textRemark LIKE '%张%'   #Service层
		ORDER BY o.textDate ASC,o.textName DESC  #Service层
	 */
	public List<MrbsText> findCollectionByConditionNoPage(MrbsText mrbsText) {
		//查询条件
		String condition = "";
		//查询条件对应的参数
		List<Object> paramsList = new ArrayList<Object>();
		if(StringUtils.isNotBlank(mrbsText.getTextName())){
			condition += " and o.textName like ?";
			paramsList.add("%"+mrbsText.getTextName()+"%");
		}
		if(StringUtils.isNotBlank(mrbsText.getTextRemark())){
			condition += " and o.textRemark like ?";
			paramsList.add("%"+mrbsText.getTextRemark()+"%");
		}
		//传递可变参数
		Object [] params = paramsList.toArray();
		//排序
		Map<String, String> orderby = new LinkedHashMap<String, String>();//有序
		orderby.put("o.textDate", "asc");
		orderby.put("o.textName", "desc");
		//查询
		List<MrbsText> list = mrbsTextDao.findCollectionByConditionNoPage(condition,params,orderby);
		return list;
	}

}
