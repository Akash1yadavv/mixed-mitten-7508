package com.studentManagementSystem.model;

public class Student {
	
	 private int roll;
	 private String FirstName; 
	 private String LastName;
	 private String UserName;  
	 private String Password;
	 private long Mobile;
	 private String Address;
	 private String Batch;
	 private int CourseId;
	 private String joiningDate;
	 private String courseName;
	 
	public Student(int roll, String firstName, String lastName, String userName, String password, long mobile,
			String address, String batch, int courseId, String joiningDate) {
		super();
		this.roll = roll;
		FirstName = firstName;
		LastName = lastName;
		UserName = userName;
		Password = password;
		Mobile = mobile;
		Address = address;
		Batch = batch;
		CourseId = courseId;
		this.joiningDate = joiningDate;
	}


	
	
	public Student(String firstName, String lastName, String userName, String password, long mobile, String address,
			String courseName) {
		super();
		FirstName = firstName;
		LastName = lastName;
		UserName = userName;
		Password = password;
		Mobile = mobile;
		Address = address;
		this.courseName = courseName;
	}




	@Override
	public String toString() {
		return "Student [roll=" + roll + ", FirstName=" + FirstName + ", LastName=" + LastName + ", UserName="
				+ UserName + ", Password=" + Password + ", Mobile=" + Mobile + ", Address=" + Address + ", Batch="
				+ Batch + ", CourseId=" + CourseId + ", joiningDate=" + joiningDate + "]";
	}




	public int getRoll() {
		return roll;
	}




	public void setRoll(int roll) {
		this.roll = roll;
	}




	public String getFirstName() {
		return FirstName;
	}




	public void setFirstName(String firstName) {
		FirstName = firstName;
	}




	public String getLastName() {
		return LastName;
	}




	public void setLastName(String lastName) {
		LastName = lastName;
	}




	public String getUserName() {
		return UserName;
	}




	public void setUserName(String userName) {
		UserName = userName;
	}




	public String getPassword() {
		return Password;
	}




	public void setPassword(String password) {
		Password = password;
	}




	public long getMobile() {
		return Mobile;
	}




	public void setMobile(long mobile) {
		Mobile = mobile;
	}




	public String getAddress() {
		return Address;
	}




	public void setAddress(String address) {
		Address = address;
	}




	public String getBatch() {
		return Batch;
	}




	public void setBatch(String batch) {
		Batch = batch;
	}




	public int getCourseId() {
		return CourseId;
	}




	public void setCourseId(int courseId) {
		CourseId = courseId;
	}




	public String getJoiningDate() {
		return joiningDate;
	}




	public void setJoiningDate(String joiningDate) {
		this.joiningDate = joiningDate;
	}




	public String getCourseName() {
		return courseName;
	}




	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}


	 
	 

}
