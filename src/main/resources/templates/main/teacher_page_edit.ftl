<#import "*/page.ftl" as p>
<#import "/spring.ftl" as s>
<#assign title>
    <@s.message 'page.edit.title'/> - Grade&Work
</#assign>
<@p.page title=title>
    <@p.navbar exit=true/>
    <script src="${rc.getContextPath()}/static/js/add-list.js"></script>
    <script>
        window.onload = function () {
            $("#competence-adder").click(function () {
                addList("competence-");
            });
        }
    </script>
    <div class="d-flex flex-column justify-content-center my-auto mx-auto card-width">
        <div class="card">
            <h3 class="m-0 regular text-center mb-2"><@s.message 'page.edit.teacher.header'/></h3>
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
                    <input type="text" class="form-control" name="middleName" <#if user.middleName??>value="${user.middleName}"</#if> aria-describedby="middle-name" required>
                </div>

                <div class="input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="year-start"><@s.message 'sign.up.teacher.experience'/></span>
                    </div>
                    <input type="number" max="100" class="form-control" name="experience" <#if user.experience??>value="${user.experience}"</#if> aria-describedby="year-start" placeholder="<@s.message 'sign.up.teacher.experience.placeholder'/>" required>
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
                        <span class="input-group-text" id="position"><@s.message 'sign.up.teacher.position'/></span>
                    </div>
                    <input type="text" class="form-control" name="position" <#if user.position??>value="${user.position}"</#if> aria-describedby="position" required>
                </div>

                <div class="input-group mt-2">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="link"><@s.message 'sign.contact.link.kfu'/></span>
                    </div>
                    <input type="text" class="form-control" name="link" <#if user.link??>value="${user.link}"</#if> aria-describedby="link" required>
                </div>

                <span class="text-center"><@s.message 'sign.up.teacher.competences'/></span>

                <#assign index = 1>

                <#if user.competence?? && user.competence?size gt 0>
                    <#list user.competence as userCompetence>
                        <div class="input-group d-inline-flex mb-2 w-100" id="competence-${userCompetence?counter}">
                            <div class="input-group">
                                <div class="input-group-prepend">
                                    <span class="input-group-text"><@s.message 'sign.competence'/></span>
                                </div>

                                <select class="custom-select" name="competence-${userCompetence?counter}">
                                    <#list competences as competence>
                                        <option <#if competence == userCompetence.name>selected</#if>>${competence}</option>
                                    </#list>
                                </select>
                                <button type="button" onclick="removeList(this, 'competence-')" class="btn btn-icon ml-2 d-flex justify-content-center align-items-center"><i class="fas fa-times"></i></button>
                            </div>
                        </div>

                        <#if userCompetence?is_last>
                            <#assign index = (userCompetence?counter + 1)>
                        </#if>
                    </#list>
                </#if>

                <div class="input-group d-inline-flex mb-2 w-100" id="competence-${index}">
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text"><@s.message 'sign.competence'/></span>
                        </div>

                        <select class="custom-select" name="competence-${index}">
                            <#list competences as competence>
                                <option>${competence}</option>
                            </#list>
                        </select>
                        <button type="button" onclick="removeList(this, 'competence-')" class="btn btn-icon ml-2 d-flex justify-content-center align-items-center"><i class="fas fa-times"></i></button>
                    </div>
                </div>

                <button type="button" id="competence-adder" class="btn btn-light w-100"><@s.message 'sign.competence.button'/></button>

                <button type="submit" class="btn btn-outline-light w-100 mt-2"><@s.message 'page.edit.button.submit'/></button>
            </form>
        </div>
    </div>
</@p.page>