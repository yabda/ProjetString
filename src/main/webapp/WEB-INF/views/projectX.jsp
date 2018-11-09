<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page session="true" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
    <jsp:attribute name="header">
<link rel="stylesheet" href="/resources/css/projetX.css">
    </jsp:attribute>

    <jsp:attribute name="main">
        <div class="container-fluid">
            <h1 id="project-name">${project.getTitle()}</h1>
            <div class="alert alert-success goalDone" >
                <strong>Success!</strong> Indicates a successful or positive action.
            </div>
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
                <div class="card">
                <h3>Soutenir sans récompense</h3>
                <c:if test="${sessionScope.get('user').getName()!=null}">
                    <div>
                <form action="/donation" method="POST">
                    <p>Amount :  <input type=number required="true" name="donationValue" >
                    <input type="hidden" name="pId" value=${project.getId()} >
                    <input type="submit" value="DONATE"></p>
                </form>
                    </div>
            </c:if>
                <c:if test="${sessionScope.get('user')==null}">
        Vous devez etre connecté pour participer à un projet
    </c:if>
                </div>
            </div>
            <br>

            <div>

                    <c:forEach items="${project.getCounterparts()}" var="counterpart">
                        <div class="card">
                            <h3>${counterpart.getName()}</h3>
                            <p>${counterpart.getDescription()}</p>

                            <div >
                                <c:if test="${sessionScope.get('user').getName()!=null}">
                <form action="/donation" method="POST">
                    <p>Amount :  <input value=${counterpart.getPrice()} min=${counterpart.getPrice()} type=number required="true" name="donationValue">
                    <input type="hidden" name="pId" value=${project.getId()} >
                    <input type="submit" value="DONATE"></p>
                </form>

            </c:if>
                                <c:if test="${sessionScope.get('user')==null}">
        Vous devez etre connecté pour participer à un projet
    </c:if>
                            </div>

                        </div>
                        <br>
                    </c:forEach>
            </div>
            <hr>
        </div>
    </jsp:attribute>
</t:layout>

