<%@ page import="com.megacitycab.util.CSRFTokenUtil" %>
<% 
    CSRFTokenUtil.setToken(request);
    String csrfToken = CSRFTokenUtil.getToken(request); 
%>
<input type="hidden" name="csrf_token" value="<%= csrfToken %>">
