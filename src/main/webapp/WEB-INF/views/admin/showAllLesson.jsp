<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="eu.wsiz.school.models.Lesson" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<% ArrayList<Lesson> lessons = (ArrayList) session.getAttribute("lessons");
    int count = 0;
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    Date date = new Date();
    String dateString = df.format(date);
    df = new SimpleDateFormat("HH:mm");
    String timeString = df.format(date);

    String nowDate = dateString + "T" + timeString;


%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Lista studentów</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css">
    <link rel="stylesheet " href="/resourse/css/style.css">


</head>

<body>

<!-- HEADER: Begin -->
<header class="navbar navbar-toggleable-md navbar-light bg-faded header-border">
    <div class="container">
        <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse"
                data-target="#navbarTogglerDemo02" aria-controls="navbarTogglerDemo02" aria-expanded="false"
                aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <a class="navbar-brand mb-0" href="#">
            <img src="https://img.icons8.com/bubbles/2x/school.png" width="32" height="32">
        </a>
        <a class="navbar-brand mb-0" href="#"><strong>Online</strong> school</a>

        <div class="collapse navbar-collapse justify-content-end" id="navbarTogglerDemo02">
            <ul class="navbar-nav mt-2 mt-md-0">
                <li class="nav-item active">
                    <a class="nav-link" href="/groups/admin/">Grupy</a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="/users/admin/">Studenci</a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="/lessons/admin/">Zajęcia</a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="/presences/admin/">Lista obecności</a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="/">Logout</a>
                </li>
            </ul>
        </div>

    </div>
</header>

<!-- HEADER: End -->

<article>

    <div class="container">
        <div class="row pt-5">
            <div class="col-12">
                <div class="text-center mt-5">
                    <h3 class="card-title">Zajęcia JavaDev</h3>
                    <p class="card-text text-muted">Lista zajęć</p>
                </div>
            </div>
        </div>

        <div class="row mt-3">
            <div class="col pull-sm-0 pull-md-1">
                <div class="p-2">
                    <div class="input-group mb-2 mr-sm-2 mb-sm-0 justify-content-end">
                        <button type="button" class="btn btn-sm btn-success" data-toggle="modal"
                                data-target="#additionModal"
                                onClick='location.href="/lessons/"'>Dodaj
                            zajęcie
                        </button>
                    </div>
                </div>
            </div>
        </div>
        <!-- TABLE: Begin -->
        <div class="row justify-content-md-center pt-0">
            <div class="col ol-lg-8 col-md-10 col-sm-12 mb-4">
                <c:forEach items="${lessons}" var="lesson">
                    <div class="row border-bottom pb-3 pt-3">
                        <div class="col-2 border-right">
                            <strong>Zajęcia <%= ++count%>
                            </strong>
                            <p>${lesson.date} ${lesson.getTime()} </p>
                        </div>
                        <div class="col-8">
                            <strong>${lesson.theme}</strong>
                            <p>${lesson.place}</p>
                            <p class="font-weight-bold pgs-text-color d-inline mt-1">Uzupewnij ankietę:</p>
                            <a href="#" class="d-inline text-muted">${group.teacher}</a>
                        </div>
                        <div class="col-2 d-flex justify-content-center pt-1">
                            <a class="pgs-text-color mr-2"
                               href="/lessons/edit/${group.id}"><i
                                    class="fa fa-pencil-square-o fa-lg" aria-hidden="true"></i></a>
                            <a class="pgs-text-color mr-2" data-method="delete" href="/lessons/?id=${group.id}"><i
                                    class="fa fa-trash-o fa-lg" aria-hidden="true"></i></a>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
        <!-- TABLE: End -->
    </div>
</article>

<!-- Footer: Begin -->
</body>

   <footer>
    <div class="container fixed-bottom">
        <div class="row">
            <div class="col bg-inverse p-2">
                <p class="text-white text-center mb-0">Made with <i class="fa fa-heart text-danger" aria-hidden="true"></i> by Alevtyna Yarychevska and Serhij Moskalenko</p>
            </div>
        </div>
    </div>
</footer>

<!-- SCRIPTS: Begin -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"
        integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb"
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"
        integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn"
        crossorigin="anonymous"></script>
<!-- SCRIPTS: End -->
<!-- Footer: End -->

</html>

