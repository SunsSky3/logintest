package com.zhang.NewRoom;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhang.dao.MysqlAction;

public class NewRoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//System.out.println("NewRoomServlet Begin!");
		response.setContentType("text/html;charset=UTF-8") ;
		request.setCharacterEncoding("UTF-8") ;	
		
		String meetRoomName = request.getParameter("meetRoomName");	
		String meetRoomCapacity = request.getParameter("meetRoomCapacity");
		String meetRoomLocation = request.getParameter("meetRoomLocation");	
		String meetRoomEqui = request.getParameter("meetRoomEqui");	
		
		Date now = new Date();
		
		MysqlAction mysqlAction = new MysqlAction();
		try {
			mysqlAction.addRoom(meetRoomName, meetRoomCapacity, meetRoomLocation, meetRoomEqui, now);
			response.sendRedirect("choosetofutureorhistory.html") ;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
