package com.zhang.booking;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.* ;

public class InquireServlet extends HttpServlet implements Servlet {

	public InquireServlet(){
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//System.out.println("InquireServlet Begin!");

		response.setContentType("text/html;charset=UTF-8") ;
		request.setCharacterEncoding("UTF-8") ;	  

		String date = request.getParameter("chooseDate") ;
		HttpSession session =  request.getSession() ;
		session.setAttribute("chooseDate", date);

		response.sendRedirect("reservationInfo.jsp");
	} 

	private static final long serialVersionUID = 1L;
}
