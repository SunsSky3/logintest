package com.zhang.booking;

import java.io.* ;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.http.* ;
import javax.servlet.* ;

import java.util.Date;

import com.zhang.dao.MysqlAction;
import com.zhang.javabean.Booking;

//@WebServlet("/BookingServlet")
public class BookingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//System.out.println("BookingServlet Begin!");
		
		response.setContentType("text/html;charset=UTF-8") ;
		request.setCharacterEncoding("UTF-8") ; 
		//get login username
		HttpSession session =  request.getSession() ;
		String username =(String) session.getAttribute("userName");  
//	    System.out.println("登录用户名："+ username) ;

		String meetRoomSel = request.getParameter("meetRoomSel");		  
//		System.out.println("预定的会议室1："+ meetRoomSel);
		int bookingroomnum=Integer.parseInt(meetRoomSel);
//		System.out.println("预定的会议室："+ bookingroomnum);

		String[] thistime = request.getParameterValues("thisTime");		 
//		System.out.println("时隙长度："+thistime.length);
		String timelots="";
		if(thistime!=null){
			for(int i=0;i<thistime.length;i++){	
				timelots = timelots+thistime[i]+",";
			}
		}

		String confertype = request.getParameter("meetType");
//		System.out.println("预定的会议室类型："+confertype);
		
		String[] participant = request.getParameterValues("thisparticipants");
		String  participants="";
		if(participant!=null){
			for(int i=0;i<participant.length;i++){	
				participants = participants+participant[i]+",";
			}
		}

		String confername = request.getParameter("meetName");
//		System.out.println("会议名："+confername );

		String confertheme = request.getParameter("meetContent");
//		System.out.println("会议主题："+confertheme );		   

		String chooseResevDate = request.getParameter("chooseResevDate");
//		System.out.println("预定的日期："+chooseResevDate);	  			  
		Date date=null ;
		String conferagenda = "";
		SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
		try {
			date = DATE_FORMAT.parse(chooseResevDate);
//			System.out.println("转换格式之后的时间："+date);	 		

			String meetAgenda = request.getParameter("meetAgenda");
			String meetAgenda1 = request.getParameter("meetAgenda1");
			String meetAgenda2 = request.getParameter("meetAgenda2");
			conferagenda ="1:"+ meetAgenda+","+"2:"+meetAgenda1+","+"3:"+meetAgenda2+",";
//			System.out.println("预定会议议程："+conferagenda);	
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};

		Date now = new Date();
		java.sql.Date sql_date = new java.sql.Date(now.getTime());

		Boolean allow_font_styles = true;				
		Boolean allow_recording = true;
		Boolean allow_user_questions = false;
		Boolean appointment = false;
		Boolean auto_video_select = false;
		Boolean chat_moderated = false;
		Boolean chat_opened =false;
		String comment_field = "";
		String confno = "";
		Boolean deleted = false;
		int demo_time =0;
		long externalRoomId = 0;
		String externalRoomType = "";
		Boolean files_opened = false ;
		Boolean hide_actions_menu = false;
		Boolean hide_activities_and_actions = false;
		Boolean hide_chat = false;
		Boolean hide_files_explorer = false;
		Boolean hide_screen_sharing = false;
		Boolean hide_top_bar = false;
		Boolean hide_whiteboard = false;
		Boolean is_audio_only = false;
		Boolean is_closed = false;
		Boolean isdemoroom = false;
		Boolean ismoderatedroom = false;
		Boolean ispublic = false;
		String confersystemname = confername ;
		long numberOfPartizipants = 20;
		long owner_id = 0;
		String pin = "";
		String redirect_url = "";
		Boolean show_microphone_status = false;
		Boolean sip_enabled = false;		
		java.sql.Date starttime = sql_date;
		java.sql.Date updatetime = null;
		Boolean wait_for_recording = false;
		long roomtypes_id = 1;	
		String bookinghash = "";
		int insertedby =1;

		Booking newbooking = new Booking();
		newbooking.setName(username);
		newbooking.setDate(date);
		newbooking.setTime(timelots);
		newbooking.setRoomNum(bookingroomnum);
		newbooking.setConferparticipants(participants);
		newbooking.setConferType(confertype);
		newbooking.setConferName(confername);
		newbooking.setConferTheme(confertheme);
		newbooking.setConferAgenda(conferagenda);
		newbooking.setBookingHash(bookinghash);
		try {
			newbooking.AddBooking();
			MysqlAction mysqlAction = new MysqlAction();					
			mysqlAction.addBookingtoConferSystem(allow_font_styles, allow_recording, allow_user_questions, appointment, auto_video_select, chat_moderated, chat_opened, comment_field, confno, deleted, demo_time, externalRoomId, externalRoomType, files_opened, hide_actions_menu, hide_activities_and_actions, hide_chat, hide_files_explorer, hide_screen_sharing, hide_top_bar, hide_whiteboard, is_audio_only, is_closed, isdemoroom, ismoderatedroom, ispublic, confersystemname, numberOfPartizipants, owner_id, pin, redirect_url, show_microphone_status, sip_enabled, starttime, updatetime, wait_for_recording, roomtypes_id);
			mysqlAction.addOrganisation(insertedby, confername, starttime);
			int rooms_id= mysqlAction.getroom_idbyName(confername);
			session.setAttribute("rooms_id", rooms_id);
			System.out.println("rooms_id:"+rooms_id);

			int organisation_id = mysqlAction.getOrganization_idbyName(confername);
			System.out.println("organisation_id:"+organisation_id);
			mysqlAction.addrooms_organisation(rooms_id, organisation_id);			

			if(participant!=null){
				for(int i=0;i<participant.length;i++){
					int user_id = mysqlAction.getuser_idbyName(participant[i]);
					System.out.println("user_id:"+user_id);
					mysqlAction.addOrganisation_usersbyId(organisation_id, user_id);
				}
			}
				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("reservationInfo.jsp");
	}

}
