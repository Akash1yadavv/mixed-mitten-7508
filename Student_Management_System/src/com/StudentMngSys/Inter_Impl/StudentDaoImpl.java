package com.StudentMngSys.Inter_Impl;

import java.awt.image.DataBuffer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.StudentMngSys.Dao.DBUtil;
import com.StudentMngSys.Exception.StudentException;

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
				
	
				return true;
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

}
