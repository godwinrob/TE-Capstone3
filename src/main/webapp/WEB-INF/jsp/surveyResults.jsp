<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="common/header.jsp">
	<c:param name="pageTitle">Favorite Parks</c:param>
</c:import>
<h2 style="text-align: center">Favorite Park Survey Results!</h2>
<div class="container">
	<c:forEach var="park" items="${survey}">
		<div class="row">
			<div class="col">
				<c:url var="parkImgSrc" value="/img/parks/${park.parkCode.toLowerCase()}.jpg" />
				<a href="parkDetail?id=${park.parkCode}"><img src="${parkImgSrc}" style="max-width:100%;"/></a>
			</div>
			<div class="col">
				<h3>${park.parkName}</h3>
				<h5>${park.parkState}</h5>
				<h5>Number of votes: ${park.surveyCount}</h5>
			</div>
		</div>
	</c:forEach>

</div>


<c:import url="common/footer.jsp"/>