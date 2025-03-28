<%@ include file="/WEB-INF/components/common-tags.jsp" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My Bookings</title>
    
    <jsp:include page="/WEB-INF/components/imports.jsp" />
</head>
<body>

	<jsp:include page="/WEB-INF/components/navbar.jsp" />
	<jsp:include page="/WEB-INF/components/toast.jsp" />

	<div class="container">
   	
        <div class="booking-container">
            <c:forEach var="booking" items="${bookings}">
                <div class="booking">
                    <p>Booking Number: <span>${booking.id}</span></p>
                    
                    <p>Pickup Location: <span>${booking.pickupLocation}</span></p>
                    <p>Destination: <span>${booking.destination}</span></p>
                    <p>Booked Time:
                        <span>${fn:substringBefore(booking.bookingDateTime, 'T')}  
                              ${fn:substringAfter(booking.bookingDateTime, 'T')}</span>
                    </p>
                    <p>Pickup Time: <span>${booking.pickupDateTime}</span></p>
                    <p>Seats: <span>${booking.noOfSeats}</span></p>
                    <p>Status: <span>${booking.bookingStatus}</span></p>
                    <p>Message: <span>${booking.message}</span></p>
                    
                    <c:if test="${status eq 'accepted'}">
                    	<p>Fare: Rs <span>${booking.fare}</span></p>
                    </c:if>
                    
                </div>
            </c:forEach>
        </div>
    </div>
    
    <jsp:include page="/WEB-INF/components/footer.jsp" />
	
	<script src="<%= request.getContextPath() %>/assets/js/script.js"></script>
</body>
</html>