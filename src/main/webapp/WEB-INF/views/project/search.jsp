<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>


<t:layout>
    <jsp:attribute name="header">
        <link rel="stylesheet" href="/resources/css/myProject.css">
    </jsp:attribute>
    <jsp:attribute name="main">
        <div class="container-fluid" style="padding-left: 100px; padding-right: 100px;">
            <div class="row">
                <div class="col-md-12">
                    <c:if test="${find}">
                        <div class="myProjects">
                            <h2>Search results for <strong>${terms}</strong></h2>
                            <ul class="listeProject">
                            <c:forEach items="${searchResult}" var="p">
                                <a class="linkProject" href="/project/${p.getId()}">
                                    <li class="aProject">
                                        <h3 class="titleProjet">${p.getTitle()}</h3>
                                        <p>${p.getDescription()}</p>
                                    </li>
                                </a>
                            </c:forEach>
                            </ul>
                        </div>
                    </c:if>
                    <c:if test="${!find}">
                        <h2>No results for <strong>${terms}</strong></h2>
                        you might want to start your own project <a href="/project/new">here</a> or look at the others available on KICKERSTART
                    </c:if>
                </div>
            </div>
        </div>



    </jsp:attribute>
</t:layout>
