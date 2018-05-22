<#include "base.ftl"/>
<!DOCTYPE html>
<html lang="en">
<head>
<@head/>
</head>
<body>

<@nav/>

<div class="container">
<#if model??>
    <div class="well text-center">
        <#list model.iterator() as e>
            <div class="row">
                <div class="col-lg-5">
                    <img src="${e.getPictureFile()!''}" width="140" height="140">
                </div>
                <div class="col-lg-7">
                    <a href="/exhibits/${e.id}"><h2>${e.name}</h2></a>
                <#--<p>${q.description}</p>-->
                </div>
            </div>
            <hr align="center" width="100%" size="1" color="#fafafa"/>
        </#list>
    </div>
</#if>
</div>

<@footer/>

<@scripts/>
</body>
</html>