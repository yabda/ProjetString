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
        <c:if test="${find}">


            <div class="myProjects">
                <h2>Search result for ${terms}</h2>
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
            <h2>No result for ${terms}</h2>
            you might want to start your own project <a href="/project/new">here</a> or look at the others available on KICKERSTART
        </c:if>



    </jsp:attribute>
</t:layout>
