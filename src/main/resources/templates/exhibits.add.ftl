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
                Редактирование описания экспоната
            </h1>
            <ol class="breadcrumb">
                <li><a href="/admin"><i class="fa fa-dashboard"></i> Home</a></li>
                <li><a href="/admin/exhibits">Exhibits</a></li>
                <li class="active">${model.name!"New"}</li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-md-12">
                <#--<div class="box-header">-->
                    <!-- tools box -->
                <#--<div class="pull-right box-tools">-->
                <#--<button type="button" class="btn btn-info btn-sm" data-widget="collapse" data-toggle="tooltip"-->
                <#--title="Collapse">-->
                <#--<i class="fa fa-minus"></i></button>-->
                <#--<button type="button" class="btn btn-info btn-sm" data-widget="remove" data-toggle="tooltip"-->
                <#--title="Remove">-->
                <#--<i class="fa fa-times"></i></button>-->
                <#--</div>-->
                    <!-- /. tools -->
                <#--</div>-->
                    <!-- /.box-header -->
                    <div class="box-body pad">
                        <div id="feedback"></div>
                        <form id="exhibit-form">
                        <#if model??>
                            <input type="hidden" id="id" value="${model.id!""}">
                            <input type="hidden" id="exp-id" value="${model.exposition.id!""}">
                            <input type="text" id="name" value="${model.name!""}">
                            <textarea id="content" rows="10" cols="80">${model.content!""}</textarea>
                        </#if>
                            <select id="exposition_id">
                            <#list expositions as e>
                                <option value="${e.id}" id="${e.id}">${e.name}</option>
                            </#list>
                            </select>
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
    document.getElementById('exposition_id').value = $("#exp-id").val();
</script>
<script>
    $(function () {
        CKEDITOR.replace('content');
    })
</script>
<script>
    $(document).ready(function () {
        $("#exhibit-form").submit(function (event) {
            event.preventDefault();
            $('#feedback').html("");
            var exhibit = {};
            var id = $("#id").val();
            exhibit["id"] = id;
            exhibit["name"] = $("#name").val();
            exhibit["content"] = $("#content").val();
            var e = document.getElementById("exposition_id");
            exhibit["expositionId"] = e.options[e.selectedIndex].value;
            $("#submit").prop("disabled", true);
            var ajax_url = id == "" ? "/rest/exhibits/add" : "/rest/exhibits/" + id + "/edit";
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: ajax_url,
                data: JSON.stringify(exhibit),
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
