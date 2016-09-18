package com.zhang.mrbs.dao.impl;

import org.springframework.stereotype.Repository;

import com.zhang.mrbs.dao.IMrbsTextDao;
import com.zhang.mrbs.domain.MrbsText;

/**
 * @Repository(IMrbsTextDao.SERVICE_NAME)
 * 相当于在spring容器中定义：
 * <bean id="com.itheima.elec.dao.impl.ElecTextDaoImpl" class="com.itheima.elec.dao.impl.ElecTextDaoImpl">
 *
 */
@Repository(IMrbsTextDao.SERVICE_NAME)
public class MrbsTextDaoImpl extends CommonDaoImpl<MrbsText> implements IMrbsTextDao{

}
