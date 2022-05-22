 $(document).ready(function()
{

	$("#alertSuccess").hide();
	$("#alertError").hide();
});
// SAVE ============================================
$(document).on("click", "#btnSave", function(event) 
{ 
// Clear alerts---------------------
 $("#alertSuccess").text(""); 
 $("#alertSuccess").hide(); 
 $("#alertError").text(""); 
 $("#alertError").hide(); 
// Form validation-------------------
var status = validateItemForm(); 
if (status != true) 
 { 
 $("#alertError").text(status); 
 $("#alertError").show(); 
 return; 
 } 
// If valid------------------------
var type = ($("#hidIDSave").val() == "") ? "POST" : "PUT"; 
 $.ajax( 
 { 
 url : "employeeAPI", 
 type : type, 
 data : $("#formItem").serialize(), 
 dataType : "text", 
 complete : function(response, status) 
 { 
 onItemSaveComplete(response.responseText, status); 
 } 
 });   
 


});
function onItemSaveComplete(response, status) 
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully saved."); 
 $("#alertSuccess").show(); 
 $("#divItemsGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
 $("#alertError").text("Error while saving."); 
 $("#alertError").show(); 
 } else
 { 
 $("#alertError").text("Unknown error while saving.."); 
 $("#alertError").show(); 
 } 
 14
 $("#hidIDSave").val(""); 
 $("#formItem")[0].reset(); 
}  

// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event) 
{ 
$("#hidIDSave").val($(this).data("id")); 
 $("#employeeID").val($(this).closest("tr").find('td:eq(0)').text()); 
 $("#employeename").val($(this).closest("tr").find('td:eq(1)').text()); 
 $("#employeemail").val($(this).closest("tr").find('td:eq(2)').text());
 $("#employeeage").val($(this).closest("tr").find('td:eq(3)').text()); 
 $("#employeephonenumber").val($(this).closest("tr").find('td:eq(4)').text()); 
 $("#employeetype").val($(this).closest("tr").find('td:eq(5)').text()); 
 $("#employeeaddress").val($(this).closest("tr").find('td:eq(6)').text());  
 $("#employeegender").val($(this).closest("tr").find('td:eq(7)').text()); 
});
 // DELETE==========================================
 $(document).on("click", ".btnRemove", function(event) 
{ 
 $.ajax( 
 { 
 url : "employeeAPI", 
 type : "DELETE", 
 data : "ID=" + $(this).data("id"),
 dataType : "text", 
 complete : function(response, status) 
 { 
 onItemDeleteComplete(response.responseText, status); 
 } 
 }); 
 });
 function onItemDeleteComplete(response, status) 
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully deleted."); 
 $("#alertSuccess").show(); 
 $("#divItemsGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
 $("#alertError").text("Error while deleting."); 
 $("#alertError").show(); 
 } else
 { 
 $("#alertError").text("Unknown error while deleting.."); 
 $("#alertError").show(); 
 } 
}
 
 
// CLIENT-MODEL================================================================
function validateItemForm() 
{ 
// CODE
if ($("#employeeID").val().trim() == "") 
 { 
 return "Insert Employee ID."; 
 } 
// NAME
if ($("#employeename").val().trim() == "") 
 { 
 return "Insert the Employee Name."; 
 } 
// EMAIL
if ($("#employeemail").val().trim() == "") 
 { 
 return "Insert the Employee Email."; 
 } 
// AGE
if ($("#employeeage").val().trim() == "") 
 { 
 return "Insert the Employee Age ."; 
 } 
// PHONE NUMBER
if ($("#employeephonenumber").val().trim() == "") 
 { 
 return "Insert the Employee Phone Number ."; 
 } 
// TYPE
if ($("#employeetype").val().trim() == "") 
 { 
 return "Insert the Employee Type ."; 
 } 
// ADDRESS
if ($("#employeeaddress").val().trim() == "") 
 { 
 return "Insert the Employee Address ."; 
 } 
 
// GENDER
if ($("#employeegender").val().trim() == "") 
 { 
 return "Insert the Employee Gender ."; 
 } 
 

return true; 
}
