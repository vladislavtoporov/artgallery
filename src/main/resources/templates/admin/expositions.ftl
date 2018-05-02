<#include "base.admin.ftl"/>
<!DOCTYPE html>
<html>
<head>
<@head/>
    <!-- DataTables -->
    <link rel="stylesheet" href="/bower_components/datatables.net-bs/css/dataTables.bootstrap.min.css">
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
                Таблица экспонатов
            </h1>
            <ol class="breadcrumb">
                <li><a href="/admin"><i class="fa fa-dashboard"></i> Home</a></li>
                <li><a href="/admin">Tables</a></li>
                <li class="active">Expositions</li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-xs-12">
                    <div class="box">
                        <div class="box-header">
                            <form method="post" id="delete-form" action="">
                                <a class="btn btn-primary" type="button" href="/admin/expositions/add">Добавить</a>
                                <button class="btn btn-primary" type="submit">Удалить</button>
                            </form>
                        </div>
                        <!-- /.box-header -->
                        <div class="box-body">
                            <table id="example1" class="table table-bordered table-striped">
                                <thead>
                                <tr>
                                    <th></th>
                                    <th>id</th>
                                    <th>name</th>
                                <#--<th>description</th>-->
                                    <th>start date</th>
                                    <th>finish date</th>
                                    <th>price</th>
                                    <th>owner</th>
                                </tr>
                                </thead>
                                <tbody>
                                <#list model as exposition>
                                <tr id="row-${exposition.id}">
                                    <td><input type="checkbox" id="${exposition.id}" name="checkboxes"></td>
                                    <td onclick="document.location = '/admin/expositions/${exposition.id}/edit'">${exposition.id}</td>
                                    <td>${exposition.name}</td>
                                <#--<td>-->
                                <#--<#if exposition.description?length &lt; 51>-->
                                <#--${exposition.description}-->
                                <#--<#else>-->
                                <#--${exposition.description?substring(0,50)} ...-->
                                <#--</#if>-->
                                <#--</td>-->
                                    <td>${exposition.start}</td>
                                    <td>${exposition.finish}</td>
                                    <td>${exposition.price}</td>
                                    <td>${exposition.owner.name}</td>
                                </tr>
                                </#list>
                                </tbody>
                                <tfoot>
                                <tr>
                                    <th></th>
                                    <th>id</th>
                                    <th>name</th>
                                <#--<th>description</th>-->
                                    <th>start date</th>
                                    <th>finish date</th>
                                    <th>price</th>
                                    <th>owner</th>
                                </tr>
                                </tfoot>
                            </table>
                        </div>
                        <!-- /.box-body -->
                    </div>
                    <!-- /.box -->
                </div>
                <!-- /.col -->
            </div>
            <!-- /.row -->
        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->
<@footer/>
</div>
<!-- ./wrapper -->

<@scripts/>
<!-- DataTables -->
<script src="/bower_components/datatables.net/js/jquery.dataTables.min.js"></script>
<script src="/bower_components/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
<!-- SlimScroll -->
<script src="/bower_components/jquery-slimscroll/jquery.slimscroll.min.js"></script>
<!-- page script -->
<script>
    $(function () {
        $('#example1').DataTable();
    })
</script>
<script>
    $(document).ready(function () {
        $("#delete-form").submit(function (event) {
            event.preventDefault();
            $('#feedback').html("");
            var checkboxes = document.getElementsByName('checkboxes');
            for (var i = 0; i < checkboxes.length; i++)
                if (checkboxes[i].checked) {
                    var id = checkboxes[i].id;
                    var ajax_url = "/rest/expositions/" + id + "/delete";
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
                            $('#row-' + id).remove();
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
