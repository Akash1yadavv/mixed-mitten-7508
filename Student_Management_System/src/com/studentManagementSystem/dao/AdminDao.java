package com.studentManagementSystem.dao;

import java.util.List;

import com.studentManagementSystem.exception.AdminException;
import com.studentManagementSystem.exception.BatchException;
import com.studentManagementSystem.exception.CourseException;
import com.studentManagementSystem.exception.StudentException;
import com.studentManagementSystem.model.Course;
import com.studentManagementSystem.model.Student;

public interface AdminDao {
	
	public boolean adminLogIn(String userName, String password) throws AdminException;

	public String insertNewCourse(Course course) throws CourseException;
	
	public String updateCourseFee(int c_id,int fee) throws  CourseException;
	
	public String deleteCourse(int c_id) throws  CourseException;
	
	public Course searchInformationAboutCourse(int c_id) throws  CourseException;
	
	public String createBatchUnderCourse(int c_id, String batch,int totalSeats) throws BatchException;
	
	public int  allocateBatchToStudent(String email ,String batchname) throws BatchException, StudentException;
	
	public String updateTotalSeatsInBatch(String batch, int seats) throws BatchException;
	
	public List<Student> veiwStudentList()  throws StudentException;
	//View the students of every batch
}
