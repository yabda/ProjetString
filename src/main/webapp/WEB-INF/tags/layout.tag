<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ tag description="Main Layout" pageEncoding="UTF-8" %>
<%@ attribute name="header" fragment="true" %>
<%@ attribute name="main" fragment="true" %>

<html>
<head>
    <title>KickerStart -- Index</title>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
    <link rel="stylesheet" href="/resources/css/index.css">
    <link href="https://fonts.googleapis.com/css?family=K2D" rel="stylesheet">

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

    <jsp:invoke fragment="header"/>
</head>
<body>
<header>
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-12">
                <a href="/"><img class="img-responsive" src="/resources/images/logo.png"></a>
                <h1>A kick in your projects' butt</h1>
            </div>
        </div>
    </div>
</header>
<nav class="navbar navbar-toggleable-md navbar-light bg-faded">
    <div class="container-fluid">
        <ul class="nav navbar-nav mr-auto"></ul>
        <ul class="nav navbar-nav" id="right-bar">
            <c:if test="${sessionScope.user == null}">
                <li class="nav-item"><a href="/login"><i class="fas fa-sign-in-alt fa-2x"></i></a></li>
            </c:if>
            <c:if test="${sessionScope.user != null}">
                <li class="nav-item">${sessionScope.user.getName()}</li>
                <li class="nav-item"><a href="#"><i class="fas fa-plus fa-2x"></i></a></li>
                <li class="nav-item"><a href="/users/me"><i class="fas fa-cogs fa-2x"></i></a></li>
                <li class="nav-item"><a href="/logout"><i class="fas fa-sign-out-alt fa-2x"></i></a></li>
            </c:if>
        </ul>
    </div>
</nav>
<main>
    <jsp:invoke fragment="main"/>
</main>

</body>
</html>

