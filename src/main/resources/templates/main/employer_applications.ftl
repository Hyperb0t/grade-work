<#import "*/page.ftl" as p>
<#import "/spring.ftl" as s>
<#assign title>
    <@s.message 'page.employer.applies.title'/> - Grade&Work
</#assign>
<@p.page title=title>
    <@p.navbar exit=true/>

    <div class="d-flex flex-column justify-content-center my-auto mx-auto card-width">
        <div class="card">
            <h3 class="m-0 regular text-center mb-2"><@s.message 'page.employer.applies.header'/></h3>
            <span class="regular"><@s.message 'page.employer.applies.hint'/></span>
            <hr>
            <form method="POST" action="/applications">
                <input id="csrf" type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                <span class="regular"><@s.message 'page.employer.applies.unread'/></span>

                <#list unreadApplications as appl>
                    <div class="input-group mb-2 w-100">
                        <div class="input-group-text d-flex flex-column w-90">
                            <a class="regular link" href="/user/${appl.student.id}">${appl.student.surname} ${appl.student.name} ${appl.student.middleName}</a>
                        </div>

                        <div class="btn-group btn-group-toggle d-flex w-10" data-toggle="buttons">
                            <label class="btn btn-light btn-light-sex-right btn-confirm w-50 d-flex align-items-center justify-content-center">
                                <input type="checkbox" class="custom-checkbox" name="${appl.id}"><@s.message 'page.employer.applies.mark.read'/>
                            </label>
                        </div>
                    </div>
                </#list>

                <hr>
                <span class="regular"><@s.message 'page.employer.applies.read'/></span>

                <#list readApplications as appl>
                    <div class="input-group mb-2 w-100">
                        <div class="input-group-text d-flex flex-column w-100">
                            <a class="regular link" href="/user/${appl.student.id}">${appl.student.surname} ${appl.student.name} ${appl.student.middleName}</a>
                        </div>
                    </div>
                </#list>

                <button type="submit" class="btn btn-outline-light w-100"><@s.message 'page.employer.applies.submit'/></button>

            </form>
        </div>
    </div>
</@p.page>