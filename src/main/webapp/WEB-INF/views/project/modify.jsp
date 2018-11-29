<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<jsp:useBean id="now" class="java.util.Date"/>

<t:layout>
    <jsp:attribute name="header">
        <link rel="stylesheet" href="/resources/css/projetX.css">
    </jsp:attribute>
    <jsp:attribute name="main">
        <div class="container-fluid" style="padding-left: 100px; padding-right: 100px;">
            <div class="row">
                <div class="col-md-12">
                    <h1>Edit project : ${project.getTitle()}</h1>
                    <form method="POST" role="form" action="/project/update">
                        <div>
                            <input class="form-control" type="hidden" type="number" id="IDProjet" name="IDProjet" min="0" required value=${project.getId()}>
                        </div>
                        <div class="form-group">
                            <label for="projectName">Name of the project</label>
                            <input type="text" class="form-control" id="projectName" name="projectName" required value="${project.getTitle()}">
                        </div>

                        <div class="form-group">
                            <label for="deadline">Deadline</label>
                            <input class="form-control" type="date" id="deadline" name="deadline" min=${now} required value=${project.getDeadLine()}>
                        </div>
                        <div class="form-group">
                            <label for="goal">Goal</label>
                            <input class="form-control" type="number" id="goal" name="goal" min="0" required value="${project.getGoal()}">
                        </div>

                        <div class="form-group">
                            <label for="categorie">Category</label>
                            <select class="form-control" id="categorie" required name="category">

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

                        <button class="btn btn-primary" type="submit">Save</button>
                    </form>

                    <div>

                        <h3>Counterparts</h3>
                        <form method="POST" role="form" action="/project/removeCounterPart">
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

                    <h3>Add a counterpart</h3>
                    <div>
                        <form method="POST" role="form" action="/project/addCounterPart">
                            <div>
                                <input class="form-control" type="hidden" type="number" id="IDProjet" name="IDProjet" min="0" value=${project.getId()}>
                            </div>
                            <div class="form-group">
                                <label for="cpName">Name</label>
                                <input class="form-control" id="cpName" name="cpName" required>
                            </div>
                            <div class="form-group">
                                <label for="price">Price</label>
                                <input class="form-control" type="number" id="price" name="price" min="0" required>
                            </div>
                            <div class="form-group">
                                <label for="cpDescription">Description</label>
                                <textarea class="form-control" id="cpDescription" rows="3" name="cpDescription" required ></textarea>
                            </div>
                            <button class="btn btn-primary" type="submit">Add</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>

    </jsp:attribute>
</t:layout>

