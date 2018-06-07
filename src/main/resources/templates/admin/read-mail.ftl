<#include "base.admin.ftl"/>
<!DOCTYPE html>
<html>
<head>
<@head/>
    <!-- AdminLTE Skins. Choose a skin from the css/skins
         folder instead of downloading all of them to reduce the load. -->
    <link rel="stylesheet" href="/dist/css/skins/_all-skins.min.css">
    <!-- iCheck -->
    <link rel="stylesheet" href="/plugins/iCheck/flat/blue.css">
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
                Read Mail
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
                    <a href="/admin/compose" class="btn btn-primary btn-block margin-bottom">Compose</a>

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
                                <li class="active"><a href="/admin/mailbox"><i class="fa fa-inbox"></i> All
                                <li><a href="/admin/mailbox/new"><i class="fa fa-envelope-o"></i> New</a></li>
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
                <div class="col-md-9">
                    <div class="box box-primary">
                        <div class="box-header with-border">
                            <h3 class="box-title">Read Mail</h3>

                            <div class="box-tools pull-right">
                                <a href="#" class="btn btn-box-tool" data-toggle="tooltip" title="Previous"><i
                                        class="fa fa-chevron-left"></i></a>
                                <a href="#" class="btn btn-box-tool" data-toggle="tooltip" title="Next"><i
                                        class="fa fa-chevron-right"></i></a>
                            </div>
                        </div>
                        <!-- /.box-header -->
                        <div class="box-body no-padding">
                            <div class="mailbox-read-info">
                                <h3>${model.header}</h3>
                                <h5>From: ${model.sender.login}
                                    <span class="mailbox-read-time pull-right">${model.ts}</span></h5>
                            </div>
                            <!-- /.mailbox-read-info -->
                            <div class="mailbox-controls with-border text-center">
                                <div class="btn-group">
                                    <button type="button" class="btn btn-default btn-sm" data-toggle="tooltip"
                                            data-container="body" title="Delete">
                                        <i class="fa fa-trash-o"></i></button>
                                    <button type="button" class="btn btn-default btn-sm" data-toggle="tooltip"
                                            data-container="body" title="Reply">
                                        <i class="fa fa-reply"></i></button>
                                    <button type="button" class="btn btn-default btn-sm" data-toggle="tooltip"
                                            data-container="body" title="Forward">
                                        <i class="fa fa-share"></i></button>
                                </div>
                                <!-- /.btn-group -->
                                <button type="button" class="btn btn-default btn-sm" data-toggle="tooltip"
                                        title="Print">
                                    <i class="fa fa-print"></i></button>
                            </div>
                            <!-- /.mailbox-controls -->
                            <div class="mailbox-read-message">
                            ${model.content}
                            </div>
                            <!-- /.mailbox-read-message -->
                        </div>
                        <!-- /.box-body -->
                    <#--<div class="box-footer">-->
                    <#--<ul class="mailbox-attachments clearfix">-->
                    <#--<li>-->
                    <#--<span class="mailbox-attachment-icon"><i class="fa fa-file-pdf-o"></i></span>-->

                    <#--<div class="mailbox-attachment-info">-->
                    <#--<a href="#" class="mailbox-attachment-name"><i class="fa fa-paperclip"></i>-->
                    <#--Sep2014-report.pdf</a>-->
                    <#--<a href="#" class="btn btn-default btn-xs pull-right"><i-->
                    <#--class="fa fa-cloud-download"></i></a>-->
                    <#--</div>-->
                    <#--</li>-->
                    <#--</ul>-->
                    <#--</div>-->
                        <!-- /.box-footer -->
                    </div>
                    <!-- /. box -->
                </div>
                <!-- /.col -->
            </div>
            <!-- /.row -->
        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->
<@footer/>
    <!-- Add the sidebar's background. This div must be placed
         immediately after the control sidebar -->
    <div class="control-sidebar-bg"></div>
</div>
<!-- ./wrapper -->
<@scripts/>
<!-- Slimscroll -->
<script src="/bower_components/jquery-slimscroll/jquery.slimscroll.min.js"></script>
<!-- FastClick -->
<script src="/bower_components/fastclick/lib/fastclick.js"></script>
</body>
</html>
