<%@ include file="/WEB-INF/components/common-tags.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    
    <jsp:include page="/WEB-INF/components/imports.jsp" />
</head>
<body>

	<jsp:include page="/WEB-INF/components/toast.jsp" />
	<jsp:include page="/WEB-INF/components/navbar.jsp" />

    <div class="outter-box">
        <div class="inner-box">
            <form action="<%= request.getContextPath() %>/login" method="POST">
            	<jsp:include page="/WEB-INF/components/csrf-token.jsp" />
                <h3>Login</h3>
                
                <div class="input-container">
                    <label>Username</label>
                    <input type="text" minlength="3" maxlength="25" value="${username}" onkeypress="if(this.value.length == 25) return false;" required name="username">
                </div>
                <div class="input-container">
                    <label>Password</label>
                    <input type="password" minlength="5" maxlength="255" required name="password">
                </div>

                <input type="submit" value="Login" class="button btn1">
            </form>

            <p>Don't have an account? <a href="<%= request.getContextPath() %>/register">Register</a></p>
        </div>
    </div>
	
	<jsp:include page="/WEB-INF/components/footer.jsp" />
	
	<script src="<%= request.getContextPath() %>/assets/js/script.js"></script>
</body>
</html>