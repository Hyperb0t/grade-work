<#import "*/page.ftl" as p>
<#import "/spring.ftl" as s>
<@p.page title="Grade&Work">
    <#if me??>
        <@p.navbar exit=true/>
    <#else>
        <@p.navbar/>
    </#if>
    <div class="d-flex flex-column justify-content-center my-auto mx-auto card-width">
        <div class="card">
            <h3 class="text-center"><@s.message 'landing.header'/></h3>
            <hr>
            <span><@s.message 'landing.first'/></span>
            <a class="btn btn-light w-100" href="/institutes"><@s.message 'landing.institutes'/></a>
            <span><@s.message 'landing.second'/></span>
            <a class="btn btn-light w-100" href="/search"><@s.message 'landing.search'/></a>
        </div>
    </div>
</@p.page>