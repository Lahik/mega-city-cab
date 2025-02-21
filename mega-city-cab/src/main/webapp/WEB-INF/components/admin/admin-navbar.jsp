<%@ page import="com.megacitycab.model.Admin" %>
<header class="header">

   <section class="flex">

      <a href="index.php" class="logo">Admin<span>Panel</span></a>

      <nav class="navbar">
         <a href="<%= request.getContextPath() %>/admin">home</a>
         <a href="<%= request.getContextPath() %>/bookings">bookings</a>
         <a href="<%= request.getContextPath() %>/users">users</a>
      </nav>

      <div class="icons">
         <div id="user-btn" class="fas fa-user"></div>
         <div id="menu-btn" class="fas fa-bars"></div>
      </div>
      
      <div class="profile">
      	   <%
          	 if (session.getAttribute("admin") != null) {
          		Admin admin = (Admin) session.getAttribute("admin");
      	   %>
                <p><%= admin.getUsername() %></p>
      	   <%
               }
          	 if (session.getAttribute("admin") != null) {
      	   %>
            <a href="<%= request.getContextPath() %>/admin/profile" class="btn">Update Profile</a>
            <form action="<%= request.getContextPath() %>/admin/logout" method="POST" style="display:inline;">
                <Button type="submit" class="delete-btn" onclick="return confirm('Logout as Admin?');">Logout</Button>
            </form>
       	   <%
               } 
      	   %>
         </div>

   </section>

</header>