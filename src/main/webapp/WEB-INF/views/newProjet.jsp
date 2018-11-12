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
            <form>
                <div class="form-group">
                    <label for="projectName">Nom du projet</label>
                    <input type="text" class="form-control" id="projectName" placeholder="Mon projet">
                </div>

                <div class="form-group">
                    <label for="deadline">Fin du projet</label>
                    <input class="form-control" type="date" id="deadline">
                </div>

                <div class="form-group">
                    <label for="categorie">Categorie</label>
                    <select class="form-control" id="categorie">
                        <option>1</option>
                        <option>2</option>
                        <option>3</option>
                        <option>4</option>
                        <option>5</option>
                    </select>
                </div>

                <div class="form-group">
                    <label for="description">Description</label>
                    <textarea class="form-control" id="description" rows="3"></textarea>
                </div>

                <div class="cp">
                    <h3>Contreparties</h3>
                    <div class="form-row">

                        <div class="col-md-4 mb-4">
                            <label for="NomCP">Nom</label>
                            <input type="text" class="form-control is-valid" id="NomCP" placeholder="Nom" required>
                            <div class="valid-feedback">
                                Looks good!
                            </div>
                        </div>

                        <div class="col-md-4 mb-4">
                            <label for="Valeur">Valeur</label>
                            <input type="number" class="form-control is-valid" id="Valeur" placeholder="Valeur" required>
                            <div class="valid-feedback">
                                Looks good!
                            </div>
                        </div>
                        <div class="col-md-4 mb-4">
                            <label for="descriptionCP">Description</label>
                            <textarea class="form-control is-valid" id="descriptionCP" rows="3" required></textarea>
                            <div class="valid-feedback">
                                Looks good!
                            </div>
                        </div>
                        <button type="button" class="btn btn-info">Ajouter un contrepartie</button>
                    </div>
                </div>
            </form>
            <button class="btn btn-primary" type="submit">Créer le projet</button>
        </div>
    </jsp:attribute>
</t:layout>
