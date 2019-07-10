package com.ajay.crudtrial;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DataServiceHelper {
	
	public static DataServiceHelper dataServiceHelper=null;
	private Connection con=null;
	
	public static final String DB_URL="jdbc:mysql://localhost/mysql";
	public static final String DRIVER_NAME="com.mysql.jdbc.Driver"; 
	
	public Connection getConnection() throws ClassNotFoundException,SQLException {
	Class.forName(DRIVER_NAME);
	con = DriverManager.getConnection(DB_URL, "root", "root");
	return con;
	}
	
	private void closeConnection() throws SQLException{
	
		if(isConnectionOpen())
			con.close();
		con=null;

	}

	private boolean isConnectionOpen() {
		return (con!=null);
	}
	
	public static DataServiceHelper getInstance() {
		if (dataServiceHelper == null) {
		dataServiceHelper = new DataServiceHelper();
		}
		return dataServiceHelper;
	}
	
	public void executeUpdateQuery(String query) throws SQLException,
	ClassNotFoundException {
	Connection con = getConnection();
	Statement stmt = con.createStatement();
	stmt.execute(query);
	closeConnection();
	}
	
	public List<Student> executeQuery(String query) throws ClassNotFoundException,
	SQLException {
	Connection con = getConnection();
	PreparedStatement stmt = con.prepareStatement(query);
	ResultSet rs = stmt.executeQuery();
	List<Student> stLst = convertPojoList(rs);
	closeConnection();
	return stLst;
	}
	
	private List<Student> convertPojoList(ResultSet rs) throws SQLException {
		List<Student> stLst = new ArrayList<>();
		while (rs.next()) {
			Student std = new Student(rs.getInt("rollno"),rs.getString("name"), rs.getString("course"));
			stLst.add(std);
		}
		return stLst;
		}
	
}
