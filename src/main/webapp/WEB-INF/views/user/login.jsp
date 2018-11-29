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
        <div class="container-fluid" id="main-content" style="padding-left: 100px; padding-right: 100px;">
            <div class="row">
                <div class="col-md-12">
                    <h2>Login</h2>
                    <form class="form-horizontal" method="post" role="form" action="/login" id="login-form">
                    <c:if test="${badLogin==true}">
                        <div class="form-group">
                            <label class="control-label col-md-2" for="name" id="error-label">Bad credentials</label>
                        </div>
                    </c:if>
                        <div class="form-group">
                            <label class="control-label col-md-2" for="name">Name:</label>
                            <div class="col-md-10">
                                <input type="text" class="form-control" id="name" name="name" placeholder="Name" required>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-2" for="password">Password:</label>
                            <div class="col-md-10">
                                <input type="password" class="form-control" id="password" name="password" placeholder="Password" required>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-2 col-md-10">
                                <button type="submit" class="btn btn-success">Login</button>
                                <a href="/register"><button type="button" class="btn btn-primary">Register</button></a>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </jsp:attribute>
</t:layout>