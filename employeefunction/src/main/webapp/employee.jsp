<%@page import="model.emp"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employee Details</title>
<link rel="stylesheet" href="View/bootstrap.min.css">
<script src="Components/jquery-3.4.0.min.js"></script>
<script src="Components/emp.js"></script>

</head>
<body>
<div class="container"><div class="row"><div class="col-6"> 
<h1>Employee Details Form</h1>
<br>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>

 

<form id="formItem" name="formItem">
 Employee ID
 <input id="employeeID" name="employeeID" type="text" 
 class="form-control form-control-sm">
 
 <br>  Employee Name
 <input id="employeename" name="employeename" type="text" 
 class="form-control form-control-sm">
 
 <br> Employee Email
 <input id="employeemail" name="employeeemail" type="text" 
 class="form-control form-control-sm">
 
 <br>  Employee Age
 <input id="employeeage" name="employeeage" type="text" 
 class="form-control form-control-sm">
 
 <br>  Employee Phone Number
 <input id="employeephonenumber" name="employeephonenumber" type="text" 
 class="form-control form-control-sm">
 
 <br>  Employee Type
 <input id="employeetype" name="employeetype" type="text" 
 class="form-control form-control-sm">
 
 <br>  Employee address
 <input id="employeeaddress" name="employeeaddress" type="text" 
 class="form-control form-control-sm">
 
 <br> Employee Gender 
 <input id="employeegender" name="employeegende" type="text" 
 class="form-control form-control-sm">
 <br>
 <input id="btnSave" name="btnSave" type="button" value="Save" 
 class="btn btn-primary">
 <input type="hidden" id="hidIDSave"name="hidIDSave" value="">
</form>
 
<br>
<div id="divItemsGrid">
 <%
  
 emp itemObj = new emp(); 
 out.print(itemObj.readItems()); 
 %>
</div>
</div> </div> </div> 



</body>
</html>