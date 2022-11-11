package com.StudentMngSys.Inter_Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.StudentMngSys.Been.Course;
import com.StudentMngSys.Dao.DBUtil;
import com.StudentMngSys.Exception.AdminException;
import com.StudentMngSys.Exception.BatchException;
import com.StudentMngSys.Exception.CourseException;

public class AdminDaoImpl implements AdminDao {
	
	@Override
	public String adminLogIn(String userName, String password) throws AdminException {
		String message = "Invalid username OR password";
		
		
		try(Connection conn = DBUtil.provideConnection()) {
			
			PreparedStatement ps  = conn.prepareStatement("select * from Administrator where userName = ? and password=?");
			ps.setString(1, userName);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				System.out.println("Successfully login");
				return rs.getString("firstName")+ " "+"Your Welcome";
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return message;
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
	

}
