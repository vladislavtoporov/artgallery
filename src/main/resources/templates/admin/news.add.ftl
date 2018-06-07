<#include "base.admin.ftl"/>
<!DOCTYPE html>
<html>
<head>
<@head/>
    <!-- bootstrap wysihtml5 - text editor -->
    <link rel="stylesheet" href="/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css">
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
                <li class="active">${model.name!"New"}</li>
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
                            <input type="hidden" id="id" value="${model.id!""}">
                            <input placeholder="название" class="form-control pull-right" required type="text"
                                   id="header" value="${model.header!""}" pattern=".+">
                            <textarea required id="preview" rows="4" cols="80">${model.preview!"preview"}</textarea>
                            <textarea required id="content" rows="10" cols="80">${model.content!"content"}</textarea>
                            <div class="text-center">
                                <input class="btn btn-lg btn-primary" type="submit" value="Сохранить" id="submit">
                            </div>
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
<!-- CK Editor -->
<script src="/bower_components/ckeditor/ckeditor.js"></script>
<!-- Bootstrap WYSIHTML5 -->
<script src="/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"></script>
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
            var id = $("#id").val();
            news["id"] = id;
            news["header"] = $("#header").val();
            news["preview"] = CKEDITOR.instances['preview'].getData();
            news["content"] = CKEDITOR.instances['content'].getData();
            $("#submit").prop("disabled", true);
            var ajax_url = id == "" ? "/rest/news/add" : "/rest/news/" + id + "/edit";
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: ajax_url,
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
