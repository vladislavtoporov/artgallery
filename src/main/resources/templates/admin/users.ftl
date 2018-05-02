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
                Управление доступом пользователей
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
                <li><a href="#">Tables</a></li>
                <li class="active">Users</li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-xs-12">
                    <div class="box">
                        <div class="box-header form-inline">
                            <form method="post" id="delete-form" action="">
                                <button class="btn btn-primary" type="submit">Удалить</button>
                            </form>
                            <form method="post" id="save-form" action="">
                                <button class="btn btn-primary" type="submit" id="submit2">Сохранить</button>
                            </form>
                        </div>
                        <div id="feedback"></div>
                        <!-- /.box-header -->
                        <div class="box-body">
                            <table id="example1" class="table table-bordered table-striped">
                                <thead>
                                <tr>
                                    <th></th>
                                    <th>id</th>
                                    <th>email</th>
                                    <th>name</th>
                                    <th>role</th>
                                </tr>
                                </thead>
                                <tbody>
                                <#list model as user>
                                <#--<input type="hidden" id="user-role-${user.id}" value="${user.role}">-->
                                <tr id="row-${user.id}">
                                    <td><input type="checkbox" id="${user.id}" name="checkboxes"></td>
                                    <td onclick="document.location = '/admin/users/${user.id}/edit'">${user.id}</td>
                                    <td>${user.login}</td>
                                    <td>${user.name}</td>
                                    <td contenteditable="true" id="user-role-${user.id}">${user.role}</td>
                                <#--<td>-->
                                <#--<select name="roles" id="roles-${user.id}">-->
                                <#--<option value="USER">USER</option>-->
                                <#--<option value="ADMIN">ADMIN</option>-->
                                <#--<option value="SUPERVISER">SUPERVISER</option>-->
                                <#--</select>-->
                                <#--</td>-->
                                </tr>
                                </#list>
                                </tbody>
                                <tfoot>
                                <tr>
                                    <th></th>
                                    <th>id</th>
                                    <th>email</th>
                                    <th>name</th>
                                    <th>role</th>
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
<#--<script>-->
<#--for (var i = 0; i < document.getElementsByName('roles').length; i++) {-->
<#--document.getElementsByName('roles')[$("#user_id").val()] = $("#user-role-").val();-->
<#--}-->
<#--</script>-->

<script>
    $(document).ready(function () {
        $("#delete-form").submit(function (event) {
            event.preventDefault();
            $('#feedback').html("");
            var checkboxes = document.getElementsByName('checkboxes');
            for (var i = 0; i < checkboxes.length; i++)
                if (checkboxes[i].checked) {
                    var id = checkboxes[i].id;
                    var ajax_url = "/rest/users/" + id + "/delete";
                    $('#row-' + id).remove();
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


        $("#save-form").submit(function (event) {
            event.preventDefault();
            $('#feedback').html("");
            var checkboxes = document.getElementsByName('checkboxes');
            for (var i = 0; i < checkboxes.length; i++)
                if (checkboxes[i].checked) {
                    var ajax_url = "/rest/users/" + checkboxes[i].id + "/edit";
                    var user = {};
//                    var e = document.getElementById("roles-" + checkboxes[i].id);
////                    user["id"] = e.options[e.selectedIndex].value;
//                    user["role"] = e.options[e.selectedIndex].value;
                    user["role"] = $("#user-role-" + checkboxes[i].id).text();
                    alert($("#user-role-" + checkboxes[i].id).text());
                    $.ajax({
                        type: "POST",
                        contentType: "application/json",
                        url: ajax_url,
                        data: JSON.stringify(user),
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
                }
            $("#submit").prop("disabled", true);
        });
    });
</script>


</body>
</html>
