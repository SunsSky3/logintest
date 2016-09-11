package com.zhang.dao;
//import java.awt.Component;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

//import javax.swing.JOptionPane;







import com.zhang.javabean.*;


public class MysqlAction {
	private Connection connect = null;
	private Statement statement = null;
	private ResultSet resultSet = null;
	
    public static String url = "jdbc:mysql://localhost:3306/openmeetings";
    public static String user = "root";
    public static String password = "SunsSky";

	public void databaseConnection() throws Exception{
		// This will load the MySQL driver
		Class.forName("com.mysql.jdbc.Driver");
		// Setup the connection with the DB
		connect = DriverManager.getConnection(url,user,password); 
		// Statements allow to issue SQL queries to the database
		statement = connect.createStatement();
	}

	//method to get result by username and password "zhang"
	public int getResultbyUserNameandPassword(String username,String password) throws Exception {
		try {
			databaseConnection();	    	
			// Result set get the result of the SQL query
			resultSet = statement.executeQuery("select * from openmeetings.om_user where login='"+username+"' and password= '"+password+"'" );
			int a=0;
			if (resultSet.next()){
				a=1;  
			}
			else{
				a=0;	    		 
			}
			return a;

		} catch (Exception e) {
			throw e;

		} finally {
			close();
		}

	}

	//method to get the available time slots
	public String getNotAvailableTimes(Date date,int roomnum) throws Exception {
		try {
			databaseConnection();
			SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
			// Result set get the result of the SQL query
			resultSet = statement.executeQuery("select bookingnum,timeslotID from openmeetings.booking "
					+ " where date='"+DATE_FORMAT.format(date)+"' order by bookingnum desc");
			String timeslotstring = new String();//new String object to get the time slots
			while (resultSet.next()) {
				//accessing the time slot by ID  	 
				timeslotstring = timeslotstring+ resultSet.getString("timeslot")+",";	         	          

			} 
			return timeslotstring;

		} catch (Exception e) {
			throw e;

		} finally {
			close();
		}

	}

	//method to getInfo by bookingnum
	public Booking getBookingbyconferId(int conferId) throws Exception {
		try {
			databaseConnection();	    	
			// Result set get the result of the SQL query
			resultSet = statement.executeQuery("select * from openmeetings.booking where bookingnum='"+conferId+"'" );
			Booking newbooking = new Booking();
			// ResultSet is initially before the first data set
			while (resultSet.next()) {

				int roomnum = resultSet.getInt("roomnum");
				String confername = resultSet.getString("confername");
				String confertype = resultSet.getString("confertype");
				String confertheme = resultSet.getString("confertheme");	 
				String conferagenda = resultSet.getString("conferagenda");
				String participants = resultSet.getString("participants");
				String timeslotID = resultSet.getString("timeslotID");

				newbooking.setRoomNum(roomnum);
				newbooking.setConferName(confername);
				newbooking.setConferType(confertype);
				newbooking.setConferTheme(confertheme);
				newbooking.setConferAgenda(conferagenda);
				newbooking.setConferparticipants(participants);
				newbooking.setTime(timeslotID);       

			}
			return newbooking;

		} catch (Exception e) {
			throw e;

		} finally {
			close();
		}

	}

	//  method to get room_id by conferName from room table 
	public int getRoom_idbyconferName(String conferName) throws Exception {
		try {
			databaseConnection();	    	
			// Result set get the result of the SQL query
			resultSet = statement.executeQuery("select id from openmeetings.room where name ='"+conferName+"'" );
			int room_id = 0; 
			// ResultSet is initially before the first data set
			while (resultSet.next()) {

				room_id = resultSet.getInt("id");

			}
			return room_id;

		} catch (Exception e) {
			throw e;

		} finally {
			close();
		}

	}

	//method to get the users
	public User getUserbyName(String username) throws Exception {
		try {
			databaseConnection();

			resultSet = statement.executeQuery("select * from openmeetings.om_user where login = '"+ username+"'");

			User newuser = new User();
			// ResultSet is initially before the first data set
			while (resultSet.next()) {
				//  to get the columns via name

				int userID = resultSet.getInt("id");
				String uname = resultSet.getString("login");
				String password = resultSet.getString("password");
				String firstName = resultSet.getString("firstname");
				String lastName = resultSet.getString("lastname");
				int uType = resultSet.getInt("level_id");	          

				newuser.setUserId(userID);
				newuser.setUserName(uname);
				newuser.setPassWord(password);
				newuser.setFName(firstName);
				newuser.setLName(lastName);
				newuser.setUserType(uType);


			} 
			return newuser; 

		} catch (Exception e) {
			throw e;

		} finally {
			close();
		}

	}

	//method to add a new booking
	public void addBooking(String name, Date date,int roomnum,String confername,String confertype,String confertheme,String conferagenda, String participants,String time,String bookinghash) throws Exception {
		try {			
			databaseConnection();
			// change the format of the date 
			SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			statement.execute("insert into openmeetings.booking set name='"+name+"',date='"+DATE_FORMAT.format(date)+"',roomnum='"+roomnum+"',confername='"+confername+"',confertype='"+confertype+"',confertheme='"+confertheme+"',conferagenda='"+conferagenda+"', participants='"+participants+"',timeslotID='"+time+"',bookinghash='"+bookinghash+"'");			
		} catch (Exception e) {
			throw e;

		} finally {
			close();
		}
//		Component frame = null;
//		JOptionPane.showMessageDialog(frame,
//				"Booking is added successfully",
//				"",
//				JOptionPane.PLAIN_MESSAGE); 		

	}

	public void addBookingtoConferSystem(Boolean allow_font_styles,Boolean allow_recording,Boolean allow_user_questions,Boolean appointment,
			Boolean auto_video_select,Boolean chat_moderated,Boolean chat_opened,
			String comment_field,String confno,Boolean deleted,int demo_time,
			long externalRoomId,String externalRoomType,Boolean files_opened,
			Boolean hide_actions_menu,Boolean hide_activities_and_actions,
			Boolean hide_chat,Boolean hide_files_explorer,Boolean hide_screen_sharing,
			Boolean hide_top_bar,Boolean hide_whiteboard,Boolean is_audio_only,
			Boolean is_closed,Boolean isdemoroom,Boolean ismoderatedroom,Boolean ispublic,
			String confersystemname,long numberOfPartizipants,long owner_id,String pin,String redirect_url,
			Boolean show_microphone_status,Boolean sip_enabled,java.sql.Date  starttime,java.sql.Date  updatetime,
			Boolean wait_for_recording,long roomtypes_id) throws Exception {    
				try {
				    connect = DriverManager.getConnection(url,user,password);
		
					String queryString="insert into openmeetings.room("
							+ "allow_font_styles,allow_recording,allow_user_questions,appointment,auto_video_select,chat_moderated,chat_opened,comment_field,confno,deleted,demo_time,externalRoomId,externalRoomType,files_opened"
							+ ",hide_actions_menu,hide_activities_and_actions"
							+ ",hide_chat,hide_files_explorer,hide_screen_sharing"
							+ ",hide_top_bar,hide_whiteboard,is_audio_only,is_closed,isdemoroom"
							+",ismoderatedroom,ispublic,name,numberOfPartizipants,owner_id,pin,redirect_url,show_microphone_status,sip_enabled"
							+ ",starttime,updatetime,wait_for_recording,roomtypes_id) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
					PreparedStatement ps=connect.prepareStatement(queryString);
					ps.setBoolean(1, allow_font_styles);
					ps.setBoolean(2, allow_recording);
					ps.setBoolean(3, allow_user_questions);	     	 
					ps.setBoolean(4, appointment);
					ps.setBoolean(5, auto_video_select);
					ps.setBoolean(6, chat_moderated);
					ps.setBoolean(7, chat_opened);
					ps.setString(8, comment_field);
					ps.setString(9, confno);
					ps.setBoolean(10, deleted);
					ps.setInt(11, demo_time);
					ps.setLong(12, externalRoomId);
					ps.setString(13, externalRoomType);	     	 
					ps.setBoolean(14, files_opened );	
					ps.setBoolean(15, hide_actions_menu );
					ps.setBoolean(16, hide_activities_and_actions );
					ps.setBoolean(17, hide_chat);	
					ps.setBoolean(18, hide_files_explorer);
					ps.setBoolean(19, hide_screen_sharing);
					ps.setBoolean(20, hide_top_bar);	
					ps.setBoolean(21, hide_whiteboard);
					ps.setBoolean(22, is_audio_only);
					ps.setBoolean(23, is_closed);	
					ps.setBoolean(24, isdemoroom);
					ps.setBoolean(25, ismoderatedroom);
					ps.setBoolean(26, ispublic);
					ps.setString(27, confersystemname);
					ps.setLong(28, numberOfPartizipants);
					ps.setLong(29, owner_id);
					ps.setString(30, pin);
					ps.setString(31, redirect_url);
					ps.setBoolean(32, show_microphone_status);
					ps.setBoolean(33, sip_enabled);
					ps.setDate(34, starttime);	     	 
					ps.setDate(35, updatetime);
					ps.setBoolean(36, wait_for_recording);
					ps.setLong(37, roomtypes_id);
		
					ps.executeUpdate();
		
				} catch (Exception e) {
					throw e;
		
				} finally {
					close();
				}

	}

	// method add file info into table fileexploreritem 2015/4/15
	public void addFileInfo2Fileexploreritem(String filehash,String filename,java.sql.Date inserted,
			long room_id,java.sql.Date updated
			) throws Exception {    
		try {
			connect = DriverManager.getConnection("jdbc:mysql://localhost?"

              + "user=root&password=0000");             
			boolean Deleted = false;
			long external_file_id = 0;
			String external_type = null;
			long filesize = 0;
			int flv_height = 0;
			Integer flv_width = 0;//此处需要注意！
			long inserted_by = 1; 
			boolean is_chart = false;
			boolean is_folder = false;
			boolean is_image = false;
			boolean is_presentation = false;
			boolean is_stored_wml_file = false;
			boolean is_video = false;
			long owner_id =0; 
			long parent_fileexploreritem_id=0;
			String preview_image = null;
			String wml_file_path =null;

			String queryString="insert into openmeetings.fileexploreritem("
					+ "deleted,external_file_id,external_type,filehash,filename,filesize,flv_height,flv_width,inserted,inserted_by"
					+ ",is_chart,is_folder,is_image,is_presentation"
					+ ",is_stored_wml_file,is_video,owner_id"
					+ ",parent_fileexploreritem_id,preview_image"
					+",room_id,updated,wml_file_path) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps=connect.prepareStatement(queryString);
			ps.setBoolean(1, Deleted);
			ps.setLong(2, external_file_id);
			ps.setString(3, external_type);	     	 
			ps.setString(4, filehash);
			ps.setString(5, filename);
			ps.setLong(6, filesize);
			ps.setInt(7, flv_height);
			ps.setInt(8, flv_width);
			ps.setDate(9, inserted);
			ps.setLong(10, inserted_by);
			ps.setBoolean(11, is_chart);
			ps.setBoolean(12, is_folder);
			ps.setBoolean(13, is_image);	     	 
			ps.setBoolean(14, is_presentation );	
			ps.setBoolean(15, is_stored_wml_file );
			ps.setBoolean(16, is_video );
			ps.setLong(17, owner_id);	
			ps.setLong(18, parent_fileexploreritem_id);
			ps.setString(19, preview_image);
			ps.setLong(20, room_id);	
			ps.setDate(21, updated);
			ps.setString(22, wml_file_path);	        
			ps.executeUpdate();

		} catch (Exception e) {
			throw e;

		} finally {
			close();
		}

	}

	public void addOrganisation(int insertedby,String organisationname,java.sql.Date starttime)throws Exception{
		try {
		    connect = DriverManager.getConnection(url,user,password);			
			boolean deleted = false;
			// Statements allow to issue SQL queries to the database

			String queryString="insert into openmeetings.organisation("
					+"deleted,insertedby,name,starttime) values(?,?,?,?)";
			PreparedStatement ps=connect.prepareStatement(queryString);
			ps.setBoolean(1, deleted);
			ps.setInt(2, insertedby);
			ps.setString(3, organisationname);
			ps.setDate(4, starttime);	     	 

			int i= ps.executeUpdate();

			System.out.println("fileInfo:"+i);   	     	


		} catch (Exception e) {
			throw e;

		} finally {
			close();
		}
	} 
	public void addrooms_organisation(int room_id,int organisation_id)throws Exception{
		try {
		    connect = DriverManager.getConnection(url,user,password);			
			boolean deleted = false;
			// Statements allow to issue SQL queries to the database

			String queryString="insert into openmeetings.rooms_organisation("
					+"deleted,rooms_id,organisation_id) values(?,?,?)";
			PreparedStatement ps=connect.prepareStatement(queryString);
			ps.setBoolean(1, deleted);
			ps.setInt(2, room_id);
			ps.setInt(3, organisation_id);	  	 
			ps.executeUpdate();               

		} catch (Exception e) {
			throw e;

		} finally {
			close();
		}
	}

	public int getOrganization_idbyName(String organization_name) throws Exception {
		try {
			databaseConnection();
			// Result set get the result of the SQL query
			resultSet = statement.executeQuery("select id from openmeetings.organisation "
					+ " where name='"+organization_name+"' ");
			int id=0;
			while (resultSet.next()) {

				id = resultSet.getInt("id");         	          

			} 
			return id;

		} catch (Exception e) {
			throw e;

		} finally {
			close();
		}

	} 
	public int getroom_idbyName(String confer_name) throws Exception {
		try {
			databaseConnection();
			// Result set get the result of the SQL query
			resultSet = statement.executeQuery("select id from openmeetings.room "
					+ " where name='"+confer_name+"' ");
			int id=0;
			while (resultSet.next()) {

				id = resultSet.getInt("id");         	          

			} 
			return id;

		} catch (Exception e) {
			throw e;

		} finally {
			close();
		}

	} 
	public void addOrganisation_usersbyId(int organisation_id,int  user_id)throws Exception{
		try {
		    connect = DriverManager.getConnection(url,user,password);
			boolean deleted = false;
			// Result set get the result of the SQL query
			String querString = "insert into openmeetings.organisation_users(deleted,organisation_id,user_id) values(?,?,?)";   		                

			PreparedStatement ps = connect.prepareStatement(querString);
			ps.setBoolean(1, deleted);
			ps.setInt(2, organisation_id);
			ps.setInt(3, user_id);

			ps.executeUpdate();

		} catch (Exception e) {
			throw e;

		} finally {
			close();
		}
	}

	public int getuser_idbyName(String user_name) throws Exception {
		try {
			databaseConnection();
			// Result set get the result of the SQL query
			resultSet = statement.executeQuery("select id from openmeetings.om_user "
					+ " where login='"+user_name+"' ");
			int id=0;
			while (resultSet.next()) {

				id = resultSet.getInt("id");         	          

			} 
			return id;

		} catch (Exception e) {
			throw e;

		} finally {
			close();
		}

	} 
	public void addFileInfo(String filename,String uploader,Date uploadtime,int roomnum,int bookingnum,double filesize)throws Exception{
		try {
			databaseConnection();	    	
			// Result set get the result of the SQL query
			SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			statement.execute("insert into openmeetings.file set filename='"+filename+"',uploader='"+uploader+"',uploadtime='"+DATE_FORMAT.format(uploadtime)+"',roomnum='"+roomnum+"',bookingnum='"+bookingnum+"',filesize='"+filesize+"'");

		} catch (Exception e) {
			throw e;

		} finally {
			close();
		}
	}
	//method file Info by filename 
	public int getFileInfobyName(String filename) throws Exception {
		try {
			databaseConnection();
			// Result set get the result of the SQL query
			resultSet = statement.executeQuery("select filename from openmeetings.file "
					+ " where filename='"+filename+"' ");
			int a = 0;
			while (resultSet.next()) {

				a=1;         	          

			} 
			return a;

		} catch (Exception e) {
			throw e;

		} finally {
			close();
		}

	} 
	
	/**  
	* @Name: deleteFileByFileName
	* @Description: 通过文件名删除该文件在数据库中的信息
	* @Author: 汪志文（作者）
	* @Version: V1.00 （版本号）
	* @Create Date:2016-9-9 
	* @Parameters: fileName 文件名
	* @Return: 无
	*/
	public void deleteFileByFileName(String fileName) throws Exception{
		try {		
			databaseConnection();
			statement.execute(" delete from openmeetings.file where filename='"+fileName+"' ");
		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}
	}


	public String getBookingInfobyNameandDate(int roomnum,Date date) throws Exception {
		try {
			databaseConnection();
			SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
			// Result set get the result of the SQL query

			resultSet = statement.executeQuery("select * from openmeetings.booking where roomnum='"+roomnum+"'and date = '"+DATE_FORMAT.format(date)+"' ");
			String timeslot = new String();
			while (resultSet.next()) {

				timeslot = timeslot + resultSet.getString("timeslotID")+",";       	          

			} 
			return timeslot;

		} catch (Exception e) {
			throw e;

		} finally {
			close();
		}

	} 

	public ArrayList<Booking> getBookingInfobyBookingnum(int bookingnum) throws Exception {
		try {
			databaseConnection();	    	
			// Result set get the result of the SQL query
			resultSet = statement.executeQuery("select * from openmeetings.booking where bookingnum='"+bookingnum+"' ");
			ArrayList<Booking> list = new ArrayList<Booking>();

			// ResultSet is initially before the first data set
			while (resultSet.next()) {

				Booking newbooking = new Booking();
				String booker = resultSet.getString("name");
				Date conferdate = resultSet.getDate("date");
				int roomnum = resultSet.getInt("roomnum");
				String confername = resultSet.getString("confername");
				String confertype = resultSet.getString("confertype");
				String confertheme = resultSet.getString("confertheme");	 
				String conferagenda = resultSet.getString("conferagenda");
				String participants = resultSet.getString("participants");
				String timeslotID = resultSet.getString("timeslotID");
				String bookinghash = resultSet.getString("bookinghash");

				newbooking.setName(booker);
				newbooking.setDate(conferdate);
				newbooking.setRoomNum(roomnum);
				newbooking.setConferName(confername);
				newbooking.setConferType(confertype);
				newbooking.setConferTheme(confertheme);
				newbooking.setConferAgenda(conferagenda);
				newbooking.setConferparticipants(participants);
				newbooking.setTime(timeslotID);       
				newbooking.setBookingHash(bookinghash);              


				list.add(newbooking);
				System.out.println("查询启动了"+list+booker+confername+confertype);
			}
			return list;

		} catch (Exception e) {
			throw e;

		} finally {
			close();
		}

	}

	public void addRoom(String roomname,String roomcapacity,String roomlocation,String roomequipment,Date newroomtime)throws Exception{
		try {
			databaseConnection();	    	
			// Result set get the result of the SQL query
			SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			statement.execute("insert into openmeetings.room_huiqian set roomname='"+roomname+"',capacity='"+roomcapacity+"',location='"+roomlocation+"',equipment='"+roomequipment+"',date='"+DATE_FORMAT.format(newroomtime)+"'");

		} catch (Exception e) {
			throw e;

		} finally {
			close();
		}
//		Component frame = null;//message
//		JOptionPane.showMessageDialog(frame,
//				"room is added successfully",
//				"",
//				JOptionPane.PLAIN_MESSAGE);	
	}

	//  to close the resultSet and connection
	private void close() {
		try {
			if (resultSet != null) {
				resultSet.close();
			}

			if (statement != null) {
				statement.close();
			}

			if (connect != null) {
				connect.close();
			}
		} catch (Exception e) {

		}
	}

	//method to get all the users from the user table
	public String getAllUsers() throws Exception {
		try {
			databaseConnection();

			// Result set get the result of the SQL query
			resultSet = statement.executeQuery("select login from openmeetings.om_user");
			String allusers = new String();//new String object to store the users
			while (resultSet.next()) {
				//accessing the users by username 
				allusers = allusers+ resultSet.getString("login")+","; 

			} 
			return allusers;

		} catch (Exception e) {
			throw e;

		} finally {
			close();
		}

	}
	//by zhang
	public String getAllRooms() throws Exception{
		try {
			databaseConnection();
			resultSet = statement.executeQuery("select roomnum from openmeetings.room_huiqian");
			String allrooms = new String();
			while (resultSet.next()) {
				allrooms = allrooms + resultSet.getInt("roomnum") + ",";
			}
			return allrooms;
		} catch (Exception e) {
			throw e;
		}finally{
			close();
		}

	}
	//by zhang
	public ArrayList<Room> getRoomInfo() throws Exception{
		try {
			databaseConnection();

			resultSet = statement.executeQuery("select * from openmeetings.room_huiqian");

			ArrayList<Room> list = new ArrayList<Room>();

			while (resultSet.next()) {

				Room newroom = new Room();

				int roomnum = resultSet.getInt("roomnum");    				
				String location = resultSet.getString("location");
				String capacity = resultSet.getString("capacity");    				
				String equipment =resultSet.getString("equipment");

				newroom.setRoomnum(roomnum);
				newroom.setLocation(location);
				newroom.setCapacity(capacity);    				
				newroom.setEquipment(equipment);		

				list.add(newroom);
				System.out.println("room list:"+list);
			}
			return list;
		} catch (Exception e) {
			throw e;
		}finally{
			close();
		}

	}

	public ArrayList<Room> getRoomInfo(int roomnum) throws Exception{
		try {
			databaseConnection();

			resultSet = statement.executeQuery("select * from openmeetings.room_huiqian where roomnum = '"+roomnum+"'");

			ArrayList<Room> list = new ArrayList<Room>();

			while (resultSet.next()) {

				Room newroom = new Room();

				String location = resultSet.getString("location");
				String capacity = resultSet.getString("capacity");    				
				String equipment =resultSet.getString("equipment");

				newroom.setLocation(location);
				newroom.setCapacity(capacity);    				
				newroom.setEquipment(equipment);		

				list.add(newroom);
				System.out.println("room list:"+list);
			}
			return list;
		} catch (Exception e) {
			throw e;
		}finally{
			close();
		}

	}

	// by zhang
	public String getAllTimeslots() throws Exception{
		try {
			databaseConnection();
			resultSet = statement.executeQuery("select ID,timeslots.timeslot from openmeetings.timeslots ");
			String timeslotstring = new String();
			while (resultSet.next()) {
				timeslotstring = timeslotstring+resultSet.getString("ID")+",";
			}
			return timeslotstring;
		} catch (Exception e) {
			throw e;
		}finally{
			close();
		}

	}
	//
	public ArrayList<Booking> getBookingInfobyDate(String date) throws Exception {
		try {
			databaseConnection();	    	
			// Result set get the result of the SQL query
			resultSet = statement.executeQuery("select * from openmeetings.booking where date = '"+ date+"'" );
			ArrayList<Booking> list = new ArrayList<Booking>();

			// ResultSet is initially before the first data set
			while (resultSet.next()) {

				Booking newbooking = new Booking();
				int bookingNum = resultSet.getInt("bookingNum");
				String booker = resultSet.getString("name");
				int roomnum = resultSet.getInt("roomnum");
				String confername = resultSet.getString("confername");
				String confertype = resultSet.getString("confertype");
				String confertheme = resultSet.getString("confertheme");	 
				String conferagenda = resultSet.getString("conferagenda");
				String participants = resultSet.getString("participants");
				String timeslotID = resultSet.getString("timeslotID");
				String bookinghash = resultSet.getString("bookinghash");

				newbooking.setBookingNum(bookingNum);
				newbooking.setName(booker);
				newbooking.setRoomNum(roomnum);
				newbooking.setConferName(confername);
				newbooking.setConferType(confertype);
				newbooking.setConferTheme(confertheme);
				newbooking.setConferAgenda(conferagenda);
				newbooking.setConferparticipants(participants);
				newbooking.setTime(timeslotID);       
				newbooking.setBookingHash(bookinghash);              


				list.add(newbooking);
				System.out.println("查询启动了"+list+booker+confername+confertype);
			}
			return list;

		} catch (Exception e) {
			throw e;

		} finally {
			close();
		}

	}


	//by chenxin
	public String getlotbyDate(String date) throws Exception{
		// TODO Auto-generated method stub
		try {
			databaseConnection();
			// Result set get the result of the SQL query
			resultSet = statement.executeQuery("select timeslotID from openmeetings.booking where date='"+date+"'");
			String allslot = new String();		

			while (resultSet.next()) {
				allslot  = allslot + resultSet.getString("timeslotID") + ",";		    	 
			}			 
			return allslot;
		} catch (Exception e) {
			throw e; 
		} finally {
			close();
		}
	}

	//by chenxin
	public String getinquirebyDate(String date) throws Exception {

		try {
			databaseConnection();
			// Result set get the result of the SQL query
			resultSet = statement.executeQuery("select roomnum from openmeetings.booking where date='"+date+"'");

			String allroom = new String();

			while (resultSet.next()) {
				//  to get the columns via name
				allroom = allroom + resultSet.getInt("roomnum") + ",";		    	 
			}				 
			return allroom;
		} catch (Exception e) {
			throw e;   
		} finally {
			close();
		}
	}	    
	//by zhang
	public int  checkIdentityToReservation(String loginname,int bookingnum) throws Exception {

		try {
			databaseConnection();

			resultSet = statement.executeQuery("select participants from openmeetings.booking where bookingnum='"+bookingnum+"'");

			int a = 0;

			String participants = new String();
			String[] participants2=null;

			while (resultSet.next()) {

				participants = resultSet.getString("participants") ;		    	 
			}	
			participants2=participants.split(",");
			System.out.println("参会者："+participants2);
			for (int i = 0; i < participants2.length; i++) {
				System.out.println("参会者2："+participants2[i]);
				if(participants2[i].contentEquals(loginname)){
					a=1;
				}else {
					a=0;
				}

			}
			return a;
		} catch (Exception e) {
			throw e;   
		} finally {
			close();
		}
	}	    

	//method to get the fileInfo by bookinghash
	public ArrayList<File> getFileInfobyBookingHash(int bookingnum) throws Exception {
		ArrayList<File> list = new ArrayList<File>();
		try {
			databaseConnection();

			// Result set get the result of the SQL query
			resultSet = statement.executeQuery("select id,filename,uploader,uploadtime,roomnum,bookingnum,filesize from openmeetings.file "
					+ "where bookingnum= '"+bookingnum+"'");


			while (resultSet.next()) {
				File newfile = new File();
				int id = resultSet.getInt("id");
				String filename = resultSet.getString("filename");
				String uploader = resultSet.getString("uploader");
				Date uploadtime = resultSet.getDate("uploadtime");
				int roomnum = resultSet.getInt("roomnum");
				// int bookingnum = resultSet.getInt("bookingnum");
				int filesize = resultSet.getInt("filesize");

				newfile.setId(id);
				newfile.setFilename(filename);
				newfile.setUploader(uploader);
				newfile.setUploadtime(uploadtime);
				newfile.setRoomnum(roomnum);
				// newfile.setBookingnum(bookingnum);
				newfile.setFilesize(filesize);	  	

				list.add(newfile);

			} 
			return list;

		} catch (Exception e) {
			throw e;

		} finally {
			close();
		}

	}

	public void deleteBookingbybookingnum(int bookingnum,int room_id,int organisation_id) throws Exception {
		try {
			databaseConnection();  
			//sql to delete a booking from the booking table
			statement.execute(" delete from openmeetings.booking where bookingnum='"+bookingnum+"' ");
			statement.execute(" delete from openmeetings.file where bookingnum='"+bookingnum+"' ");
			statement.execute(" delete from openmeetings.room where id='"+room_id+"' ");
			statement.execute("delete from openmeetings.rooms_organisation where rooms_id='"+room_id+"'AND organisation_id='"+organisation_id+"' ");	
			statement.execute(" SET FOREIGN_KEY_CHECKS = 0");
			statement.execute(" delete from openmeetings.organisation where id='"+organisation_id+"' ");
			statement.execute(" SET FOREIGN_KEY_CHECKS = 1");
		} catch (Exception e) {
			throw e;

		} finally {
			close();
		}
//		Component frame = null;//message
//		JOptionPane.showMessageDialog(frame,
//				"Booking is deleted successfully",
//				"",
//				JOptionPane.PLAIN_MESSAGE);
	}
	//method to delete a booking
	public void deleteBooking(String name, Date date,int time) throws Exception {
		try {
			databaseConnection();
			// change the format of the date
			SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
			//sql to delete a booking from the booking table
			statement.execute("delete from openmeetings.booking where name='"+ name+"' and date='"+DATE_FORMAT.format(date)+"' and timeslotID='"+time+"'");
		} catch (Exception e) {
			throw e;

		} finally {
			close();
		}
//		Component frame = null;//message
//		JOptionPane.showMessageDialog(frame,
//				"Booking is deleted successfully",
//				"",
//				JOptionPane.PLAIN_MESSAGE);
	}

	public ArrayList<File> searchFile(String name, String people, String room, String start, String end) throws Exception{
		// TODO Auto-generated method stub  "select ID,timeslots.timeslot from room_booking.timeslots "
		ArrayList<File> list = new ArrayList<File>();
		String insert ="";
		String sql ="";
		try {
			databaseConnection();
			// Result set get the result of the SQL query
			if (!name.equals("")||!people.equals("")||!room.equals("")||!start.equals("")||!end.equals(""))
			{
				insert = insert+" where ";

				if(!name.equals("")) 
				{
					insert = insert + "filename LIKE '%"+name+"%' AND ";  
				}
				if(!people.equals(""))
				{
					insert = insert + "uploader='"+people+"' AND "; 
				}
				if(!room.equals("")&&!room.equals("null"))
				{
					insert = insert + "roomnum='"+room+"' AND "; 
				}
				if(!start.equals("")&&!end.equals(""))
				{
					insert = insert +"uploadtime > '"+start+"' AND uploadtime <='"+end+"' AND";
				}
				insert=insert.substring(0,insert.length()-4);

				sql = "select * from openmeetings.file" + insert;

				System.out.println(sql);
				resultSet = statement.executeQuery(sql);
				while(resultSet.next()){

					String filename = resultSet.getString("filename");
					String uploader = resultSet.getString("uploader");
					Date uploadtime = resultSet.getDate("uploadtime");
					int roomnum = resultSet.getInt("roomnum");
					int bookingNum =resultSet.getInt("bookingnum");

					File file = new File();	         

					file.setFilename(filename);
					file.setUploader(uploader);
					file.setUploadtime(uploadtime);
					file.setRoomnum(roomnum);
					file.setBookingnum(bookingNum);

					list.add(file);
				}
			}
			return list;

		} catch (Exception e) {
			throw e;

		} finally {
			close();
		}
	}

	


}

