<#include "base.ftl"/>
<!DOCTYPE html>
<html lang="en">
<head>
<@head/>
    <!-- Custom CSS -->
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>


<@nav/>

<div class="container-fluid space">
    <form action="" class="form-inline" style="justify-content: center;">

        <input type="text" class="form-control form-control-lg" placeholder="Введите услугу или специальность"
               style="width: 90%"/>
        <button type="submit" class="btn btn-primary btn-lg">Найти</button>
    </form>
    <div class="row space">
        <div class="col-lg-2">
            <div class="well">
                <h4 class="text-center">Фильтр</h4>
                <div class="checkbox">
                    <label><input type="checkbox" name="p1" value="Экскурсии">Экскурсии</label>
                </div>
                <div class="checkbox">
                    <label><input type="checkbox" name="p2" value="Экспонаты">Экспонаты</label>
                </div>
                <div style="margin-top: 30px">
                    <h4 class="text-center">Сортировка</h4>
                    <div class="radio">
                        <p><input type="radio" checked name="optradio"> По рейтингу</p>
                        <p><input type="radio" name="optradio"> По отзывам</p>
                        <p><input type="radio" name="optradio"> По цене</p>
                    </div>
                </div>
            </div>
        </div>


        <div class="col-lg-7">

            <div class="well">
                <div class="row">
                    <div class="col-lg-8">
                        <div class="row">
                            <div class="col-lg-5">
                                <img src="/img/portfolio_03.jpg" width="140" height="140">
                            </div>
                            <div class="col-lg-7 text-center">
                                <a href=""><h2>Название</h2></a>
                                <p>Описание</p>
                            </div>
                        <#--<div style="margin-left: 20px">-->
                        <#--<h4>Отзывы</h4>-->
                        <#--<p>Отзыв 1</p>-->
                        <#--</div>-->
                        </div>
                    </div>
                    <div class="col-lg-4">
                        <button class="btn btn-lg btn-primary btn-block">Выбрать</button>

                        <div class="space text-center">
                            <h4>Рейтинг 5++</h4>
                            <h4>138 отзывов</h4>
                        </div>
                        <h6><i class="fa fa-check"></i> Работает по договору</h6>
                    </div>
                </div>
            </div>
        </div>


        <div class="col-lg-3 text-center">
            <div class="well">
                <h3>Другие услуги</h3>
                <div>
                    <hr align="center" width="100%" size="1" color="#fafafa"/>
                    <h3>Услуга 1</h3>
                    <img src="/img/portfolio_08.jpg" width="140" height="140">
                    <p style="margin-top: 10px">Donec sed odio dui. Etiam porta sem malesuada magna mollis euismod.
                        Nullam id dolor id nibh
                        ultricies vehicula ut id elit. Morbi leo risus, porta ac consectetur ac, vestibulum at eros.</p>
                    <p><a class="btn btn-primary" href="#" role="button">Подробнее</a></p>
                </div>
                <div>
                    <hr align="center" width="100%" size="1" color="#fafafa"/>
                    <h3>Услуга 1</h3>
                    <img src="/img/portfolio_08.jpg" width="140" height="140">
                    <p style="margin-top: 10px">Donec sed odio dui. Etiam porta sem malesuada magna mollis euismod.
                        Nullam id dolor id nibh
                        ultricies vehicula ut id elit. Morbi leo risus, porta ac consectetur ac, vestibulum at eros.</p>
                    <p><a class="btn btn-primary" href="#" role="button">Подробнее</a></p>
                </div>
                <div>
                    <hr align="center" width="100%" size="1" color="#fafafa"/>
                    <h3>Услуга 1</h3>
                    <img src="/img/portfolio_08.jpg" width="140" height="140">
                    <p style="margin-top: 10px">Donec sed odio dui. Etiam porta sem malesuada magna mollis euismod.
                        Nullam id dolor id nibh
                        ultricies vehicula ut id elit. Morbi leo risus, porta ac consectetur ac, vestibulum at eros.</p>
                    <p><a class="btn btn-primary" href="#" role="button">Подробнее</a></p>
                </div>
            </div>
        </div><!-- /.col-lg-4 -->
    </div>
</div>

<@footer/>
<@scripts/>
</body>
</html>