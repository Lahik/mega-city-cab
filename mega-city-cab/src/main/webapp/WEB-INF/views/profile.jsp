<%@ page import="com.megacitycab.model.User"%>
<%@ include file="/WEB-INF/components/check-if-logged-in.jsp" %>
<%@ include file="/WEB-INF/components/common-tags.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Profile</title>

    <jsp:include page="/WEB-INF/components/imports.jsp" />
</head>
<body>

	<jsp:include page="/WEB-INF/components/toast.jsp" />
	<jsp:include page="/WEB-INF/components/navbar.jsp" />
	
	<%
   		User user = (User) session.getAttribute("user");
   	%>
    <div class="outter-box update-profile">
        <div class="inner-box">
            <form action="<%= request.getContextPath() %>/profile" method="POST">
                <h3>Update Profile Details</h3>
                
                    <div class="input-container">
                        <label>Name</label>
                        <input type="text" value="<%= user.getName() %>" minlength="3" maxlength="55" required name="name">
                    </div>
                    <div class="input-container">
                        <label>Address</label>
                        <input type="text" value="<%= user.getAddress() %>" maxlength="255" required name="address">
                    </div>
                    <div class="input-container">
                        <label>NIC</label>
                        <input type="number" value="<%= user.getNic() %>" minlength="9" maxlength="12" onkeypress="if(this.value.length == 12) return false;" required name="nic">
                    </div>
                    <div class="input-container">
                        <label>Telephone</label>
                        <input type="number" value="<%= user.getTelephone() %>" minlength="9" maxlength="10" onkeypress="if(this.value.length == 10) return false;" required name="telephone">
                    </div>
                    <div class="input-container">
                        <label>Username</label>
                        <input type="text" value="<%= user.getUsername() %>" minlength="3" maxlength="25" onkeypress="if(this.value.length == 25) return false;" required name="username">
                    </div>
                    
                <input type="submit" value="Update Profile" class="button btn1">
            </form>
		</div>

		<div class="inner-box">
            <form action="<%= request.getContextPath() %>/update-password" method="POST">
            	<h3>Update Password</h3>
            
            	<div class="input-container">
                     <label>Current Password</label>
                     <input type="password" minlength="5" maxlength="255" required name="current_password">
                </div>
            	<div class="input-container">
                     <label>New Password</label>
                     <input type="password" minlength="5" maxlength="255" required name="new_password">
                </div>
                <div class="input-container">
                    <label>Confirm New Password</label>
                    <input type="password" minlength="5" maxlength="255" required name="new_password_confirmation">
                </div>
                
                <input type="submit" value="Update Password" class="button btn1">
            </form>
         </div>
    </div>

	<script src="<%= request.getContextPath() %>/assets/js/script.js"></script>
</body>

</html>