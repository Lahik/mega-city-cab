<%@ page import="com.megacitycab.model.Admin" %>
<header class="header">

   <section class="flex">

      <a href="<%= request.getContextPath() %>/admin" class="logo">Admin<span>Panel</span></a>

      <nav class="navbar">
         <a href="<%= request.getContextPath() %>/admin">home</a>
         <a href="<%= request.getContextPath() %>/admin/bookings?status=pending">bookings</a>
         <%
		    Admin admin = (Admin) session.getAttribute("admin"); 
         	boolean isSuperAdmin = admin.getId() == 1 ? true : false;
			if (isSuperAdmin) {
		 %>
				<a href="<%= request.getContextPath() %>/admin/admins">admins</a>		 	
		        <a href="<%= request.getContextPath() %>/admin/users">users</a>
		 <%
			}
		 %>
         <a href="<%= request.getContextPath() %>/admin/vehicles">vehicles</a>
         <a href="<%= request.getContextPath() %>/admin/drivers">drivers</a>
         <% if (isSuperAdmin) {
		 %>
				<a href="<%= request.getContextPath() %>/admin/fare">fare</a>		 	
		 <%
			}
		 %>
      </nav>

      <div class="icons">
         <div id="user-btn" class="fas fa-user"></div>
         <div id="menu-btn" class="fas fa-bars"></div>
      </div>
      
      <div class="profile">
			<p><%= admin.getUsername() %></p>
            <a href="<%= request.getContextPath() %>/admin/profile" class="btn">Update Profile</a>
            <form action="<%= request.getContextPath() %>/admin/logout" method="POST" style="display:inline;">
            	<jsp:include page="/WEB-INF/components/csrf-token.jsp" />
                <Button type="submit" class="delete-btn" onclick="return confirm('Logout as Admin?');">Logout</Button>
            </form>
         </div>

   </section>

</header>