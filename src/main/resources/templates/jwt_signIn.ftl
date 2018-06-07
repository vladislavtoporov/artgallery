<!--view-source:https://getbootstrap.com/docs/4.0/examples/floating-labels/-->
<#import "/spring.ftl" as spring/>
<!DOCTYPE html>
<html lang="en">
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
    <form class="form-signin" method="post" action="/jwt/login">
    <#if error??>
        <div class="alert alert-warning alert-dismissible fade show" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
            ${error}
        </div>
    </#if>
        <div class="form-label-group validation">
            <input type="email" name="login" id="login" class="form-control" placeholder="Email address" required
                   autofocus>
            <span class="validity"></span>
            <label for="login">Email</label>
        </div>

        <div class="form-label-group validation">
            <input type="password" pattern="(?=^.{6,}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$"
                   name="password" id="password" class="form-control" placeholder="Password"
                   required>
            <span class="validity"></span>
            <label for="password">Пароль</label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Войти</button>
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
