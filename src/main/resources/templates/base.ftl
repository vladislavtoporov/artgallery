<#macro head>
    <#assign user_image="https://pp.userapi.com/c836530/v836530244/61eab/ob94LhFXq8k.jpg">
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- Tell the browser to be responsive to screen width -->
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
<name>ARTGALLERY</name
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="/css/bootstrap.min.css">
<!-- Font Awesome -->
<link rel="stylesheet" href="/bower_components/font-awesome/css/font-awesome.min.css">
<!-- Ionicons -->
<link rel="stylesheet" href="/bower_components/Ionicons/css/ionicons.min.css">
<!-- Google Font -->
<link rel="stylesheet"
      href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">

</#macro>

<#macro header>

</#macro>

<#macro nav>
<nav class="navbar sticky-top navbar-toggleable-md navbar-light bg-faded" id="navbar1">
    <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse"
            data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false"
            aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li>
                <a class="navbar-brand" href="/">
                    <img src="${user_image!''}" width="30" height="30" class="d-inline-block align-top" alt="">
                    Profi</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/">Главная</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Экспозиции</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Экспонаты</a>
            </li>
        </ul>

        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link"><img src="/img/logo.png" width="30" height="30"
                                         class="rounded-circle" alt=""></a>
            </li>
            <li class="nav-item">
                <a tabindex="0" class="nav-link" data-toggle="tooltip" name="Войти в личный кабинет"
                   href="account.html">Иван</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="auth.html">Войти</a>
            </li>
            <li class="nav-item">
                <form method="post">
                    <a class="nav-link" role="button" name="logout">Выйти</a>
                </form>
            </li>
        </ul>

    </div>
</nav>
</#macro>

<#macro footer>
<footer id="footerwrap">
    <div class="container">
        <div class="row">
            <div class="col-lg-4">
                <h4>Links</h4>
                <div class="hline-w"></div>
                <p>
                    <a href="https://vk.com/kazan_federal_university"><i class="fa fa-vk" aria-hidden="true"></i></a>
                    <a href="https://www.facebook.com/KazanUniversity/"><i class="fa fa-facebook"></i></a>
                    <a href="https://twitter.com/kazanuniversity"><i class="fa fa-twitter"></i></a>
                    <a href="https://www.instagram.com/kazanfederaluniversity/"><i class="fa fa-instagram"></i></a>
                    <a href="https://www.youtube.com/univertv"><i class="fa fa-youtube"></i></a>
                </p>
                <p>&copy; 2017</p>
            </div>

            <div class="col-lg-2">
                <h4>Our product</h4>
                <div class="hline-w"></div>
                <ul class="list-unstyled">
                    <li><a href="">Features</a></li>
                    <li><a href="">Plans</a></li>
                </ul>
            </div>
            <div class="col-lg-2">
                <h4>Our team</h4>
                <div class="hline-w"></div>
                <ul class="list-unstyled">
                    <li><a href="">About</a></li>
                    <li><a href="">Investors</a></li>
                    <li><a href="">Partners</a></li>
                </ul>
            </div>
            <div class="col-lg-2">
                <h4>Other things</h4>
                <div class="hline-w"></div>
                <ul class="list-unstyled">
                    <li><a href="">Contact</a></li>
                    <li><a href="">FAQ</a></li>
                </ul>
            </div>
        </div>
        <! --/row -->
    </div>
    <! --/container -->
</footer>
<! --/footerwrap -->
</#macro>

<#macro scripts>
<!-- jQuery 3 -->
<script src="/bower_components/jquery/dist/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"
        integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb"
        crossorigin="anonymous"></script>
<script src="/js/bootstrap.min.js"></script>
</#macro>
