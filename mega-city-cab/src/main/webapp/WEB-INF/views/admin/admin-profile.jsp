<%@ page import="com.megacitycab.model.Admin" %>
<%@ include file="/WEB-INF/components/admin/check-admin-login.jsp" %>
<%@ include file="/WEB-INF/components/common-tags.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin - Update Profile</title>
    
    <jsp:include page="/WEB-INF/components/admin/admin-imports.jsp" />
</head>
<body>

	<jsp:include page="/WEB-INF/components/admin/admin-navbar.jsp" />
	<jsp:include page="/WEB-INF/components/toast.jsp" />

	<section class="form-container">

	<%
		Admin admin = (Admin) session.getAttribute("admin");	
	%>
      <form action="<%= request.getContextPath() %>/admin/profile" method="POST">
         <h3>update profile</h3>
         <input type="text" minlength="4" value=<%= admin.getUsername() %> name="username" maxlength="20" class="box username" required>
         <input type="password" minlength="5" name="current_password" maxlength="20" placeholder="Enter your current password" class="box" required>
         <input type="password" minlength="5" name="new_password" maxlength="20" placeholder="Enter your new password" class="box" required>
         <input type="password" minlength="5" name="confirm_password" maxlength="20" placeholder="Confirm your new password" class="box" required>
         <input type="submit" value="update profile" name="submit" class="btn">
      </form>

   	</section> 
	
	<script src="<%= request.getContextPath() %>/assets/js/admin-script.js"></script>
</body>
</html>