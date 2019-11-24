<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:import url="common/header.jsp">
	<c:param name="pageTitle">Survey</c:param>
</c:import>
<h4 style="text-align: center">Daily Survey</h4>


<div id="surveyContainer" class="d-flex justify-content-center">
		<form:form action="survey" method="POST" modelAttribute="survey">
			<div class="form-group">
				<label for="parkCode">Select Favorite Park</label> 
				<form:select class="form-control" path="parkCode" id="parkCode" name="parkCode" required="required">
					<form:option selected="true" disabled="disabled" value="">Please select...</form:option>
					<!-- Read all parks from database for dropdown selection-->
					<c:forEach var="park" items="${parks}">
						<form:option value="${park.parkCode}">${park.parkName}</form:option>
					</c:forEach>
				</form:select>
				<form:errors path="parkCode" class="error"/>
			</div>
			<div class="form-group">
    			<label for="emailAddress">Email Address</label>
   				 <form:input type="email" path="emailAddress" class="form-control" id="emailAddress" name ="emailAddress" placeholder="Enter email" required="required"/>
   				 <small id="emailAddressText" class="form-text text-muted">We'll never share your email with anyone else.</small>
   				 <form:errors path="emailAddress" class="error"/>
  			</div>
			<div class="form-group">
				<label for="state">State of Residence</label> 
				<!-- Read all States from Survey.java states array for dropdown selection-->
				<form:select class="form-control" path="state" id="stateSelection" name="state" required="required">
					<form:option selected="true" disabled="disabled" value="">Please select...</form:option>
					<c:forEach var="state" items="${states}">
							<form:option value="${state}">${state}</form:option>
					</c:forEach>
				</form:select>
				<form:errors path="validState" class="error"/>
			</div>
			<div class="form-group">
				<label for="activityLevel">Choose Level of Activity</label> 
				<form:radiobutton  name="activityLevel" path="activityLevel" value="inactive" required="required"/> Inactive
				<form:radiobutton  name="activityLevel" path="activityLevel" value="sedentary" required="required"/> Sedentary
				<form:radiobutton  name="activityLevel" path="activityLevel" value="active" required="required"/> Active
				<form:radiobutton  name="activityLevel" path="activityLevel" value="extremely_active" required="required"/> Extremely Active
			<form:errors path="activityLevel" class="error"/>
			</div>
			
			<button type="submit" value="Submit" class="btn btn-primary">Submit</button>
		</form:form>
	</div>

<c:import url="common/footer.jsp"/>