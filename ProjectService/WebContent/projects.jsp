<%@ page import="com.Project"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Project Service</title>

<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/projects.js"></script>

</head>
<body>
	<div class="container">
 		<div class="row">
 			<div class="col-6">
 				<h1>Items Management v10</h1>
 				
				<form id="formProject" name="formProject" method="post" action="projects.jsp">
					Project Code: <input id="projectCode" name="projectCode" type="text" class="form-control form-control-sm"><br>
					Project Name: <input id="projectName" name="projectName" type="text" class="form-control form-control-sm"><br>
					Project price: <input id="projectPrice" name="projectPrice" type="text" class="form-control form-control-sm"><br>
					Project Description: <input id="projectDesc" name="projectDesc" type="text" class="form-control form-control-sm"><br>
					Project Developed By: <input id="projectBy" name="projectBy" type="text" class="form-control form-control-sm"><br>
					Project Category: <input id="projectCtg" name="projectCtg" type="text" class="form-control form-control-sm"><br>
					
					<div id="alertSuccess" class="alert alert-success"></div>
				 	<div id="alertError" class="alert alert-danger"></div>
				 	
					<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
					<input type="hidden" id="hidProjectIDSave" name="hidProjectIDSave" value="">
					
				</form>
	
				
				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>
				
				<br>
				<div id="divProjectsGrid">
					 <%
						 Project projectObj = new Project(); 
					 	 out.print(projectObj.readProjects()); 
					 %>
				</div>

 
 			</div>
 		</div>
	</div>
	
</body>
</html>