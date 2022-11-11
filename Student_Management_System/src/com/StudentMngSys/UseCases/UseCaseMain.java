package com.StudentMngSys.UseCases;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.StudentMngSys.Been.Course;
import com.StudentMngSys.Been.Student;
import com.StudentMngSys.Exception.AdminException;
import com.StudentMngSys.Exception.BatchException;
import com.StudentMngSys.Exception.CourseException;
import com.StudentMngSys.Exception.StudentException;
import com.StudentMngSys.Inter_Impl.AdminDao;
import com.StudentMngSys.Inter_Impl.AdminDaoImpl;
import com.StudentMngSys.Inter_Impl.StudentDao;
import com.StudentMngSys.Inter_Impl.StudentDaoImpl;

public class UseCaseMain {

	public static void main(String[] args) {
		startRun();

	}
	public static void startRun() {
		Scanner sc = new Scanner(System.in);
		AdminDao dao1 = new AdminDaoImpl();
		StudentDao dao2 = new StudentDaoImpl();
		try {
			System.out.println("Enter '1': If You are Administrator. ");
			System.out.println("Enter '2': If you are Student. ");
			System.out.println("Enter '0': 'Exit Now' ");
			
			int num = sc.nextInt();
			switch(num){
			case 1:
				System.out.println("Enter your username:");
				String username = sc.next();
				System.out.println("Enter your Password");
				String password = sc.next();		
				try {
					boolean check =dao1.adminLogIn(username, password);
					System.out.println();
					if(check==true) {
						adminWork();
					}else{
						System.out.println("log in faild.. invalid user name or password");
						System.out.println("*************************************************");
						startRun();
						
					}
					
					
				} catch (AdminException e) {
					System.out.println(e.getMessage());
					startRun();
				}
				break;
			case 2:
				System.out.println("Enter your user name");
				username = sc.next();
				System.out.println("Enter your password");
				password = sc.next();
				try {
					boolean ch = dao2.loginStudent(username, password);
					if(ch==true) {
						studentWork(username);
					}else {
						System.out.println("log in faild.. invalid user name or password");
						System.out.println("*************************************************");
						startRun();
					}
				} catch (StudentException e) {
					System.out.println(e.getMessage());
					startRun();
				}

			}
		} catch (InputMismatchException e) {
			System.out.println("Oops.. invalid input..");
			startRun();
		}
		

	}
	
	public static void studentWork(String username) {
		Scanner sc = new Scanner(System.in);
		StudentDao dao2 = new StudentDaoImpl();
		System.out.println("Enter '1': update your password");
		System.out.println("Enter '2': Update your email id");
		System.out.println("Enter '3': Update your Mobile number");
		System.out.println("Enter '4': update your address");
		System.out.println("Enter '5': can see all the available course list and their seat availability");
		System.out.println("Enter '0': 'Log out'");
		int num2 = sc.nextInt();
		useCaseStudent(num2,dao2,sc,username);
	}
	
	
	private static void useCaseStudent(int num2, StudentDao dao2, Scanner sc,String username) {
		switch(num2) {
		case 1:
			System.out.println("Enter new Password");
			String pass = sc.next();
			try {
				boolean check = dao2.updatePassword(username, pass);
				if(check==true) {
					System.out.println("Your password successfully changed");
					
				}else {
					System.out.println("password has not changed ");
					studentWork(username);
				}
			} catch (StudentException e) {
				System.out.println(e.getMessage());
				studentWork(username);
			}
			System.out.println("*************************************************************");
			studentWork(username);
			break;
		case 2:
			System.out.println("Enter new Email id");
			String email  = sc.next();
			try {
				boolean check = dao2.updateEmail(username, email);
				if(check==true) {
					System.out.println("Your Email successfully changed");
					studentWork(email);
				}else {
					System.out.println("Email id has not changed ");
					studentWork(username);
				}
			} catch (StudentException e) {
				System.out.println(e.getMessage());
				studentWork(username);
			}
			System.out.println("*************************************************************");
			
			break;
		case 3:
			System.out.println("Enter new Mobile number");
			long mobile = sc.nextLong();
			try {
				try {
					boolean check = dao2.updateMobile(username, mobile);
					if(check==true) {
						System.out.println("Your Mobile number successfully changed");
						
					}else {
						System.out.println("Mobile number has not changed ");
						studentWork(username);
					}
				} catch (StudentException e) {
					System.out.println(e.getMessage());
					studentWork(username);
				}
			} catch (InputMismatchException e) {
				System.out.println("Oops.. invalid input..");
				studentWork(username);
			}
			System.out.println("*************************************************************");
			studentWork(username);
			break;
		case 4:
			System.out.println("Enter new address");
			sc.nextLine();
			String add = sc.nextLine();
			
			try {
				boolean check = dao2.updateAddress(username, add);
				if(check==true) {
					System.out.println("Your address successfully changed");
					
				}else {
					System.out.println("your address has not changed ");
					studentWork(username);
				}
			} catch (StudentException e) {
				System.out.println(e.getMessage());
				studentWork(username);
			}
			System.out.println("*************************************************************");
			studentWork(username);
			break;
		case 5:
		}
		
	}
	
	//***********************************************************************Admin work start******************************************************
	

	public static void adminWork() {
		AdminDao dao1 = new AdminDaoImpl();
		Scanner sc = new Scanner(System.in);
		try {
			System.out.println("Enter '1': Add new course");
			System.out.println("Enter '2': Update fee of course");
			System.out.println("Enter '3': Delete A course from any Training session new course");
			System.out.println("Enter '4': Search information about a course");
			System.out.println("Enter '5': Create Batch under  a course");
			System.out.println("Enter '6': Allocate students in a Batch under a course ");
			System.out.println("Enter '7': Update total seats of a batch");
			System.out.println("Enter '8': View the students of every batch. ");
			System.out.println("Enter '0': 'Log out' ");
			int num1 = sc.nextInt();
			useCaseCourseInsertion(num1,dao1,sc);
		} catch (InputMismatchException e) {
			System.out.println("Oops.. invalid input..");
			System.out.println("--------------------------------");
			adminWork();
		}
		
	}
	
	public static void useCaseCourseInsertion(int num,AdminDao dao1,Scanner sc){
		switch(num) {
		case 1:
			try {
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
			} catch (InputMismatchException e) {
				System.out.println("Oops.. invalid input..");
			}
			System.out.println("*********************************************************************");
			
			adminWork();
			break;
			
		case 2:
			System.out.println("You have selected:'Update fee of course'");
			try {
				System.out.println("Enter course id");
			    int c_id = sc.nextInt();
			    System.out.println("Enter course new Fee");
			    int fee = sc.nextInt();
			    try {
					System.out.println(dao1.updateCourseFee(c_id, fee));
					
				} catch (CourseException e) {
					System.out.println(e.getMessage());
				}
			} catch (InputMismatchException e) {
				System.out.println("Oops .. invalid input..");
			}
		    System.out.println("*********************************************************************");
			adminWork();
			break;
			
		case 3:
			System.out.println("You have selected: 'Delete A course from any Training session new course' ");
			try {
				System.out.println("Enetr Course id ");
				int c_id = sc.nextInt();
				try {
					System.out.println(dao1.deleteCourse(c_id));
				} catch (CourseException e) {
					System.out.println(e.getMessage());
				}
			} catch (InputMismatchException e) {
				System.out.println("oops.. invalid input");
			}
			System.out.println("**********************************************************************");
			adminWork();
			break;
		case 4:
			System.out.println("You have selected: 'Search information about a course' ");
			try {
				System.out.println("Enetr Course id ");
				int c_id = sc.nextInt();
				 try {
					System.out.println(dao1.searchInformationAboutCourse(c_id));
				} catch (CourseException e) {
					System.out.println(e.getMessage());
				}	
			} catch (InputMismatchException e) {
				System.out.println("Oops.. invalid input... ");
			}
			 System.out.println("************************************************************");
			 adminWork();
			 break;
		case 5:
			System.out.println("You have selected: Create Batch under  a course'");
			try {
				System.out.println("Enter course id ");
				int c_id = sc.nextInt();
				System.out.println("Enter Batch name");
				String batch = sc.next();
				try {
					System.out.println(dao1.createBatchUnderCourse(c_id, batch));
					
				} catch (BatchException e) {
					System.out.println(e.getMessage());
				}
			} catch (InputMismatchException e) {
				System.out.println("Oops.. invalid input...");
			}
			System.out.println("************************************************************");
			adminWork();
			break;
		case 6:
			System.out.println("You have selected:  Allocate students in a Batch under a course'");
			System.out.println("here you can allocate batch accoding to student joining month and his course");
		
			try {
				System.out.println("Enter Course Name ");
			    String c_name = sc.next();
			    System.out.println("Enter current month (in digit) ");
				int month = sc.nextInt();
				try {
					int x = dao1.allocateBatchToStudent(month, c_name);
					if(x>0)
						System.out.println(x+" students have batch allocated");
					else if(x==0)
						System.out.println(" invalid user name or password");
					
				} catch (BatchException | StudentException e) {
					System.out.println(e.getMessage());
				}
				
			} catch (InputMismatchException e) {
				System.out.println("oops... invalid input...");
			}
			System.out.println("S**********************************************************");
			adminWork();
			break;
		case 7:
			System.out.println("You have selected: Update total seats of a batch");
			try {
				System.out.println("Enetr batch name");
				String batch = sc.next();
				System.out.println("Enetr total seats");
				int seats = sc.nextInt();
				try {
					System.out.println(dao1.updateTotalSeatsInBatch(batch, seats));
					
				} catch (BatchException e) {
					System.out.println(e.getMessage());
				}
				
			} catch (InputMismatchException e) {
				System.out.println("Oops.. invaild input...");
				
			}
			System.out.println("**************************************************************");
			adminWork();
			break;
		case 8:
			System.out.println("You have selected:  View the students of every batch");
			try {
				try {
					List<Student> list = dao1.veiwStudentList();
					if(list!=null) {
						list.forEach(s -> System.out.println(s));
					}
					else
						System.out.println("Epmty list");
				} catch (StudentException e) {
					System.out.println(e.getMessage());
				}
			} catch (InputMismatchException e) {
				System.out.println("Oops.. invalid input...");
			}
			System.out.println("***************************************************************");
			adminWork();
			break;
		case 0:
			System.out.println("|************************|");
			System.out.println("|     --THANK YOU--      |");
			System.out.println("**************************");
			startRun();
			break;
			
		}
	}

}
