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
        <h1 class="h3 mb-3 font-weight-normal">Пожалуйста заполните форму, чтобы забронировать посещение</h1>
    </div>
    <form class="form-signin" method="post" action="/reservation">
        <div class="form-label-group validation">
            <input type="text" name="customer" id="customer" class="form-control" placeholder="ФИО" required
                   pattern="[A-Za-zА-Яа-яё ]+" autofocus value="${form.customer!''}">
            <span class="validity"></span>
            <label for="customer">ФИО</label>

        </div>

        <div class="form-label-group validation">
            <input type="number"
                   name="numberOfVisitors" id="numberOfVisitors" class="form-control"
                   placeholder="количество посетителей"
                   required value="${form.numberOfVisitors!''}">
            <span class="validity"></span>
            <label for="numberOfVisitors">количество посетителей</label>
        </div>

        <div class="form-label-group">
            <input type="hidden" id="cityValue" value="${form.city!''}">
            <select class="form-control" name="city" id="city" required>
                <option value="Казань">Kazan</option>
                <option value="Набережные Челны">Naberezhnie Chelni</option>
                <option value="Альметьевск">Almetevsk</option>
            </select>
        </div>

        <div class="form-label-group">
            <input type="text" class="form-control datepicker" name=date id="datepicker" required
                   value="${form.date!''}">
        <#--<label for="city">дата</label>-->
        </div>

        <div class="form-label-group">
            <select class="form-control" name="exposition" id="exposition" required>
            <#list expositions as ex>
                <option value="${ex.name}">${ex.name}</option>
            </#list>
            </select>
            <input type="hidden" id="expValue" value="${form.exposition!''}">
        </div>

        <div class="form-label-group">
            <select class="form-control" name="time" id="time" required>
                <option value="09:00">09:00</option>
                <option value="12:00">12:00</option>
                <option value="15:00">15:00</option>
            </select>
            <input type="hidden" id="timeValue" value="${form.time!''}">
        <#--<label for="city">дата</label>-->
        </div>

        <div class="form-label-group">
        <#if ticket??>
            <a class="btn btn-primary btn-lg btn-block" role="button"
               href="${ticket}" download>Скачать билет</a>
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


<script>
    if $("#cityValue").val() != "" {
        document.getElementById('city').value = $("#cityValue").val();
        if $("#timeValue").val() != "" {
            document.getElementById('time').value = $("#timeValue").val();

            if $("#expValue").val() != "" {
                document.getElementById('exposition').value = $("#expValue").val();
            }
</script>
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
