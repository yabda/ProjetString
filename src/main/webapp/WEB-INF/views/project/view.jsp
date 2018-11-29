<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page session="true" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<jsp:useBean id="now" class="java.util.Date"/>
<jsp:useBean id="decimal" class="java.text.DecimalFormat"/>
<%
    decimal.setMaximumFractionDigits(2);
%>

<t:layout>
    <jsp:attribute name="header">
        <link rel="stylesheet" href="/resources/css/project.css">
    </jsp:attribute>

    <jsp:attribute name="main">
        <div class="container-fluid" style="padding-left: 100px; padding-right: 100px;" id="main-content">
            <div class="row"></div>
            <div class="row" id="top-panel">
                <div class="col-md-12">
                    <h1 id="title-project">${project.getTitle()}</h1>
                    <div class="w3-light-grey">
                        <c:if test="${project.getCurrent() > project.getGoal()}">
                            <div id="text-bar" class="w3-container w3-blue w3-center" style="height:24px;width:100.0%">${decimal.format(project.getCurrent() / project.getGoal() * 100)}%</div>
                        </c:if>
                        <c:if test="${project.getCurrent() <= project.getGoal()}">
                            <div id="text-bar" class="w3-container w3-blue w3-center" style="height:24px;width:${project.getCurrent() / project.getGoal() * 100}%">${decimal.format(project.getCurrent() / project.getGoal() * 100)}%</div>
                        </c:if>
                    </div>
                    <div id="subbar">
                        <div id="goal">${project.getCurrent()} / ${project.getGoal()} €</div>
                        <div id="date">
                            <c:set var="time" scope="page" value="${now}"/>
                            <c:if test="${time.after(project.getDeadLine())}">
                                Time's up !
                            </c:if>
                            <c:if test="${!time.after(project.getDeadLine())}">
                                ${Integer.valueOf( (project.getDeadLine().getTime() - time.getTime()) / (1000 * 60 * 60 * 24))} days left !
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-8">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-md-12" id="mid-panel">
                                <h2>Description</h2>
                                <p>${project.getDescription()}</p>
                                <h2>Supporters</h2>
                                <ul>
                                    <c:forEach items="${project.getUsersParticipation()}" var="helpers">
                                        <li>${helpers.getName()} : ${project.getParticipations().get(helpers.getId())}€</li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </div>
                        <div class="row"></div>
                        <div class="row" id="bottom-panel">
                            <div class="col-md-12">
                                <h2>Question area</h2>
                                <div>
                                    <c:if test="${sessionScope.get('user').getName()!=null}">
                                        <form action="/project/sendMsg" class="form-horizontal" method="post" role="form">
                                            <div class="form-group">
                                                <label for="content">Ask something :</label>
                                                <div>
                                                    <input id="content" class="form-control" type=text min="1" name="content" placeholder="Question" required/>
                                                </div>
                                            </div>
                                            <input type="hidden" name="pId" value=${project.getId()} >
                                            <div class="form-group">
                                                <div>
                                                    <button type="submit" class="btn btn-success">Ask !</button>
                                                </div>
                                            </div>
                                        </form>
                                    </c:if>
                                    <c:if test="${sessionScope.get('user')==null}">
                                        <p>You must be <a href="/login">connected</a> to send a message</p>
                                    </c:if>
                                </div>
                                <c:forEach items="${project.getMessages()}" var="m">
                                    <div class="message-box">
                                        <div>
                                            <strong>Question by ${m.getBelongUser().getName()}</strong> : <br/> ${m.getContent()}
                                            <br/>
                                        </div>
                                        <c:if test="${m.getBelongAnswer()!=null}">
                                            <div>
                                                <strong>Answer</strong> : <br> ${m.getBelongAnswer().getContent()}
                                            </div>
                                        </c:if>
                                        <c:if test="${(sessionScope.get('user').getId() == project.getBelongUser().getId() && m.getBelongAnswer() == null)}">
                                            <form action="/project/answerMsg" class="form-horizontal" method="post" role="form">
                                                <div class="form-group">
                                                    <label for="content">Answer something :</label>
                                                    <div><input id="content" class="form-control" type=text min="1" name="content" placeholder="Question" required/></div>
                                                </div>
                                                <input type="hidden" name="pId" value=${project.getId()} >
                                                <input type="hidden" name="mId" value=${m.getId()} >
                                                <div class="form-group">
                                                    <div><button type="submit" class="btn btn-success">Answer</button></div>
                                                </div>
                                            </form>
                                        </c:if>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-md-1"></div>

                <div class="col-md-3">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-md-12" id="side-panel">
                                <h2>Pledge</h2>
                                <c:forEach items="${project.getCounterparts()}" var="counterpart">
                                    <div class="counterpart" onclick="$('#donationValue').val(${counterpart.getPrice()});">
                                        <h3>Pledge of ${counterpart.getPrice()}€ or more</h3>
                                        <h4>${counterpart.getName()}</h4>
                                        <p>${counterpart.getDescription()}</p>
                                    </div>
                                </c:forEach>
                                <c:if test="${!time.after(project.getDeadLine())}">
                                    <c:if test="${sessionScope.get('user') != null}">
                                        <div class="donate">
                                            <form class="form-horizontal" method="post" role="form" action="/project/donation">
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
                                </c:if>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </jsp:attribute>
</t:layout>
