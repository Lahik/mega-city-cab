<%@ include file="/WEB-INF/components/common-tags.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin - Login</title>
    
    <jsp:include page="/WEB-INF/components/admin/admin-imports.jsp" />
</head>
<body>

	<jsp:include page="/WEB-INF/components/toast.jsp" />

	<section class="form-container">

      <form action="<%= request.getContextPath() %>/admin/login" method="POST">
      	 <jsp:include page="/WEB-INF/components/csrf-token.jsp" />
         <h3>admin login</h3>
         <input type="text" name="username" minlength="4" maxlength="20" value="${username}" required placeholder="Enter your username" class="box">
         <input type="password" name="password" minlength="5" maxlength="20" required placeholder="Enter your password" class="box">
         <input type="submit" value="login" name="submit" class="btn">
      </form>

   	</section>

 	<script src="<%= request.getContextPath() %>/assets/js/admin-script.js"></script>
</body>
</html>