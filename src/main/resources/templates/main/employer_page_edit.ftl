<#import "*/page.ftl" as p>
<#import "/spring.ftl" as s>
<#assign title>
    <@s.message 'page.edit.title'/> - Grade&Work
</#assign>
<@p.page title=title>
    <@p.navbar />
    <div class="d-flex flex-column justify-content-center my-auto mx-auto card-width">
        <div class="card">
            <h3 class="m-0 regular text-center mb-2"><@s.message 'page.edit.employer.header'/></h3>
            <form method="POST" action="/user/${user.id}/edit">
                <input id="csrf" type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                <div class="input-group mb-2">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="organisation-name"><@s.message 'sign.up.employer.organisation.name'/></span>
                    </div>
                    <input type="text" class="form-control" name="companyName" <#if user.companyName??>value="${user.companyName}"</#if> aria-describedby="organisation-name" placeholder="организации" required>
                </div>

                <div class="input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="psrn"><@s.message 'sign.up.employer.psrn'/></span>
                    </div>
                    <input type="number" min="1000000000000" max="9999999999999" class="form-control" <#if user.psrn??>value="${user.psrn}"</#if> name="psrn" aria-describedby="psrn" required>
                </div>

                <span class="text-center"><@s.message 'sign.up.employer.juridical.person'/></span>

                <div class="input-group mb-2">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="name"><@s.message 'sign.name'/></span>
                    </div>
                    <input type="text" class="form-control" name="name" <#if user.name??>value="${user.name}"</#if> aria-describedby="name" required>
                </div>

                <div class="input-group mb-2">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="surname"><@s.message 'sign.surname'/></span>
                    </div>
                    <input type="text" class="form-control" name="surname" <#if user.surname??>value="${user.surname}"</#if> aria-describedby="surname" required>
                </div>

                <div class="input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="middle-name"><@s.message 'sign.middle.name'/></span>
                    </div>
                    <input type="text" class="form-control" name="middleName" <#if user.middleName??>value="${user.middleName}"</#if> aria-describedby="middle-name" required>
                </div>

                <span class="text-center"><@s.message 'sign.contacts'/></span>

                <div class="input-group mb-2">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="phone"><@s.message 'sign.contact.phone'/></span>
                    </div>
                    <input type="text" class="form-control" name="phone" <#if user.phone??>value="${user.phone}"</#if> aria-describedby="phone" required>
                </div>

                <div class="input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="email"><@s.message 'sign.contact.email'/></span>
                    </div>
                    <input type="email" class="form-control" name="email" <#if user.email??>value="${user.email}"</#if> aria-describedby="email" required>
                </div>

                <button type="submit" class="btn btn-outline-light w-100 mt-2"><@s.message 'page.edit.button.submit'/></button>
            </form>
        </div>
    </div>
</@p.page>