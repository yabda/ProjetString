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
                <h2>Register</h2>
                <form class="form-horizontal" method="post" role="form" action="/register" id="register-form">
                <c:if test="${not empty badRegister}">
                    <div class="form-group">
                        <label class="control-label col-md-2" id="error-label" for="name">${badRegister}</label>
                    </div>
                </c:if>
                    <div class="form-group">
                        <label class="control-label col-md-2" for="name">Name:</label>
                        <div class="col-md-10">
                            <input type="text" class="form-control" id="name" name="name" placeholder="Name" required value="${name}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2" for="password">Password:</label>
                        <div class="col-md-10">
                            <input type="password" class="form-control" id="password" name="password" placeholder="Password" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2" for="repeat-password">Reapeat password:</label>
                        <div class="col-md-10">
                            <input type="password" class="form-control" id="repeat-password" name="repeat-password" placeholder="Password" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-offset-2 col-md-10">
                            <button type="submit" class="btn btn-success">Register</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </jsp:attribute>
</t:layout>