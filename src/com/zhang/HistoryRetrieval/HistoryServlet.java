package com.zhang.HistoryRetrieval;

import com.zhang.dao.MysqlAction;
import com.zhang.javabean.File;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class HistoryServlet
 */
public class HistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HistoryServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//System.out.println("HistoryServlet Begin!");
		
		response.setContentType("text/html;charset=UTF-8") ;
		request.setCharacterEncoding("UTF-8") ;

		HttpSession session = request.getSession();
		String username =(String)session.getAttribute("userName");
		int usertype =Integer.parseInt( session.getAttribute("usertype").toString());
		System.out.println("username & usertype:"+username+usertype);

		String name = request.getParameter("choosename") ;
		String people = request.getParameter("chooseuploader") ;
		String room = request.getParameter("chooseroomnum") ;
		String start = request.getParameter("start") ;
		String end = request.getParameter("end") ;

		MysqlAction mysqlAction = new MysqlAction();

		try {

			ArrayList<File> list = mysqlAction.searchFile( name,people,room,start,end);
			System.out.println("listsize:"+list.size());
			for(int i = 0; i<list.size();i++){
				File file = (File)list.get(i);
				String filename = file.getFilename();
				String uploader = file.getUploader();
				Date uploadtime = file.getUploadtime();
				int roomnum = file.getRoomnum();
				int bookingNum = file.getBookingnum();
				int a = mysqlAction.checkIdentityToReservation(username, bookingNum);
				System.out.println("a:"+a);
				if (usertype==3||a==1) {
					break;
				}else {
					list.remove(list.get(i));
				}


			}

			request.setAttribute("list", list);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		RequestDispatcher rd = request.getRequestDispatcher("documentSearch.jsp");  

		rd.forward(request, response); 
		return;  
	}
}
