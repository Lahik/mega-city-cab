<%@ include file="/WEB-INF/components/common-tags.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin - Vehicle Update</title>
    
    <jsp:include page="/WEB-INF/components/admin/admin-imports.jsp" />
</head>
<body>

	<jsp:include page="/WEB-INF/components/admin/admin-navbar.jsp" />
	<jsp:include page="/WEB-INF/components/toast.jsp" />
	
	<section class="form-container">
	     <form action="<%= request.getContextPath() %>/admin/vehicles/update?id=${vehicle.id}" method="POST">
	     	<jsp:include page="/WEB-INF/components/csrf-token.jsp" />
	        <h3>update vehicle</h3>
	        <input type="text" required minlength="3" value="${vehicle.vehicleNumber}" name="vehicle_number" placeholder="Enter the vehicle number" maxlength="10" class="box username">
	        
	        <select name="vehicle_type" required class="box username">
			    <option value="" disabled selected>--Select vehicle type--</option>
			    <option value="Car">Car</option>
			    <option value="Van">Van</option>
			    <option value="SUV">SUV</option>
			</select>
			
	        <input type="number" required min="1" max="12" value="${vehicle.noOfSeats}" name="seats" placeholder="Enter the number of seats" class="box username">
	        
	        <div class="flex-btn">
		        <a href="<%= request.getContextPath() %>/admin/vehicles" class="option-btn">go back</a>
		        <input type="submit" value="update" name="submit" class="btn">
	        </div>
	     </form>
   	</section>

	
	<script src="<%= request.getContextPath() %>/assets/js/admin-script.js"></script>
</body>
</html>