<!--view-source:https://getbootstrap.com/docs/4.0/examples/floating-labels/-->
<#import "/spring.ftl" as spring/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap core CSS -->
    <link href="/css/bootstrap.min.css" type="text/css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="/css/floating-labels.css" type="text/css" rel="stylesheet">

    <link rel="stylesheet" href="/bower_components/bootstrap-datepicker/dist/css/bootstrap-datepicker.min.css">
    <!-- Bootstrap time Picker -->
    <link rel="stylesheet" href="/plugins/timepicker/bootstrap-timepicker.min.css">
<#--<link href="/css/style.css" type="text/css" rel="stylesheet">-->

</head>

<body>
<div class="container">
    <div class="text-center mb-4">
        <h1 class="h3 mb-3 font-weight-normal">Пожалуйста выберите экспозицию, чтобы получить программу</h1>
    </div>
    <form class="form-signin" method="post" action="/expositions/plan">

        <div class="form-label-group">
            <select class="form-control" name="exposition" id="exposition" required>
            <#list expositions as ex>
                <option value="${ex.id}">${ex.name}</option>
            </#list>
            </select>
        </div>

        <div class="form-label-group">
        <#if ticket??>
            <a class="btn btn-primary btn-lg btn-block" role="button"
               href="${ticket}" download>Скачать программку экскурсии</a>
            <br>
        </#if>
        </div>
        <a class="btn btn-primary btn-lg btn-block" role="button"
           href="/">Обратно на сайт</a>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <br>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Сохранить</button>
    </form>
</div>
<!-- jQuery 3 -->
<script src="/bower_components/jquery/dist/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"
        integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb"
        crossorigin="anonymous"></script>
<script type="application/javascript" src="/js/bootstrap.min.js"></script>
<script src="/bower_components/bootstrap-datepicker/dist/js/bootstrap-datepicker.min.js"></script>
<!-- bootstrap time picker -->
<script src="/plugins/timepicker/bootstrap-timepicker.min.js"></script>
<script>
    //Date picker
    $('#datepicker').datepicker({
        autoclose: true
    });
    //Timepicker
    $('.timepicker').timepicker({
        showInputs: false
    });
</script>
</body>
</html>
