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

            <h1>Edit projet : ${project.getTitle()}</h1>
            <form method="POST" role="form" action="/updateProject">
                <div>
                    <input class="form-control" type="hidden" type="number" id="IDProjet" name="IDProjet" min="0" value=${project.getId()}>
                </div>
                <div class="form-group">
                    <label for="projectName">Nom du projet</label>
                    <input type="text" class="form-control" id="projectName" name="projectName" value="${project.getTitle()}">
                </div>

                <div class="form-group">
                    <label for="deadline">Fin du projet</label>
                    <input class="form-control" type="date" id="deadline" name="deadline" value=${project.getDeadLine()}>

                    <label for="goal">Objectif</label>
                    <input class="form-control" type="number" id="goal" name="goal" min="0" value=${project.getGoal()}>
                </div>

                <div class="form-group">
                    <label for="categorie">Categorie</label>
                    <select class="form-control" id="categorie" name="category">

                        <c:forEach  items="${categories}" var="c">

                            <c:if test="${c.getName() == project.getCategory().getName()}">
                                <option value=${c.getId()} selected>${c.getName()}</option>
                            </c:if>

                            <c:if test="${c.getName() != project.getCategory().getName()}">
                                <option value=${c.getId()}>${c.getName()}</option>
                            </c:if>


                        </c:forEach>
                    </select>
                </div>

                <div class="form-group">
                    <label for="description">Description</label>
                    <textarea class="form-control" id="description" rows="3" name="description" >${project.getDescription()}</textarea>
                </div>

                <button class="btn btn-primary" type="submit">Sauver le projet</button>
            </form>

            <div>

            <h3>Contreparties</h3>
                <form method="POST" role="form" action="/removeCounterPart">
                    <div>
                        <input class="form-control" type="hidden" type="number" id="IDProjet" name="IDProjet" min="0" value=${project.getId()}>
                    </div>
                    <ul>
                        <c:forEach items="${project.getCounterparts()}" var="counterpart">

                                <li><input type="radio" name="CP" value="${counterpart.getId()}">${counterpart.getName()}, ${counterpart.getPrice()} €</li>
                        </c:forEach>
                     </ul>
                    <button class="btn btn-danger" type="submit"><i class="fa fa-trash" aria-hidden="true"></i></button>
                </form>
            </div>

            <h3>Ajouter une contrepartie</h3>
            <div>
                <form method="POST" role="form" action="/addCounterPart">
                    <div>
                        <input class="form-control" type="hidden" type="number" id="IDProjet" name="IDProjet" min="0" value=${project.getId()}>
                    </div>
                    <div class="form-group">
                        <label for="cpName">Nom</label>
                        <input class="form-control" id="cpName" name="cpName" ></input>
                    </div>
                    <div class="form-group">
                        <label for="price">Prix</label>
                        <input class="form-control" type="number" id="price" name="price" min="0">
                    </div>
                    <div class="form-group">
                        <label for="cpDescription">Description</label>
                        <textarea class="form-control" id="cpDescription" rows="3" name="cpDescription" ></textarea>
                    </div>
                    <button class="btn btn-primary" type="submit">Ajouter la contrepartie</button>
                </form>
            </div>
        </div>

    </jsp:attribute>
</t:layout>

