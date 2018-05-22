<#include "base.admin.ftl"/>

<!DOCTYPE html>
<html>
<head>
<@head/>
    <!-- iCheck -->
    <link rel="stylesheet" href="/plugins/iCheck/flat/blue.css">
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
                Mailbox
                <small>no new messages</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
                <li class="active">Mailbox</li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-md-3">
                    <a href="/admin/mailbox" class="btn btn-primary btn-block margin-bottom">Back to Inbox</a>

                    <div class="box box-solid">
                        <div class="box-header with-border">
                            <h3 class="box-title">Folders</h3>

                            <div class="box-tools">
                                <button type="button" class="btn btn-box-tool" data-widget="collapse"><i
                                        class="fa fa-minus"></i>
                                </button>
                            </div>
                        </div>
                        <div class="box-body no-padding">
                            <ul class="nav nav-pills nav-stacked">
                                <li><a href="/admin/mailbox"><i class="fa fa-inbox"></i> Inbox
                                    <span class="label label-primary pull-right">0</span></a></li>
                                <li><a href="#"><i class="fa fa-envelope-o"></i> Sent</a></li>
                                <li><a href="#"><i class="fa fa-file-text-o"></i> Drafts</a></li>
                                <li><a href="#"><i class="fa fa-filter"></i> Junk <span
                                        class="label label-warning pull-right">65</span></a>
                                </li>
                                <li><a href="#"><i class="fa fa-trash-o"></i> Trash</a></li>
                            </ul>
                        </div>
                        <!-- /.box-body -->
                    </div>
                    <!-- /. box -->
                    <div class="box box-solid">
                        <div class="box-header with-border">
                            <h3 class="box-title">Labels</h3>

                            <div class="box-tools">
                                <button type="button" class="btn btn-box-tool" data-widget="collapse"><i
                                        class="fa fa-minus"></i>
                                </button>
                            </div>
                        </div>
                        <!-- /.box-header -->
                    <#--<div class="box-body no-padding">-->
                    <#--<ul class="nav nav-pills nav-stacked">-->
                    <#--<li><a href="#"><i class="fa fa-circle-o text-red"></i> Important</a></li>-->
                    <#--<li><a href="#"><i class="fa fa-circle-o text-yellow"></i> Promotions</a></li>-->
                    <#--<li><a href="#"><i class="fa fa-circle-o text-light-blue"></i> Social</a></li>-->
                    <#--</ul>-->
                    <#--</div>-->
                        <!-- /.box-body -->
                    </div>
                    <!-- /.box -->
                </div>
                <!-- /.col -->
                <div id="feedback"></div>
                <form action="" id="message-form">
                    <div class="col-md-9">
                    <div class="box box-primary">
                        <div class="box-header with-border">
                            <h3 class="box-title">Compose New Message</h3>
                        </div>
                        <!-- /.box-header -->
                        <div class="box-body">
                            <div class="form-group">
                                <input autocomplete="true" class="form-control" placeholder="To:" id="recipient">
                            </div>
                            <div class="form-group">
                                <input class="form-control" placeholder="Subject:" id="subject">
                            </div>
                            <div class="form-group">
                                <textarea id="compose-textarea" class="form-control" style="height: 300px"></textarea>
                            </div>

                        <#--<div class="form-group">-->
                        <#--<div class="btn btn-default btn-file">-->
                        <#--<i class="fa fa-paperclip"></i> Attachment-->
                        <#--<input type="file" name="attachment">-->
                        <#--</div>-->
                        <#--<p class="help-block">Max. 32MB</p>-->
                        <#--</div>-->

                        </div>
                        <!-- /.box-body -->
                        <div class="box-footer">
                            <div class="pull-right">
                                <button id="submit" type="submit" class="btn btn-primary"><i
                                        class="fa fa-envelope-o"></i> Send
                                </button>
                            </div>
                        </div>
                        <!-- /.box-footer -->
                    </div>
                    <!-- /. box -->
                </div>
                </form>
                <!-- /.col -->
            </div>
            <!-- /.row -->
        </section>
        <!-- /.content -->
    </div>
<@footer/>
    <div class="control-sidebar-bg"></div>
</div>
<!-- ./wrapper -->

<@scripts/>
<!-- Slimscroll -->
<script src="/bower_components/jquery-slimscroll/jquery.slimscroll.min.js"></script>
<!-- iCheck -->
<script src="/plugins/iCheck/icheck.min.js"></script>
<!-- Bootstrap WYSIHTML5 -->
<script src="/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"></script>
<!-- Page Script -->
<script>
    $(function () {
        //Add text editor
        $("#compose-textarea").wysihtml5();
    });
</script>
<script>
    $(document).ready(function () {
        $("#message-form").submit(function (event) {
            event.preventDefault();
            $('#feedback').html("");
            var message = {};
            message["recipient"] = $("#recipient").val();
            message["content"] = $("#compose-textarea").wysihtml5().val();
            message["header"] = $("#subject").val();
            $("#submit").prop("disabled", true);
            var ajax_url = "/rest/privateMessages/add";
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: ajax_url,
                data: JSON.stringify(message),
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
