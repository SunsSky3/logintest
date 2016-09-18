package com.zhang.mrbs.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.zhang.mrbs.domain.MrbsText;

public interface ICommonDao<T> {

	void save(T entity);
	void update(T entity);
	T findObjectByID(Serializable id);
	void deleteObjectByIds(Serializable... ids);
	void deleteObjectByCollection(List<T> list);
	List<T> findCollectionByConditionNoPage(String condition,Object[] params, Map<String, String> orderby);
}
