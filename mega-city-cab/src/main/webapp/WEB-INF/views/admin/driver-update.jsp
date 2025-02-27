<%@ include file="/WEB-INF/components/admin/check-admin-login.jsp" %>
<%@ include file="/WEB-INF/components/common-tags.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin - Driver Update</title>
    
    <jsp:include page="/WEB-INF/components/admin/admin-imports.jsp" />
</head>
<body>

	<jsp:include page="/WEB-INF/components/admin/admin-navbar.jsp" />
	<jsp:include page="/WEB-INF/components/toast.jsp" />
	
	<section class="form-container">
	     <form action="<%= request.getContextPath() %>/admin/drivers/update?id=${driver.id}" method="POST">
	        <h3>update driver</h3>
	        <input type="text" required minlength="3" value="${driver.name}" name="name" placeholder="Enter the name" maxlength="25" class="box">
	        <input type="text" required minlength="3" value="${driver.licenseNumber}" name="license_number" placeholder="Enter the driver license number" maxlength="20" class="box">
	        
	        <div class="flex-btn">
		        <a href="<%= request.getContextPath() %>/admin/drivers" class="option-btn">go back</a>
		        <input type="submit" value="update" name="submit" class="btn">
	        </div>
	     </form>
   	</section>

	
	<script src="<%= request.getContextPath() %>/assets/js/admin-script.js"></script>
</body>
</html>