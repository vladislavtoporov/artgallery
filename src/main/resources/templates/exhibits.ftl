<#include "base.ftl"/>
<!DOCTYPE html>
<html lang="en">
<head>
<@head/>
    <!-- Custom CSS -->
    <link rel="stylesheet" href="/css/style.css">
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
                    <div class="col-lg-9">
                        <div class="row">
                            <div class="col-lg-5">
                                <img src="/img/portfolio_03.jpg" width="240" height="240">
                            </div>
                            <div class="col-lg-7 text-center" style="margin: auto">
                                <h2>${model.name!""}</h2>
                                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Cumque, dignissimos quo.
                                    Animi nobis optio qui veniam? Ad atque aut, consequatur earum excepturi fugiat
                                    ipsam, ipsum laborum nemo soluta tempora voluptates.</p>
                            </div>
                        </div>

                    <#--<div class="container">-->
                    <#--<h4>Услуги и цены</h4>-->
                    <#--<div class="row">-->
                    <#--<div class="col-lg-6 text-left">-->
                    <#--<p>Полы</p>-->
                    <#--<p>Укладка ламината</p>-->
                    <#--</div>-->
                    <#--<div class="col-lg-6 text-right">-->
                    <#--<p>по договоренности</p>-->
                    <#--<p>по договоренности</p>-->
                    <#--</div>-->
                    <#--</div>-->
                    <#--<h4>Опыт</h4>-->
                    <#--<p>Опыт работы – с 2012 года</p>-->
                    <#--</div>-->

                    </div>

                    <div class="col-lg-3 " style="margin: auto">
                        <div class="lobster">
                            <h4 class="text-center">Рейтинг 5++</h4>
                            <h4 class="text-center">138 отзывов</h4>
                        </div>
                        <h6><i class="fa fa-check"></i> Работает по договору</h6>
                        <h6><i class="fa fa-check"></i> Даёт гарантию</h6>
                        <h6><i class="fa fa-check"></i> Собеседование пройдено</h6>
                        <h6><i class="fa fa-check"></i> Данные проверены</h6>
                    </div>
                </div>

                <div class="container space owl-carousel owl-theme">
                    <div class="item"><img src="/img/portfolio_01.jpg"></div>
                    <div class="item"><img src="/img/portfolio_02.jpg"></div>
                    <div class="item"><img src="/img/portfolio_03.jpg"></div>
                    <div class="item"><img src="/img/portfolio_04.jpg"></div>
                    <div class="item"><img src="/img/portfolio_05.jpg"></div>
                    <div class="item"><img src="/img/portfolio_06.jpg"></div>
                    <div class="item"><img src="/img/portfolio_07.jpg"></div>
                </div>

                <div class="container lobster">
                ${model.description!""}
                </div>
                <div class="container lobster">
                    <h3 class="text-center">Отзывы</h3>
                    <hr align="center" width="100%" size="1" color="#fafafa"/>
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