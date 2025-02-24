<%@ include file="/WEB-INF/components/admin/check-admin-login.jsp" %>
<%@ include file="/WEB-INF/components/common-tags.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin - Drivers</title>
    
    <jsp:include page="/WEB-INF/components/admin/admin-imports.jsp" />
</head>
<body>

	<jsp:include page="/WEB-INF/components/admin/admin-navbar.jsp" />
	<jsp:include page="/WEB-INF/components/toast.jsp" />

	<section class="form-container" style="min-height: 0;">

	     <form action="<%= request.getContextPath() %>/admin/drivers" method="POST">
	        <h3>create a driver</h3>
	        <input type="text" required minlength="3" name="name" placeholder="Enter the name" maxlength="25" class="box username">
	        <input type="text" required minlength="3" name="license_number" placeholder="Enter the driver license number" maxlength="20" class="box username">
	        <input type="submit" value="create" name="submit" class="btn">
	     </form>
   	</section>
   	
   	<section>
     	<div class="table-container">
            <h1>Registered Drivers</h1>
            <div class="table">
                <table>
                    <thead>
                        <tr class="heading">
                            <th>#</th>
                            <th>Name</th>
                            <th>License Number</th>
                            <th>Manage</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="driver" items="${drivers}" varStatus="status">
		                    <tr>
		                        <td>${status.index + 1}</td>
		                        <td>${driver.name}</td>
		                        <td>${driver.licenseNumber}</td>
		                        <td>
		                        	<div>
			                        	<a href="<%= request.getContextPath() %>/admin/drivers/update?id=${driver.id}">
			                        		<i class="ri-edit-box-line edit"></i>
			                        	</a>
			                        	<form action="<%= request.getContextPath() %>/admin/drivers/delete" method="POST" 
			                        		onsubmit="return confirm('Are you sure you want to delete driver ${driver.name}?');">
			                        		<input type="hidden" name="id" value="${driver.id}" />
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