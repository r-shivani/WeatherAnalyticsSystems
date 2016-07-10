<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Weather Data</title>
<link href="http://fonts.googleapis.com/css?family=Roboto:300,400,700|"
	rel="stylesheet" type="text/css">
<link href="fonts/font-awesome.min.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="style.css">
</head>
<body>

	<div class="site-content">
		<div class="site-header">
			<div class="container">
				<div class="branding">
					<img src="images/logo.png" alt="" class="logo">
					<div class="logo-type">
						<h1 class="site-title">Weather App</h1>
					</div>
					<br /> <br /> <br />
					<div class="main-navigation">
						<button type="button" class="menu-toggle">
							<i class="fa fa-bars"></i>
						</button>
						<ul class="menu">

						</ul>
						<!-- .menu -->
					</div>
					<div class="mobile-navigation"></div>

				</div>
			</div>
			<div class="hero" data-bg-image="images/banner_bg.png">
				<div class="container">
					<form action="weatherServlet" class="find-location"
						method="post">
						<input type="text" placeholder="Enter City Name" name="zipcode" value="${zipcode1}">
						<input type="submit" name="getweatherdata"
							value="Get Weather Data">
					</form>

				</div>
			</div>
		</div>

		<c:if test='${not empty weatherMap}'>
		<div>
		<div class="forecast-table">
			<div class="container">
				<div class="forecast-container">
					<div class="today forecast">
						<div class="forecast-header">
							<div class="day">
							
							<c:forEach items="${weatherMap}" var="entry">
							<c:if test="${entry.key == 'd0'}">
									<c:out value="${entry.value}" />
							</c:if>
							</c:forEach>
							
							</div>
							<div class="date">
							
							<c:forEach items="${weatherMap}" var="entry">
									<c:if test="${entry.key == 'date'}">
										<c:out value="${entry.value}" />
									</c:if>
								</c:forEach>
							
							</div>
						</div>
						<!-- .forecast-header -->
						<div class="forecast-content">
							<div class="location">

								<c:forEach items="${weatherMap}" var="entry">
									<c:if test="${entry.key == 'name'}">
										<c:out value="${entry.value}" />
									</c:if>
								</c:forEach>

							</div>
							<div class="degree">
								<div class="num">

									<c:forEach items="${weatherMap}" var="entry">
										<c:if test="${entry.key == 'max0'}">
											<c:out value="${entry.value}" />
										</c:if>
									</c:forEach>

									<sup>o</sup>C
								
								

			<c:forEach items="${weatherMap}" var="entry">
									<c:if test="${entry.key == 'main0'}">
										<c:if test="${entry.value == 'Clear'}">
											<img src="images/icons/icon-2.svg" alt="" width=90>
										</c:if>
										<c:if test="${entry.value == 'Rain'}">
											<img src="images/icons/icon-10.svg" alt="" width=90>
										</c:if>
										<c:if test="${entry.value == 'Clouds'}">
										<img src="images/icons/icon-6.svg" alt="" width=48>
									    </c:if>
										<c:if test="${entry.value == 'Snow'}">
											<img src="images/icons/icon-14.svg" alt="" width=90>
										</c:if>
									</c:if>
</c:forEach>

								</div>
							</div>
							<span><img src="images/icon-umberella.png" alt="">
							<c:forEach items="${weatherMap}" var="entry">
							<c:if test="${entry.key == 'clouds0'}">
									<c:out value="${entry.value}" />%
							</c:if>
							</c:forEach>
</span>
							<span><img src="images/icon-wind.png" alt="">
							
							<c:forEach items="${weatherMap}" var="entry">
							<c:if test="${entry.key == 'speed0'}">
									<c:out value="${entry.value}" /> mph
							</c:if>
							</c:forEach>
							
							</span>
							<span><img src="images/icon-compass.png" alt="">
							
							<c:forEach items="${weatherMap}" var="entry">
							<c:if test="${entry.key == 'humidity0'}">
									<c:out value="${entry.value}" />
							</c:if>
							</c:forEach>
							
							</span>
						</div>
					</div>
					<div class="forecast">
						<div class="forecast-header">
							<div class="day">
							
							<c:forEach items="${weatherMap}" var="entry">
							<c:if test="${entry.key == 'd1'}">
									<c:out value="${entry.value}" />
							</c:if>
							</c:forEach>
							
							</div>
						</div>
						<!-- .forecast-header -->
						<div class="forecast-content">
							<div class="forecast-icon">
<c:forEach items="${weatherMap}" var="entry">
								<c:if test="${entry.key == 'main1'}">
									<c:if test="${entry.value == 'Clear'}">
										<img src="images/icons/icon-2.svg" alt="" width=48>
									</c:if>
									<c:if test="${entry.value == 'Rain'}">
										<img src="images/icons/icon-10.svg" alt="" width=48>
									</c:if>
									<c:if test="${entry.value == 'Clouds'}">
										<img src="images/icons/icon-6.svg" alt="" width=48>
									</c:if>
									<c:if test="${entry.value == 'Snow'}">
										<img src="images/icons/icon-14.svg" alt="" width=48>
									</c:if>
								</c:if>
</c:forEach>




							</div>
							<div class="degree">

								<c:forEach items="${weatherMap}" var="entry">
									<c:if test="${entry.key == 'max1'}">
										<c:out value="${entry.value}" />
									</c:if>
								</c:forEach>

								<sup>o</sup>C
							</div>
							<small> <c:forEach items="${weatherMap}" var="entry">
									<c:if test="${entry.key == 'min1'}">
										<c:out value="${entry.value}" />
									</c:if>
								</c:forEach> <sup>o</sup></small>
						</div>
					</div>
					<div class="forecast">
						<div class="forecast-header">
							<div class="day">
							
							<c:forEach items="${weatherMap}" var="entry">
							<c:if test="${entry.key == 'd2'}">
									<c:out value="${entry.value}" />
							</c:if>
							</c:forEach>
							
							</div>
						</div>
						<!-- .forecast-header -->
						<div class="forecast-content">
							<div class="forecast-icon">
<c:forEach items="${weatherMap}" var="entry">
								<c:if test="${entry.key == 'main2'}">
									<c:if test="${entry.value == 'Clear'}">
										<img src="images/icons/icon-2.svg" alt="" width=48>
									</c:if>
									<c:if test="${entry.value == 'Rain'}">
										<img src="images/icons/icon-10.svg" alt="" width=48>
									</c:if>
									<c:if test="${entry.value == 'Clouds'}">
										<img src="images/icons/icon-6.svg" alt="" width=48>
									</c:if>
									<c:if test="${entry.value == 'Snow'}">
										<img src="images/icons/icon-14.svg" alt="" width=48>
									</c:if>
								</c:if>
</c:forEach>


							</div>
							<div class="degree">

								<c:forEach items="${weatherMap}" var="entry">
									<c:if test="${entry.key == 'max2'}">
										<c:out value="${entry.value}" />
									</c:if>
								</c:forEach>

								<sup>o</sup>C
							</div>
							<small> <c:forEach items="${weatherMap}" var="entry">
									<c:if test="${entry.key == 'min2'}">
										<c:out value="${entry.value}" />
									</c:if>
								</c:forEach> <sup>o</sup></small>
						</div>
					</div>
					<div class="forecast">
						<div class="forecast-header">
							<div class="day">
							
							<c:forEach items="${weatherMap}" var="entry">
							<c:if test="${entry.key == 'd3'}">
									<c:out value="${entry.value}" />
							</c:if>
							</c:forEach>
							
							</div>
						</div>
						<!-- .forecast-header -->
						<div class="forecast-content">
							<div class="forecast-icon">
							
							<c:forEach items="${weatherMap}" var="entry">
								<c:if test="${entry.key == 'main3'}">
									<c:if test="${entry.value == 'Clear'}">
										<img src="images/icons/icon-2.svg" alt="" width=48>
									</c:if>
									<c:if test="${entry.value == 'Rain'}">
										<img src="images/icons/icon-10.svg" alt="" width=48>
									</c:if>
									<c:if test="${entry.value == 'Clouds'}">
										<img src="images/icons/icon-6.svg" alt="" width=48>
									</c:if>
									<c:if test="${entry.value == 'Snow'}">
										<img src="images/icons/icon-14.svg" alt="" width=48>
									</c:if>
								</c:if>
								</c:forEach>

							</div>
							<div class="degree">
								<c:forEach items="${weatherMap}" var="entry">
									<c:if test="${entry.key == 'max3'}">
										<c:out value="${entry.value}" />
									</c:if>
								</c:forEach>
								<sup>o</sup>C
							</div>
							<small> <c:forEach items="${weatherMap}" var="entry">
									<c:if test="${entry.key == 'min3'}">
										<c:out value="${entry.value}" />
									</c:if>
								</c:forEach> <sup>o</sup></small>
						</div>
					</div>
					<div class="forecast">
						<div class="forecast-header">
							<div class="day"><c:forEach items="${weatherMap}" var="entry">
							<c:if test="${entry.key == 'd4'}">
									<c:out value="${entry.value}" />
							</c:if>
							</c:forEach></div>
						</div>
						<!-- .forecast-header -->
						<div class="forecast-content">
							<div class="forecast-icon">
							
							<c:forEach items="${weatherMap}" var="entry">
								<c:if test="${entry.key == 'main4'}">
									<c:if test="${entry.value == 'Clear'}">
										<img src="images/icons/icon-2.svg" alt="" width=48>
									</c:if>
									<c:if test="${entry.value == 'Rain'}">
										<img src="images/icons/icon-10.svg" alt="" width=48>
									</c:if>
									<c:if test="${entry.value == 'Clouds'}">
										<img src="images/icons/icon-6.svg" alt="" width=48>
									</c:if>
									<c:if test="${entry.value == 'Snow'}">
										<img src="images/icons/icon-14.svg" alt="" width=48>
									</c:if>
								</c:if>
</c:forEach>
							</div>
							<div class="degree">
								<c:forEach items="${weatherMap}" var="entry">
									<c:if test="${entry.key == 'max4'}">
										<c:out value="${entry.value}" />
									</c:if>
								</c:forEach>
								<sup>o</sup>C
							</div>
							<small> <c:forEach items="${weatherMap}" var="entry">
									<c:if test="${entry.key == 'min4'}">
										<c:out value="${entry.value}" />
									</c:if>
								</c:forEach> <sup>o</sup></small>
						</div>
					</div>
					<div class="forecast">
						<div class="forecast-header">
							<div class="day">
							<c:forEach items="${weatherMap}" var="entry">
							<c:if test="${entry.key == 'd5'}">
									<c:out value="${entry.value}" />
							</c:if>
							</c:forEach>
							</div>
						</div>
						<!-- .forecast-header -->
						<div class="forecast-content">
							<div class="forecast-icon">
								<c:forEach items="${weatherMap}" var="entry">
								<c:if test="${entry.key == 'main5'}">
									<c:if test="${entry.value == 'Clear'}">
										<img src="images/icons/icon-2.svg" alt="" width=48>
									</c:if>
									<c:if test="${entry.value == 'Rain'}">
										<img src="images/icons/icon-10.svg" alt="" width=48>
									</c:if>
									<c:if test="${entry.value == 'Clouds'}">
										<img src="images/icons/icon-6.svg" alt="" width=48>
									</c:if>
									<c:if test="${entry.value == 'Snow'}">
										<img src="images/icons/icon-14.svg" alt="" width=48>
									</c:if>
								</c:if>
</c:forEach>

							</div>
							<div class="degree">
								<c:forEach items="${weatherMap}" var="entry">
									<c:if test="${entry.key == 'max5'}">
										<c:out value="${entry.value}" />
									</c:if>
								</c:forEach>
								<sup>o</sup>C
							</div>
							<small> <c:forEach items="${weatherMap}" var="entry">
									<c:if test="${entry.key == 'min5'}">
										<c:out value="${entry.value}" />
									</c:if>
								</c:forEach> <sup>o</sup></small>
						</div>
					</div>
					<div class="forecast">
						<div class="forecast-header">
							<div class="day">
							
							<c:forEach items="${weatherMap}" var="entry">
							<c:if test="${entry.key == 'd6'}">
									<c:out value="${entry.value}" />
							</c:if>
							</c:forEach>
							
							</div>
						</div>
						<!-- .forecast-header -->
						<div class="forecast-content">
							<div class="forecast-icon">
								<c:forEach items="${weatherMap}" var="entry">
								<c:if test="${entry.key == 'main6'}">
									<c:if test="${entry.value == 'Clear'}">
										<img src="images/icons/icon-2.svg" alt="" width=48>
									</c:if>
									<c:if test="${entry.value == 'Rain'}">
										<img src="images/icons/icon-10.svg" alt="" width=48>
									</c:if>
									<c:if test="${entry.value == 'Clouds'}">
										<img src="images/icons/icon-6.svg" alt="" width=48>
									</c:if>
									<c:if test="${entry.value == 'Snow'}">
										<img src="images/icons/icon-14.svg" alt="" width=48>
									</c:if>
								</c:if>
</c:forEach>

							</div>
							<div class="degree">
								<c:forEach items="${weatherMap}" var="entry">
									<c:if test="${entry.key == 'max6'}">
										<c:out value="${entry.value}" />
									</c:if>
								</c:forEach>
								<sup>o</sup>C
							</div>
							<small> <c:forEach items="${weatherMap}" var="entry">
									<c:if test="${entry.key == 'min6'}">
										<c:out value="${entry.value}" />
									</c:if>
								</c:forEach> <sup>o</sup></small>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<div class="fullwidth-block">
					<div class="container">
						<div class="row">
						<div class="content col-md-8">
	<center><div class="sidebar col-md-8 col-md-offset-5" align="center">
								<div class="widget">
									<u><h3 class="widget-title">Wether Info</h3></u>
									<h3 class="widget-title"><c:forEach items="${weatherMap}" var="entry">
									<c:if test="${entry.key == 'name'}">
										<c:out value="${entry.value}" />
									</c:if>
								</c:forEach></h3>
								<h5 class="widget-title"><c:forEach items="${weatherMap}" var="entry">
									<c:if test="${entry.key == 'country'}">
										<c:out value="${entry.value}" />
									</c:if>
								</c:forEach></h5>
									<h5 class="widget-title">
										<c:forEach items="${weatherMap}" var="entry">
									<c:if test="${entry.key == 'date'}">
										<c:out value="${entry.value}" />
									</c:if>
								</c:forEach>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<c:forEach items="${weatherMap}" var="entry">
									<c:if test="${entry.key == 'd0'}">
										<c:out value="${entry.value}" />
									</c:if>
								</c:forEach></h5>
									<h5 class="widget-title">
										 <c:forEach items="${weatherMap}" var="entry">
									<c:if test="${entry.key == 'description'}">
										<c:out value="${entry.value}" />
									</c:if>
								</c:forEach></h5>
									
								</div>
								</div></center>
	</div></div>
	
	</div>
	
	</div>
	</div>
	</c:if>
	<script src="js/jquery-1.11.1.min.js"></script>
	<script src="js/plugins.js"></script>
	<script src="js/app.js"></script>

</body>
</html>