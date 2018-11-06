<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
    <jsp:attribute name="header">
        <link rel="stylesheet" href="/resources/css/user.css">
    </jsp:attribute>
    <jsp:attribute name="main">
        <div class="container">
            <div>
                <h2 id="user-name">${sessionScope.user.getName()}</h2>
                <hr>
                <div id="display-user">
                    <div id="projects-supported">
                        <h3>Projects supported</h3>
                        <div class="container-fluid">
                            <c:set var="i" scope="page" value="0"/>
                            <c:forEach items="${sessionScope.user.getParticipeProjects()}" var="p">
                            <c:if test="${i % 4 == 0}">
                                <div class="row">
                            </c:if>
                                    <div class="col-md-3">
                                        <h4>${p.getTitle()}</h4>
                                    </div>
                            <c:set var="i" scope="page" value="${i + 1}"/>
                            <c:if test="${i % 4 == 0}">
                                </div>
                            </c:if>
                        </c:forEach>
                            <c:if test="${i % 4 != 0}"></div></c:if>
                    </div>
                </div>
                <hr>
                <div id="my-projects">
                    <h3>My Projects</h3>
                    <div class="container-fluid">
                        <c:set var="i" scope="page" value="0"/>
                        <c:forEach items="${sessionScope.user.getCreatedProjects()}" var="p">
                            <c:if test="${i % 4 == 0}">
                                <div class="row">
                            </c:if>
                            <div class="col-md-3">
                                <h4>${p.getTitle()}</h4>
                            </div>
                            <c:set var="i" scope="page" value="${i + 1}"/>
                            <c:if test="${i % 4 == 0}">
                                </div>
                            </c:if>
                        </c:forEach>
                        <c:if test="${i % 4 != 0}"></div></c:if>
                </div>
            </div>
            <hr>
            <div id="my-messages">
                <h3>My messages</h3>
                <div class="container-fluid">
                    <c:set var="i" scope="page" value="0"/>
                    <c:forEach items="${sessionScope.user.getMessages()}" var="m">
                            <c:if test="${i % 4 == 0}">
                                <div class="row">
                            </c:if>
                            <div class="col-md-3">
                                <h4>${m.getContent()}</h4>
                            </div>
                            <c:set var="i" scope="page" value="${i + 1}"/>
                            <c:if test="${i % 4 == 0}">
                                </div>
                            </c:if>
                        </c:forEach>
                    <c:if test="${i % 4 != 0}"></div></c:if>
            </div>
        </div>
                <hr>
                <div id="form-edit-name">
                    <form class="form-horizontal" method="post" role="form" action="/users/me/editName">
                        <div class="form-group">
                            <label class="control-label col-md-2" for="name">New name:</label>
                            <div class="col-md-10">
                                <input type="text" class="form-control" id="name" name="name" placeholder="Name" required>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-2 col-md-10">
                                <button type="submit" class="btn btn-success">Change Name</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    </jsp:attribute>
</t:layout>