package com.zhang.booking;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhang.dao.MysqlAction;
import com.zhang.javabean.Booking;


public class DeleteBookingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("This is DeleteBookingServlet !");
		int conferId =Integer.parseInt( request.getParameter("conferId"));
		MysqlAction mysqlAction = new MysqlAction();
		try {
			Booking booking = mysqlAction.getBookingbyconferId(conferId);
			String confername = booking.getConferName();	
			int room_id = mysqlAction.getroom_idbyName(confername);
			int organization_id = mysqlAction.getOrganization_idbyName(confername);
			
			mysqlAction.deleteBookingbybookingnum(conferId,room_id,organization_id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
