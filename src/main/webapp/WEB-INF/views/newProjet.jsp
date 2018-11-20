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
            <form method="post" role="form" action="newProjet">
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
                    <textarea class="form-control" id="description" rows="3" name="description"></textarea>
                </div>

                <div class="cp">
                    <h3>Contreparties</h3>

                    <div class="col-md-4 mb-4">
                        <label for="nbCp">Nombre de contrepartie</label>
                        <input type="number" class="form-control is-valid" id="nbCp" min=0 placeholder="Valeur">
                        <div class="valid-feedback">
                            Looks good!
                        </div>
                        <button type="button" class="btn btn-info" onclick="ajoutCP()">Valider</button>
                    </div>
                    <div id="cpDiv">

                    </div>
                </div>
                <button class="btn btn-primary" type="submit">Créer le projet</button>
            </form>

        </div>


        <script>
            function ajoutCP() {

                var nb=document.getElementById("nbCp").value;
                var i;
                var string="";
                for (i = 0; i < nb; i++) {
                    string=string+"<h4>Counterpart "+i+"</h4><div class=\"form-row\">" +
                        "\n" +
                        "                        <div class=\"col-md-4 mb-4\">\n" +
                        "                            <label for=\"NomCP\">Nom</label>\n" +
                        "                            <input type=\"text\" class=\"form-control is-valid\" id=\"NomCP\" placeholder=\"Nom\" required>\n" +
                        "                            <div class=\"valid-feedback\">\n" +
                        "                                Looks good!\n" +
                        "                            </div>\n" +
                        "                        </div>\n" +
                        "\n" +
                        "                        <div class=\"col-md-4 mb-4\">\n" +
                        "                            <label for=\"Valeur\">Valeur</label>\n" +
                        "                            <input type=\"number\" class=\"form-control is-valid\" id=\"Valeur\" placeholder=\"Valeur\" required>\n" +
                        "                            <div class=\"valid-feedback\">\n" +
                        "                                Looks good!\n" +
                        "                            </div>\n" +
                        "                        </div>\n" +
                        "                        <div class=\"col-md-4 mb-4\">\n" +
                        "                            <label for=\"descriptionCP\">Description</label>\n" +
                        "                            <textarea class=\"form-control is-valid\" id=\"descriptionCP\" rows=\"3\" required></textarea>\n" +
                        "                            <div class=\"valid-feedback\">\n" +
                        "                                Looks good!\n" +
                        "                            </div>\n" +
                        "                        </div>\n" +
                        "                    </div>"
                }
                document.getElementById('cpDiv').innerHTML=string;
            }
        </script>
    </jsp:attribute>
</t:layout>

