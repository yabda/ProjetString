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
        <div class="container-fluid">

            <h1>Nouveau projet</h1>
            <form method="POST" role="form" action="project/new">
                <div class="form-group">
                    <label for="projectName">Nom du projet</label>
                    <input type="text" class="form-control" id="projectName" name="projectName" placeholder="Mon projet">
                </div>

                <div class="form-group">
                    <label for="deadline">Fin du projet</label>
                    <input class="form-control" type="date" id="deadline" name="deadline">

                    <label for="goal">Objectif</label>
                    <input class="form-control" type="number" id="goal" name="goal" min="0">
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

    </jsp:attribute>
</t:layout>

