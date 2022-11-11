package com.StudentMngSys.Inter_Impl;

import com.StudentMngSys.Exception.StudentException;

public interface StudentDao {
	public boolean loginStudent(String username, String password) throws StudentException;
	
	public boolean updatePassword(String username, String newpassword) throws StudentException;
	
	public boolean updateEmail(String username, String newEmail) throws StudentException;
	
	public boolean updateMobile(String username, long newMobile) throws StudentException;
	
	public boolean updateAddress(String username, String add) throws StudentException;
	
}
