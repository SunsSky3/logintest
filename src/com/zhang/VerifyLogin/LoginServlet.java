package com.zhang.VerifyLogin;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zhang.dao.MysqlAction;
import com.zhang.javabean.User;


public class LoginServlet extends HttpServlet implements Servlet {

	public LoginServlet(){

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.setContentType("text/html;charset=UTF-8") ;
		request.setCharacterEncoding("UTF-8") ;
		String result = "" ;
		HttpSession session =  request.getSession() ;

		String username = request.getParameter("username") ;
		String psw = request.getParameter("password") ;



		if ((username == "") || (username==null) || (username.length() > 20 )){
			try{
				result = "请输入用户名(不能超过20个字符)!" ;
				request.setAttribute("message" ,result) ;
				response.sendRedirect("login.jsp") ;
			}catch(Exception e){
				e.printStackTrace() ;
			}
		}

		if ((psw == "") || (psw==null) || (psw.length() > 20 )){
			try{
				result = "请输入密码(不能超过20个字符)!" ;
				response.sendRedirect("login.jsp") ;
			}catch(Exception e){
				e.printStackTrace() ;
			}
		}	   

		//	  MD5Implementation md5Implementation = new MD5Implementation();
		//	  String password = md5Implementation.createPassPhrase(psw);
		String password = psw;
		System.out.println("用户输入的密码为："+psw+"，处理后的密码为："+password);



		MysqlAction mysqlAction = new MysqlAction();
		try {
			int a=0;
			a= mysqlAction.getResultbyUserNameandPassword(username, password);
			if (a==1){ 

				response.sendRedirect("choosetofutureorhistory.jsp") ;	

				session.setAttribute("userName", username) ;
				session.setAttribute("password", psw);

				MysqlAction mysqlaction = new MysqlAction();
				User user = mysqlaction.getUserbyName(username);
				int user_level_id = user.getUserType();
				session.setAttribute("usertype", user_level_id) ;		

			}else{
				response.sendRedirect("login.jsp");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	private static final long serialVersionUID = 1L;

}
