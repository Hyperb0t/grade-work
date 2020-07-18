<#import "*/page.ftl" as p>
<#import "/spring.ftl" as s>
<#assign title>
    <@s.message 'page.error.title'/> - Grade&Work
</#assign>
<@p.page title=title>
    <@p.navbar account=false />
    <div class="d-flex flex-column justify-content-center my-auto mx-auto card-width">
        <div class="card">
            <h3 class="m-0 regular text-center"><@s.message 'page.error.header'/> ${code}</h3>
            <hr>
            <div class="alert alert-danger p-0 m-0 mt-2" role="alert">
                <h5><@s.message 'page.error.info'/></h5>
            </div>
        </div>
    </div>
</@p.page>