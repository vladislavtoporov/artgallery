<!--view-source:https://getbootstrap.com/docs/4.0/examples/floating-labels/-->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Sign in</title>

    <!-- Bootstrap core CSS -->
    <link href="/css/bootstrap.min.css" type="text/css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="/css/floating-labels.css" type="text/css" rel="stylesheet">
    <link href="/css/style.css" type="text/css" rel="stylesheet">

</head>

<body>
<div class="container">
    <div class="text-center mb-4">
        <h1 class="h3 mb-3 font-weight-normal">Пожалуйста, авторизуйтесь</h1>
        <form action="/signin/facebook" method="POST">
            <input type="hidden" name="scope" value="public_profile"/>
            <button class="btn btn-sm btn-primary" type="submit">Login using Facebook</button>
        </form>
    </div>
    <form class="form-signin" method="post" action="/login">

        <div class="form-label-group validation">
            <input type="text" name="login" id="inputLogin" class="form-control" placeholder="Email address" required
                   autofocus>
            <span class="validity"></span>
            <label for="inputLogin">Email</label>
        </div>

        <div class="form-label-group validation">
            <input type="password" name="password" id="inputPassword" class="form-control" placeholder="Password"
                   required>
            <span class="validity"></span>
            <label for="inputPassword">Пароль</label>
        </div>
    <#--pattern="(?=^.{6,}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$"-->
        <div class="checkbox mb-3">
            <label>
                <input type="checkbox" name="remember-me-param" checked> Remember me
            </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Войти</button>
        <button class="btn btn-lg btn-primary btn-block" type="button" data-toggle="modal" data-target="#myModal">
            Зарегистрироваться
        </button>
    </form>

</div>
<!-- Modal -->

<div id="myModal" class="modal fade" tabindex="-1">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Регистрация</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="container">
                    <form action="/signUp" method="post" class="form-signin">

                        <div class="form-label-group validation">
                            <input type="email" name="login" id="regEmail" class="form-control"
                                   placeholder="Email address" required autofocus>
                            <span class="validity"></span>
                            <label for="regEmail">Email</label>
                        </div>

                        <div class="form-label-group validation">
                            <input type="text" pattern="[A-Za-zА-Яа-ЯЁ]+" name="name" id="regName" class="form-control"
                                   placeholder="Full name" required autofocus>
                            <span class="validity"></span>
                            <label for="regName">ФИО</label>
                        </div>

                        <div class="form-label-group validation">
                            <input type="password"
                                   pattern="(?=^.{6,}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$"
                                   name="password" id="regPassword" class="form-control" placeholder="Email address"
                                   required autofocus>
                            <span class="validity"></span>
                            <label for="regPassword">Пароль</label>
                        </div>

                        <div class="form-label-group validation">
                            <input type="password"
                                   pattern="(?=^.{6,}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$"
                                   name="passwordRepeat" id="regPasswordRepeat" class="form-control"
                                   placeholder="Email address" required autofocus>
                            <span class="validity"></span>
                            <label for="regPasswordRepeat">Повторите Пароль</label>
                        </div>

                        <!--<div class="alert alert-warning alert-dismissible fade show" role="alert">-->
                        <!--<button type="button" class="close" data-dismiss="alert" aria-label="Close">-->
                        <!--<span aria-hidden="true">&times;</span>-->
                        <!--</button>-->
                        <!--Такой email уже зарегистрирован <br/>-->
                        <!--Этот логин уже занят другим пользователем <br/>-->
                        <!--Пароль должен содержать строчные и заглавные латинские буквы, а также цифры <br/>-->
                        <!--Пароли не совпадают-->
                        <!--</div>-->

                        <button class="btn btn-lg btn-primary btn-block" type="submit">Зарегистрироваться</button>
                    </form>
                </div>
            </div>
            <div class="modal-footer">
                <button class="btn btn-primary" type="button" data-dismiss="modal">Закрыть</button>
            </div>
        </div>
    </div>
</div>


<script src="https://code.jquery.com/jquery-3.1.1.slim.min.js"
        integrity="sha384-A7FZj7v+d/sdmMqp/nOQwliLvUsJfDHW+k9Omg/a/EheAdgtzNs3hpfag6Ed950n"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"
        integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb"
        crossorigin="anonymous"></script>
<script type="application/javascript" src="/js/bootstrap.min.js"></script>

</body>
</html>
