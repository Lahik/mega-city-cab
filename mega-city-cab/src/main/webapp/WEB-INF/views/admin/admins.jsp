<%@ include file="/WEB-INF/components/admin/check-admin-login.jsp" %>
<%@ include file="/WEB-INF/components/admin/check-super-admin.jsp" %>
<%@ include file="/WEB-INF/components/common-tags.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin - Admins</title>
    
    <jsp:include page="/WEB-INF/components/admin/admin-imports.jsp" />
</head>
<body>

	<jsp:include page="/WEB-INF/components/admin/admin-navbar.jsp" />
	<jsp:include page="/WEB-INF/components/toast.jsp" />

	<section class="form-container" style="min-height: 0;">
	     <form action="<%= request.getContextPath() %>/admin/admins" method="POST">
	        <h3>create an admin</h3>
	        <input type="text" required minlength="3" name="username" placeholder="Enter the username" maxlength="20" class="box username">
	        <input type="password" minlength="5" name="password" maxlength="20" placeholder="Enter your password" class="box" required>
         	<input type="password" minlength="5" name="confirm_password" maxlength="20" placeholder="Confirm your password" class="box" required>
	        <input type="submit" value="create" name="submit" class="btn">
	     </form>
   	</section>
   	
   	<section>
     	<div class="table-container" style="max-width: 500px; margin-inline: auto;">
            <h1>Registered Admins</h1>
            <div class="table">
                <table>
                    <thead>
                        <tr class="heading">
                            <th>#</th>
                            <th>username</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="admin" items="${admins}" varStatus="status">
		                    <tr>
		                        <td>${status.index + 1}</td>
		                        <td>${admin.username}</td>
		                        <td>
			                        <c:if test="${admin.id ne 1}">
			                        	<div>
				                        	<form action="<%= request.getContextPath() %>/admin/admins/delete" method="POST" 
				                        		onsubmit="return confirm('Are you sure you want to delete admin ${admin.username}?');">
				                        		<input type="hidden" name="id" value="${admin.id}" />
					                        	<button type="submit">
					                        		<i class="ri-delete-bin-line delete"></i>
					                        	</button>
				                        	</form>
			                        	</div>
			                        </c:if>
		                        </td>
		                    </tr>
		                </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
     </section>
	
	<script src="<%= request.getContextPath() %>/assets/js/admin-script.js"></script>
</body>
</html>