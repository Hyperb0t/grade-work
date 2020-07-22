<#import "*/page.ftl" as p>
<#import "/spring.ftl" as s>
<#assign title>
    <@s.message 'page.edit.title'/> - Grade&Work
</#assign>
<@p.page title=title>
    <@p.navbar exit=true/>
    <div class="d-flex flex-column justify-content-center my-auto mx-auto card-width">
        <div class="card">
            <h3 class="m-0 regular text-center mb-2"><@s.message 'page.edit.student.header'/></h3>
            <form method="POST" action="/user/${user.id}/edit">
                <input id="csrf" type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                <div class="input-group mb-2">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="name"><@s.message 'sign.name'/></span>
                    </div>
                    <input type="text" class="form-control" name="name" aria-describedby="name" <#if user.name??>value="${user.name}"</#if> required>
                </div>

                <div class="input-group mb-2">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="surname"><@s.message 'sign.surname'/></span>
                    </div>
                    <input type="text" class="form-control" name="surname" aria-describedby="surname" <#if user.surname??>value="${user.surname}"</#if> required>
                </div>

                <div class="input-group mb-2">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="middle-name"><@s.message 'sign.middle.name'/></span>
                    </div>
                    <input type="text" class="form-control" name="middleName" aria-describedby="middle-name" <#if user.middleName??>value="${user.middleName}"</#if> required>
                </div>

                <div class="input-group mb-2">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="birth"><@s.message 'sign.birth'/></span>
                    </div>
                    <input type="date" class="form-control" name="birth" aria-describedby="birth" <#if user.birthday??>value="${user.birthday?string['yyyy-MM-dd']}"</#if> required>
                </div>

                <div class="input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="year-start"><@s.message 'sign.up.student.year.start'/></span>
                    </div>
                    <input type="number" min="1970" class="form-control" name="yearStart" aria-describedby="year-start" <#if user.yearStart??>value="${user.yearStart?c}"</#if> placeholder="<@s.message 'sign.up.student.year.placeholder'/>" required>
                </div>

                <div class="input-group mt-2">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="year-graduate"><@s.message 'sign.up.student.year.graduate'/></span>
                    </div>
                    <input type="number" min="1973" class="form-control" name="yearGraduate" aria-describedby="year-graduate" <#if user.yearGraduate??>value="${user.yearGraduate?c}"</#if> placeholder="<@s.message 'sign.up.student.year.placeholder'/>" required>
                </div>

                <div class="input-group mt-2">
                    <div class="input-group-prepend">
                        <span class="input-group-text"><@s.message 'sign.institute'/></span>
                    </div>

                    <select class="custom-select" name="institute">
                        <#list institutes as institute>
                            <option <#if user.institute.name == institute>selected</#if>>${institute}</option>
                        </#list>
                    </select>
                </div>

                <div class="input-group mt-2">
                    <div class="input-group-prepend">
                        <span class="input-group-text"><@s.message 'sign.up.student.faculty'/></span>
                    </div>

                    <select class="custom-select" name="faculty">
                        <#list faculties as faculty>
                            <option <#if user.faculty.name == faculty>selected</#if>>${faculty}</option>
                        </#list>
                    </select>
                </div>

                <div class="input-group mt-2">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="group"><@s.message 'sign.up.student.group'/></span>
                    </div>
                    <input type="text" class="form-control" name="group" <#if user.group??>value="${user.group}"</#if> aria-describedby="group" required>
                </div>

                <div class="input-group mt-2">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="link"><@s.message 'sign.contact.link.kfu'/></span>
                    </div>
                    <input type="text" class="form-control" name="link" <#if user.link??>value="${user.link}"</#if> aria-describedby="link" required>
                </div>

                <div class="input-group mt-2">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="average"><@s.message 'sign.up.student.average'/></span>
                    </div>
                    <input type="text" class="form-control" name="average" <#if user.average??>value="${user.average}"</#if> aria-describedby="average">
                </div>

                <button type="submit" class="btn btn-outline-light w-100 mt-2"><@s.message 'page.edit.button.submit'/></button>
            </form>
        </div>
    </div>
</@p.page>