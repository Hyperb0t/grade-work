<#import "*/page.ftl" as p>
<#import "/spring.ftl" as s>
<#assign title>
    <@s.message 'page.institutes.title'/> - Grade&Work
</#assign>
<@p.page title=title>
    <#if me??>
        <@p.navbar exit=true/>
    <#else>
        <@p.navbar/>
    </#if>
    <div class="d-flex flex-column justify-content-center my-auto mx-auto card-width">
        <div class="card">
            <h3 class="m-0 regular text-center mb-2"><@s.message 'page.institutes.header'/></h3>
            <hr>
            <span class="regular"><@s.message 'page.institutes.info'/></span>

            <#list institutes as institute>
                <a class="inst-card <#if !institute?is_last>mb-2</#if> bold link" href="/search?i=${institute.id}">${institute.name}</a>
            </#list>
        </div>
    </div>
</@p.page>