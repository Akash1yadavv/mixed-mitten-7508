package com.StudentMngSys.UseCases;

import java.util.Scanner;

import com.StudentMngSys.Been.Course;
import com.StudentMngSys.Exception.AdminException;
import com.StudentMngSys.Exception.BatchException;
import com.StudentMngSys.Exception.CourseException;
import com.StudentMngSys.Inter_Impl.AdminDao;
import com.StudentMngSys.Inter_Impl.AdminDaoImpl;
import com.StudentMngSys.Inter_Impl.StudentDao;
import com.StudentMngSys.Inter_Impl.StudentDaoImpl;

public class UseCaseMain {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter '1': If You are Administrator. ");
		System.out.println("Enter '2': If you are Student. ");
		int num = sc.nextInt();
		
		AdminDao dao1 = new AdminDaoImpl();
		StudentDao dao2 = new StudentDaoImpl();
		
		switch(num){
		case 1:
			System.out.println("Enter your username:");
			String username = sc.next();
			System.out.println("Enter your Password");
			String password = sc.next();
			
			try {
				System.out.println(dao1.adminLogIn(username, password));
				adminWork(dao1,sc);
				
			} catch (AdminException e) {
				System.out.println(e.getMessage());
			}
			break;
		case 2:
			
			
		}

	}
	
	public static void adminWork(AdminDao dao1, Scanner sc) {
		System.out.println("Enter '1': Add new course");
		System.out.println("Enter '2': Update fee of course");
		System.out.println("Enter '3': Delete A course from any Training session new course");
		System.out.println("Enter '4': Search information about a course");
		System.out.println("Enter '5': Create Batch under  a course");
		System.out.println("Enter '6': Allocate students in a Batch under a course ");
		System.out.println("Enter '7': Update total seats of a batch");
		System.out.println("Enter '8': View the students of every batch. ");
		System.out.println("Enter '9': 'Log Out ");
		int num1 = sc.nextInt();
		useCaseCourseInsertion(num1,dao1,sc);
	}
	
	public static void useCaseCourseInsertion(int num,AdminDao dao1,Scanner sc){
		switch(num) {
		case 1:
			System.out.println("You have selected:' Add new course'");
			System.out.println("Enter course Id ");
			int c_id = sc.nextInt();
			System.out.println("Enter course name ");
			String c_name = sc.next();
			System.out.println("Enter course fee ");
			int c_fee = sc.nextInt();
			System.out.println("Enter course duration ");
			String duration = sc.next();
			System.out.println("Enter course Total seats ");
			int t_seats = sc.nextInt();
			Course  crs =  new Course(c_id, c_name, c_fee, duration, t_seats);	
			try {
				System.out.println(dao1.insertNewCourse(crs));
			} catch (CourseException e) {
				
				System.out.println(e.getMessage());
			}
			System.out.println("                   ");
			
			adminWork(dao1, sc);
			break;
			
		case 2:
			System.out.println("You have selected:'Update fee of course'");
			System.out.println("Enter course id");
		    c_id = sc.nextInt();
		    System.out.println("Enter course new Fee");
		    int fee = sc.nextInt();
		    try {
				System.out.println(dao1.updateCourseFee(c_id, fee));
				
			} catch (CourseException e) {
				System.out.println(e.getMessage());
			}
		    System.out.println("                          ");
			adminWork(dao1, sc);
			break;
			
		case 3:
			System.out.println("You have selected: 'Delete A course from any Training session new course' ");
			System.out.println("Enetr Course id ");
			c_id = sc.nextInt();
			try {
				System.out.println(dao1.deleteCourse(c_id));
			} catch (CourseException e) {
				System.out.println(e.getMessage());
			}
			System.out.println(" ");
			adminWork(dao1, sc);
			break;
		case 4:
			System.out.println("You have selected: 'Search information about a course' ");
			System.out.println("Enetr Course id ");
			c_id = sc.nextInt();
			 try {
				System.out.println(dao1.searchInformationAboutCourse(c_id));
			} catch (CourseException e) {
				System.out.println(e.getMessage());
			}		 
			 System.out.println(" ");
			 adminWork(dao1, sc);
			 break;
		case 5:
			System.out.println("You have selected: Create Batch under  a course'");
			System.out.println("Enter course id ");
			c_id = sc.nextInt();
			System.out.println("Enter Batch name");
			String batch = sc.next();
			try {
				System.out.println(dao1.createBatchUnderCourse(c_id, batch));
				
			} catch (BatchException e) {
				System.out.println(e.getMessage());
			}
			System.out.println("  ");
			adminWork(dao1, sc);
			break;
		case 6:
//			System.out.println("You have selected:  Allocate students in a Batch under a course'");
//			System.out.println("here you can allocate batch accoding to student joining month and his course");
//			System.out.println("Enter ");
			
			
		}
	}

}
