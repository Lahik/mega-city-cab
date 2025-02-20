<%@ include file="/WEB-INF/components/common-tags.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home</title>
    
    <jsp:include page="/WEB-INF/components/imports.jsp" />
</head>
<body>

	<jsp:include page="/WEB-INF/components/navbar.jsp" />
	<jsp:include page="/WEB-INF/components/toast.jsp" />

	
	
	<script src="<%= request.getContextPath() %>/assets/js/script.js"></script>
</body>
</html>