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
    <link href="https://fonts.googleapis.com/css?family=K2D" rel="stylesheet">
    <link rel="stylesheet" href="/resources/css/index.css">

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
<nav class="navbar navbar-expand-lg bg-dark navbar-dark">
    <c:if test="${sessionScope.user != null}">
        <div class="navbar-brand" href="#">${sessionScope.user.getName()}</div>
    </c:if>
    <button style="color: black" class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarToggler" aria-controls="navbarToggler" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarToggler">
        <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
            <c:if test="${sessionScope.user == null}">
                <li class="nav-item"><a href="/login"><i class="nav-link fas fa-sign-in-alt fa-2x"></i></a></li>
            </c:if>
            <c:if test="${sessionScope.user != null}">
                <li class="nav-item"><a href="#"><i class="nav-link fas fa-plus fa-2x"></i></a></li>
                <li class="nav-item"><a href="/users/me"><i class="nav-link fas fa-cogs fa-2x"></i></a></li>
                <li class="nav-item"><a href="/logout"><i class="nav-link fas fa-sign-out-alt fa-2x"></i></a></li>
            </c:if>
        </ul>
        <form class="form-inline my-2 my-lg-0" action="/search" method="POST" id="search-form">
            <input class="form-control mr-sm-2" type="search" placeholder="Search" name="terms" aria-label="Search">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit"><i class="fas fa-search fa-2x"></i></button>
        </form>
    </div>
</nav>
<main>
    <jsp:invoke fragment="main"/>
</main>

</body>
</html>

