<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page session="true" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
    <jsp:attribute name="header">

    </jsp:attribute>

    <jsp:attribute name="main">
        <div class="container-fluid">
            <h1>${project.getTitle()}</h1>
            <p>${project.getCreatedAt()} - ${project.getDeadLine()}</p>
            <div>
                <p>${project.getDescription()}</p>
            </div>
            <div>
                On y est presque, le projet a déjà récolté ${project.getCurrent()} sur ${project.getGoal()}€
            </div>
            <hr>
            <div>
                <h1>Supporters</h1>
                <ul>
        <c:forEach items="${project.getUsersParticipation()}" var="helpers">
            <div class="col-md-4">
                <li>${helpers.getName()} : ${project.getParticipations().get(helpers.getId())}$</li>
            </div>
        </c:forEach>
                </ul>
            </div>
            <hr>

            <div>
                <h1>Soutenir</h1>
                <div>
                    <h3>S'engager sans récompense</h3>
                    <form action="/donation" method="POST">
                        <p>ARGENT :  <input type=number name="donationValue" /></p>
                        <input type="hidden" name="pId" value=${project.getId()} >
                        <p><input type="submit" value="PARRAINER"></p>
                    </form>
                </div>
                <div>AUTRE COUNTERPART</div>
            </div>

    </ul>

</div>
<div>
    <h2>Donate to project</h2>

    <c:if test="${sessionScope.get('user').getName()!=null}">
        <form action="/donation" method="POST">
            <p>Amount :  <input type=number required="true" name="donationValue" /></p>
            <input type="hidden" name="pId" value=${project.getId()} >
            <p><input type="submit" value="DONATE"></p>
        </form>
    </c:if>
    <c:if test="${sessionScope.get('user')==null}">
        You must be connected to donate
    </c:if>
</div>

        </div>
    </jsp:attribute>
</t:layout>