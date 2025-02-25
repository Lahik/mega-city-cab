<%@ include file="/WEB-INF/components/admin/check-admin-login.jsp" %>
<%@ include file="/WEB-INF/components/common-tags.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin - Registered Users</title>
    
    <jsp:include page="/WEB-INF/components/admin/admin-imports.jsp" />
</head>
<body>

	<jsp:include page="/WEB-INF/components/admin/admin-navbar.jsp" />
	<jsp:include page="/WEB-INF/components/toast.jsp" />

   	<section>
     	<div class="table-container">
            <h1>Registered Users</h1>
            <div class="table">
                <table>
                    <thead>
                        <tr class="heading">
                            <th>#</th>
                            <th>Name</th>
                            <th>Address</th>
                            <th>NIC</th>
                            <th>Telephone</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="user" items="${users}" varStatus="status">
		                    <tr>
		                        <td>${status.index + 1}</td>
		                        <td style="text-transform: capitalize;">${user.name}</td>
		                        <td style="max-width: 400px">${user.address}</td>
		                        <td>${user.nic}</td>
		                        <td>${user.telephone}</td>
		                        <td>
		                        	<div>
			                        	<form action="<%= request.getContextPath() %>/admin/users/delete" method="POST" 
			                        		onsubmit="return confirm('Are you sure you want to delete user ${user.name}?');">
			                        		<input type="hidden" name="id" value="${user.id}" />
				                        	<button type="submit">
				                        		<i class="ri-delete-bin-line delete"></i>
				                        	</button>
			                        	</form>
		                        	</div>
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