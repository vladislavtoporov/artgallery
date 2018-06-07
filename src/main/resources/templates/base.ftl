<#macro head>
<#--${user.getFullAvatarPath()!'-->
    <#assign user_image="/img/portfolio_03.jpg">
<meta charset="UTF-8">
<meta name="_csrf" content="${_csrf.token}"/>
<meta name="_csrf_header" content="${_csrf.headerName}"/>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- Tell the browser to be responsive to screen width -->
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="/css/bootstrap.min.css">
<!-- Font Awesome -->
<link rel="stylesheet" href="/bower_components/font-awesome/css/font-awesome.min.css">
<!-- Ionicons -->
<link rel="stylesheet" href="/bower_components/Ionicons/css/ionicons.min.css">
<!-- Custom CSS -->
<link rel="stylesheet" href="/css/style.css">
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
                    <img src="/img/logo.png" width="30" height="30" class="d-inline-block align-top" alt="">
                    ARTGALLERY</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/expositions">Экспозиции</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/exhibits">Экспонаты</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/reservation">Забронировать</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/expositions/plan">Программа</a>
            </li>
        </ul>
        <ul class="navbar-nav">
            <#if user??>
                <li class="nav-item">
                    <a style="margin-top: -4px" class="nav-link"><img src="${user_image!''}" width="30" height="30"
                                                                      class="rounded-circle" alt=""></a>
                </li>
                <li class="nav-item">
                    <a tabindex="0" class="nav-link" data-toggle="tooltip" name="Войти в личный кабинет"
                       href="/admin">${user.name}</a>
                </li>
                <li class="nav-item">
                    <form id="logout-form" action="/logout" method="post">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <a class="nav-link" onclick="document.getElementById('logout-form').submit();">Выйти</a>
                    </form>
                </li>
            <#else>
                <li class="nav-item">
                    <a class="nav-link" href="/signIn">Войти</a>
                </li>
            </#if>
            <li class="nav-item">
                <form class="form-inline" method="post" action="/search/tag">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <div id="the-basics">
                        <input style="margin-top: -4px" class="form-control typeahead" name="query" type="text"
                               placeholder="Search">
                        <button style="margin-top: 4px" class="form-control btn btn-outline-success" type="submit">
                            Search
                        </button>
                    </div>
                </form>
            </li>
        </ul>
    </div>
</nav>
</#macro>

<#macro footer>
<footer id="footerwrap">
    <div class="container text-center">
        <div class="row">
            <div class="col-lg-4">
                <h4>Мы в социальных сетях</h4>
                <div class="hline-w"></div>
                <p>
                    <a href="https://vk.com/kazan_federal_university"><i class="fa fa-vk" aria-hidden="true"></i></a>
                    <a href="https://www.facebook.com/KazanUniversity/"><i class="fa fa-facebook"></i></a>
                    <a href="https://twitter.com/kazanuniversity"><i class="fa fa-twitter"></i></a>
                    <a href="https://www.instagram.com/kazanfederaluniversity/"><i class="fa fa-instagram"></i></a>
                    <a href="https://www.youtube.com/univertv"><i class="fa fa-youtube"></i></a>
                </p>
                <p>&copy; 2018</p>
            </div>

            <div class="col-lg-4">
                <h4>Наша команда</h4>
                <div class="hline-w"></div>
                <ul class="list-unstyled">
                    <li><a href="">About</a></li>
                    <li><a href="">Investors</a></li>
                    <li><a href="">Partners</a></li>
                </ul>
            </div>
            <div class="col-lg-4">
                <h4>Контакты и обратная связь</h4>
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
<script src="/js/typeahead.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"
        integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb"
        crossorigin="anonymous"></script>
<script src="/js/bootstrap.min.js"></script>

<script>
    var substringMatcher = function (strs) {
        return function findMatches(q, cb) {
            var matches, substringRegex;

            // an array that will be populated with substring matches
            matches = [];

            // regex used to determine if a string contains the substring `q`
            substrRegex = new RegExp(q, 'i');

            // iterate through the pool of strings and for any string that
            // contains the substring `q`, add it to the `matches` array
            $.each(strs, function (i, str) {
                if (substrRegex.test(str)) {
                    matches.push(str);
                }
            });

            cb(matches);
        };
    };

    var states = [];

    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/api/exhibits",
        data: {},
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {
            console.log("SUCCESS : ", data);
            for (var i = 0; i < data.length; i++) {
                states.push(data[i]["name"]);
            }
        },
        error: function (e) {
            console.log("ERROR : ", e);
        }
    });


    $('#the-basics .typeahead').typeahead({
                hint: true,
                highlight: true,
                minLength: 1
            },
            {
                name: 'states',
                source: substringMatcher(states)
            });
</script>


<script>
    (function () {
        var widget_id = 895967;
        _shcp = [{widget_id: widget_id}];
        var lang = (navigator.language || navigator.systemLanguage
                || navigator.userLanguage || "en")
                .substr(0, 2).toLowerCase();
        var url = "widget.siteheart.com/widget/sh/" + widget_id + "/" + lang + "/widget.js";
        var hcc = document.createElement("script");
        hcc.type = "text/javascript";
        hcc.async = true;
        hcc.src = ("https:" == document.location.protocol ? "https" : "http")
                + "://" + url;
        var s = document.getElementsByTagName("script")[0];
        s.parentNode.insertBefore(hcc, s.nextSibling);
    })();
</script>
<script>
    $(function () {
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        $(document).ajaxSend(function (e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });
    });
</script>
</#macro>
