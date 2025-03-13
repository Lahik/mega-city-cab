<%@ include file="/WEB-INF/components/common-tags.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Booking</title>
    
    <jsp:include page="/WEB-INF/components/imports.jsp" />
</head>
<body>

	<jsp:include page="/WEB-INF/components/navbar.jsp" />
	<jsp:include page="/WEB-INF/components/toast.jsp" />

	<div class="outter-box">
        <div class="inner-box">
            <form action="<%= request.getContextPath() %>/booking" method="POST">
            	<jsp:include page="/WEB-INF/components/csrf-token.jsp" />
                <h3>Book a Cab</h3>
               
                <div class="input-container">
                    <label>Pickup Location</label>
                    <input type="text" value="${pickup_location}" minlength="3" maxlength="255" required name="pickup_location">
                </div>
                <div class="input-container">
                    <label>Destination</label>
                    <input type="text" value="${destination}" minlength="3" maxlength="255" required name="destination">
                </div>
                
                <div class="input-container">
	                 <label>Pickup Date</label>
	                 <input type="date" value="${pickup_date}" required name="pickup_date">
                </div>

                <div class="input-container">
                    <label>Pickup Time</label>
                    <input type="time" value="${pickup_time}" required name="pickup_time">
                </div>
                
                <div class="input-container">
				    <label>Number of Seats</label>
				    <input type="number" value="${seats}" name="seats" min="1" max="12" required>
				</div>

                <div class="input-container">
                    <label>Message (optional)</label>
                    <textarea name="message" rows="5" maxlength="500" placeholder="Any special requests?">${message}</textarea>
                </div>

                <input type="submit" value="Book Now" class="button btn1">
            </form>
        </div>
    </div>
    
    <jsp:include page="/WEB-INF/components/footer.jsp" />
	
	<script src="<%= request.getContextPath() %>/assets/js/script.js"></script>
</body>
</html>