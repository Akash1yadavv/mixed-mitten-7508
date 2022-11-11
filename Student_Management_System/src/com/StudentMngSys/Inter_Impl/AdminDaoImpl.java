package com.StudentMngSys.Inter_Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.StudentMngSys.Been.Course;
import com.StudentMngSys.Been.Student;
import com.StudentMngSys.Dao.DBUtil;
import com.StudentMngSys.Exception.AdminException;
import com.StudentMngSys.Exception.BatchException;
import com.StudentMngSys.Exception.CourseException;
import com.StudentMngSys.Exception.StudentException;


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
				message = course.getCourseName()+" course inserted in DataBase";
			
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
				int avlblSeats = rs.getInt(" AvailableSeats");
				Course crs = new Course(courseId, courseName, fee, duration,totalsets, avlblSeats);
				System.out.println("bellow you can see all information about this course...");
				course =crs;
				
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return course;
	}
//*****************************************************************Creating Batch****************************************************************

	@Override
	public String createBatchUnderCourse(int c_id, String batch) throws BatchException {
		String message  = "Batch creation failed please try again ";
		
		try(Connection conn = DBUtil.provideConnection()) {
			PreparedStatement ps = conn.prepareStatement("insert into batch (courseId,batchName) values(?,?)");
			
			ps.setInt(1, c_id);
			ps.setString(2, batch);
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
public int allocateBatchToStudent(int month, String c_name) throws BatchException, StudentException {
	int totalStudents = 0;
	
	
	try(Connection conn = DBUtil.provideConnection()) {
		
		PreparedStatement ps = conn.prepareStatement("select b.batchname, c.course_id,b.totalEnrolledStudent,b.totalSeats from course c, batch b, Student s where c.course_Id=b.courseId and c.Course_Id = s.CourseId and c.CourseName=? and month(s.JoiningDate)=? ");
		ps.setString(1, c_name);
		ps.setInt(2, month);
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			
			String batch= rs.getString("batchname");
			
			int c_id = rs.getInt("course_Id");
			int totalEnrolledStudent = rs.getInt("totalEnrolledStudent");
			int totalSeats = rs.getInt("totalSeats");
			
			PreparedStatement ps1 = conn.prepareStatement("Update student set batchName=? where month(joiningDate)=? and courseId=? and batchName =null");
			ps1.setString(1, batch);
			ps1.setInt(2, month);
			ps1.setInt(3, c_id);
			int x = ps1.executeUpdate();
			
			if(x>0) {
				
				PreparedStatement ps2;
				if(totalEnrolledStudent <= totalSeats) {
					ps2 = conn.prepareStatement("update batch set  totalEnrolledStudent = totalEnrolledStudent+?");
					ps2.setInt(1, x);
					totalStudents=x;
					int y = ps2.executeUpdate();
				}else {
					System.out.println("batch seats full you can not allocate to any student ");	
					return -1;
				}
			}			
		}

	} catch (SQLException e) {
		System.out.println(e.getMessage());
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
			Student std = new Student(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7), rs.getString(8), rs.getInt(9), rs.getString(10));
			list.add(std);
		}
	} catch (SQLException e) {
		System.out.println(e.getMessage());
		return list;
	}
	
	return list;
}
	

}
