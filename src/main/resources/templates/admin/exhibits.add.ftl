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
                    <!-- /.box-header -->
                    <div class="box-body pad">
                        <div id="feedback"></div>
                        <form id="exhibit-form">
                            <input type="hidden" id="id" value="${model.id!""}">
                        <#if model.exposition??>
                            <input type="hidden" id="exp-id" value="${model.exposition.id!""}">
                        </#if>
                            <input class="form-control pull-right" placeholder="название" required pattern=".+"
                                   type="text" id="name" value="${model.name!""}">
                            <textarea required id="content" rows="10" cols="80">${model.content!"описание"}</textarea>

                            <select class="form-control pull-right" required id="exposition_id">
                            <#list expositions as e>
                                <#if e?counter == 1>
                                    <option selected value="${e.id}" id="${e.id}">${e.name}</option>
                                <#else>
                                    <option value="${e.id}" id="${e.id}">${e.name}</option>
                                </#if>
                            </#list>
                            </select>
                            <div class="text-center">
                                <input class="btn btn-primary btn-lg" type="submit" value="Сохранить" id="submit">
                            </div>
                        </form>

                    <#if model.id??>
                        <div class="feedback"></div>
                        <div id="uploaded">
                            <form action="" id="fileForm">
                                <button type="submit" class="btn btn-primary btn-lg">Удалить</button>
                            </form>
                            <ul>
                                <#list files as file>
                                    <li id="li-${file.id}"><input type="checkbox" name="checkboxes"
                                                                  id="file-${file.id}"><a
                                            href="${file.getFullPath()}">${file.name}</a></li>
                                </#list>
                            </ul>
                        </div>
                        <form id="form1" enctype="multipart/form-data" method="post" action="/upload">
                            <input placeholder="Enter file name" class="form-control pull-right" type="text"
                                   name="originName"
                                   id="originName">
                            <div class="btn btn-default btn-file">
                                <i class="fa fa-paperclip"></i> Прикрепить файл
                                <input type="file" name="fileToUpload" id="fileToUpload" onchange="fileSelected()"/>
                            </div>
                            <div id="fileName"></div>
                            <div id="fileSize"></div>
                            <div id="fileType"></div>
                            <input type="button" class="btn btn-priamary btn-file" onclick="uploadFile()"
                                   value="Upload"/>
                            <div class="progress">
                                <div class="progress-bar progress-bar-striped active" role="progressbar"
                                     aria-valuenow="40"
                                     aria-valuemin="0" aria-valuemax="100" style="width:0%" id="progressNumber">
                                </div>
                            </div>
                        </form>
                    </#if>
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
    if $("#exp-id").val() != null {
        document.getElementById('exposition_id').value = $("#exp-id").val();
    }
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
<script type="application/javascript">
    function fileSelected() {
        $('#progressNumber').css('width', '0%');
        var file = document.getElementById('fileToUpload').files[0];
        if (file) {
            var fileSize = 0;
            if (file.size > 1024 * 1024)
                fileSize = (Math.round(file.size * 100 / (1024 * 1024)) / 100).toString() + 'MB';
            else
                fileSize = (Math.round(file.size * 100 / 1024) / 100).toString() + 'KB';

            document.getElementById('fileName').innerHTML = 'Name: ' + file.name;
            document.getElementById('fileSize').innerHTML = 'Size: ' + fileSize;
            document.getElementById('fileType').innerHTML = 'Type: ' + file.type;
        }
    }

    function uploadFile() {
        var fd = new FormData();
        fd.append("name", $('#originName').val());
        fd.append("exhibitId", $('#id').val());
        fd.append("file", document.getElementById('fileToUpload').files[0]);
        var xhr = new XMLHttpRequest();
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        xhr.upload.addEventListener("progress", uploadProgress, false);
        xhr.addEventListener("error", uploadFailed, false);
        xhr.addEventListener("load", uploadComplete, false);
        xhr.addEventListener("abort", uploadCanceled, false);
        xhr.open("POST", "/upload");
        xhr.setRequestHeader(header, token);
        xhr.send(fd);
    }

    function uploadProgress(evt) {
        if (evt.lengthComputable) {
            var percentComplete = Math.round(evt.loaded * 100 / evt.total);
            $('#progressNumber').css('width', percentComplete.toString() + '%');
            document.getElementById('progressNumber').innerHTML = percentComplete.toString() + '%';
        }
    }

    function uploadComplete(evt) {
        var filename = $('#originName').val();
        $("#uploaded ul").append('<li><input type="checkbox"><a href="">' + filename + '</a></li>');
    }

    function uploadFailed(evt) {
        alert("There was an error attempting to upload the file.");
    }

    function uploadCanceled(evt) {
        alert("The upload has been canceled by the user or the browser dropped the connection.");
    }
</script>


<script>
    $(document).ready(function () {
        $("#fileForm").submit(function (event) {
            event.preventDefault();
            $('#feedback').html("");
            var checkboxes = document.getElementsByName('checkboxes');
            for (var i = 0; i < checkboxes.length; i++)
                if (checkboxes[i].checked) {
                    var id = checkboxes[i].id.substring(5);
                    var ajax_url = "/rest/files/" + id + "/delete";
                    $.ajax({
                        type: "POST",
                        contentType: "application/json",
                        url: ajax_url,
                        data: JSON.stringify({}),
                        dataType: 'json',
                        cache: false,
                        timeout: 600000,
                        success: function (data) {
                            var json = "<h4>Ajax Response</h4><pre>"
                                    + JSON.stringify(data, null, 4) + "</pre>";
                            $('#feedback').html(json);
                            console.log("SUCCESS : ", data);
                            $('#li-' + id).remove();
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
                }
            $("#submit").prop("disabled", true);
        });
    });
</script>
</body>
</html>
