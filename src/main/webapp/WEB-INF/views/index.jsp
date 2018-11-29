<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
    <jsp:attribute name="header">
    </jsp:attribute>
    <jsp:attribute name="main">
        <div class="container-fluid" style="padding-left: 100px; padding-right: 100px;">
            <div class="row" id="front-projects">
                <div class="col-md-12">
                    <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
                        <ol class="carousel-indicators">
                            <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
                            <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
                            <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
                        </ol>
                        <div class="carousel-inner">
                            <c:set var="nbr" scope="page" value="1"/>
                            <c:forEach items="${frontProjects}" var="p">
                                <c:if test="${nbr == 2}">
                                    <div class="carousel-item">
                                </c:if>
                                <c:if test="${nbr == 1}">
                                    <div class="carousel-item active">
                                    <c:set var="nbr" value="2"/>
                                </c:if>
                                <div>
                                    <a href="/project/${p.getId()}"><h2>${p.getTitle()}</h2></a>
                                </div>
                                </div>
                            </c:forEach>
                        </div>
                        <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
                            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                            <span class="sr-only">Previous</span>
                        </a>
                        <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
                            <span class="carousel-control-next-icon" aria-hidden="true"></span>
                            <span class="sr-only">Next</span>
                        </a>
                    </div>
                </div>
            </div>
            <hr>
            <div class="row">
                <div class="col-md-12">
                <c:forEach items="${allProj}" var="p">

                    <a class="linkProject" href="/project/${p.getId()}">
                        <li class="aProject">
                            <h3 class="titleProjet">${p.getTitle()}</h3>
                            <p>${p.getDescription()}</p>
                        </li>
                    </a>
                </c:forEach>
                </div>
            </div>
        </div>
    </jsp:attribute>
</t:layout>
