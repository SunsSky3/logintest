package com.zhang.booking;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class GetRoomInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//System.out.println("This is GetRoomInfoServlet !");
		int conferId =Integer.parseInt( request.getParameter("conferId"));
		HttpSession session =  request.getSession();
		session.setAttribute("conferId",conferId);
		response.sendRedirect("documentInfo.jsp") ;
		
	}

}
