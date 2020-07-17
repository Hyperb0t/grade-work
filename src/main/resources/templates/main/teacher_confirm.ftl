<#import "*/page.ftl" as p>
<#import "/spring.ftl" as s>
<#assign title>
    <@s.message 'confirmation.teacher.title'/> - Grade&Work
</#assign>
<@p.page title=title>
    <@p.navbar exit=true/>
    <div class="d-flex flex-column justify-content-center my-auto mx-auto card-width">
        <div class="card">

            <h3 class="m-0 regular text-center mb-2"><@s.message 'confirmation.teacher.header'/></h3>
            <form method="POST" action="/confirm/competences">
                <input id="csrf" type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                <#if competences?? && competences?size gt 0>
                    <span class="text-center"><@s.message 'confirmation.teacher.hint'/></span>

                    <#list competences as competence>
                        <div class="input-group mb-2 w-100">
                            <div class="input-group-text d-flex flex-column w-90">
                                <div id="block"><a class="regular link" href="/user/${competence.student.id}">${competence.student.surname} ${competence.student.name} ${competence.student.middleName}, ${competence.student.group}</a></div>
                                <div>${competence.competence.name}</div>
                            </div>

                            <div class="btn-group btn-group-toggle d-flex w-10" data-toggle="buttons">
                                <label class="btn btn-light btn-light-sex-left btn-confirm w-50 d-flex align-items-center justify-content-center">
                                    <input type="radio" name="${competence.student.id}_${competence.competence.id}" value="yes" autocomplete="off" id="yes${competence.student.id}_${competence.competence.id}"><@s.message 'confirmation.teacher.yes'/>
                                </label>
                                <label class="btn btn-light btn-light-sex-right btn-confirm w-50 d-flex align-items-center justify-content-center">
                                    <input type="radio" name="${competence.student.id}_${competence.competence.id}" value="no" autocomplete="off" id="no${competence.student.id}_${competence.competence.id}"><@s.message 'confirmation.teacher.no'/>
                                </label>
                            </div>
                        </div>
                    </#list>

                    <button type="submit" class="btn btn-outline-light w-100"><@s.message 'confirmation.teacher.button'/></button>
                <#else>
                    <div class="bold link text-center"><@s.message 'confirmation.teacher.empty'/></div>

                    <a class="btn btn-outline-light mt-2 w-100" href="/user"><@s.message 'confirmation.teacher.back'/></a>
                </#if>
                
            </form>
        </div>
    </div>
</@p.page>