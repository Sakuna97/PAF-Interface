<%@page import="com.Project"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Project Service</title>

<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/items.js"></script>

</head>
<body>
	<div class="container">
 		<div class="row">
 			<div class="col-6">
 				<h1>Items Management v10</h1>
 				
				<form id="formProject" name="formProject" method="post" action="Projects.jsp">
					Project Code: <input id="projectCode" name="projectCode" type="text" class="form-control form-control-sm"><br>
					Project Name: <input id="projectName" name="projectName" type="text" class="form-control form-control-sm"><br>
					Project Description: <input id="projectDesc" name="projectDesc" type="text" class="form-control form-control-sm"><br>
					Project Developed By: <input id="projectDevBy" name="projectDevBy" type="text" class="form-control form-control-sm"><br>
					Project price: <input id="projectPrice" name="projectPrice" type="text" class="form-control form-control-sm"><br>
					Project Category: <input id="projectCategory" name="projectCategory" type="text" class="form-control form-control-sm"><br>
					
					<div id="alertSuccess" class="alert alert-success"></div>
				 	<div id="alertError" class="alert alert-danger"></div>
				 	
					<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
					<input type="hidden" id="hidProjectIDSave" name="hidProjectIDSave" value="">
					
				</form>
	
				
				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>
				
				<br>
				<div id="divItemsGrid">
					 <%
						 Project itemObj = new Project(); 
					 	 out.print(itemObj.readProjects()); 
					 %>
				</div>

 
 			</div>
 		</div>
	</div>
	
</body>
</html>