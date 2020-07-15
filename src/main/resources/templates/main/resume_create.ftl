<#import "*/page.ftl" as p>
<#import "/spring.ftl" as s>
<#assign title>
    <@s.message 'resume.create.title'/> - Grade&Work
</#assign>
<@p.page title=title>
    <@p.navbar exit=true/>
    <script src="${rc.getContextPath()}/static/js/add-list.js"></script>
    <script>
        window.onload = function () {
            $("#competence-adder").click(function () {
                addList("competence-");
            });
            $("#project-adder").click(function () {
                addList("project-");
            });
        }
    </script>
    <div class="d-flex flex-column justify-content-center my-auto mx-auto card-width">
        <div class="card">
            <h3 class="m-0 regular text-center mb-2"><@s.message 'resume.create.header'/></h3>
            <form method="POST">
                <input id="csrf" type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                <span class="text-center"><@s.message 'resume.competences'/></span>

                <div class="input-group d-inline-flex mb-2 w-100" id="competence-1">
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text"><@s.message 'sign.competence'/></span>
                        </div>

                        <select class="custom-select" name="competence-1">
                            <#list competences as competence>
                                <option>${competence}</option>
                            </#list>
                        </select>
                        <button type="button" onclick="removeList(this, 'competence-')" class="btn btn-icon ml-2 d-flex justify-content-center align-items-center"><i class="fas fa-times"></i></button>
                    </div>
                </div>

                <button type="button" id="competence-adder" class="btn btn-light w-100"><@s.message 'sign.competence.button'/></button>

                <span class="text-center"><@s.message 'sign.contacts'/></span>

                <div class="input-group mb-2">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="phone"><@s.message 'sign.contact.phone'/></span>
                    </div>
                    <input type="text" class="form-control" name="phone" aria-describedby="phone" required>
                </div>

                <div class="input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="email"><@s.message 'sign.contact.email'/></span>
                    </div>
                    <input type="email" class="form-control" name="email" aria-describedby="email" required>
                </div>

                <span class="text-center"><@s.message 'resume.projects'/></span>

                <div class="input-group d-inline-flex mb-2 w-100" id="project-1">
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text"><@s.message 'resume.project'/></span>
                        </div>
                        <input type="text" class="form-control right-border-none" name="name-project-1" placeholder="<@s.message 'resume.project.name'/>" aria-describedby="project-1">
                        <input type="text" class="form-control" name="link-project-1" placeholder="<@s.message 'resume.project.link'/>" aria-describedby="project-1">

                        <button type="button" onclick="removeList(this, 'project-')" class="btn btn-icon ml-2 d-flex justify-content-center align-items-center"><i class="fas fa-times"></i></button>
                    </div>
                </div>
                <button type="button" id="project-adder" class="btn btn-light w-100"><@s.message 'resume.project.button'/></button>

                <button type="submit" class="btn btn-outline-light w-100 mt-2"><@s.message 'resume.submit'/></button>
            </form>
        </div>
    </div>
</@p.page>