<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="common/header.jsp">
	<c:param name="pageTitle">Home Page</c:param>
</c:import>

<!--
	Nation Park Geek Website built by Rob Godwin, Todd Faulkner
	Tech Elevator Capstone 3
	Built with SpringBoot Framework, utilizing a PostgreSQL database.
	Version: 1.00
	Date: Nov 14, 2019
-->
<div id="park-container">
	<c:forEach var="park" items="${parks}">
		<div class='park-info'>
			<div class="park-image">
				<c:url var="parkImgSrc" value="/img/parks/${park.parkCode.toLowerCase()}.jpg" />
				<a href="parkDetail?id=${park.parkCode}"><img src="${parkImgSrc}" style="max-width:100%;"/></a>
			</div>
			<div class="park-text">
				<div>
					<h3>${park.parkName}</h3>
					<h5>${park.state}</h5>
				</div>
				<div>
					<p>${park.parkDescription}</p>
				</div>
			</div>
		</div>
	</c:forEach>

</div>


<c:import url="common/footer.jsp"/>