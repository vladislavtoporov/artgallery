<#macro head>
<#--${user.getFullAvatarPath()!'-->
    <#assign user_image="/img/portfolio_01.jpg">
<meta charset="UTF-8">
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
                    <img src="" width="30" height="30" class="d-inline-block align-top" alt="">
                    ARTGALLERY</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Экспозиции</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Экспонаты</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Забронировать</a>
            </li>
        </ul>

        <ul class="navbar-nav">
            <#if user??>
                <li class="nav-item">
                    <a class="nav-link"><img src="${user_image!''}" width="30" height="30"
                                             class="rounded-circle" alt=""></a>
                </li>
                <li class="nav-item">
                    <a tabindex="0" class="nav-link" data-toggle="tooltip" name="Войти в личный кабинет"
                       href="/admin">${user.name}</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/logout">Выйти</a>
                </li>
            <#else>
                <li class="nav-item">
                    <a class="nav-link" href="/signIn">Войти</a>
                </li>
            </#if>
        </ul>
        <form class="form-inline my-2 my-lg-0">
            <div id="the-basics"></div>
            <input class="form-control mr-sm-2 typeahead" type="search" placeholder="Search" aria-label="Search">
            <button class="btn btn-outline-success my-2 my-sm-0" onclick="search()">Search</button>
        </form>
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
<script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"
        integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb"
        crossorigin="anonymous"></script>
<script src="/js/bootstrap.min.js"></script>
<script src="/js/typeahead.js"></script>
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

    var states = ['Alabama', 'Alaska', 'Arizona', 'Arkansas', 'California',
        'Colorado', 'Connecticut', 'Delaware', 'Florida', 'Georgia', 'Hawaii',
        'Idaho', 'Illinois', 'Indiana', 'Iowa', 'Kansas', 'Kentucky', 'Louisiana',
        'Maine', 'Maryland', 'Massachusetts', 'Michigan', 'Minnesota',
        'Mississippi', 'Missouri', 'Montana', 'Nebraska', 'Nevada', 'New Hampshire',
        'New Jersey', 'New Mexico', 'New York', 'North Carolina', 'North Dakota',
        'Ohio', 'Oklahoma', 'Oregon', 'Pennsylvania', 'Rhode Island',
        'South Carolina', 'South Dakota', 'Tennessee', 'Texas', 'Utah', 'Vermont',
        'Virginia', 'Washington', 'West Virginia', 'Wisconsin', 'Wyoming'
    ];

    $('#the-basics').find('.typeahead').typeahead({
                hint: true,
                highlight: true,
                minLength: 1
            },
            {
                name: 'states',
                source: substringMatcher(states)
            });
</script>
</#macro>
