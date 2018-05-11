<#include "base.admin.ftl"/>
<!DOCTYPE html>
<html>
<head>
<@head/>
    <!-- daterange picker -->
    <link rel="stylesheet" href="/bower_components/bootstrap-daterangepicker/daterangepicker.css">
    <!-- bootstrap datepicker -->
    <link rel="stylesheet" href="/bower_components/bootstrap-datepicker/dist/css/bootstrap-datepicker.min.css">
    <!-- Bootstrap time Picker -->
    <link rel="stylesheet" href="/plugins/timepicker/bootstrap-timepicker.min.css">
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
                Редактирование описания экскурсии
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
                <li><a href="#">Expositions</a></li>
                <li class="active">${model.name!"New"}</li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-md-12">
                    <div class="box-body pad">
                        <div id="feedback"></div>
                        <form id="exposition-form">
                            <input type="hidden" id="id" value="${model.id!""}">
                            <input placeholder="название" required pattern=".+" class="form-control pull-right"
                                   type="text" id="name" value="${model.name!""}">
                            <input type="text" class="form-control pull-right" id="reservation"
                                   value="${model.getRange()}">
                            <textarea required id="description" rows="10"
                                      cols="80">${model.description!"описание экспозиции"}</textarea>
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
<!-- date-range-picker -->
<script src="/bower_components/moment/min/moment.min.js"></script>
<script src="/bower_components/bootstrap-daterangepicker/daterangepicker.js"></script>
<!-- bootstrap datepicker -->
<script src="/bower_components/bootstrap-datepicker/dist/js/bootstrap-datepicker.min.js"></script>
<!-- bootstrap time picker -->
<script src="/plugins/timepicker/bootstrap-timepicker.min.js"></script>
<script>
    $(function () {
        CKEDITOR.replace('description');
    })
</script>
<script>
    //Date range picker
    $('#reservation').daterangepicker({locale: {format: 'YYYY.MM.DD'}});
    //Date picker
    $('#datepicker').datepicker({
        autoclose: true
    })
</script>
<script>
    $(document).ready(function () {
        $("#exposition-form").submit(function (event) {
            event.preventDefault();
            $('#feedback').html("");
            var exposition = {};
            var id = $("#id").val();
            exposition["id"] = id;
            exposition["name"] = $("#name").val();
            var date = $("#reservation").val().split(" - ");
            exposition["start"] = date[0];
            exposition["finish"] = date[1];
            alert(exposition["start"]);
            alert(exposition["finish"]);
            exposition["description"] = CKEDITOR.instances['description'].getData();
            $("#submit").prop("disabled", true);
            var ajax_url = id == "" ? "/rest/expositions/add" : "/rest/expositions/" + id + "/edit";
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: ajax_url,
                data: JSON.stringify(exposition),
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
