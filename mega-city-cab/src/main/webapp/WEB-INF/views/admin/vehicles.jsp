<%@ include file="/WEB-INF/components/admin/check-admin-login.jsp" %>
<%@ include file="/WEB-INF/components/common-tags.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin - Vehicles</title>
    
    <jsp:include page="/WEB-INF/components/admin/admin-imports.jsp" />
</head>
<body>

	<jsp:include page="/WEB-INF/components/admin/admin-navbar.jsp" />
	<jsp:include page="/WEB-INF/components/toast.jsp" />

	<section class="form-container" style="min-height: 0;">

	     <form action="<%= request.getContextPath() %>/admin/vehicles" method="POST">
	        <h3>create a vehicle</h3>
	        <input type="text" required minlength="3" name="vehicle_number" placeholder="Enter the vehicle number" maxlength="10" class="box username">
	        
	        <select name="vehicle_type" required class="box username">
			    <option value="" disabled selected>--Select vehicle type--</option>
			    <option value="Car">Car</option>
			    <option value="Van">Van</option>
			    <option value="SUV">SUV</option>
			</select>
			
	        <input type="number" required min="1" max="12" name="seats" placeholder="Enter the number of seats" class="box username">
	        <input type="submit" value="create" name="submit" class="btn">
	     </form>
   	</section>
   	
   	<section>
     	<div class="table-container">
            <h1>Registered Vehicles</h1>
            <div class="table">
                <table>
                    <thead>
                        <tr class="heading">
                            <th>#</th>
                            <th>Number</th>
                            <th>Type</th>
                            <th>Seats</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="vehicle" items="${vehicles}" varStatus="status">
		                    <tr>
		                        <td>${status.index + 1}</td>
		                        <td style="text-transform: uppercase;">${vehicle.vehicleNumber}</td>
		                        <td>${vehicle.vehicleType}</td>
		                        <td>${vehicle.noOfSeats}</td>
		                        <td>
		                        	<div>
			                        	<a href="<%= request.getContextPath() %>/admin/vehicles/update?id=${vehicle.id}">
			                        		<i class="ri-edit-box-line edit"></i>
			                        	</a>
			                        	<form action="<%= request.getContextPath() %>/admin/vehicles/delete" method="POST" 
			                        		onsubmit="return confirm('Are you sure you want to delete vehicle ${vehicle.vehicleNumber}?');">
			                        		<input type="hidden" name="id" value="${vehicle.id}" />
				                        	<button type="submit">
				                        		<i class="ri-delete-bin-line delete"></i>
				                        	</button>
			                        	</form>
		                        	</div>
		                        </td>
		                    </tr>
		                </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
     </section>

	
	<script src="<%= request.getContextPath() %>/assets/js/admin-script.js"></script>
</body>
</html>