package com.studentManagementSystem.dao;

import java.awt.image.DataBuffer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.studentManagementSystem.exception.CourseException;
import com.studentManagementSystem.exception.StudentException;
import com.studentManagementSystem.model.Course;
import com.studentManagementSystem.model.Student;
import com.studentManagementSystem.util.DBUtil;

public class StudentDaoImpl implements StudentDao{

	@Override
	public boolean loginStudent(String username, String password) throws StudentException {
		
		try (Connection conn  = DBUtil.provideConnection()){
			PreparedStatement ps = conn.prepareStatement("select * from student where email = ? and password = ?");
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				String name = rs.getString("FirstName") ;
				System.out.println("login successfully..");
				System.out.println(" ________________________________");
				System.out.println("|                                |");
				System.out.println("|       WELCOME BACK "+name.toUpperCase()+"      |");
				System.out.println("|________________________________|");
				System.out.println(" ");
	
				return true;
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return false;
	}
	
//******************************************************************Registration student****************************************************
	@Override
	public boolean registraterStudent(Student std) throws StudentException {
		
		System.out.println(std.getCourseName());
		try (Connection conn  = DBUtil.provideConnection()){
			PreparedStatement ps1= conn.prepareStatement("select * from course where courseName = ? ");
			ps1.setString(1,std.getCourseName());
			ResultSet rs1 = ps1.executeQuery();
			int totalSeats;
			int availableSeats = 0;
			int c_id = 0 ;
			while(rs1.next()) {
				  totalSeats = rs1.getInt(5);
				  availableSeats = rs1.getInt(6);
				  c_id = rs1.getInt(1);
			}
			if(availableSeats > 0) {
				PreparedStatement ps2 = conn.prepareStatement("insert into student (firstName, LastName, Email, password, Mobile,"
						+ " address, CourseId, JoiningDate) values(?,?,?,?,?,?,(select course_Id from course where courseName = ?),"
						+ "curDate())");
			
				ps2.setString(1, std.getFirstName());
				ps2.setString(2,std.getLastName());
				ps2.setString(3, std.getUserName());
				ps2.setString(4, std.getPassword());
				ps2.setLong(5, std.getMobile());
				ps2.setString(6, std.getAddress());
				ps2.setString(7, std.getCourseName());
				
				int x = ps2.executeUpdate();
				if(x>0) {
		
					try {
						PreparedStatement ps3 = conn.prepareStatement("update course set AvailableSeats = AvailableSeats - 1 where course_Id = ?" );
						ps3.setInt(1, c_id);
						int y = ps3.executeUpdate();
						
					} catch (SQLException e) {
						System.out.println(e.getMessage());
					}
					
					return true;
				}
			}else {
				System.out.println("registration failed..");
			}
			
			
		
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
//************************************************************************Update password***********************************************************

	
@Override
public boolean updatePassword(String username, String newpassword) throws StudentException {
	
		try (Connection conn  = DBUtil.provideConnection()){
			PreparedStatement ps = conn.prepareStatement("update student set password = ? where email = ?");
			ps.setString(1, newpassword);
			ps.setString(2, username);
			int x = ps.executeUpdate();
			if(x>0) {
				return true;
			}else{
				return false;
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	return false;
}

//***************************************************Update Email***************************************************************************

@Override
public boolean updateEmail(String username, String newEmail) throws StudentException {
	
	try (Connection conn  = DBUtil.provideConnection()){
		PreparedStatement ps = conn.prepareStatement("update student set email= ? where email = ?");
		ps.setString(1, newEmail);
		ps.setString(2, username);

		int x = ps.executeUpdate();
		if(x>0) {
			return true;
		}else{
			return false;
		}
		
	} catch (SQLException e) {
		System.out.println(e.getMessage());
	}
return false;
}

//******************************************************************Update Mobile***********************************************************************

@Override
public boolean updateMobile(String username, long newMobile) throws StudentException {
	
	try (Connection conn  = DBUtil.provideConnection()){
		PreparedStatement ps = conn.prepareStatement("update student set mobile = ? where email = ?");
		ps.setLong(1, newMobile);
		ps.setString(2, username);
		int x = ps.executeUpdate();
		if(x>0) {
			return true;
		}else{
			return false;
		}
		
	} catch (SQLException e) {
		System.out.println(e.getMessage());
	}
	return false;
}

//*********************************************************************Update Address***************************************************************
@Override
public boolean updateAddress(String username, String add) throws StudentException {
	
	try (Connection conn  = DBUtil.provideConnection()){
		PreparedStatement ps = conn.prepareStatement("update student set address = ? where email = ?");
		ps.setString(1, add);
		ps.setString(2, username);
		int x = ps.executeUpdate();
		if(x>0) {
			return true;
		}else{
			return false;
		}
		
	} catch (SQLException e) {
		System.out.println(e.getMessage());
	}
	return false;
}
//******************************************************************************View Course List********************************************************************
@Override
public List<Course> viewAllCourseList() throws CourseException {
	List<Course> list = new ArrayList<>();
	
	try(Connection conn = DBUtil.provideConnection()) {
		PreparedStatement ps = conn.prepareStatement("select * from course");
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			Course crs = new Course(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getString(4),rs.getInt(5),rs.getInt(6));
			list.add(crs);
		}
	} catch (SQLException e) {
		System.out.println(e.getMessage());
	}
	
	return list;
}




}
