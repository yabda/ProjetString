<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page session="true" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
    <jsp:attribute name="header">
        <link rel="stylesheet" href="/resources/css/project.css">
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    </jsp:attribute>

    <jsp:attribute name="main">
        <div class="container-fluid" style="padding-left: 100px; padding-right: 100px;">
            <div class="row"></div>
            <div class="row" id="top-panel">
                <div class="col-md-12">
                    <h1 id="title-project">${project.getTitle()}</h1>
                    <div class="w3-light-grey">
                        <c:if test="${project.getCurrent() > project.getGoal()}">
                            <div id="text-bar" class="w3-container w3-blue w3-center" style="height:24px;width:100.0%">${project.getCurrent() / project.getGoal() * 100}%</div>
                        </c:if>
                        <c:if test="${project.getCurrent() <= project.getGoal()}">
                            <div id="text-bar" class="w3-container w3-blue w3-center" style="height:24px;width:${project.getCurrent() / project.getGoal() * 100}%">${project.getCurrent() / project.getGoal() * 100}%</div>
                        </c:if>
                    </div>
                    <div>${project.getCurrent()} / ${project.getGoal()} €</div>
                    <div>
                        <c:set var="time" scope="page" value="${Date()}"/>
                        <c:if test="${time.after(project.getDeadLine())}">
                            <h3>Time's up !</h3>
                        </c:if>
                        <c:if test="${!time.after(project.getDeadLine())}">
                            <h3>${Integer.valueOf( (project.getDeadLine().getTime() - time.getTime()) / (1000 * 60 * 60 * 24))} days left !</h3>
                        </c:if>
                    </div>
                </div>
            </div>
            <div class="row" id="mid-panel">
                <div class="col-md-8">
                    <h2>Description</h2>
                    <p>${project.getDescription()}</p>
                    <h2>Supporters</h2>
                    <ul>
                        <c:forEach items="${project.getUsersParticipation()}" var="helpers">
                            <li>${helpers.getName()} : ${project.getParticipations().get(helpers.getId())}€</li>
                        </c:forEach>
                    </ul>
                </div>
                <div class="col-md-1"></div>
                <div class="col-md-3">
                    <h2>Pledge</h2>
                    <c:forEach items="${project.getCounterparts()}" var="counterpart">
                        <div class="counterpart" onclick="$('#donationValue').val(${counterpart.getPrice()});">
                            <h3>Pledge of ${counterpart.getPrice()}€ or more</h3>
                            <h4>${counterpart.getName()}</h4>
                            <p>${counterpart.getDescription()}</p>
                        </div>
                    </c:forEach>
                    <c:if test="${sessionScope.get('user') != null}">
                        <div class="donate">
                            <form class="form-horizontal" method="post" role="form" action="/donation">
                                <div class="form-group">
                                    <label for="donationValue">Donation :</label>
                                    <input type="hidden" name="pId" value=${project.getId()} >
                                    <div>
                                        <input id="donationValue" class="form-control" type=number min="1" name="donationValue" placeholder="Amount" required value="1"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div>
                                        <button type="submit" class="btn btn-success">Donate</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </c:if>
                    <c:if test="${sessionScope.get('user') == null}">
                        <div class="donate">
                            <p>You must be <a href="/login">connected</a> to pledge</p>
                        </div>
                    </c:if>
                </div>
            </div>
        </div>
    </jsp:attribute>
</t:layout>
