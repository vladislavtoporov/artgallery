<#include "base.ftl"/>
<!DOCTYPE html>
<html lang="en">
<head>
<@head/>
    <!-- Custom CSS -->
    <link rel="stylesheet" href="/owlcarousel/owl.theme.default.min.css">
    <link rel="stylesheet" href="/owlcarousel/owl.theme.green.min.css">
    <link rel="stylesheet" href="/owlcarousel/owl.carousel.min.css">
    <script type="text/javascript" src="//vk.com/js/api/openapi.js?154"></script>
    <script type="text/javascript">
        VK.init({apiId: 6492163, onlyWidgets: true});
    </script>
</head>
<body>

<@nav/>

<div class="container space">
    <div class="row">
        <div class="col-lg-9">
            <div class="well">
                <div class="row">
                    <div class="col-lg-6">
                        <div class="row">
                        <#if picture??>
                            <img src="${picture.getFullPath()!""}"
                                 alt="${picture.name!""}"
                                 width="240" height="240">
                        </#if>
                        </div>
                    </div>

                    <div class="col-lg-6 lobster text-center" style="margin: auto">
                        <h2>${model.name!""}</h2>
                        <h4>Автор ${model.author.name!""}</h4>
                        <h4>Рейтинг 5.00</h4>
                        <h4 class="text-right">Опубликовано ${model.ts}</h4>
                    </div>
                </div>

                <div class="container space owl-carousel owl-theme">
                <#if images??>
                    <#list images as img>
                        <div class="item"><img src="${img.getFullPath()}"
                                               alt="${img.name}"></div>
                    </#list>
                </#if>
                </div>
                <hr align="center" width="100%" size="1" color="#fafafa"/>
                <div class="container lobster">
                ${model.content!""}
                </div>
            </div>
            <div class="marketing">
                <div class="row">
                <#list videos as video>
                    <div class="col-lg-6">
                        <video width="320" height="240" controls controlsList="nodownload">
                            <source src="${video.getFullPath()}" type="video/${video.format}">
                            Your browser does not support the video tag.
                        </video>
                    </div>
                </#list>
                </div>
            </div>
            <div class="marketing">
                <div class="row">
                <#list audios as audio>
                    <div class="col-lg-6">
                        <audio controls controlsList="nodownload">
                            <source src="${audio.getFullPath()}" type="audio/${audio.format}">
                            Your browser does not support the audio element.
                        </audio>
                    </div>
                </#list>
                </div>
            </div>
            <div class="well">
                <div id="vk_comments"></div>
                <script type="text/javascript">
                    VK.Widgets.Comments("vk_comments", {limit: 50, attach: "*"});
                </script>
            <#--<div class="container lobster">-->
            <#--<h3 class="text-center">Отзывы</h3>-->
            <#--<div class="row">-->
            <#--<div class="col-lg-2">-->
            <#--<img src="/img/portfolio_07.jpg" width="90" height="90" class="rounded-circle">-->
            <#--</div>-->
            <#--<div class="col-lg-10">-->
            <#--<h4>Иванов Иван</h4>-->
            <#--<h5>Оценка 5</h5>-->
            <#--</div>-->
            <#--<div style="margin-top: 10px">-->
            <#--<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Alias iusto natus officia-->
            <#--placeat-->
            <#--recusandae-->
            <#--tempora temporibus velit. A aliquam eos nostrum quia rerum tempora unde velit. Deleniti-->
            <#--facere-->
            <#--reiciendis voluptate!</p>-->
            <#--<h6 class="text-right">Опубликовано 21.10.2017</h6>-->
            <#--<hr align="center" width="100%" size="1" color="#fafafa"/>-->
            <#--</div>-->
            <#--</div>-->
            <#--</div>-->
            <#--<div class="text-center"><a class="btn btn-lg btn-primary" href="#" role="button">-->
            <#--Показать ещё</a>-->
            <#--</div>-->
            </div>
        </div>

        <div class="col-lg-3 text-center">
            <div class="well lobster">
                <h3>Экспонаты этого автора</h3>
            <#list exhibits.iterator() as ex>
                <div>
                    <hr align="center" width="100%" size="1" color="#fafafa"/>
                    <a href="/exhibits/${ex.id}">
                        <h3>${ex.name}</h3></a>
                    <img src="${ex.getPictureFile()!''}" width="100" height="100">
                <#--<div class="lobster">-->
                <#--<h4>Рейтинг 100</h4>-->
                <#--<h4>25 отзывов</h4>-->
                <#--</div>-->
                </div>
            </#list>

            </div>
        </div>
    </div>
</div>

<@footer/>

<@scripts/>
<script src="/owlcarousel/owl.carousel.min.js"></script>

<script>
    $('.owl-carousel').owlCarousel({
        loop: true,
        margin: 10,
        nav: true,
        autoplay: true,
        smartSpeed: 1000, //Время движения слайда
        autoplayTimeout: 3000, //Время смены слайда
        responsive: {
            0: {
                items: 1
            },
            600: {
                items: 3
            }
        }
    })
</script>
</body>
</html>