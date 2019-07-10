package com.ajay.crudtrial;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Student {
	
	int rollno;
	String name;
	String course;
	
	public Student(int rollno, String name, String course) {
		this.rollno = rollno;
		this.name = name;
		this.course = course;
	}
	public int getRollNo() {
		return rollno;
	}
	public void setRollNo(int rollno) {
		this.rollno = rollno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}

}
