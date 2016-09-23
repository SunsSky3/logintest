package com.zhang.HistoryRetrieval;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zhang.dao.MysqlAction;
import com.zhang.javabean.Booking;

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

	/*
	 *@Description:会议检索请求处理
	 *@Author：sky
	 *@Create Date: 2016-9-19（修改）
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("HistoryServlet Begin!");
		
		response.setContentType("text/html;charset=UTF-8") ;
		request.setCharacterEncoding("UTF-8") ;

		HttpSession session = request.getSession();
		String username =(String)session.getAttribute("userName");
		int usertype =Integer.parseInt( session.getAttribute("usertype").toString());
		System.out.println("username & usertype:"+username+usertype);

		String confername = request.getParameter("confername") ;    //获取会议名称
		String confertheme = request.getParameter("confertheme") ;	//获取会议主题
		String roomnum = request.getParameter("roomnum") ;			//获取会议室号
		String start = request.getParameter("start") ;		//获取起始查询时间
		String end = request.getParameter("end") ;			//获取终止查询时间

		MysqlAction mysqlAction = new MysqlAction();

		try {
			ArrayList<Booking> list = mysqlAction.searchConference( confername,confertheme,roomnum,start,end); //调用检索函数
			for(int i = 0; i<list.size();i++){
				Booking booking = (Booking)list.get(i);
				int bookingNum = booking.getBookingNum();
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

		//为检索结果页保留检索信息，通过session会话实现
		session.setAttribute("conferNameSearch",confername);  
		session.setAttribute("conferThemeSearch",confertheme);
		session.setAttribute("roomNumSearch",roomnum);
		session.setAttribute("startSearch",start);
		session.setAttribute("endSearch",end);
		
		rd.forward(request, response); 

	}
}
