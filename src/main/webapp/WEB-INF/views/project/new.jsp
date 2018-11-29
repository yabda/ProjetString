<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Date" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
    <jsp:attribute name="header">
        <link rel="stylesheet" href="/resources/css/projetX.css">
    </jsp:attribute>
    <jsp:attribute name="main">
        <div class="container-fluid" style="padding-left: 100px; padding-right: 100px;">
            <div class="row">
                <div class="col-md-12">
                    <h1>New project</h1>
                    <form method="POST" role="form" action="/project/new">
                        <div class="form-group">
                            <label for="projectName">Name of project</label>
                            <input type="text" class="form-control" id="projectName" name="projectName" required>
                        </div>
                        <div class="form-group">
                            <label for="deadline">Deadline</label>
                            <input class="form-control" type="date" id="deadline" name="deadline" required>
                        </div>
                        <div class="form-group">
                            <label for="goal">Goal</label>
                            <input class="form-control" type="number" id="goal" name="goal" min="0" required>
                        </div>

                <div class="form-group">
                    <label for="categorie">Categorie</label>
                    <select class="form-control" id="categorie" name="category">
                        <c:forEach  items="${categories}" var="c">
                            <option value="${c.getId()}">${c.getName()}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="form-group">
                    <label for="description">Description</label>
                    <textarea class="form-control" id="description" rows="3" name="description"></textarea>
                </div>
                <button class="btn btn-primary" type="submit">Créer le projet</button>
            </form>

        </div>

    <script type="application/javascript">
        function setMinMaxTime(){
            var dt = new Date();
            var dt2 = new Date();
            dt2.setMonth(dt.getMonth()+2);
            var time = dt.getFullYear() + "-" + dt.getMonth() + "-" + dt.getDate();
            var time2 = dt2.getFullYear() + "-" + dt2.getMonth() + "-" + dt2.getDate();
            $("#deadline").attr({
                "max" : time2,        // substitute your own
                "min" : time          // values (or variables) here
            });
        }
        setMinMaxTime();
    </script>
    </jsp:attribute>

</t:layout>

