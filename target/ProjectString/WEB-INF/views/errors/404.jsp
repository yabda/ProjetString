<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
    <jsp:attribute name="header">
    </jsp:attribute>
    <jsp:attribute name="main">
        <div class="container">
            <div>
                <h2>404 - Not found</h2>
            </div>
        </div>
    </jsp:attribute>
</t:layout>