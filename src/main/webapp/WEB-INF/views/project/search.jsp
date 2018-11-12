<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>


<t:layout>
    <jsp:attribute name="header">
    </jsp:attribute>
    <jsp:attribute name="main">
        <c:if test="${find}">
        <h2>Search result for ${terms}</h2>
        </c:if>
        <c:if test="${!find}">
            <h2>No result for ${terms}</h2>
            you might want to start your own project <a href="/">here</a> or look at the others available on KICKERSTART
        </c:if>
        <div class="container-fluid">
            <div class="row" id="front-projects">
            <c:forEach items="${searchResult}" var="p">
                <div class="col-md-4">
                    <div>
                        <a href="/Project/${p.getId()}"><h2>${p.getTitle()}</h2></a>
                    </div>
                </div>
            </c:forEach>
            </div>
        </div>
    </jsp:attribute>
</t:layout>
