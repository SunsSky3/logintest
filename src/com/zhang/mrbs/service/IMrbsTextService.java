package com.zhang.mrbs.service;

import java.util.List;

import com.zhang.mrbs.domain.MrbsText;



public interface IMrbsTextService {
	public static final String SERVICE_NAME = "com.zhang.mrbs.service.impl.MrbsTextServiceImpl";
	
	void saveMrbsText(MrbsText mrbsText);

	List<MrbsText> findCollectionByConditionNoPage(MrbsText elecText);
}
