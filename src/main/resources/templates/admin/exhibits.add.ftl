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
                <#--name="Collapse">-->
                <#--<i class="fa fa-minus"></i></button>-->
                <#--<button type="button" class="btn btn-info btn-sm" data-widget="remove" data-toggle="tooltip"-->
                <#--name="Remove">-->
                <#--<i class="fa fa-times"></i></button>-->
                <#--</div>-->
                    <!-- /. tools -->
                <#--</div>-->
                    <!-- /.box-header -->
                    <div class="box-body pad">
                        <div id="feedback"></div>
                        <form id="exhibit-form">
                            <input type="hidden" id="id" value="${model.id!""}">
                        <#if model.exposition??>
                            <input type="hidden" id="exp-id" value="${model.exposition.id!""}">
                        </#if>
                            <input required pattern=".+" type="text" id="name" value="${model.name!""}">
                            <textarea required id="content" rows="10" cols="80">${model.content!""}</textarea>

                            <select required id="exposition_id">
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
<!-- CK Editor -->
<script src="/bower_components/ckeditor/ckeditor.js"></script>
<!-- Bootstrap WYSIHTML5 -->
<script src="/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"></script>
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
            exhibit["content"] = CKEDITOR.instances['content'].getData();
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
