package com.ajay.crudtrial;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDao {

public static StudentDao studentDao = new StudentDao();
public static final String GET_STUDENT="SELECT * FROM Student";
public static final String INSERT_STUDENT="Insert into Student";

public List<Student> getStudentAll() throws ClassNotFoundException, SQLException {
List<Student> ls = new ArrayList<>();
System.out.println("inside dao");
ls=DataServiceHelper.getInstance().executeQuery(GET_STUDENT);
return ls;
}
 
public List<Student> getStudent(int rollno) throws ClassNotFoundException, SQLException{
String SQL_WHERE_CASE=" where rollno='"+rollno+"'";
List<Student> als=DataServiceHelper.getInstance().executeQuery(GET_STUDENT+SQL_WHERE_CASE);
return als;
}
 
public void createStudent(Student std) throws SQLException, ClassNotFoundException {
String SQL_WHERE_CASE=" values("+std.getRollNo()+",'" + std.getName() + "','" + std.getCourse() + "')";
DataServiceHelper.getInstance().executeUpdateQuery(INSERT_STUDENT+SQL_WHERE_CASE);
}
public static StudentDao getInstance() {
return studentDao;
}
}
