<#include "base.ftl"/>
<!DOCTYPE html>
<html lang="en">
<head>
<@head/>
    <!-- Custom CSS -->
</head>
<body>


<@nav/>

<div class="container-fluid space">
    <form action="/search" method="post" id="querryForm" class="form-inline" style="justify-content: center;">

        <input type="text" class="form-control form-control-lg" name="querry" value="${querry!""}"
               style="width: 90%"/>
        <button type="submit" class="btn btn-primary btn-lg">Найти</button>
    </form>
    <div class="row space">
        <div class="col-lg-2">
            <div class="well">
                <h4 class="text-center">Фильтр</h4>
                <div class="checkbox">
                    <label><input type="checkbox" form="querryForm" checked name="expositionFlag" value="true">Экспозиции</label>
                </div>
                <div class="checkbox">
                    <label><input type="checkbox" form="querryForm" name="exhibitFlag" value="true">Экспонаты</label>
                </div>
                <div style="margin-top: 30px">
                    <h4 class="text-center">Сортировка</h4>
                    <div class="radio">
                        <p><input type="radio" form="querryForm" checked name="sort" value="price"> По цене</p>
                        <p><input type="radio" form="querryForm" name="sort" value="author"> По автору</p>
                        <p><input type="radio" form="querryForm" name="sort" value="date"> По датe</p>
                        <p><input type="radio" form="querryForm" name="sort" value="rating"> По рейтингу</p>
                        <p><input type="radio" form="querryForm" name="sort" value="feedback"> По отзывам</p>
                    </div>
                </div>
                <button type="submit" form="querryForm" class="btn btn-primary btn-lg">Применить</button>
            </div>
        </div>


        <div class="col-lg-7">
        <#--<#if !expositions.iterator().hasNext() && !exhibits.iterator().hasNext() >-->
        <#--<p>Не найдено результатов по вашему запросу</p>-->
        <#--</#if>-->
        <#if expositions??>
            <div class="well text-center">
                <#list expositions.iterator() as e>
                    <div class="row">
                        <div class="col-lg-5">
                            <img src="/img/portfolio_03.jpg" width="140" height="140">
                        </div>
                        <div class="col-lg-7">
                            <a href="/expositions/${e.id}"><h2>${e.name}</h2></a>
                        <#--<p>${q.description}</p>-->
                        </div>
                    </div>
                </#list>
            </div>
        </#if>
        <#if exhibits??>
            <div class="well text-center">
                <#list exhibits.iterator() as e>
                    <div class="row">
                        <div class="col-lg-5">
                            <img src="/img/portfolio_03.jpg" width="140" height="140">
                        </div>
                        <div class="col-lg-7">
                            <a href="/exhibits/${e.id}"><h2>${e.name}</h2></a>
                        <#--<p>${q.description}</p>-->
                        </div>
                    </div>
                </#list>
            </div>
        </#if>
        </div>


        <div class="col-lg-3 text-center">
            <div class="well">
                <h3>Топ просмотров</h3>
                <div>
                    <hr align="center" width="100%" size="1" color="#fafafa"/>
                    <h3>Услуга 1</h3>
                    <img src="/img/portfolio_08.jpg" width="140" height="140">
                    <p style="margin-top: 10px">Donec sed odio dui. Etiam porta sem malesuada magna mollis euismod.
                        Nullam id dolor id nibh
                        ultricies vehicula ut id elit. Morbi leo risus, porta ac consectetur ac, vestibulum at eros.</p>
                    <p><a class="btn btn-primary btn-lg" href="#" role="button">Подробнее</a></p>
                </div>
            </div>
        </div><!-- /.col-lg-4 -->
    </div>
</div>

<@footer/>
<@scripts/>
</body>
</html>