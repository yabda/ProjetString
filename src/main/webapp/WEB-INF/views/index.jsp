<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>KickerStart -- Index</title>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
    <link rel="stylesheet" href="/resources/css/index.css">

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</head>
<body>
<header>
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-12">
                <a href="/"><img src="/resources/images/logo.png"></a>
                <h1>A kick in your projects' butt</h1>
            </div>
        </div>
    </div>
</header>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">

        </div>
        <ul class="nav navbar-nav">
            <c:if test="${log!=true}">
                <li><a href="/login"><i class="fas fa-sign-in-alt fa-2x"></i></a></li>
            </c:if>
            <c:if test="${log==true}">
                <li>${login.getName()}</li>
                <li><a href=""><i class="fas fa-plus fa-2x"></i></a></li>
                <li><a href=""><i class="fas fa-cogs fa-2x"></i></a></li>
                <li><a href="/logout"><i class="fas fa-sign-out-alt fa-2x"></i></a></li>
            </c:if>
        </ul>
    </div>
</nav>
<main>
    <div class="container-fluid">
        <div class="row" id="front-projects">
            <c:forEach items="${frontProjects}" var="p">
                <div class="col-md-4">
                    <div>
                        <h2>${p.getTitle()}</h2>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</main>

</body>
</html>

