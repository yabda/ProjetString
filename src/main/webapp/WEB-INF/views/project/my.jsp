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

        <div class="myProjects">
            <h3>My Projects</h3>
            <ul class="listeProject">

                <c:forEach items="${user.getCreatedProjects()}" var="p">

                    <a class="linkProject" href="/project/modify/${p.getId()}">
                        <li class="aProject">

                            <h3 class="titleProjet">${p.getTitle()}</h3>
                            <p>${p.getDescription()}</p>

                        </li>

                    </a>


                </c:forEach>
            </ul>
        </div>

    </jsp:attribute>
</t:layout>

