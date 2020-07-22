<#import "*/page.ftl" as p>
<#import "/spring.ftl" as s>
<#assign administration_title>
    <@s.message 'page.administration.title'/> - Grade&Work
</#assign>
<#assign me_title>
    <@s.message 'page.my.title'/> - Grade&Work
</#assign>
<#if me?? && me.id == administration.id>
    <#assign title = me_title>
<#else>
    <#assign title = administration_title>
</#if>
<@p.page title=title>
    <#if me??>
        <@p.navbar exit=true/>
    <#else>
        <@p.navbar/>
    </#if>
    <div class="d-flex flex-column justify-content-center my-auto mx-auto card-width">
        <div class="card">
            <h3 class="text-center"><@s.message 'page.administration.header'/></h3>
            <#if me?? && me.id == administration.id>
                <a class="btn btn-outline-light mb-2" href="/users"><@s.message 'page.administration.button.users'/></a>
<#--                <a class="btn btn-outline-light mt-2" href="/confirm/accounts"><@s.message 'page.administration.confirmations.accounts'/></a>-->
                <div class="d-inline-flex">
                    <a class="btn btn-light w-100" href="/signUp/teacher"><@s.message 'page.administration.sign.up.teacher'/></a>
                    <a class="btn btn-light ml-2" href="/signUp/teacher/file"><@s.message 'page.administration.sign.up.file'/></a>
                </div>
                <div class="d-inline-flex">
                    <a class="btn btn-light mt-2 w-100" href="/signUp/student"><@s.message 'page.administration.sign.up.student'/></a>
                    <a class="btn btn-light mt-2 ml-2" href="/signUp/student/file"><@s.message 'page.administration.sign.up.file'/></a>
                </div>
            <#else>
                <#if channelId??>
                    <a class="btn btn-outline-light mt-2" href="/chat?ch=${channelId}"><@s.message 'page.chat'/></a>
                <#else>
                    <a class="btn btn-outline-light mt-2" href="/user/${administration.id}/createChat"><@s.message 'page.chat'/></a>
                </#if>
            </#if>
        </div>
    </div>
</@p.page>