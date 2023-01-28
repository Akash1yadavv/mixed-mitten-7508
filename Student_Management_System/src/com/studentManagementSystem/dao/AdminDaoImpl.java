package com.studentManagementSystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.studentManagementSystem.exception.AdminException;
import com.studentManagementSystem.exception.BatchException;
import com.studentManagementSystem.exception.CourseException;
import com.studentManagementSystem.exception.StudentException;
import com.studentManagementSystem.model.Course;
import com.studentManagementSystem.model.Student;
import com.studentManagementSystem.util.DBUtil;


public class AdminDaoImpl implements AdminDao {
	
	@Override
	public boolean adminLogIn(String userName, String password) throws AdminException {
		boolean check =false;
		
		
		try(Connection conn = DBUtil.provideConnection()) {
			
			PreparedStatement ps  = conn.prepareStatement("select * from Administrator where userName = ? and password=?");
			ps.setString(1, userName);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				System.out.println("login Successfully..");
				System.out.println(" ________________________________");
				System.out.println("|                                |");
				System.out.println("|       WELCOME BACK "+rs.getString("firstName").toUpperCase()+"        |");
				System.out.println("|________________________________|");
				System.out.println(" ");
				return true;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
//*******************************************************************************Add New Course**********************************************************************
	
	
	@Override
	public String insertNewCourse(Course course) throws CourseException {
		String message  = "Insertion failed";
		
		try(Connection conn = DBUtil.provideConnection()) {
			PreparedStatement ps = conn.prepareStatement("insert into course values(?,?,?,?,?,?)");
			
			ps.setInt(1, course.getCourseId());
			ps.setString(2, course.getCourseName());
			ps.setInt(3, course.getFee());
			ps.setString(4,course.getDuration());
			ps.setInt(5,course.getTotalsets());
			ps.setInt(6, course.getTotalsets());
			int x = ps.executeUpdate();
			if(x>0)
				message = course.getCourseName()+" course added successfully..";
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return message;
	}
//**************************************************************************Update**************************************************************************


	@Override
	public String updateCourseFee(int c_id,int fee) throws CourseException {
		String message = "Fee Updation failed Plrease try again";
		
		try(Connection conn = DBUtil.provideConnection()) {
			
			PreparedStatement ps = conn.prepareStatement("update course set courseFee = ? where course_Id = ?");
			ps.setInt(1, fee);
			ps.setInt(2, c_id);
			int x = ps.executeUpdate();
			if(x>0) {
				message ="for course Id "+c_id+" Fee Updated";
			}
			
		} catch (SQLException e) {
			
			System.out.println(e.getMessage());
		}
		return message;
	}
//*********************************************************************Delete************************************************************************************
	@Override
	public String deleteCourse(int c_id) throws CourseException {
		String message = "Deletion has failed Please try again";
		
		try(Connection conn = DBUtil.provideConnection()) {
			PreparedStatement ps = conn.prepareStatement("delete from course where course_id = ?");
			
			ps.setInt(1, c_id);
			int x = ps.executeUpdate();
			if(x>0) {
				message  = x+" course deleted";
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return message;
	}
	//************************************************************Search Information about a Course*****************************************************
	
	
	@Override
	public Course searchInformationAboutCourse(int c_id) throws CourseException {
		
		Course course = null;
		
		try(Connection conn = DBUtil.provideConnection()) {
			PreparedStatement ps = conn.prepareStatement("select * from course where course_id = ?");
			
			ps.setInt(1, c_id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				int courseId=rs.getInt("course_Id");
				String courseName = rs.getString("courseName");
				int fee  = rs.getInt("courseFee");
				String duration= rs.getString("duration");
				int totalsets = rs.getInt("TotalSeats");
				int avlblSeats = rs.getInt("AvailableSeats");
				Course crs = new Course(courseId, courseName, fee, duration,totalsets, avlblSeats);
				System.out.println("bellow you can see all information about this course...");
				course =crs;
				
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new CourseException("Invalid course Details try again");
		}
		return course;
	}
//*****************************************************************Creating Batch****************************************************************

	@Override
	public String createBatchUnderCourse(int c_id, String batch, int totalSeats) throws BatchException {
		String message  = "Batch creation failed please try again ";
		
		try(Connection conn = DBUtil.provideConnection()) {
			PreparedStatement ps = conn.prepareStatement("insert into batch (courseId,batchName,totalSeats) values(?,?,?)");
			
			ps.setInt(1, c_id);
			ps.setString(2, batch);
			ps.setInt(3, totalSeats);
			int x = ps.executeUpdate();
			if(x>0)
				message =batch+" Batch created successfully ";
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return message;
	}
//**********************************************************Allocate Batch*****************************************************************************

	
@Override

public int allocateBatchToStudent(String email,String batchname) throws BatchException, StudentException {
	int totalStudents = 0;
	
	
	try(Connection conn = DBUtil.provideConnection()) {
		
//		PreparedStatement ps = conn.prepareStatement("select b.batchname, b.totalEnrolledStudent,b.totalSeats from course c,"
//												+ " batch b, Student s where c.course_Id=b.courseId and c.Course_Id = s.CourseId and c.CourseName=?  ");
		
		
		PreparedStatement ps = conn.prepareStatement("select c.course_Id, b.totalEnrolledStudent,b.totalSeats from course c,"
				+ " batch b, Student s where c.course_Id=b.courseId and c.Course_Id = s.CourseId and b.batchName=?  ");
		
		ps.setString(1, batchname);
		
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			
			int c_Id = rs.getInt("Course_Id");
			int totalEnrolledStudent = rs.getInt("totalEnrolledStudent");
			int totalSeats = rs.getInt("totalSeats");

				
			PreparedStatement ps2;
			if(totalEnrolledStudent < totalSeats) {
				PreparedStatement ps1 = conn.prepareStatement("Update student set batchName=? where Email =?");
				ps1.setString(1, batchname);
				ps1.setString(2, email);
				int x = ps1.executeUpdate();
				if(x>0) {
					ps2 = conn.prepareStatement("update batch set  totalEnrolledStudent = totalEnrolledStudent+? where batchName = ? ");
					ps2.setInt(1, x);
					ps2.setString(2, batchname);
					totalStudents=5;
					int y = ps2.executeUpdate();
					return totalStudents;
					
				}else {
					System.out.println("Invalid user name OR student already assigned in any batch");
					return -1;
				}
			}else {
				System.out.println("batch seats full you can not allocate to any student ");	
				return -1;
			}			
		}

	} catch (SQLException e) {
		throw new BatchException("Invalid user name or course name !");
		//return 2;
	}
	return totalStudents;
}
//*****************************************************************Update seats In Batch*************************************************

@Override
public String updateTotalSeatsInBatch(String batch, int seats) throws BatchException {
	String message = "Seat updation failed";
	
	try (Connection conn = DBUtil.provideConnection()){
		PreparedStatement ps = conn.prepareStatement("update batch set totalSeats=? where batchName = ?");
		ps.setInt(1, seats);
		ps.setString(2, batch);
		int x =ps.executeUpdate();
		if(x>0) {
			message = "Seats updated successfully";
		}
	} catch (SQLException e) {
		message = e.getMessage();
	}
	return message;
}
//*********************************************************************View Students List**************************************************


@Override
public List<Student> veiwStudentList() throws StudentException {
	List<Student> list = new ArrayList<>();
	
	Connection conn = DBUtil.provideConnection();
	try {
		PreparedStatement ps = conn.prepareStatement("select * from student order by batchName ASC");
		ResultSet rs =ps.executeQuery();
		while(rs.next()) {
			Student std = new Student(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getLong(6), rs.getString(7), rs.getString(8), rs.getInt(9), rs.getString(10));
			list.add(std);
		}
		//return list;
	} catch (SQLException e) {
		System.out.println(e.getMessage());
		
	}
	
	return list;
}
	

}
