<#import "*/page.ftl" as p>
<#import "/spring.ftl" as s>
<#assign employer_title>
    <@s.message 'page.employer.title'/> - Grade&Work
</#assign>
<#assign me_title>
    <@s.message 'page.my.title'/> - Grade&Work
</#assign>
<#if me?? && me.id == employer.id>
    <#assign title = me_title>
<#else>
    <#assign title = employer_title>
</#if>
<@p.page title=title>
    <#if me??>
        <@p.navbar exit=true/>
    <#else>
        <@p.navbar/>
    </#if>
    <div class="d-flex flex-column justify-content-center my-auto mx-auto card-width">
        <div class="card">
            <h3 class="text-center"><@s.message 'page.employer.header'/></h3>
            <hr>
            <span><@s.message 'page.employer.organisation'/><text class="bold"> ${employer.companyName}</text></span>
            <span><@s.message 'page.employer.psrn'/><text class="bold"> ${employer.psrn}</text></span>
            <span><@s.message 'page.employer.juridical.person'/><text class="bold"> ${employer.surname} ${employer.name} ${employer.middleName}</text></span>
            <hr>
            <span class="bold"><@s.message 'sign.contacts'/></span>
            <span><@s.message 'sign.contact.email'/>: ${employer.email}</span>
            <span><@s.message 'sign.contact.phone'/>: ${employer.phone}</span>
            <hr>
            <#if me?? && me.id == employer.id>
                <a class="btn btn-outline-light mt-2" href="/applications"><@s.message 'page.employer.applications'/></a>
                <a class="btn btn-light mt-2" href="/edit"><@s.message 'page.employer.info.edit'/></a>
            <#else>
                <a class="btn btn-outline-light mt-2" href="/chat/${employer.id}"><@s.message 'page.chat'/></a>
                <a class="btn btn-outline-light mt-2" href="/apply/${employer.id}"><@s.message 'page.employer.apply'/></a>
            </#if>
        </div>
    </div>
</@p.page>