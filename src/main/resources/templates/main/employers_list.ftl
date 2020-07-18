<#import "*/page.ftl" as p>
<#import "/spring.ftl" as s>
<@p.page title="Работодатели">
    <#if me??>
        <@p.navbar exit=true/>
    <#else>
        <@p.navbar/>
    </#if>

    <div class="justify-content-center">
    <#list employers as empl>
        <div class="card border m-2 px-2">
            <a href="/user/${empl.getId()}" class="px-2">
                <p>${empl.getCompanyName()}</p>
                <p>${empl.getName()} ${empl.getMiddleName()} ${empl.getSurname()}</p>
            </a>
        </div>
    </#list>
    </div>
</@p.page>