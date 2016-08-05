package com.zhang.javabean;
//User class 
public class User {

	private String userName;
	private String firstName;
	private String lastName;
	private int userType;
	public String passWord;
	private static int userId;
	
	public User(){
		userName = "";
		passWord = "";
	}
	public User(int userId,String userName,String passWord, String firstName){
		User.userId =userId;
		this.userName =userName;
		this.passWord = passWord;
		this.firstName = firstName;
		
	}
	public String changeUserName(){
		return getUserName();
		
	}
	
	public String changePassword(){
		return passWord;
		
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFName(String name){
		this.firstName = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLName(String last){
		this.lastName = last;
	}
	public int getUserType() {
		return userType;
	}
	public void setUserType(int uType){
		this.userType = uType;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord){
		this.passWord = passWord;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
}
