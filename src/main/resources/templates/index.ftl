<#include "base.ftl"/>
<!DOCTYPE html>
<html lang="en">
<head>
<@head/>
    <!-- Custom CSS -->
</head>
<body>

<@nav/>
<div class="container-fluid">
    <div class="bd-example space">
        <h2 class="text-center">Топ новых экспозиций</h2>
        <div id="carouselExampleCaptions" class="carousel slide" data-ride="carousel">
            <ol class="carousel-indicators">
                <li data-target="#carouselExampleCaptions" data-slide-to="0" class="active"></li>
                <li data-target="#carouselExampleCaptions" data-slide-to="1"></li>
                <li data-target="#carouselExampleCaptions" data-slide-to="2"></li>
            </ol>
            <div class="carousel-inner">
            <#list carousel as img>
                <#if img?counter == 1>
                    <div class="carousel-item active">
                        <img class="d-block w-100" src="${img.getFullPath()}" alt=""/>
                        <div class="carousel-caption d-none d-md-block">
                            <h5>${img.name}</h5>
                        </div>
                    </div>
                <#else>
                    <div class="carousel-item">
                        <img class="d-block w-100" src="${img.getFullPath()}" alt=""/>
                        <div class="carousel-caption d-none d-md-block">
                            <h5>${img.name}</h5>
                        </div>
                    </div>
                </#if>

            </#list>
            </div>
            <a class="carousel-control-prev" href="#carouselExampleCaptions" role="button" data-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="sr-only">Previous</span>
            </a>
            <a class="carousel-control-next" href="#carouselExampleCaptions" role="button" data-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="sr-only">Next</span>
            </a>
        </div>
    </div>
<#--</div>-->

    <div class="row">
        <div class="col-sm-6">
            <h3 class="text-center">Новости</h3>
            <div class="well">
            <#list news as n>
                <h4 class="text-center">${n.header}</h4>
                ${n.preview}
                <div class="text-right">
                    <h6>Опубликовано ${n.ts}</h6>
                    <button class="text-right btn btn-primary btn-small" id="regModal" type="button" data-toggle="modal"
                            data-target="#myModal${n.id}">Подробнее
                    </button>
                </div>
                <hr align="center" width="100%" size="1" color="#fafafa"/>
            </#list>
            </div>
        </div>
    <div class="col-sm-6">
        <h3 class="text-center">Обратная связь</h3>
    <#if user??>
        <div class="well">
            <form id="ticketForm">
                <p>Оставьте заявку и мы обязательно ответим вам по email</p>
                <div class="row">
                    <div class="col-sm-6 form-group">
                        <input class="form-control" id="header" name="header" placeholder="тема" type="text"
                               required>
                    </div>
                </div>
                <textarea class="form-control" id="content" name="content" placeholder="сообщение"
                          rows="5"></textarea>
                <br>
                <div class="row">
                    <div class="col-sm-12 form-group">
                        <button class="btn btn-primary btn-lg" type="submit">Send</button>
                    </div>
                </div>
            </form>
            <div id="feedback"></div>
            <div style="margin: auto;">
                <h3 class="centered ctitle">Где нас найти</h3>
                <script type="text/javascript" charset="utf-8" async
                        src="https://api-maps.yandex.ru/services/constructor/1.0/js/?um=constructor%3Abbfede36bbbb7858416300865efaf0f94426c1d57089d46702ad1292d536ea1f&amp;width=375&amp;height=290&amp;lang=ru_RU&amp;scroll=true"></script>
            </div>
        </div>
    </div>
    <#else>
        <p class="text-center">Пожалуйста <a href="/signIn">авторизуйтесь</a>, чтобы оставить вашу заявку</p>
    </#if>
    </div>
</div>

<#list news as n>
<div id="myModal${n.id}" class="modal fade" tabindex="-1">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-name">${n.header}</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body container">
                ${n.content}
            </div>
        <#--<div class="modal-footer">-->
        <#--<a class="btn btn-primary" role="button" data-dismiss="modal">Закрыть</a>-->
        <#--</div>-->
        </div>
    </div>
</div>
</#list>



<@footer/>

<@scripts/>
<script>
    $('.carousel').carousel()
</script>


<!-- Start SiteHeart code -->

<script>
    $(document).ready(function () {
        $("#ticketForm").submit(function (event) {
            event.preventDefault();
            $('#feedback').html("");
            var ticket = {};
            ticket["content"] = $('#content').val();
            ticket["header"] = $('#header').val();
            $("#submit").prop("disabled", true);
            var ajax_url = "/rest/tickets/add";
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: ajax_url,
                data: JSON.stringify(ticket),
                dataType: 'json',
                cache: false,
                timeout: 600000,
                success: function (data) {
                    console.log("SUCCESS : ", data);
                    $('#content').val("");
                    $('#header').val("");
                    $("#submit").prop("disabled", false);

                    $("#feedback").html(
                            "<div style=\"width: 100%\" class=\"alert alert-success alert-dismissible fade show\" role=\"alert\">\n" +
                            "                            <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\">\n" +
                            "                                <span aria-hidden=\"true\">&times;</span>\n" +
                            "                            </button>\n" +
                            "                            Ваша заявка принята\n" +
                            "                        </div>"
                    );


                },
                error: function (e) {
                    console.log("ERROR : ", e);
                    $("#submit").prop("disabled", false);
                    $("#feedback").html(
                            "<div style=\"width: 100%\" class=\"alert alert-warning alert-dismissible fade show\" role=\"alert\">\n" +
                            "                            <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\">\n" +
                            "                                <span aria-hidden=\"true\">&times;</span>\n" +
                            "                            </button>\n" +
                            "                            Cервис временно недоступен\n" +
                            "                        </div>"
                    );
                }
            });
        });
    });
</script>
<!-- End SiteHeart code -->
</body>
</html>