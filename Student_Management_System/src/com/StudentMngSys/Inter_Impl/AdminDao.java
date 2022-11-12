package com.StudentMngSys.Inter_Impl;

import java.util.List;
import com.StudentMngSys.Been.Course;
import com.StudentMngSys.Been.Student;
import com.StudentMngSys.Exception.AdminException;
import com.StudentMngSys.Exception.BatchException;
import com.StudentMngSys.Exception.CourseException;
import com.StudentMngSys.Exception.StudentException;

public interface AdminDao {
	
	public boolean adminLogIn(String userName, String password) throws AdminException;

	public String insertNewCourse(Course course) throws CourseException;
	
	public String updateCourseFee(int c_id,int fee) throws  CourseException;
	
	public String deleteCourse(int c_id) throws  CourseException;
	
	public Course searchInformationAboutCourse(int c_id) throws  CourseException;
	
	public String createBatchUnderCourse(int c_id, String batch,int totalSeats) throws BatchException;
	
	public int  allocateBatchToStudent(String email ,String c_name) throws BatchException, StudentException;
	
	public String updateTotalSeatsInBatch(String batch, int seats) throws BatchException;
	
	public List<Student> veiwStudentList()  throws StudentException;
	//View the students of every batch
}
