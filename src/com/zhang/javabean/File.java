package com.zhang.javabean;

import java.util.Date;

public class File{
	private int id;
    private String filename ;
    private String uploader ;
    private Date uploadtime ;
    private int roomnum ;  
    private int bookingnum;     
    private int filesize;
    private String bookinghash;
    
    public int getFilesize() {
		return filesize;
	}
	public void setFilesize(int filesize) {
		this.filesize = filesize;
	}
	public String getBookinghash() {
		return bookinghash;
	}
	public void setBookinghash(String bookinghash) {
		this.bookinghash = bookinghash;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}	
    
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getUploader() {
		return uploader;
	}
	public void setUploader(String uploader) {
		this.uploader = uploader;
	}
	public Date getUploadtime() {
		return uploadtime;
	}
	public void setUploadtime(Date uploadtime) {
		this.uploadtime = uploadtime;
	}
	public int getRoomnum() {
		return roomnum;
	}
	public void setRoomnum(int roomnum) {
		this.roomnum = roomnum;
	}
	public int getBookingnum() {
		return bookingnum;
	}
	public void setBookingnum(int bookingnum) {
		this.bookingnum = bookingnum;
	}
	
	
}
