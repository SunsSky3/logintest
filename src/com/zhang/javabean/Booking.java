package com.zhang.javabean;

import java.util.Date;

import com.zhang.dao.MysqlAction;

public class Booking {
	//Booking class with variables and methods needed for a booking 
	private int bookingNum;
	private String name;
	private Date date;
	private int roomnum;
	private String participants;
	private String confername;
	private String confertype;
	private String confertheme;
	private String conferagenda;
	private String time;
	private String bookinghash;

	public Booking(){
		setBookingNum(0);
		setName("");
		setDate(null);		
		setRoomNum(0);
		setConferparticipants("");
		setConferName("");
		setConferType("");
		setConferTheme("");
		setConferAgenda("");
		setTime("");
		setBookingHash("");
	}
	public Booking(int bookingNum, String bookedBy, Date date,int roomnum, String confername,String confertype,String confertheme,String conferagenda,String time,String bookinghash){
		this.setBookingNum(bookingNum);
		this.setName(bookedBy);
		this.setDate(date);	
		this.setRoomNum(roomnum);
		this.setConferName(confername);
		this.setConferType(confertype);
		this.setConferTheme(confertheme);
		this.setConferAgenda(conferagenda);
		this.setTime(time);
		this.setBookingHash(bookinghash);
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getBookingNum() {
		return bookingNum;
	}
	public void setBookingNum(int bookingNum) {
		this.bookingNum = bookingNum;
	}
	public int getRoomNum() {
		return roomnum;
	}
	public void setRoomNum(int roomnum) {
		this.roomnum = roomnum;
	}
	public String getConferparticipants() {
		return participants;
	}
	public void setConferparticipants(String participants) {
		this.participants = participants;
	}
	public String getConferName() {
		return confername;
	}
	public void setConferName(String confername) {
		this.confername = confername;
	}
	public String getConferType() {
		return confertype;
	}
	public void setConferType(String confertype) {
		this.confertype = confertype;
	}
	public String getConferTheme() {
		return confertheme;
	}
	public void setConferTheme(String confertheme) {
		this.confertheme = confertheme;
	}

	public String getConferAgenda() {
		return conferagenda;
	}
	public void setConferAgenda(String conferagenda) {
		this.conferagenda = conferagenda;
	}

	public String getBookingHash(){
		return bookinghash;
	}
	public void setBookingHash(String bookinghash){
		this.bookinghash = bookinghash;
	}

	public void AddBooking() throws Exception {
		try {
			MysqlAction newMysqlAction = new MysqlAction();	
			newMysqlAction.addBooking(this.name, this.date, this.roomnum,this.confername,this.confertype,this.confertheme,this.conferagenda,this.participants,this.time,this.bookinghash);
		} catch (Exception e) {
			throw e;
		}

	}	

}



