<#include "base.admin.ftl"/>
<!DOCTYPE html>
<html>
<head>
<@head/>
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
    <!-- bootstrap wysihtml5 - text editor -->
    <link rel="stylesheet" href="/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css">

    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <link rel="stylesheet"
          href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
<@header/>
<@aside/>
    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                Редактирование новости
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
                <li><a href="#">News</a></li>
                <li class="active">Name</li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-md-12">
                    <!-- /.box-header -->
                    <div class="box-body pad">
                        <div id="feedback"></div>
                        <form id="news-form">
                            header
                            <input type="text" id="header">
                            preview
                            <textarea id="preview" rows="4" cols="80"></textarea>
                            content
                            <textarea id="content" rows="10" cols="80"></textarea>
                            <input class="btn btn-sm btn-primary" type="submit" value="Сохранить" id="submit">
                        </form>
                    </div>
                    <!-- /.box -->
                </div>
                <!-- /.col-->
            </div>
            <!-- ./row -->
        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->
<@footer/>
</div>
<!-- ./wrapper -->
<@scripts/>
<script>
    $(function () {
        CKEDITOR.replace('preview');
        CKEDITOR.replace('content');
    })
</script>
<script>
    $(document).ready(function () {
        $("#news-form").submit(function (event) {
            event.preventDefault();
            var news = {};
            news["header"] = $("#header").val();
            news["preview"] = $("#preview").val();
            news["content"] = $("#content").val();
            $("#submit").prop("disabled", true);

            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/rest/news/add",
                data: JSON.stringify(news),
                dataType: 'json',
                cache: false,
                timeout: 600000,
                success: function (data) {

                    var json = "<h4>Ajax Response</h4><pre>"
                            + JSON.stringify(data, null, 4) + "</pre>";
                    $('#feedback').html(json);

                    console.log("SUCCESS : ", data);
                    $("#submit").prop("disabled", false);
                },
                error: function (e) {
                    var json = "<h4>Ajax Response</h4><pre>"
                            + e.responseText + "</pre>";
                    $('#feedback').html(json);
                    console.log("ERROR : ", e);
                    $("#submit").prop("disabled", false);
                }
            });
        });
    });
</script>
</body>
</html>
