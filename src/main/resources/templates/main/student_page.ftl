<#import "*/page.ftl" as p>
<#import "/spring.ftl" as s>
<#assign student_title>
    <@s.message 'page.student.title'/> - Grade&Work
</#assign>
<#assign me_title>
    <@s.message 'page.my.title'/> - Grade&Work
</#assign>
<#if me?? && me.id == student.id>
    <#assign title = me_title>
<#else>
    <#assign title = student_title>
</#if>
<@p.page title=title>
    <#if me??>
        <@p.navbar exit=true/>
    <#else>
        <@p.navbar/>
    </#if>
    <div class="d-flex flex-column justify-content-center my-auto mx-auto card-width">
        <div class="card">
            <h3 class="text-center"><@s.message 'page.student.header'/></h3>
            <hr>
            <span><text class="bold">${student.surname} ${student.name} ${student.middleName}</text>, ${student.birthday?date}</span>
            <span><text class="bold">${student.institute.name}</text>, ${student.faculty.name}</span>
            <span><@s.message 'page.student.group'/> <text class="bold">${student.group}</text>, ${student.yearStart?c}-${student.yearGraduate?c}</span>
            <span><@s.message 'page.student.average'/>: <text class="bold">${student.average}</text></span>
            <span><a href="${student.link}" class="link"><@s.message 'page.link.kfu'/></a></span>
            <hr>
            <#if student.competences?? && student.competences?size gt 0>
                <span class="bold"><@s.message 'page.student.competences'/></span>
                <#list student.competences as competence>
                    <span class="bold link">${competence.competence.name}</span>
                </#list>
                <hr>
            </#if>
            <#if student.email?? || student.phone??>
                <span class="bold"><@s.message 'sign.contacts'/></span>
                <#if student.email??>
                    <span><@s.message 'sign.contact.email'/>: ${student.email}</span>
                </#if>
                <#if student.phone??>
                    <span><@s.message 'sign.contact.phone'/>: ${student.phone}</span>
                </#if>
                <hr>
            </#if>
            <#if student.projects?? && student.projects?size gt 0>
                <span class="bold"><@s.message 'page.student.projects'/></span>
                <#list student.projects as project>
                    <span><a class="bold link" href="${project.link}">${project.name}</a></span>
                </#list>
                <hr>
            </#if>
            <#if student.bio??>
                <span class="bold"><@s.message 'sign.bio'/></span>
                <span>${student.bio}</span>
                <hr>
            </#if>
            <#if me?? && me.id == student.id>
<#--                <#if student.competences?? || student.competences?size gt 0>-->
<#--                    <a class="btn btn-outline-light mt-2" href="/requests"><@s.message 'page.student.requests'/></a>-->
<#--                    <a class="btn btn-light mt-2" href="/resume/edit"><@s.message 'page.student.resume.edit'/></a>-->
<#--                <#else>-->
                    <a class="btn btn-outline-light mt-2" href="/resume/create"><@s.message 'page.student.resume.create'/></a>
<#--                </#if>-->
                <a class="btn btn-light mt-2" href="/edit"><@s.message 'page.student.info.edit'/></a>
            <#else>
                <a class="btn btn-outline-light mt-2" href="/chat/${student.id}"><@s.message 'page.chat'/></a>
            </#if>
        </div>
    </div>
</@p.page>