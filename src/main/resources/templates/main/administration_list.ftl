<#import "*/page.ftl" as p>
<#import "/spring.ftl" as s>
<#assign title>
    <@s.message 'page.institutes.title'/> - Grade&Work
</#assign>
<@p.page title=title>
    <@p.navbar exit=true/>
    <div class="d-flex flex-column justify-content-center my-auto mx-auto card-width">
        <div class="card">
            <h3 class="m-0 regular text-center mb-2"><@s.message 'page.administration.users.header'/></h3>
            <hr>
            <span class="regular"><@s.message 'page.administration.users.hint'/></span>

            <hr>
            <span class="regular"><@s.message 'page.administration.users.students'/></span>
            <#list students as student>
                <a class="inst-card <#if !student?is_last>mb-2</#if> bold link" href="/user/${student.id}">${student.surname} ${student.name} ${student.middleName}<br>${student.institute.name}, ${student.group}</a>
            </#list>

            <span class="regular"><@s.message 'page.administration.users.employers'/></span>
            <#list employers as employer>
                <a class="inst-card <#if !employer?is_last>mb-2</#if> bold link" href="/user/${employer.id}">${employer.surname} ${employer.name} ${employer.middleName}<br>${employer.companyName}</a>
            </#list>

            <span class="regular"><@s.message 'page.administration.users.teachers'/></span>
            <#list teachers as teacher>
                <a class="inst-card <#if !teacher?is_last>mb-2</#if> bold link" href="/user/${teacher.id}">${teacher.surname} ${teacher.name} ${teacher.middleName}<br>${teacher.institute.name}</a>
            </#list>
        </div>
    </div>
</@p.page>