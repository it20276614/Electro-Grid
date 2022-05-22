package com;

//For REST Service
import javax.ws.rs.Consumes;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//For XML
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

//For JSON
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.emp;

 

@Path("/employees") 

public class employeeService {
	emp itemObj = new emp(); 
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String readItems() 
	 { 
		return itemObj.readItems();
	 } 
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertItem(@FormParam("employeeID") String employeeID, 
	 @FormParam("employeename") String employeename, 
	 @FormParam("employeemail") String employeemail, 
	 @FormParam("employeeage") String employeeage, 
	 @FormParam("employeephonenumber") String employeephonenumber, 
	 @FormParam("employeetype") String employeetype, 
	 @FormParam("employeeaddress") String employeeaddress, 
	 @FormParam("employeegender") String employeegender) 
	{ 
	 String output = itemObj.insertItem(employeeID, employeename, employeemail, employeeage, employeephonenumber, employeetype, employeeaddress, employeegender  ); 
	return output; 
	}
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateItem(String itemData) 
	{ 
	//Convert the input string to a JSON object 
	 JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject(); 
	//Read the values from the JSON object
	 String ID = itemObject.get("ID").getAsString(); 
	 String employeeID = itemObject.get("employeeID").getAsString(); 
	 String employeename = itemObject.get("employeename").getAsString(); 
	 String employeemail = itemObject.get("employeemail").getAsString();
	 String employeeage = itemObject.get(" employeeage").getAsString(); 
	 String employeephonenumber = itemObject.get("employeephonenumber").getAsString(); 
	 String employeetype = itemObject.get("employeetype").getAsString(); 
	 String employeeaddress = itemObject.get("employeeaddress").getAsString(); 
	 String employeegender= itemObject.get("employeegender").getAsString(); 
	 String output = itemObj.updateItem(ID, employeeID,employeename,employeemail,employeeage,employeephonenumber,employeetype,employeeaddress,employeegender); 
	return output; 
	}
	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteItem(String itemData) 
	{ 
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(itemData, "", Parser.xmlParser()); 
	 
	//Read the value from the element <itemID>
	 String paymentID = doc.select("ID").text(); 
	 String output = itemObj.deleteItem(ID); 
	return output; 
	}
}
