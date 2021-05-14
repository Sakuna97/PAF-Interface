$(document).ready(function()
{ 
	if ($("#alertSuccess").text().trim() == "") 
	 { 
	 $("#alertSuccess").hide(); 
	 } 
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
	var status = validateProjectForm(); 
	if (status != true) 
 	{ 
 		$("#alertError").text(status); 
 		$("#alertError").show(); 
 		return; 
 	} 
 	
	// If valid------------------------
	var type = ($("#hidProjectIDSave").val() == "") ? "POST" : "PUT"; 
	
 	$.ajax( 
 	{ 
 		url : "ProjectsAPI", 
 		type : type, 
 		data : $("#formProject").serialize(), 
 		dataType : "text", 
 		complete : function(response, status) 
 		{ 
 			onProjectSaveComplete(response.responseText, status); 
 		} 
	}); 
}); 

// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event) 
{ 
	 $("#hidItemIDSave").val($(this).data("projectID")); 
	 $("#ProjectCode").val($(this).closest("tr").find('td:eq(0)').text()); 
	 $("#ProjectName").val($(this).closest("tr").find('td:eq(1)').text());
	 $("#ProjectDesc").val($(this).closest("tr").find('td:eq(2)').text());
	 $("#ProjectDevBy").val($(this).closest("tr").find('td:eq(3)').text()); 
	 $("#ProjectPrice").val($(this).closest("tr").find('td:eq(4)').text()); 
	 $("#ProjectCategory").val($(this).closest("tr").find('td:eq(5)').text()); 
});

//DELETE==========================================
$(document).on("click", ".btnRemove", function(event)
{ 
 	$.ajax( 
 	{ 
 		url : "ProjectsAPI", 
 		type : "DELETE", 
 		data : "projectID=" + $(this).data("projectID"),
 		dataType : "text", 
 		complete : function(response, status) 
 		{ 
 			onProjectDeleteComplete(response.responseText, status); 
 		} 
	}); 
});


// CLIENT-MODEL================================================================
function validateProjectForm() 
{ 
	// CODE
	if ($("#ProjectCode").val().trim() == "") 
	 { 
	 	return "Insert Project Code."; 
	 } 
	 
	// NAME
	if ($("#ProjectName").val().trim() == "") 
	 { 
	 	return "Insert Project Name."; 
	 }
	 
	 // DESCRIPTION------------------------
	if ($("#projectDesc").val().trim() == "") 
	 { 
	 	return "Insert Project Description."; 
	 } 
	 
	  // DEVELOPEDBY------------------------
	if ($("#projectDevBy").val().trim() == "") 
	 { 
	 	return "Insert Project Developed By."; 
	 } 
	 
	 // PRICE-------------------------------
	if ($("#ProjectPrice").val().trim() == "") 
	 { 
	 	return "Insert Project Price."; 
	 } 
	 
	// is numerical value
	var tmpPrice = $("#ProjectPrice").val().trim(); 
	if (!$.isNumeric(tmpPrice)) 
	 { 
	 	return "Insert a numerical value for Project Price."; 
	 } 
	 
	// convert to decimal price
	 $("#projectPrice").val(parseFloat(tmpPrice).toFixed(2)); 
	 
	 // CATEGORY------------------------
	if ($("#projectCategory").val().trim() == "") 
	 { 
	 	return "Insert Project Category."; 
	 } 
}

function onProjectSaveComplete(response, status)
{ 
	if (status == "success") 
 	{ 
 		var resultSet = JSON.parse(response); 
 		
 		if (resultSet.status.trim() == "success") 
 		{ 
 			$("#alertSuccess").text("Successfully saved."); 
 			$("#alertSuccess").show(); 
 			
 			$("#divProjectsGrid").html(resultSet.data); 
 			
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
 		$("#hidProjectIDSave").val(""); 
 		$("#formProject")[0].reset(); 
}

function onProjectDeleteComplete(response, status)
{ 
	if (status == "success") 
	{ 
 		var resultSet = JSON.parse(response); 
 		if (resultSet.status.trim() == "success") 
 		{ 
 			$("#alertSuccess").text("Successfully deleted."); 
 			$("#alertSuccess").show(); 
 			$("#divProjectsGrid").html(resultSet.data); 
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