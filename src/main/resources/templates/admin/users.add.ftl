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
                Редактирование статуса пользователя
            </h1>
            <ol class="breadcrumb">
                <li><a href="/admin"><i class="fa fa-dashboard"></i> Home</a></li>
                <li><a href="/admin/users">Users</a></li>
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
                        <form id="exhibit-form">
                            <input type="hidden" id="id" value="${model.id!""}">

                            <input contenteditable="false" type="text" id="name" value="${model.name!""}">
                            <input contenteditable="false" type="text" id="name" value="${model.login!""}">
                            <select required id="role">
                                <option value="USER" id="USER">USER</option>
                                <option value="ARTIST" id="ARTIST">ARTIST</option>
                                <option value="STAFF" id="STAFF">STAFF</option>
                                <option value="ADMIN" id="ADMIN">ADMIN</option>
                                <option value="SUPERVISER" id="SUPERVISER">SUPERVISER</option>
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
<script>
    $(document).ready(function () {
        var errorMessage = "Please match the requested format.";

        $(this).find("textarea").on("input change propertychange", function () {

            var pattern = $(this).attr("pattern");

            if (typeof pattern !== typeof undefined && pattern !== false) {
                var patternRegex = new RegExp("^" + pattern.replace(/^\^|\$$/g, '') + "$", "g");

                hasError = !$(this).val().match(patternRegex);

                if (typeof this.setCustomValidity === "function") {
                    this.setCustomValidity(hasError ? errorMessage : "");
                }
                else {
                    $(this).toggleClass("error", !!hasError);
                    $(this).toggleClass("ok", !hasError);

                    if (hasError) {
                        $(this).attr("name", errorMessage);
                    }
                    else {
                        $(this).removeAttr("name");
                    }
                }
            }

        });
    });
</script>
</body>
</html>
