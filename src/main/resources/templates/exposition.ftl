<#include "base.ftl"/>
<!DOCTYPE html>
<html lang="en">
<head>
<@head/>
</head>
<body>

<@nav/>

<div class="container-fluid space">
    <div class="row">
        <div class="col-lg-9">
            <div class="well">
                <div class="row">
                    <div class="col-lg-6">
                    <#if model.exhibits??>
                        <img src="${model.exhibits?first.getPictureFile()!''}" width="240" height="240">
                    </#if>
                    </div>

                    <div class="col-lg-6 lobster text-center" style="margin: auto">
                        <h2>${model.name!""}</h2>
                        <h4>Автор: ${model.owner.name!""}</h4>
                        <h4 class="text-center">Цена ${model.price} ₽</h4>
                        <h4 class="text-center">Рейтинг 5.00</h4>
                        <h4 class="text-right">Открытие экспозиции ${model.start}</h4>
                        <h4 class="text-right">Закрытие экспозиции ${model.finish}</h4>

                    </div>
                </div>
                <div class="container space">
                    <hr align="center" width="100%" size="1" color="#fafafa"/>
                ${model.description!""}
                    <hr align="center" width="100%" size="1" color="#fafafa"/>
                </div>

                <div class="marketing">
                    <div class="row">
                        <!-- Three columns of text below the carousel -->
                    <#if model.exhibits??>
                        <#list model.exhibits as ex>
                            <div class="col-lg-4">
                                <img class="rounded-circle" src="${ex.getPictureFile()!''}"
                                     alt="Generic placeholder image"
                                     width="140"
                                     height="140">
                                <h2>${ex.name}</h2>
                                <h6>Рейтинг: 10</h6>
                                <h6>10 отзывов: 10</h6>
                                <p><a class="btn btn-primary" href="/exhibits/${ex.id}" role="button">Подробнее</a></p>
                            </div><!-- /.col-lg-4 -->
                        </#list>
                    </#if>

                    </div><!-- /.row -->
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
                <h3>Экспозиции этого автора</h3>
            <#list expositions.iterator() as ex>
                <div>
                    <hr align="center" width="100%" size="1" color="#fafafa"/>
                    <a href="#">
                        <h3>${ex.name}</h3></a>
                    <#if ex.exhibits??>
                        <img src="${ex.exhibits?first.getPictureFile()!''}" width="100" height="100">
                    </#if>
                    <div class="lobster">
                        <h4>Рейтинг 100</h4>
                        <h4>25 отзывов</h4>
                    </div>
                </div>
            </#list>

            </div>
        </div>
    </div>
</div>

<@footer/>

<@scripts/>
</body>
</html>