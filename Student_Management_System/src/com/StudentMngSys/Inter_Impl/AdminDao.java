package com.StudentMngSys.Inter_Impl;

import com.StudentMngSys.Been.Course;
import com.StudentMngSys.Exception.AdminException;
import com.StudentMngSys.Exception.BatchException;
import com.StudentMngSys.Exception.CourseException;

public interface AdminDao {
	
	public String adminLogIn(String userName, String password) throws AdminException;

	public String insertNewCourse(Course course) throws CourseException;
	
	public String updateCourseFee(int c_id,int fee) throws  CourseException;
	
	public String deleteCourse(int c_id) throws  CourseException;
	
	public Course searchInformationAboutCourse(int c_id) throws  CourseException;
	
	public String createBatchUnderCourse(int c_id, String batch) throws BatchException;
}
