package com.StudentMngSys.Been;

public class Admin {
	private int Admin_id;
	private String FirstName;
	private String LastName;
	private String UserName;
	private String Password;
	private long Mobile;
	private String Address;
	public Admin(int admin_id, String firstName, String lastName, String userName, String password, long mobile,
			String address) {
		super();
		Admin_id = admin_id;
		FirstName = firstName;
		LastName = lastName;
		UserName = userName;
		Password = password;
		Mobile = mobile;
		Address = address;
	}
	@Override
	public String toString() {
		return "Admin [Admin_id=" + Admin_id + ", FirstName=" + FirstName + ", LastName=" + LastName + ", UserName="
				+ UserName + ", Password=" + Password + ", Mobile=" + Mobile + ", Address=" + Address + "]";
	}
	
	
	
	public int getAdmin_id() {
		return Admin_id;
	}
	public void setAdmin_id(int admin_id) {
		Admin_id = admin_id;
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
	
	
	
	
	
}
