<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<c:import url="common/header.jsp">
	<c:param name="pageTitle">Park Weather Details</c:param>
</c:import>
<div id="detail-page">

	<!-- Detail information page for selected National park utilizing Park ID to pull information from DAO-->
	<div id="detail-image-div">
		<c:url var="parkImgSrc"
			value="/img/parks/${park.parkCode.toLowerCase()}.jpg" />
		<img class="detailImage" src="${parkImgSrc}" />
	</div>
	<div id="detailInfoDiv">
		<div id="detail-name-div">
			<h2>${park.parkName}</h2>
		</div>
	
		<div id="detail-quote">
			<h4>
				<em>"${park.inspirationalQuote}"</em>
			</h4>
			<h5>-${park.inspirationalQuoteSource}</h5>
		</div>
	
		<div id="detail-description">${park.parkDescription}</div>
	</div>
	<div id="detail-weather-block">
		<c:forEach var="day" items="${weather}">
			<span class="weather-day-block">
			<jsp:useBean id="now" class="java.util.Date" />
			<c:choose>
				<c:when test="${day.fiveDayForecastValue == 1}">
					<h4><fmt:formatDate value="${now}" pattern="MM/dd/yyyy"/></h4>
				</c:when>
				<c:otherwise>
					<br />
					<br />
				</c:otherwise>
			</c:choose>
				<c:set var="String" value="${day.forecast}" />
				<c:url var="weatherImage" value="/img/weather/${day.forecast}.png" />
				<c:choose>
					<c:when test="${day.fiveDayForecastValue == 1}">
						<img src="${weatherImage}" style="max-width: 100%;" />
					</c:when>
					<c:otherwise>
						<img src="${weatherImage}" style="max-width: 90%;" />
					</c:otherwise>
				</c:choose>
				<!-- By default will show temperature in Fahrenheit, if selected will show in Celsius for remainder of session-->
				<c:choose>
					<c:when test="${temperature == 'Celsius'}"> <!--TODO fix to use boolean and convert temp properly -->
						<span>High: ${day.highTempInCelcius} &#8451; Low: ${day.lowTempInCelcius} &#8451;</span>
					</c:when>
					<c:otherwise>
						<span>High: ${day.highTemp} &#8457; Low: ${day.lowTemp} &#8457;</span>		
					</c:otherwise>			
				</c:choose>
				<!--Will show message within weather block depending on conditions. This logic should probably be moved to within Weather.Java or WeatherDao.java -->
				<span>
					<c:choose>
						<c:when test="${fn:contains(String, 'snow')}">
							<br />Pack Showshoes.
							</c:when>
						<c:when test="${fn:contains(String, 'rain')}">
							<br />Pack raingear and wear water-proof shoes.
							</c:when>
						<c:when test="${fn:contains(String, 'thunder')}">
							<br />Seek shelter and avoid hiking on exposed ridges.
							</c:when>
						<c:when test="${fn:contains(String, 'sun')}">
							<br />Pack Sunblock.
							</c:when>
					</c:choose> <c:choose>
						<c:when test="${day.highTemp > 75}">
							<br />High Temperature Warning: Bring an extra gallon of water.
							</c:when>
						<c:when test="${day.lowTemp < 20}">
							<br />Frigid Temperature Warning: Wear warm clothing.
							</c:when>
					</c:choose> <c:if test="${(day.highTemp - day.lowTemp) > 20}">
						<br />High Temperature Fluctuation: Wear breathable layers.
					</c:if>
				</span>
			</span>
		</c:forEach>
	</div>

	<!-- Temperature selection container. This will create jsession with preference when chosen-->
	<div class="d-flex justify-content-start">
		<form action="tempSelection" method="POST">
			<div id="temperatureContainer" class="form-group">
				<label style="margin-left: 5px;" for="temperature">Temperature Preference</label>
				<select class="form-control" id="temperature" name="temperature" required="required">
					<option>Fahrenheit</option>
					<option>Celsius</option>
				</select>
				<input name="park" hidden="true" value="${param.id}" />
				<button style="margin-left: 5px;" type="submit" value="Submit" class="btn btn-primary">Submit</button>
			</div>
		</form>
	</div>

	<div id="detail-park-info">
		<h5>Park Information:</h5>
		Year Founded: ${park.yearFounded}<br /> Annual Visitors:
		${park.annualVisitorCount}<br /> State: ${park.state}<br /> Climate:
		${park.climate}<br /> Acreage: ${park.acreage}<br /> Elevation:
		${park.elevationInFeet} ft.<br /> Miles of Trail:
		${park.milesOfTrail}<br /> No. of Campsites:
		${park.numberOfCampsites}<br /> No. of Animal Species:
		${park.numberOfAnimalSpecies}<br /> Entry Fee:
		<fmt:formatNumber value="${park.entryFee}" type="currency" />
	</div>
</div>


<c:import url="common/footer.jsp" />