<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page session="true" %>

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
        <c:forEach items="${project.getUsersParticipation()}" var="helpers">
            <div class="col-md-4">
                <li>${helpers.getName()} : ${project.getParticipations().get(helpers.getId())}$</li>
            </div>
        </c:forEach>

    </ul>

</div>
<div>
    <h2>Donate to project</h2>

    <c:if test="${sessionScope.get('user').getName()!=null}">
        <form action="/donation" method="POST">
            <p>Montant :  <input type=number name="donationValue" /></p>
            <input type="hidden" name="pId" value=${project.getId()} >
            <p><input type="submit" value="PARRAINER"></p>
        </form>
    </c:if>
    <c:if test="${sessionScope.get('user')==null}">
        You must be connected to donate
    </c:if>
</div>

</body>
</html>
