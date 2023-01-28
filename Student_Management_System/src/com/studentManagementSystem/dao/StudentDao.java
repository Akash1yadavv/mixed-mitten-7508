package com.studentManagementSystem.dao;

import java.util.List;

import com.studentManagementSystem.exception.CourseException;
import com.studentManagementSystem.exception.StudentException;
import com.studentManagementSystem.model.Course;
import com.studentManagementSystem.model.Student;

public interface StudentDao {
	public boolean loginStudent(String username, String password) throws StudentException;
	
	public boolean updatePassword(String username, String newpassword) throws StudentException;
	
	public boolean updateEmail(String username, String newEmail) throws StudentException;
	
	public boolean updateMobile(String username, long newMobile) throws StudentException;
	
	public boolean updateAddress(String username, String add) throws StudentException;
	
	public List<Course> viewAllCourseList() throws CourseException;
	
	public boolean registraterStudent(Student std) throws StudentException;
	
}
