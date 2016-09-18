package com.zhang.mrbs.service;




public interface IFileDeleteService {
	public static final String SERVICE_NAME = "com.zhang.mrbs.service.impl.FileDeleteServiceImpl";
	void deleteObjectById(Integer fileInfoId,String fileName);

}
