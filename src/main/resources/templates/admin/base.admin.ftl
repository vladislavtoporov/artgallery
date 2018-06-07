<#macro head>
    <#assign user_image="/img/portfolio_03.jpg">
<meta charset="UTF-8">
<meta name="_csrf" content="${_csrf.token}"/>
<meta name="_csrf_header" content="${_csrf.headerName}"/>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- Tell the browser to be responsive to screen width -->
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">

<!-- Bootstrap 3.3.7 -->
<link rel="stylesheet" href="/bower_components/bootstrap/dist/css/bootstrap.min.css">
<!-- Font Awesome -->
<link rel="stylesheet" href="/bower_components/font-awesome/css/font-awesome.min.css">
<!-- Ionicons -->
<link rel="stylesheet" href="/bower_components/Ionicons/css/ionicons.min.css">
<!-- Theme style -->
<link rel="stylesheet" href="/dist/css/AdminLTE.min.css">
<!-- AdminLTE Skins. Choose a skin from the css/skins
     folder instead of downloading all of them to reduce the load. -->
<link rel="stylesheet" href="/dist/css/skins/_all-skins.min.css">

<script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>

<!-- Google Font -->
<link rel="stylesheet"
      href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">

</#macro>

<#macro header>
<header class="main-header">
    <!-- Logo -->
    <a href="/" class="logo">
        <!-- mini logo for sidebar mini 50x50 pixels -->
        <span class="logo-mini"><b>AG</b></span>
        <!-- logo for regular state and mobile devices -->
        <span class="logo-lg"><b>ARTGALLERY</b></span>
        ADMIN
    </a>
    <!-- Header Navbar: style can be found in header.less -->
    <nav class="navbar navbar-static-top">
        <!-- Sidebar toggle button-->
        <a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </a>

        <div class="navbar-custom-menu">
            <ul class="nav navbar-nav">
                <!-- Messages: style can be found in dropdown.less-->
                <li class="dropdown messages-menu">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <i class="fa fa-envelope-o"></i>
                        <#if messages??>
                        <span class="label label-success">${messages?size}</span>
                        </#if>
                    </a>
                    <ul class="dropdown-menu">
                        <#if messages??>
                        <li class="header">You have ${messages?size} messages</li>
                        </#if>
                        <li>
                            <!-- inner menu: contains the actual data -->
                            <ul class="menu">
                                <#if messages??>
                                    <#list messages as mes>
                                         <li><!-- start message -->
                                             <a href="/admin/readMail/${mes.id}">
                                                 <div class="pull-left">
                                                     <img src="${user_image}" class="img-circle"
                                                          alt="User Image">
                                                 </div>
                                                 <h4>
                                                     ${mes.header}
                                                     <small><i class="fa fa-clock-o">${mes.ts}</i></small>
                                                 </h4>
                                             </a>
                                         </li>
                                    </#list>
                                </#if>

                            <#--<!-- end message &ndash;&gt;-->
                            <#--<li>-->
                            <#--<a href="#">-->
                            <#--<div class="pull-left">-->
                            <#--<img src="${user_image}" class="img-circle"-->
                            <#--alt="User Image">-->
                            <#--</div>-->
                            <#--<h4>-->
                            <#--AdminLTE Design Team-->
                            <#--<small><i class="fa fa-clock-o"></i> 2 hours</small>-->
                            <#--</h4>-->
                            <#--<p>Why not buy a new awesome theme?</p>-->
                            <#--</a>-->
                            <#--</li>-->
                            <#--<li>-->
                            <#--<a href="#">-->
                            <#--<div class="pull-left">-->
                            <#--<img src="${user_image}" class="img-circle"-->
                            <#--alt="User Image">-->
                            <#--</div>-->
                            <#--<h4>-->
                            <#--Developers-->
                            <#--<small><i class="fa fa-clock-o"></i> Today</small>-->
                            <#--</h4>-->
                            <#--<p>Why not buy a new awesome theme?</p>-->
                            <#--</a>-->
                            <#--</li>-->
                            <#--<li>-->
                            <#--<a href="#">-->
                            <#--<div class="pull-left">-->
                            <#--<img src="${user_image}" class="img-circle"-->
                            <#--alt="User Image">-->
                            <#--</div>-->
                            <#--<h4>-->
                            <#--Sales Department-->
                            <#--<small><i class="fa fa-clock-o"></i> Yesterday</small>-->
                            <#--</h4>-->
                            <#--<p>Why not buy a new awesome theme?</p>-->
                            <#--</a>-->
                            <#--</li>-->
                            <#--<li>-->
                            <#--<a href="#">-->
                            <#--<div class="pull-left">-->
                            <#--<img src="${user_image}" class="img-circle"-->
                            <#--alt="User Image">-->
                            <#--</div>-->
                            <#--<h4>-->
                            <#--Reviewers-->
                            <#--<small><i class="fa fa-clock-o"></i> 2 days</small>-->
                            <#--</h4>-->
                            <#--<p>Why not buy a new awesome theme?</p>-->
                            <#--</a>-->
                            <#--</li>-->
                            </ul>
                        </li>
                        <li class="footer"><a href="/admin/mailbox/new">See All Messages</a></li>
                    </ul>
                </li>
                <!-- Notifications: style can be found in dropdown.less -->
                <li class="dropdown notifications-menu">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <i class="fa fa-bell-o"></i>
                        <span class="label label-warning">0</span>
                    </a>
                    <ul class="dropdown-menu">
                        <li class="header">You have 0 tickets</li>
                    <#--<li>-->
                    <#--<!-- inner menu: contains the actual data &ndash;&gt;-->
                    <#--<ul class="menu">-->
                    <#--<li>-->
                    <#--<a href="#">-->
                    <#--<i class="fa fa-users text-aqua"></i> 5 new members joined today-->
                    <#--</a>-->
                    <#--</li>-->
                    <#--<li>-->
                    <#--<a href="#">-->
                    <#--<i class="fa fa-warning text-yellow"></i> Very long description here that-->
                    <#--may not fit into the-->
                    <#--page and may cause design problems-->
                    <#--</a>-->
                    <#--</li>-->
                    <#--<li>-->
                    <#--<a href="#">-->
                    <#--<i class="fa fa-users text-red"></i> 5 new members joined-->
                    <#--</a>-->
                    <#--</li>-->
                    <#--<li>-->
                    <#--<a href="#">-->
                    <#--<i class="fa fa-shopping-cart text-green"></i> 35 sales made-->
                    <#--</a>-->
                    <#--</li>-->
                    <#--<li>-->
                    <#--<a href="#">-->
                    <#--<i class="fa fa-user text-red"></i> You changed your username-->
                    <#--</a>-->
                    <#--</li>-->
                    <#--</ul>-->
                    <#--</li>-->
                        <li class="footer"><a href="#">View all</a></li>
                    </ul>
                </li>
                <!-- Tasks: style can be found in dropdown.less -->
                <li class="dropdown tasks-menu">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <i class="fa fa-flag-o"></i>
                        <span class="label label-danger">0</span>
                    </a>
                    <ul class="dropdown-menu">
                        <li class="header">You have 0 tasks</li>
                    <#--<li>-->
                    <#--<!-- inner menu: contains the actual data &ndash;&gt;-->
                    <#--<ul class="menu">-->
                    <#--<li><!-- Task item &ndash;&gt;-->
                    <#--<a href="#">-->
                    <#--<h3>-->
                    <#--Design some buttons-->
                    <#--<small class="pull-right">20%</small>-->
                    <#--</h3>-->
                    <#--<div class="progress xs">-->
                    <#--<div class="progress-bar progress-bar-aqua" style="width: 20%"-->
                    <#--role="progressbar"-->
                    <#--aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">-->
                    <#--<span class="sr-only">20% Complete</span>-->
                    <#--</div>-->
                    <#--</div>-->
                    <#--</a>-->
                    <#--</li>-->
                    <#--<!-- end task item &ndash;&gt;-->
                    <#--<li><!-- Task item &ndash;&gt;-->
                    <#--<a href="#">-->
                    <#--<h3>-->
                    <#--Create a nice theme-->
                    <#--<small class="pull-right">40%</small>-->
                    <#--</h3>-->
                    <#--<div class="progress xs">-->
                    <#--<div class="progress-bar progress-bar-green" style="width: 40%"-->
                    <#--role="progressbar"-->
                    <#--aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">-->
                    <#--<span class="sr-only">40% Complete</span>-->
                    <#--</div>-->
                    <#--</div>-->
                    <#--</a>-->
                    <#--</li>-->
                    <#--<!-- end task item &ndash;&gt;-->
                    <#--<li><!-- Task item &ndash;&gt;-->
                    <#--<a href="#">-->
                    <#--<h3>-->
                    <#--Some task I need to do-->
                    <#--<small class="pull-right">60%</small>-->
                    <#--</h3>-->
                    <#--<div class="progress xs">-->
                    <#--<div class="progress-bar progress-bar-red" style="width: 60%"-->
                    <#--role="progressbar"-->
                    <#--aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">-->
                    <#--<span class="sr-only">60% Complete</span>-->
                    <#--</div>-->
                    <#--</div>-->
                    <#--</a>-->
                    <#--</li>-->
                    <#--<!-- end task item &ndash;&gt;-->
                    <#--<li><!-- Task item &ndash;&gt;-->
                    <#--<a href="#">-->
                    <#--<h3>-->
                    <#--Make beautiful transitions-->
                    <#--<small class="pull-right">80%</small>-->
                    <#--</h3>-->
                    <#--<div class="progress xs">-->
                    <#--<div class="progress-bar progress-bar-yellow" style="width: 80%"-->
                    <#--role="progressbar"-->
                    <#--aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">-->
                    <#--<span class="sr-only">80% Complete</span>-->
                    <#--</div>-->
                    <#--</div>-->
                    <#--</a>-->
                    <#--</li>-->
                    <#--<!-- end task item &ndash;&gt;-->
                    <#--</ul>-->
                    <#--</li>-->
                        <li class="footer">
                            <a href="#">View all tasks</a>
                        </li>
                    </ul>
                </li>
                <!-- User Account: style can be found in dropdown.less -->
                <li class="dropdown user user-menu">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <img src="${user_image}" width="20px" height="20px"
                             class="img-circle" alt="User Image">
                        <span class="hidden-xs">${user.name}</span>
                    </a>
                    <ul class="dropdown-menu">
                        <!-- User file -->
                        <li class="user-header">
                            <img src="${user_image}"
                                 class="img-circle" alt="User Image">
                            <p>
                                ${user.name}
                                <small>Motto</small>
                            </p>
                        </li>
                        <!-- Menu Body -->
                        <li class="user-body">
                            <div class="row">
                                <div class="col-xs-4 text-center">
                                    <a href="#">Followers</a> <br>0
                                </div>
                                <div class="col-xs-4 text-center">
                                    <a href="#">Sales</a> <br>0
                                </div>
                                <div class="col-xs-4 text-center">
                                    <a href="#">Friends</a> <br>0
                                </div>
                            </div>
                            <!-- /.row -->
                        </li>
                        <!-- Menu Footer-->
                        <li class="user-footer">
                            <div class="pull-left">
                                <a href="/profiles/${user.id}" class="btn btn-default btn-flat">Profile</a>
                            </div>
                            <div class="pull-right">
                                <form id="logout-form" action="/logout" method="post">
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                    <a class="nav-link"
                                       onclick="document.getElementById('logout-form').submit();">Выйти</a>
                                </form>
                            </div>
                        </li>
                    </ul>
                </li>
                <!-- Control Sidebar Toggle Button -->
                <li>
                    <a href="#" data-toggle="control-sidebar"><i class="fa fa-gears"></i></a>
                </li>
            </ul>
        </div>
    </nav>
</header>
</#macro>


<#macro aside>
<!-- Left side column. contains the logo and sidebar -->
<aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
        <!-- Sidebar user panel -->
        <div class="user-panel" style="margin-bottom: 20px">
            <div class="pull-left file">
                <img src="${user_image}" width="50px" height="50px" class="img-circle"
                     alt="User Image">
            </div>
            <div class="pull-left info">
                <p>${user.name}</p>
                <p><i class="fa fa-circle text-success"></i> ${user.role}</p>
            </div>
        </div>
        <br>
        <!-- search form -->
        <form action="#" method="get" class="sidebar-form">
            <div class="input-group">
                <input type="text" name="q" class="form-control" placeholder="Search...">
                <span class="input-group-btn">
                <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i>
                </button>
              </span>
            </div>
        </form>
        <!-- /.search form -->
        <!-- sidebar menu: : style can be found in sidebar.less -->
        <ul class="sidebar-menu" data-widget="tree">
            <li class="header">MAIN NAVIGATION</li>

            <li><a href="/admin/expositions"><i class="fa fa-circle-o text-red"></i> <span>Expositions</span></a></li>
            <li><a href="/admin/exhibits"><i class="fa fa-circle-o text-yellow"></i> <span>Exhibits</span></a></li>
            <li><a href="/admin/news"><i class="fa fa-circle-o text-aqua"></i> <span>News</span></a></li>
            <li><a href="/admin/users"><i class="fa fa-circle-o text-purple"></i> <span>Users</span></a></li>
            <li><a href="/admin/mailbox"><i class="fa fa-circle-o text-lime"></i> <span>Mailbox</span></a></li>
            <li><a href="/admin/ticketbox/new"><i class="fa fa-circle-o text-lime"></i> <span>Tickets</span></a></li>

        </ul>
    </section>
    <!-- /.sidebar -->
</aside>
</#macro>

<#macro footer>
<footer class="main-footer">
    <div class="pull-right hidden-xs">
    </div>
    <strong>Copyright &copy; 2018 <a href="https://adminlte.io">ARTGALLERY</a>.</strong> All rights
    reserved.
</footer>
<!-- /.control-sidebar -->
<!-- Add the sidebar's background. This div must be placed
     immediately after the control sidebar -->
<div class="control-sidebar-bg"></div>
</#macro>

<#macro scripts>
<!-- jQuery 3 -->
<script src="/bower_components/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- FastClick -->
<script src="/bower_components/fastclick/lib/fastclick.js"></script>
<!-- AdminLTE App -->
<script src="/dist/js/adminlte.min.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="/dist/js/demo.js"></script>
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
