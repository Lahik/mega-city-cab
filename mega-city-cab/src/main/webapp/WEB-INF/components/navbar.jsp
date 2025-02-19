<%@ page import="com.megacitycab.model.User" %>
<header id="header">
    	<div class="header-container container">

           <a href="<%= request.getContextPath() %>">
               <img src="<%= request.getContextPath() %>/assets/images/logo.png" alt="">
           </a>

           <nav id="nav">
               <i class="ri-close-line" id="close"></i>

               <div class="nav-links hover-underline-animation">
                   <div class="nav-link">
                       <a href="/">Home</a>
                   </div>
                   <div class="nav-link">
                       <a href="<%= request.getContextPath() %>/booking">Bookings</a>
                   </div>
                   <div class="nav-link">
                       <a href="<%= request.getContextPath() %>/about">About</a>
                   </div>
                   <div class="nav-link">
                       <a href="<%= request.getContextPath() %>/contact">Contact</a>
                   </div>
               </div>
           </nav>

           <div class="menu-container">
               <i class="ri-user-3-line" id="user"></i>
               <i class="ri-menu-line" id="menu"></i>
           </div>

           <div class="profile">
           	   <%
            	 if (session.getAttribute("user") != null) {
            		User user = (User) session.getAttribute("user");
        	   %>
	               <div>
	                   <p><%= user.getName() %></p>
	               </div>
        	   <%
                 }
        	   %>
               <div class="menu-buttons">
               <%
            	 if (session.getAttribute("user") != null) {
        	   %>
		            <a href="#" class="button btn1">Update Profile</a>
		            <form action="<%= request.getContextPath() %>/logout" method="post" style="display:inline;">
                        <button type="submit" class="button btn2" onclick="return confirm('logout from this website?');">Logout</button>
                    </form>
		       <%
                 } else {
        	   %>
		            <a href="<%= request.getContextPath() %>/login" class="button btn1">Login</a>
		            <a href="<%= request.getContextPath() %>/register" class="button btn2">Register</a>
		       <%
		         }
		       %>
               </div>
           </div>

       </div>
   </header>