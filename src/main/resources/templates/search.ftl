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
    <form action="/search" method="get" id="queryForm" class="form-inline" style="justify-content: center;">
    <#--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>-->
        <input type="text" class="form-control form-control-lg" name="query" id="query" value="${query!""}"
               style="width: 90%"/>
        <button type="submit" class="btn btn-primary btn-lg" id="submit">Найти</button>
    </form>
    <div class="row space">
        <div class="col-lg-2">
            <div class="well">
                <h4 class="text-center">Фильтр</h4>
                <div class="radio">
                    <label><input type="radio" form="queryForm" value="exposition"
                                  name="type" id="expositionRadio">Экспозиции</label>
                </div>
                <div class="radio">
                    <label><input type="radio" id="exhibitRadio" form="queryForm" checked value="exhibit" name="type">Экспонаты</label>
                </div>
                <div style="margin-top: 30px">
                    <h4 class="text-center">Сортировка</h4>
                    <div class="radio">
                        <p><input type="radio" form="queryForm" name="sort" value="price"> По цене</p>
                        <p><input type="radio" form="queryForm" name="sort" checked value="author"> По автору</p>
                        <p><input type="radio" form="queryForm" name="sort" value="date"> По датe</p>
                        <p><input type="radio" form="queryForm" name="sort" value="rating"> По рейтингу</p>
                        <p><input type="radio" form="queryForm" name="sort" value="feedback"> По отзывам</p>
                    </div>
                </div>
            <#--<button type="submit" form="queryForm" class="btn btn-primary btn-lg">Применить</button>-->
            </div>
        </div>


        <div class="col-lg-7">
            <div class="well text-center" id="content">
            <#if expositions?? && expositions?size == 0 || exhibits?? && exhibits?size == 0>
                <p>Не найдено результатов по вашему запросу</p>
            </#if>
            <#if exhibits??>
                <#list exhibits.iterator() as e>
                    <div class="row">
                        <div class="col-lg-5">
                            <img src="${e.getPictureFile()!''}" width="140" height="140">
                        </div>
                        <div class="col-lg-7">
                            <a href="/expositions/${e.id}"><h2>${e.name}</h2></a>
                            <p>Автор: ${e.author.name}</p>
                        </div>
                    </div>
                    <hr align="center" width="100%" size="1" color="#fafafa"/>
                </#list>
            </#if>
            <#if expositions??>
                <#list expositions.iterator() as e>
                    <div class="row">
                        <div class="col-lg-5">
                            <#if e.exhibits?first??>
                                <img src="${e.exhibits?first.picture.getFullPath()!''}" width="140" height="140">
                            </#if>
                        </div>
                        <div class="col-lg-7">
                            <a href="/exhibits/${e.id}"><h2>${e.name}</h2></a>
                            <p>Автор: ${e.owner.name}</p>
                        </div>
                    </div>
                    <hr align="center" width="100%" size="1" color="#fafafa"/>
                </#list>
            </#if>
            </div>
        </div>

        <div class="col-lg-3 text-center">
            <div class="well">
                <h3>Топ просмотров</h3>
                <#list toplist.iterator() as elem>
                <div>
                    <hr align="center" width="100%" size="1" color="#fafafa"/>
                    <h3><a href="/exhibits/${elem.id}">${elem.name!""}</a></h3>
                    <img src="${elem.getPictureFile()!''}" width="140" height="140">
                    <h3>Автор: ${elem.author.name}</h3>
                </div>
                </#list>
            </div>
        </div><!-- /.col-lg-4 -->
    </div>
</div>

<@footer/>
<@scripts/>
<script>
    $(document).ready(function () {
        $("#queryForm").submit(function (event) {
            event.preventDefault();

            var query = {};
            query["query"] = $('#query');
            alert($('#query'));
            query["sort"] = $('input[name="sort"]:checked').val();

            if ($("#exhibitRadio").checked) {
                var ajax_url = "/search/ajax/exhibits";
                $.ajax({
                    type: "POST",
                    contentType: "application/json",
                    url: ajax_url,
                    data: JSON.stringify(query),
                    dataType: 'json',
                    cache: false,
                    timeout: 600000,
                    success: function (data) {
                        console.log("SUCCESS : ", data);
                    },
                    error: function (e) {
                        console.log("ERROR : ", e);
                    }
                });
            }
            else {
                alert("else")
            }
        };
    });
</script>
</body>
</html>