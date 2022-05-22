package model;
import java.sql.*; 

public class emp {
	
	private Connection connect() 
	 { 
	 Connection con = null; 
	 try
	 { 
	 Class.forName("com.mysql.jdbc.Driver"); 
	 
	 //DB connection  
	 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/paf_project","root",""); 
	 } 
	 catch (Exception e) 
	 {e.printStackTrace();} 
	 return con; 
	 } 
	 public String insertItem(String employeeID, String employeename, String employeemail ,String employeeage, String employeephonenumber, String employeetype, String employeeaddress, String employeegender) 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for inserting."; } 
	 // create a prepared statement
	 String query = " insert into employee(`ID`,`employeeID`,`employeename`,`employeemail`,`employeeage`,`employeephonenumber`, `employeetype`, `employeeaddress`, `employeegender`)"
	 + " values (?, ?, ?, ?, ?,?, ?, ?, ?)"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values
	 preparedStmt.setInt(1, 0); 
	 preparedStmt.setString(2, employeeID); 
	 preparedStmt.setString(3, employeename); 
	 preparedStmt.setString(4, employeemail); 
	 preparedStmt.setString(5, employeeage);
	 preparedStmt.setString(6, employeephonenumber);
	 preparedStmt.setString(7, employeetype);
	 preparedStmt.setString(8, employeeaddress);
	 preparedStmt.setString(9, employeegender);
	 preparedStmt.execute(); 
	 con.close();
	 
	 String newItems = readItems(); 
	 output = "{\"status\":\"success\", \"data\": \"" + 
	 newItems + "\"}"; 
	 

	 
	 
	 } 
	 catch (Exception e) 
	 
		 { 
		 output = "{\"status\":\"error\", \"data\":\"Error while inserting the item.\"}"; 
		 System.err.println(e.getMessage()); 
		 } 

	
	 return output; 
	 } 
	public String readItems() 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for reading."; 
	 } 
	 // Prepare the  table to be displayed
	 output = "<table border='1'><tr><th>Employee ID</th><th>Employee Name</th>" +
	 "<th>Employee Email</th>" + 
	 "<th>Employee Age</th>" + 
	 "<th>Employee Phone Number</th>" + 
	 "<th>Employee Type</th>" + 
	 "<th>Employee address</th>" + 
	 "<th>Employee Gender </th>" +
	 "<th>Update</th><th>Delete</th></tr>"; 
	 
	 String query = "select * from employee"; 
	 Statement stmt = con.createStatement(); 
	 ResultSet rs = stmt.executeQuery(query); 
	 // iterate through the rows in the result set
	 while (rs.next()) 
	 { 
	 String ID = Integer.toString(rs.getInt("ID")); 
	 String employeeID = rs.getString("employeeID"); 
	 String employeename = rs.getString("employeename"); 
	 String employeemail =rs.getString("employeemail"); 
	 String employeeage =rs.getString("employeeage");
	 String employeephonenumber =rs.getString("employeephonenumber");
	 String employeetype =rs.getString("employeetype");
	 String employeeaddress =rs.getString("employeeaddress");
	 String employeegender = rs.getString("employeegender"); 
	 
	 output += "<tr><td><input id='hidIDUpdate' name='hidIDUpdate' type='hidden' value='" + ID + "'>"
			 + employeeID + "</td>"; 
			 output += "<td>" + employeename + "</td>"; 
			 output += "<td>" + employeemail + "</td>"; 
			 output += "<td>" + employeeage + "</td>"; 
			 output += "<td>" + employeephonenumber + "</td>"; 
			 output += "<td>" + employeetype + "</td>"; 
			 output += "<td>" + employeeaddress + "</td>"; 
			 output += "<td>" + employeegender + "</td>"; 
			 // buttons
	 output += "<td><input name='btnUpdate'type='button' value='Update'class='btnUpdate btn btn-warning' data-id='"+ID+"'>"
			 + "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-id='"+ID+"'>" + "</td></tr>";
			 } 
	 con.close(); 
	 // Complete the 
	 output += "</table>"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while reading the items."; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 
	public String updateItem(String ID, String employeeID, String employeename, String employeemail ,String employeeage, String employeephonenumber, String employeetype, String employeeaddress, String employeegender)
	{ 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 {return "Error while connecting to the database for updating."; } 
		 // create a prepared statement
		 String query = "UPDATE employee SET employeeID=?, employeename=?, employeemail=?,employeeage=?, employeephonenumber=?, employeetype=?, employeeaddress=? , employeegender=? WHERE ID=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // binding values
		 preparedStmt.setString(1, employeeID); 
		 preparedStmt.setString(2, employeename); 
		 preparedStmt.setString(3, employeemail); 
		 preparedStmt.setString(4, employeeage); 
		 preparedStmt.setString(5, employeephonenumber); 
		 preparedStmt.setString(6, employeetype); 
		 preparedStmt.setString(7, employeeaddress); 
		 preparedStmt.setString(8, employeegender); 		 
		 preparedStmt.setInt(9, Integer.parseInt(ID)); 
		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 String newItems = readItems(); 
		 output = "{\"status\":\"success\", \"data\": \"" + 
		 newItems + "\"}"; 

		 } 
		 catch (Exception e) 
		 { 
			 output = "{\"status\":\"error\", \"data\":\"Error while updating the item.\"}"; 
					 System.err.println(e.getMessage()); 

		 } 
		 return output; 
		 } 
		public String deleteItem(String ID) 
		 { 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 {return "Error while connecting to the database for deleting."; } 
		 // create a prepared statement
		 String query = "delete from employee where ID=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt(ID)); 
		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 String newItems = readItems(); 
		 output = "{\"status\":\"success\", \"data\": \"" + 
		 newItems + "\"}"; 
		 } 
		 catch (Exception e) 
		 { 
			 output = "{\"status\":\"error\", \"data\": \"Error while deleting the item.\"}"; 
  
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
		 }
		
		 
}
