package com.zhang.booking;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import com.zhang.dao.MysqlAction;


public class AvailableTimeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("This is AvailableTimeSerlet !");
		System.out.println("This is AvailableTimeSerlet !");
		
		response.setContentType("text/html;charset=utf-8"); 
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();

		String meetRoomSel1 = request.getParameter("meetRoomSel");	
		int meetRoomSel=Integer.parseInt(meetRoomSel1);
		String chooseResevDate = request.getParameter("chooseResevDate");	

		Date date=null ;
		SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
		try {
			date = DATE_FORMAT.parse(chooseResevDate);		 
		} catch (ParseException e) {
			e.printStackTrace();
		};


		MysqlAction mysqlAction = new MysqlAction();
		try {
			String alltimelots = mysqlAction.getAllTimeslots();
			String[] time1 = alltimelots.split(",");
			List<String> list = new ArrayList<String>();
			list = Arrays.asList(time1);
			ArrayList<String> arrayList = new ArrayList<String>();
			for (int i = 0; i < list.size(); i++ ){
				arrayList.add( list.get(i) ) ;
			}

			String timelots = mysqlAction.getBookingInfobyNameandDate(meetRoomSel,date);
			String[] time2 = timelots.split(",");
			List<String> list2 = new ArrayList<String>();
			list2 = Arrays.asList(time2);			 
			ArrayList<String> arrayList2 = new ArrayList<String>();
			for (int i = 0; i < list2.size(); i++ ){
				arrayList2.add( list2.get(i) ) ;
			}
			arrayList.removeAll(arrayList2);

			String jsonarray = JSONArray.fromObject(arrayList).toString();
			HttpSession session=request.getSession();
			session.setAttribute("jsonarray", jsonarray);

			out.println(jsonarray);
			out.flush();
			out.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8") ;
		request.setCharacterEncoding("UTF-8") ;
		doGet(request, response);

	}

}
