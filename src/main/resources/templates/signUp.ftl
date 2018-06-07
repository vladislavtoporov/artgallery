<!--view-source:https://getbootstrap.com/docs/4.0/examples/floating-labels/-->
<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap core CSS -->
    <link href="/css/bootstrap.min.css" type="text/css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="/css/floating-labels.css" type="text/css" rel="stylesheet">
<#--<link href="/css/style.css" type="text/css" rel="stylesheet">-->

</head>

<body>
<div class="container">
<#if error??>
    <div class="alert alert-danger" role="alert">${error}</div>
</#if>
    <h1 class="h3 mb-3 font-weight-normal">Пожалуйста зарегистрируйтесь</h1>
    <form action="/signUp" name="userForm" method="post" class="form-signin">
        <div class="form-label-group validation">
            <input type="email" name="login" id="regEmail" class="form-control"
                   placeholder="Email address" required autofocus value="${userForm.login!""}">
            <span class="validity"></span>
            <label for="regEmail">Email</label>
        </div>

        <div class="form-label-group validation">
            <input type="text" pattern="[A-Za-zА-Яа-ЯЁ]+" name="name" id="regName" class="form-control"
                   placeholder="Full name" required autofocus value="${userForm.name!""}">
            <span class="validity"></span>
            <label for="regName">ФИО</label>
        </div>

        <div class="form-label-group validation">
            <input type="password"
                   pattern="(?=^.{6,}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$"
                   name="password" id="regPassword" class="form-control" placeholder="Email address"
                   required autofocus value="${userForm.password!""}">
            <span class="validity"></span>
            <label for="regPassword">Пароль</label>
        </div>

        <div class="form-label-group validation">
            <input type="password"
                   pattern="(?=^.{6,}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$"
                   name="passwordRepeat" id="regPasswordRepeat" class="form-control"
                   placeholder="Email address" required autofocus value="${userForm.passwordRepeat!""}">
            <span class="validity"></span>
            <label for="regPasswordRepeat">Повторите Пароль</label>
        </div>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Зарегистрироваться</button>
    </form>
</div>

<!-- jQuery 3 -->
<script src="/bower_components/jquery/dist/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"
        integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb"
        crossorigin="anonymous"></script>
<script type="application/javascript" src="/js/bootstrap.min.js"></script>
</body>
</html>
