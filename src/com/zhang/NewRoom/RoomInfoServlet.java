package com.zhang.NewRoom;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.zhang.dao.MysqlAction;
import com.zhang.javabean.Room;

import java.io.PrintWriter;

public class RoomInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;       
   
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("This is RoomInfoServlet !");
		
		response.setContentType("text/html;charset=utf-8"); 
	    request.setCharacterEncoding("utf-8");
	    PrintWriter out = response.getWriter();	        
	        
		MysqlAction mysqlAction = new MysqlAction();  
		ArrayList<Room> list=null;
		try {
			list = mysqlAction.getRoomInfo();
	        String jsonarray = JSONArray.fromObject(list).toString();
	        out.println(jsonarray);
            out.flush();
            out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
         
	}

}
