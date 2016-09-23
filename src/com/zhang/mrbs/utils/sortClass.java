package com.zhang.mrbs.utils;
import java.util.Comparator;  

import com.zhang.javabean.Booking;


public class sortClass implements Comparator{

		/**  
		* @Name: compare
		* @Description: 构建按照时间的比较强
		* @Author: 汪志文（作者）
		* @Version: V1.00 （版本号）
		* @Create Date: 2016-9-23（创建日期）
		* @Parameters: Object：比较的对象
		* @Return: int：flag 比较结果1为大，0为小
		*/
	    public int compare(Object arg0,Object arg1){  
	    	Booking booking0 = (Booking)arg0;  
	    	Booking booking1 = (Booking)arg1;  
	        int flag = booking0.getDate().compareTo(booking1.getDate());  
	        return flag;       
	}  
	
}
