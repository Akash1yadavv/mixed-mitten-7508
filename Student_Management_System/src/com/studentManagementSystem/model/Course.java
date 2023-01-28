package com.studentManagementSystem.model;

public class Course {
	private int courseId;
	private String courseName;
	private int fee;
	private String duration;
	private int totalsets;
	private int AvailableSeats;
	
	public Course(int courseId, String courseName, int fee, String duration, int totalsets) {
		super();
		this.courseId = courseId;
		this.courseName = courseName;
		this.fee = fee;
		this.duration = duration;
		this.totalsets = totalsets;
	}

	

	public Course(int courseId, String courseName, int fee, String duration, int totalsets, int availableSeats) {
		super();
		this.courseId = courseId;
		this.courseName = courseName;
		this.fee = fee;
		this.duration = duration;
		this.totalsets = totalsets;
		AvailableSeats = availableSeats;
	}




	@Override
	public String toString() {
		return "Course [courseId=" + courseId + ", courseName=" + courseName + ", fee=" + fee + ", duration=" + duration
				+ ", totalsets=" + totalsets + ", AvailableSeats=" + AvailableSeats + "]";
	}




	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}


	public String getCourseName() {
		return courseName;
	}


	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public int getFee() {
		return fee;
	}




	public void setFee(int fee) {
		this.fee = fee;
	}


	public String getDuration() {
		return duration;
	}


	public void setDuration(String duration) {
		this.duration = duration;
	}


	public int getTotalsets() {
		return totalsets;
	}

	public void setTotalsets(int totalsets) {
		this.totalsets = totalsets;
	}

	public int getAvailableSeats() {
		return AvailableSeats;
	}

	public void setAvailableSeats(int availableSeats) {
		AvailableSeats = availableSeats;
	}


	
	
	
}
