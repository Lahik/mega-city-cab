<%@ include file="/WEB-INF/components/common-tags.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin - Dashboard</title>
    
    <jsp:include page="/WEB-INF/components/admin/admin-imports.jsp" />
</head>
<body>

	<jsp:include page="/WEB-INF/components/admin/admin-navbar.jsp" />
	<jsp:include page="/WEB-INF/components/toast.jsp" />

	<section>
		<div class="box-container">
	
	      <div class="box">
	      <%@ page import="com.megacitycab.model.Admin" %>
	      <% Admin admin = (Admin) session.getAttribute("admin"); %>
	         <h3>Welcome!</h3>
	         <p style="text-transform: capitalize;">${admin.username}</p>
	         <a href="<%= request.getContextPath() %>/admin/profile" class="btn">update profile</a>
	      </div>
	      
	      <% if (admin.getId() == 1) { %>
			  <div class="box">
		         <h3>${adminCount}</h3>
		         <p>Admins Registered</p>
		         <a href="<%= request.getContextPath() %>/admin/admins" class="btn">see admins</a>
		      </div>
		  <% } %>
		  
		  <div class="box">
	         <h3>${acceptedCount}</h3>
	         <p>Accepted Bookings</p>
	         <a href="<%= request.getContextPath() %>/admin/bookings?status=accepted" class="btn">see accepted bookings</a>
	      </div>
	      
	      <div class="box">
	         <h3>${pendingCount}</h3>
	         <p>Pending Bookings</p>
	         <a href="<%= request.getContextPath() %>/admin/bookings?status=pending" class="btn">see pending bookings</a>
	      </div>
	      
	      <div class="box">
	         <h3>${declinedCount}</h3>
	         <p>Declined Bookings</p>
	         <a href="<%= request.getContextPath() %>/admin/bookings?status=declined" class="btn">see declined bookings</a>
	      </div>
	      
	      <% if (admin.getId() == 1) { %>
		      <div class="box">
		         <h3>${userCount}</h3>
		         <p>Users Registered</p>
		         <a href="<%= request.getContextPath() %>/admin/users" class="btn">see users</a>
		      </div>
	      <% } %>
	      
	      <div class="box">
	         <h3>${driverCount}</h3>
	         <p>Drivers Registered</p>
	         <a href="<%= request.getContextPath() %>/admin/drivers" class="btn">see drivers</a>
	      </div>
	      
	      <div class="box">
	         <h3>${vehicleCount}</h3>
	         <p>Vehicles Registered</p>
	         <a href="<%= request.getContextPath() %>/admin/vehicles" class="btn">see vehicles</a>
	      </div>
	      
		</div>
	</section>

	
	<script src="<%= request.getContextPath() %>/assets/js/admin-script.js"></script>
</body>
</html>