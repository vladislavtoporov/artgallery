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
                <div class="carousel-item active">
                    <img class="d-block w-100" src="/img/portfolio_01.jpg" alt="First slide"/>
                    <div class="carousel-caption d-none d-md-block">
                        <h5>First slide label</h5>
                        <p>Nulla vitae elit libero, a pharetra augue mollis interdum.</p>
                    </div>
                </div>
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
<#--<div class="container-fluid">-->
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

<#list news as n>
<div id="myModal${n.id}" class="modal fade" tabindex="-1">
    <div class="modal-dialog">
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
            <div class="modal-footer">
                <a class="btn btn-primary btn-secondary" role="button" data-dismiss="modal">Закрыть</a>
            </div>
        </div>
    </div>
</div>
</#list>



<@footer/>

<@scripts/>
<script>
    $('.carousel').carousel()
</script>
</body>
</html>