package com.zhang.mrbs.dao.impl;

import org.springframework.stereotype.Repository;

import com.zhang.javabean.File;
import com.zhang.mrbs.dao.IFindFileByIdDao;

/**
 * @Repository(IFileDeleteDao.SERVICE_NAME)
 * 相当于在spring容器中定义：
 * <bean id="com.zhang.mrbs.impl.MrbsTextDaoImpl" class="com.zhang.mrbs.impl.MrbsTextDaoImpl">
 *
 */
@Repository(IFindFileByIdDao.SERVICE_NAME)
public class FindFileByIdDaoImpl extends CommonDaoImpl<File> implements IFindFileByIdDao{

}
