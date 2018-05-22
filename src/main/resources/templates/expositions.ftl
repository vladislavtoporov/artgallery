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
                    <#if e.exhibits??>
                        <img src="${e.exhibits?first.getPictureFile()!''}" width="140" height="140">
                    </#if>
                </div>
                <div class="col-lg-7">
                    <a href="/expositions/${e.id}"><h2>${e.name}</h2></a>
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