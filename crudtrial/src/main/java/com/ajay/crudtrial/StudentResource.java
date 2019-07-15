package com.ajay.crudtrial;

import java.util.List;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/students")
public class StudentResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Student> getStudents(){
		
		List<Student> stdlist=null;
		try {
			stdlist=StudentDao.getInstance().getStudentAll();
			System.out.println("hi new branch");
		}
		catch (Exception e) {
			
		}
		return stdlist;
	}
	
	@GET
	@Path("/{rollno}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Student> getStudent(@PathParam("rollno") int rollno){
		
		List<Student> stdlist=null;
		try {
			stdlist=StudentDao.getInstance().getStudent(rollno);
			System.out.println("hi there");
			Sysout.out.println("new way to trigger with webhook");
		}
		catch (Exception e) {
		}
		return stdlist;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_HTML)
	public String crearteUser(@FormParam("id") int id,@FormParam("name") String name,@FormParam("course") String course) {
		try {
			StudentDao.getInstance().createStudent(new Student(id,name,course));
			System.out.println("hi");
			return "Student created";
		}
		catch (Exception e) {
			return e+"";
		}
	}
	
}
