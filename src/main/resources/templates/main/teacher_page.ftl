<#import "*/page.ftl" as p>
<#import "/spring.ftl" as s>
<#assign teacher_title>
    <@s.message 'page.teacher.title'/> - Grade&Work
</#assign>
<#assign me_title>
    <@s.message 'page.my.title'/> - Grade&Work
</#assign>
<#if me?? && me.id == teacher.id>
    <#assign title = me_title>
<#else>
    <#assign title = teacher_title>
</#if>
<@p.page title=title>
    <#if me??>
        <@p.navbar exit=true/>
    <#else>
        <@p.navbar/>
    </#if>
    <div class="d-flex flex-column justify-content-center my-auto mx-auto card-width">
        <div class="card">
            <h3 class="text-center"><@s.message 'page.teacher.header'/></h3>
            <hr>
            <span class="bold">${teacher.surname} ${teacher.name} ${teacher.middleName}</span>
            <span><text class="bold">${teacher.institute.name}</text>, <@s.message 'page.teacher.experience'/><text class="bold"> ${teacher.experience} <@s.message 'page.teacher.experience.years'/></text></span>
            <span>${teacher.position}</span>
            <span><a href="${teacher.link}" class="link"><@s.message 'page.link.kfu'/></a></span>
            <hr>
            <#if teacher.competence?? && teacher.competence?size gt 0>
                <span class="bold"><@s.message 'page.teacher.competences'/></span>
                <#list teacher.competence as competence>
                    <span class="link bold">${competence.name}</span>
                </#list>
                <hr>
            </#if>
            <#if me?? && me.id == teacher.id>
                <a class="btn btn-outline-light mt-2" href="/confirm/competences"><@s.message 'page.teacher.confirmations'/></a>
                <a class="btn btn-light mt-2" href="/user/${teacher.id}/edit"><@s.message 'page.teacher.info.edit'/></a>
            <#else>
                <#if channelId??>
                    <a class="btn btn-outline-light mt-2" href="/chat?ch=${channelId}"><@s.message 'page.chat'/></a>
                <#else>
                    <a class="btn btn-outline-light mt-2" href="/user/${teacher.id}/createChat"><@s.message 'page.chat'/></a>
                </#if>
                <#if me?? && me.role == "ADMINISTRATION">
                    <a class="btn btn-light mt-2" href="/user/${teacher.id}/edit"><@s.message 'page.teacher.info.edit'/></a>
                </#if>
            </#if>
        </div>
    </div>
</@p.page>