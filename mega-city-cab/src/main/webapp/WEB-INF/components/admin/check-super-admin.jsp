<%@page import="com.megacitycab.model.Admin"%>
<%
    Admin admin = (Admin) session.getAttribute("admin"); 
	if (admin != null && admin.getId() != 1) {
	    response.sendRedirect(request.getContextPath() + "/admin");
	    return;
	}
%>