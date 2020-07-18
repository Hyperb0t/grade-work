<#import "*/page.ftl" as p>
<#import "/spring.ftl" as s>
<#assign title>
    <@s.message 'page.employers.title'/> - Grade&Work
</#assign>
<@p.page title=title>
    <#if me??>
        <@p.navbar exit=true/>
    <#else>
        <@p.navbar/>
    </#if>

    <div class="d-flex flex-column justify-content-center my-auto mx-auto card-width">
        <div class="card">
            <h3 class="m-0 regular text-center mb-2"><@s.message 'page.employers.header'/></h3>
            <hr>
            <span class="regular"><@s.message 'page.employers.info'/></span>

            <#list employers as employer>
                <a class="inst-card <#if !employer?is_last>mb-2</#if> bold link" href="/user/${employer.id}">${employer.companyName}</a>
            </#list>
        </div>
    </div>
</@p.page>