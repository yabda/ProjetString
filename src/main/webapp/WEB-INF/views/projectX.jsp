<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Page du projet </title>
</head>
<header>
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-12">
                <a href="/"><img src="/resources/images/logo.png"></a>
                <h1>${project.getTitle()}</h1>
            </div>
        </div>
    </div>
</header>
<body>

<div>
    On y est presque, le projet a déjà récolté ${project.getCurrent()} sur ${project.getGoal()}€
</div>

<div>Ce projet est déjà suppporté par :
    <ul>
        <c:forEach items="${project.getParticipations().values().toArray()}" var="participations">
            <div class="col-md-4">
                <li>${participations}</li>
            </div>
        </c:forEach>

    </ul>

</div>
<div>
    <h2>PARRAINER LE PROJET</h2>
    <form action="/donation" method="POST">
        <p>ARGENT :  <input type=number name="donationValue" /></p>
        <input type="hidden" name="pId" value=${project.getId()} >
        <p><input type="submit" value="PARRAINER"></p>
    </form>
</div>

</body>
</html>
