<#include "base.ftl"/>
<!DOCTYPE html>
<html lang="en">
<head>
<@head/>
    <!-- Custom CSS -->
    <link rel="stylesheet" href="/owlcarousel/owl.theme.default.min.css">
    <link rel="stylesheet" href="/owlcarousel/owl.theme.green.min.css">
    <link rel="stylesheet" href="/owlcarousel/owl.carousel.min.css">
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
                        <#--<img src="${model.images.iterator().next().getFullPath()!""}"-->
                        <#--alt="${model.images.iterator().next().name!""}"-->
                        <#--width="240" height="240">-->
                            <img src="" alt="" width="240" height="240">
                        </div>
                    </div>

                    <div class="col-lg-6 lobster text-center" style="margin: auto">
                        <h2>${model.name!""}</h2>
                        <h4>Рейтинг 5.00</h4>
                        <h4>138 отзывов</h4>
                        <h4>Опубликовано ${model.ts}</h4>
                    </div>
                </div>

                <div class="container space owl-carousel owl-theme">
                <#list model.images as img>
                    <#if img.content_type?? && img.content_type == "image">
                        <div class="item"><img src="${img.getFullPath()}"
                                               alt="${img.name}"></div>
                    </#if>
                </#list>
                </div>
                <hr align="center" width="100%" size="1" color="#fafafa"/>
                <div class="container lobster">
                ${model.content!""}
                </div>
                <hr align="center" width="100%" size="1" color="#fafafa"/>
                <div class="container lobster">
                    <h3 class="text-center">Отзывы</h3>
                    <div class="row">
                        <div class="col-lg-2">
                            <img src="/img/portfolio_07.jpg" width="90" height="90" class="rounded-circle">
                        </div>
                        <div class="col-lg-10">
                            <h4>Иванов Иван</h4>
                            <h5>Оценка 5</h5>
                        </div>
                        <div style="margin-top: 10px">
                            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Alias iusto natus officia
                                placeat
                                recusandae
                                tempora temporibus velit. A aliquam eos nostrum quia rerum tempora unde velit. Deleniti
                                facere
                                reiciendis voluptate!</p>
                            <h6 class="text-right">Опубликовано 21.10.2017</h6>
                            <hr align="center" width="100%" size="1" color="#fafafa"/>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-2">
                            <img src="/img/portfolio_07.jpg" width="90" height="90" class="rounded-circle">
                        </div>
                        <div class="col-lg-10">
                            <h4>Иванов Иван</h4>
                            <h5>Оценка 5</h5>
                        </div>
                        <div style="margin-top: 10px">
                            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Alias iusto natus officia
                                placeat
                                recusandae
                                tempora temporibus velit. A aliquam eos nostrum quia rerum tempora unde velit. Deleniti
                                facere
                                reiciendis voluptate!</p>
                            <h6 class="text-right">Опубликовано 21.10.2017</h6>
                            <hr align="center" width="100%" size="1" color="#fafafa"/>
                        </div>
                    </div>

                </div>
                <div class="text-center"><a class="btn btn-lg btn-primary" href="#" role="button">
                    Показать ещё</a>
                </div>
            </div>
        </div>

        <div class="col-lg-3 text-center">
            <div class="well lobster">
                <h3>Возможно вас заинтересуют</h3>
                <div>
                    <hr align="center" width="100%" size="1" color="#fafafa"/>
                    <a href="#">
                        <h3>Название</h3></a>
                    <img src="./img/portfolio_03.jpg" width="100" height="100">
                    <p style="margin-top: 10px">Мелкий ремонт, сантехника, бытовая техника</p>
                    <div class="lobster">
                        <h4>Рейтинг 100</h4>
                        <h4>25 отзывов</h4>
                    </div>
                </div>
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