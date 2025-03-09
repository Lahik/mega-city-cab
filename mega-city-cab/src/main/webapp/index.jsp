<%@ include file="/WEB-INF/components/common-tags.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home</title>
    
    <jsp:include page="/WEB-INF/components/imports.jsp" />
    <link
  		rel="stylesheet"
  		href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css"
	/>
</head>
<body>

	<jsp:include page="/WEB-INF/components/navbar.jsp" />
	<jsp:include page="/WEB-INF/components/toast.jsp" />

	<section class="hero-section">
		<div class="container hero-container">
			<span class="polygon"></span>
			<div class="hero">
				<h1>Fast, Safe and Affordable <br> Your <span>Ride,</span> Your <span>Way!</span></h1>
				<p>Book a ride in seconds and reach your destination comfortably. Whether it's a daily commute or a special trip
				    we ensure safe, reliable and affordable cab services - anytime, anywhere</p>
				<div>
					<a class="button btn1" href="<%= request.getContextPath() %>/booking">Book a Cab</a>
				</div>
			</div>
			
			<div class="cab">
				<img alt="cab" src="<%= request.getContextPath() %>/assets/images/cab.png">
			</div>
		</div>
	</section>
	
	<section class="container">
	    <h3 class="section-title">WHY CHOOSE US</h3>
	    <h2 class="section-sub-title">Reliable Rides, Transparent Pricing and Exceptional Service</h2>
	    <div class="services">
	
	        <div class="services__card">
	            <i class="ri-timer-fill"></i>
	            <span>Fast & On-Time Service</span>
	            <p>Quick and reliable pickups to ensure you reach your destination on time</p>
	        </div>
	
	        <div class="services__card">
	            <i class="ri-shield-check-fill"></i>
	            <span>Safe & Secure Rides</span>
	            <p>Verified drivers, GPS tracking and emergency support for your safety</p>
	        </div>
	
	        <div class="services__card">
	            <i class="ri-money-dollar-circle-fill"></i>
	            <span>Affordable Pricing</span>
	            <p>Transparent fares with no hidden charges, plus discounts and offers</p>
	        </div>
	
	        <div class="services__card">
	            <i class="ri-exchange-dollar-fill"></i>
	            <span>Flexible Ride Options</span>
	            <p>Whether you need a Car, Van, or SUV, we provide the best ride based on availability</p>
	        </div>
	
	        <div class="services__card">
	            <i class="ri-phone-fill"></i>
	            <span>24/7 Customer Support</span>
	            <p>Our team is always available to assist you with any queries or issues</p>
	        </div>
	
	        <div class="services__card">
	            <i class="ri-map-pin-fill"></i>
	            <span>Easy Booking</span>
	            <p>Book your ride effortlessly in just a few clicks and get instant confirmation</p>
	        </div>
	
	        <div class="services__card">
	            <i class="ri-team-fill"></i>
	            <span>Professional Drivers</span>
	            <p>Experienced, courteous and well-trained drivers for a comfortable ride</p>
	        </div>
	
	        <div class="services__card">
	            <i class="ri-wallet-3-fill"></i>
	            <span>Multiple Payment Options</span>
	            <p>Pay easily with cash, card or digital wallets</p>
	        </div>
	
	    </div>
	</section>
	
	<section class="transport-container">
		<div class="background-image" style="background: url('<%= request.getContextPath() %>/assets/images/lotus-tower.jpg');"></div>
		
		<div class="transport-titles">
		    <h3 class="section-title">WHAT WE OFFER</h3>
		    <h2 class="section-sub-title">Start your journey with Mega City Cab</h2>
		    <p class="container heading">We offer a range of transportation services tailored to meet your travel needs. Whether its a short trip or a long journey, our reliable and convenient rides ensure a smooth and hassle-free experience every time.</p>
		</div>
	    
	    <div class="container transport swiper">
	    	<ul class="swiper-wrapper">
	    	
	    		<li class="swiper-slide">
				    <div class="transport-card">
				        <div class="transport-picture">
				            <img alt="transport" src="<%= request.getContextPath() %>/assets/images/regular.jpg">
				            <img alt="cab" class="cab" src="<%= request.getContextPath() %>/assets/images/cab.png">
				        </div>
				        <div class="transport-description">
				            <span>Regular Rides</span>
				            <p>Seamless daily travel for your city commutes with comfort and reliability.</p>
				        </div>
				    </div>
				</li>
				
				<li class="swiper-slide">
				    <div class="transport-card">
				        <div class="transport-picture">
				            <img alt="transport" src="<%= request.getContextPath() %>/assets/images/air.jpg">
				            <img alt="cab" class="cab" src="<%= request.getContextPath() %>/assets/images/cab.png">
				        </div>
				        <div class="transport-description">
				            <span>Airport Transfers</span>
				            <p>Timely and stress-free rides to and from the airport, ensuring smooth travel.</p>
				        </div>
				    </div>
				</li>
				
				<li class="swiper-slide">
				    <div class="transport-card">
				        <div class="transport-picture">
				            <img alt="transport" src="<%= request.getContextPath() %>/assets/images/luggage.jpeg">
				            <img alt="cab" class="cab" src="<%= request.getContextPath() %>/assets/images/cab.png">
				        </div>
				        <div class="transport-description">
				            <span>Luggage Transport</span>
				            <p>Secure and reliable transport for your extra baggage and heavy items.</p>
				        </div>
				    </div>
				</li>
				
				<li class="swiper-slide">
				    <div class="transport-card">
				        <div class="transport-picture">
				            <img alt="transport" src="<%= request.getContextPath() %>/assets/images/corporate.jpg">
				            <img alt="cab" class="cab" src="<%= request.getContextPath() %>/assets/images/cab.png">
				        </div>
				        <div class="transport-description">
				            <span>Corporate Rides</span>
				            <p>Professional and punctual rides for business meetings and employee transport.</p>
				        </div>
				    </div>
				</li>
				
				<li class="swiper-slide">
				    <div class="transport-card">
				        <div class="transport-picture">
				            <img alt="transport" src="<%= request.getContextPath() %>/assets/images/long-ride.jpeg">
				            <img alt="cab" class="cab" src="<%= request.getContextPath() %>/assets/images/cab.png">
				        </div>
				        <div class="transport-description">
				            <span>Long-Distance Travel</span>
				            <p>Comfortable and safe outstation travel for long journeys across cities.</p>
				        </div>
				    </div>
				</li>
				
				<li class="swiper-slide">
				    <div class="transport-card">
				        <div class="transport-picture">
				            <img alt="transport" src="<%= request.getContextPath() %>/assets/images/parcel.jpeg">
				            <img alt="cab" class="cab" src="<%= request.getContextPath() %>/assets/images/cab.png">
				        </div>
				        <div class="transport-description">
				            <span>Parcel Delivery</span>
				            <p>Fast and secure delivery of small packages right to your destination.</p>
				        </div>
				    </div>
				</li>
				
				<li class="swiper-slide">
				    <div class="transport-card">
				        <div class="transport-picture">
				            <img alt="transport" src="<%= request.getContextPath() %>/assets/images/event.jpeg">
				            <img alt="cab" class="cab" src="<%= request.getContextPath() %>/assets/images/cab.png">
				        </div>
				        <div class="transport-description">
				            <span>Event Transfers</span>
				            <p>Convenient rides for weddings, parties, and other special occasions.</p>
				        </div>
				    </div>
				</li>
				
				<li class="swiper-slide">
				    <div class="transport-card">
				        <div class="transport-picture">
				            <img alt="transport" src="<%= request.getContextPath() %>/assets/images/hotel.jpeg">
				            <img alt="cab" class="cab" src="<%= request.getContextPath() %>/assets/images/cab.png">
				        </div>
				        <div class="transport-description">
				            <span>Hotel Transfers</span>
				            <p>Reliable and comfortable rides to and from hotels at your convenience.</p>
				        </div>
				    </div>
				</li>
				
				<li class="swiper-slide">
				    <div class="transport-card">
				        <div class="transport-picture">
				            <img alt="transport" src="<%= request.getContextPath() %>/assets/images/vip.jpeg">
				            <img alt="cab" class="cab" src="<%= request.getContextPath() %>/assets/images/cab.png">
				        </div>
				        <div class="transport-description">
				            <span>VIP & Luxury Rides</span>
				            <p>Exclusive, high-end vehicles for a luxurious and premium ride experience.</p>
				        </div>
				    </div>
				</li>

	    	</ul>
	    	
	    	<div class="swiper-pagination"></div>
	    </div>
	    
	</section>

	
	<jsp:include page="/WEB-INF/components/footer.jsp" />
	
	<script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>
	<script src="<%= request.getContextPath() %>/assets/js/script.js"></script>
	
	<script>
	const swiper = new Swiper('.swiper', {
	    speed: 500, 
	    spaceBetween: 30,

	    autoplay: {
	        delay: 3000, 
	        disableOnInteraction: false,
	    },
	  
	    pagination: {
	      el: '.swiper-pagination',
	      clickable: true
	    },
	    grabCursor: false,
	    breakpoints: {
	    	450: {
	    		slidesPerView: 2
	    	},
	        640: {
	            slidesPerView: 2
	        },
	        768: {
	            slidesPerView: 3
	        }
	    }
	});
	</script>
</body>
</html>