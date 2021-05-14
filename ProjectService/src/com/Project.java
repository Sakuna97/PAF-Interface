package com;

import java.sql.*;

import util.DB_Connection;

public class Project {
	
	public String readProjects(){ 
		
		 String output = ""; 
		 
		 try{ 
			 
			 	DB_Connection obj_DB_Connection= new DB_Connection();
				Connection con = obj_DB_Connection.connect();

		 if (con == null){ 
			 
			 return "Error while connecting to the database for reading."; 
		 } 
		 
				 // Prepare the html table to be displayed
				 output = "<table border='1'><tr><th>Project Code</th>" 
						 +"<th>Project Name</th><th>Project Price</th>"
						 + "<th>Project Description</th>" 
						 + "<th>Project By</th>"
						 + "<th>Project Category</th>" 
						 + "<th>Update</th><th>Remove</th></tr>"; 
				 
				 	String query = "select * from projects"; 
				 	Statement stmt = con.createStatement(); 
				 	ResultSet rs = stmt.executeQuery(query); 
				 
				 	// iterate through the rows in the result set
				 	while (rs.next()){ 
					 
				 		String projectID = Integer.toString(rs.getInt("projectID")); 
				 		String projectCode = rs.getString("projectCode"); 
				 		String projectName = rs.getString("projectName"); 
				 		String projectPrice = Double.toString(rs.getDouble("projectPrice")); 
				 		String projectDesc = rs.getString("projectDesc"); 
				 		String projectBy = rs.getString("projectBy"); 
				 		String projectCtg = rs.getString("projectCtg"); 
				 
				 		// Add a row into the html table
				 		output += "<tr><td><input id='hidProjectIDUpdate' name='hidProjectIDUpdate' type='hidden' value='" + projectID + "'>" + projectCode + "</td>";  
				 		output += "<td>" + projectName + "</td>"; 
				 		output += "<td>" + projectPrice + "</td>"; 
				 		output += "<td>" + projectDesc + "</td>";
				 		output += "<td>" + projectBy + "</td>";
				 		output += "<td>" + projectCtg + "</td>";
				 
				 		// buttons
				 		output += "<td><input name='btnUpdate' type='button' value='Update' "
								 + "class='btnUpdate btn btn-secondary' data-projectID='" + projectID + "'></td>"
								 + "<td><input name='btnRemove' type='button' value='Remove' "
								 + "class='btnRemove btn btn-danger' data-projectID='" + projectID + "'></td></tr>";  
				 	} 
				 	con.close(); 
				 	// Complete the html table
				 	output += "</table>"; 
				 	} 
				 
					catch (Exception e) { 
					
						output = "Error while reading the projects."; 
						System.err.println(e.getMessage()); 
					} 
				 
				 	return output; 
	}
	
	
	public String insertProject(String code, String name, String price, String desc, String by, String ctg){
		 
		 String output = ""; 
		 
		try{ 
		 
			DB_Connection obj_DB_Connection= new DB_Connection();
			Connection con = obj_DB_Connection.connect();
			
			 if (con == null){
				 return "Error while connecting to the database for inserting."; 
			 }
			 
			 // create a prepared statement
			 String query = " insert into projects (`projectID`,`projectCode`,`projectName`,`projectPrice`,`projectDesc`, `projectBy`, `projectCtg`)"
					 + " values (?, ?, ?, ?, ?, ?, ?)"; 
			 
			 PreparedStatement preparedStmt = con.prepareStatement(query); 
			 
			 	// binding values
			 	preparedStmt.setInt(1, 0); 
			 	preparedStmt.setString(2, code); 
			 	preparedStmt.setString(3, name); 
			 	preparedStmt.setDouble(4, Double.parseDouble(price)); 
			 	preparedStmt.setString(5, desc); 
			 	preparedStmt.setString(6, by);
			 	preparedStmt.setString(7, ctg); 
			 
			 	preparedStmt.execute(); 
			 	con.close(); 
			 
			 	String newProjects = readProjects(); 
				 output = "{\"status\":\"success\", \"data\": \"" + 
				 newProjects + "\"}"; 
		 	} 
		
			catch (Exception e){ 
				
				output = "{\"status\":\"error\", \"data\": \"Error while inserting the project.\"}"; 
				 
				 System.err.println(e.getMessage()); 
			} 
		
			return output; 
		}
	
	public String updateProject(String ID, String code, String name, String price, String desc, String by, String ctg) {
		 String output = ""; 
		 try {
		  
			 	DB_Connection obj_DB_Connection= new DB_Connection();
				Connection con = obj_DB_Connection.connect();
				
			 if (con == null) {
				 return "Error while connecting to the database for updating."; 
			 } 
		 // create a prepared statement
				 String query = "UPDATE projects SET projectCode=?,projectName=?,projectPrice=?,projectDesc=?,projectBy=?,projectCtg=? WHERE projectID=?"; 
				 
				 PreparedStatement preparedStmt = con.prepareStatement(query); 
				 
				 // binding values
				 preparedStmt.setString(1, code); 
				 preparedStmt.setString(2, name); 
				 preparedStmt.setDouble(3, Double.parseDouble(price)); 
				 preparedStmt.setString(4, desc);
				 preparedStmt.setString(5, by);
				 preparedStmt.setString(6, ctg);
				 preparedStmt.setInt(7, Integer.parseInt(ID)); 
				 
				 // execute the statement
				 preparedStmt.execute();
				 con.close(); 
				 
				 String newProjects = readProjects(); 
				 output = "{\"status\":\"success\", \"data\": \"" + 
				 newProjects + "\"}"; 
		 } 
		 catch (Exception e) {
		  
			 output = "{\"status\":\"error\", \"data\": \"Error while updating the project.\"}";
			 
			 System.err.println(e.getMessage());  
		 } 
		 return output;
	}
	
	
	public String deleteProject(String projectID) { 
		 String output = ""; 
		 try
		 {
			 	DB_Connection obj_DB_Connection= new DB_Connection();
				Connection con = obj_DB_Connection.connect();
				
			 if (con == null)
			 {
				 return "Error while connecting to the database for deleting."; 
			 } 
				 // create a prepared statement
				 String query = "delete from projects where projectID=?"; 
				 PreparedStatement preparedStmt = con.prepareStatement(query); 
				 
				 // binding values
				 preparedStmt.setInt(1, Integer.parseInt(projectID)); 
				 
				 // execute the statement
				 preparedStmt.execute(); 
				 con.close(); 
				 
				 String newProjects = readProjects(); 
				 output = "{\"status\":\"success\", \"data\": \"" + 
				 newProjects + "\"}"; 
		 } 
		 catch (Exception e) {
		  
			 output = "{\"status\":\"error\", \"data\": \"Error while deleting the project.\"}"; 
			 System.err.println(e.getMessage()); 
		 } 
		 return output; 
	 }
	

}
