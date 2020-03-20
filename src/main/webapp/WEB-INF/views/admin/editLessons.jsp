<%@ page import="eu.wsiz.school.models.Lesson" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<% Lesson lesson = (Lesson) session.getAttribute("lesson");

%>
<head>
    <meta charset="UTF-8">
    <title>Lista studentów</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css">
    <link rel="stylesheet " href="/resourse/css/style.css">
    <link rel="stylesheet" href="bootstrap.css">

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
            <img src="https://png.icons8.com/java/ios7/32" width="32" height="32">
        </a>
        <a class="navbar-brand mb-0" href="#"><strong>Java</strong>Dev</a>

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
                    <a class="nav-link" href="/logout">Logout</a>
                </li>
            </ul>
        </div>

    </div>
</header><!-- HEADER: End -->

<article>
    <div class="container">
        <div class="row pt-5">
            <div class="col-12">
                <div class="text-center mt-5">
                    <h3 class="card-title">Edytowanie zajęć</h3>
                    <p class="card-text text-muted">Uzupewnij formularz aby zmienić opis zajęcia</p>
                </div>
            </div>
        </div>
        <!-- FORM: Begin -->
        <div class="row justify-content-md-center mt-5 pt-0">
            <div class="col ol-lg-6 col-md-12 col-sm-12">
                <div class="container">
                    <form action="/lessons/" method="post">
                        <input type="hidden" name="id" value="<%= lesson.getId()%>">
                        <div class="form-group row">
                            <label class="col-sm-2 col-form-label">Temat zajęc</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" name="theme" value="<%=lesson.getTheme()%>">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-sm-2 col-form-label">Lokalizacja</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" name="place" value="<%=lesson.getPlace()%>">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-sm-2 col-form-label">Prowadzący</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" name="teacher"
                                       value="<%=lesson.getTeacher()%>">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-sm-2 col-form-label">Data</label>
                            <div class="col-sm-8 col-md-5 col-lg-4">
                                <input type="datetime-local" value="<%=lesson.getDate() + "T" + lesson.getTime()%>" class="form-control"
                                       name="date">
                            </div>
                        </div>
                        <div class="form-group row">
                            <div class="col text-center">
                                <button type="submit" class="btn login-button btn-primary px-5">Wyslij</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <!-- FORM: End -->
    </div>
</article>
<!-- Footer: Begin -->

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
</body>

</html>
