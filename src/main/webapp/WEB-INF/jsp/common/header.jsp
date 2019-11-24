<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><c:out value="${param.pageTitle}"/> - National Park Geek</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link href="<c:url value="/css/site.css"/>" rel="stylesheet" type="text/css" />
    <style type="text/css">
        label {
            display: block;
        }

        .form-control {
            display: inline-block;
        }
    </style>
</head>
<body>
	<c:url value="/" var="homeUrl"/>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<a style="padding: 0px" class="nav-link" href="${homeUrl}"><img id ="logo-image" src="img/logo.png"/></a>
	</nav>
    <nav id="stickThis" class="navbar navbar-expand-lg navbar-dark bg-dark">
	  <div class="collapse navbar-collapse" id="navbarNav">
	    <ul class="navbar-nav">
	      <li class="nav-item">
	        <a class="nav-link" href="${homeUrl}">Home</a>
	      </li>
	      <li class="nav-item ">
	        <c:url value="/survey" var="surveyUrl"/>
	        <a class="nav-link" href="${surveyUrl}">Survey</a>
	      </li>
	    </ul>
	  </div>
	</nav>
	
    <div class="container">