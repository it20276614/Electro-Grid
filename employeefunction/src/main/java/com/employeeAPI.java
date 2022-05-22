package com;
import model.emp;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

 
/**
 * Servlet implementation class employeeAPI
 */
@WebServlet("/employeeAPI")
public class employeeAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	emp itemObj = new emp(); 

       
     
    public employeeAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		String output = itemObj.insertItem(request.getParameter("employeeID"), 
				 request.getParameter("employeename"), 
				request.getParameter("employeemail"),
				request.getParameter("employeeage"),
				request.getParameter("employeephonenumber"),
				request.getParameter("employeetype"),
				request.getParameter("employeeaddress"),				
				request.getParameter("employeegender")); 
				response.getWriter().write(output); 
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map paras = getParasMap(request); 
		String output = itemObj.updateItem(paras.get("hidIDSave").toString(), 
		paras.get("employeeID").toString(), 
		paras.get("employeename").toString(), 
		paras.get("employeemail").toString(),
		paras.get("employeeage").toString(), 
		paras.get("employeephonenumber").toString(),
		paras.get("employeetype").toString(),
		paras.get("employeeaddress").toString(),		
		paras.get("employeegender").toString()); 
		response.getWriter().write(output); 
	
	}

	 
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map paras = getParasMap(request); 
		 String output = itemObj.deleteItem(paras.get("ID").toString());
		 
		response.getWriter().write(output); 
	}
	private Map getParasMap(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>(); 
		try
		 { 
		 Scanner scanner = new Scanner(request.getInputStream(), "UTF-8"); 
		 String queryString = scanner.hasNext() ? 
		 scanner.useDelimiter("\\A").next() : ""; 
		 scanner.close(); 
		 String[] params = queryString.split("&"); 
		 for (String param : params) 
		 {  
		 String[] p = param.split("="); 
		 map.put(p[0], p[1]); 
		 } 
		 } 
		catch (Exception e) 
		 { 
		 } 
		return map; 
	}

}
