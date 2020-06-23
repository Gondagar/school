<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<% String error = (String) session.getAttribute("error");
    String errorClass;
    if ("error".equals(error)) {
        errorClass = "has-danger";
    } else {
        errorClass = "";
    }

%>


<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Logo page</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css">
    <link rel="stylesheet " href="/resourse/css/style.css">
    <link rel="stylesheet" href="/resourse/css/style.css">

</head>

<body>


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
                    <a class="nav-link" href="/auth/registry">Sing in <span class="sr-only">(current)</span></a>
                </li>

                <li class="nav-item active">
                    <a class="nav-link" href="/auth/login">Log in <span class="sr-only">(current)</span></a>
                </li>

                <li class="nav-item active">
                    <a class="nav-link" href="/">Home <span class="sr-only">(current)</span></a>
                </li>
            </ul>

        </div>
    </div>
</header>

<article>
    <div class="container">
        <div class="row justify-content-md-center">
            <div class="col-12 col-sm-10 col-md-8 col-lg-6 ">
                <div class="card mx-auto px-5 py-4 mt-5 ">
                    <div class="card-block login-block">
                        <h4 class="card-title">Rejestracja</h4>
                        <p class="card-text">Wprowadź swoje dane poniżej</p>
                        <img src="https://i.stack.imgur.com/I6K3E.png" class="logo">

                        <form action="/auth/registry" method="post">
                            <div class="form-group">
                                <label for="recipient-name" class="form-control-label">Imie:</label>
                                <input type="text" class="form-control" id="additionName" name="name">
                            </div>
                            <div class="form-group">
                                <label for="recipient-name" class="form-control-label">Nazwisko:</label>
                                <input type="text" class="form-control" id="additionSurname" name="surname">
                            </div>
                            <div class="form-group <%=errorClass %>">
                                <label for="message-text" class="form-control-label">Email:</label>
                                <input type="text" class="form-control" id="additionKontakt" name="email">
                            </div>
                            <div class="form-group has-danger">
                                <label for="message-text" class="form-control-label">Hasło:</label>
                                <input type="password" class="form-control" id="additionPass" name="password"
                                       onkeyup="checkPass()">
                            </div>
                            <div class="form-group has-danger">
                                <label for="message-text" class="form-control-label">Powtóż hasło:</label>
                                <input type="password" class="form-control" id="additionPassRepeat"
                                       name="passRepeat" onkeyup="checkPass()">
                            </div>

                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Anuluj
                                </button>
                                <button id="additionSubmit" type="submit" class="btn btn-success"
                                        disabled="false"> Wyslij
                                </button>
                            </div>
                            <script>
                                function checkPass() {
                                    var pass = $("#additionPass");
                                    var passPepeat = $("#additionPassRepeat");

                                    if (pass.val() == passPepeat.val()) {
                                        $("#additionSubmit").removeAttr("disabled");
                                        pass.parent().removeClass("has-danger");
                                        passPepeat.parent().removeClass("has-danger");
                                    } else {
                                        $("#additionSubmit").attr("disabled", "true");
                                        pass.parent().addClass("has-danger");
                                        passPepeat.parent().addClass("has-danger");
                                    }
                                }
                            </script>
                        </form>

                    </div>
                </div>
            </div>
        </div>
    </div>
</article>

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
<script src="https://code.jquery.com/jquery-3.1.1.slim.min.js"
        integrity="sha384-A7FZj7v+d/sdmMqp/nOQwliLvUsJfDHW+k9Omg/a/EheAdgtzNs3hpfag6Ed950n"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"
        integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb"
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"
        integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn"
        crossorigin="anonymous"></script>
<!-- SCRIPTS: End -->
</body>

</html>