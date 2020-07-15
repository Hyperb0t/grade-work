<#import "*/page.ftl" as p>
<#import "/spring.ftl" as s>
<#assign title>
    <@s.message 'sign.up.student.title'/> - Grade&Work
</#assign>
<@p.page title=title>
    <@p.navbar exit=true/>
    <div class="d-flex flex-column justify-content-center my-auto mx-auto card-width">
        <div class="card">
            <h3 class="m-0 regular text-center mb-2"><@s.message 'sign.up.student.header'/></h3>
            <form method="POST" action="/signUp/student">
                <input id="csrf" type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                <div class="input-group mb-2">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="login"><@s.message 'sign.login'/></span>
                    </div>
                    <input type="text" class="form-control" name="login" aria-describedby="login" placeholder="<@s.message 'sign.login.placeholder'/>" required>
                </div>

                <div class="input-group mb-2">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="password"><@s.message 'sign.password'/></span>
                    </div>
                    <input type="password" minlength="8" class="form-control" name="password" aria-describedby="password" placeholder="<@s.message 'sign.password.placeholder'/>" required>
                </div>

                <div class="input-group mb-2">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="password-repeat"><@s.message 'sign.password.repeat'/></span>
                    </div>
                    <input type="password" class="form-control" minlength="8" name="passwordRepeat" aria-describedby="password-repeat" placeholder="<@s.message 'sign.password.repeat.placeholder'/>" required>
                </div>

                <div class="input-group mb-2">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="name"><@s.message 'sign.name'/></span>
                    </div>
                    <input type="text" class="form-control" name="name" aria-describedby="name" required>
                </div>

                <div class="input-group mb-2">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="surname"><@s.message 'sign.surname'/></span>
                    </div>
                    <input type="text" class="form-control" name="surname" aria-describedby="surname" required>
                </div>

                <div class="input-group mb-2">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="middle-name"><@s.message 'sign.middle.name'/></span>
                    </div>
                    <input type="text" class="form-control" name="middleName" aria-describedby="middle-name" required>
                </div>

                <div class="input-group mb-2">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="birth"><@s.message 'sign.birth'/></span>
                    </div>
                    <input type="date" class="form-control" name="birth" aria-describedby="birth" required>
                </div>

                <div class="input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="year-start"><@s.message 'sign.up.student.year.start'/></span>
                    </div>
                    <input type="number" min="1970" class="form-control" name="yearStart" aria-describedby="year-start" placeholder="<@s.message 'sign.up.student.year.placeholder'/>" required>
                </div>

                <div class="input-group mt-2">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="year-graduate"><@s.message 'sign.up.student.year.graduate'/></span>
                    </div>
                    <input type="number" min="1973" class="form-control" name="yearGraduate" aria-describedby="year-graduate" placeholder="<@s.message 'sign.up.student.year.placeholder'/>" required>
                </div>

                <div class="input-group mt-2">
                    <div class="input-group-prepend">
                        <span class="input-group-text"><@s.message 'sign.institute'/></span>
                    </div>

                    <select class="custom-select" name="institute">
                        <#list institutes as institute>
                            <option>${institute}</option>
                        </#list>
                    </select>
                </div>

                <div class="input-group mt-2">
                    <div class="input-group-prepend">
                        <span class="input-group-text"><@s.message 'sign.up.student.faculty'/></span>
                    </div>

                    <select class="custom-select" name="faculty">
                        <#list faculties as faculty>
                            <option>${faculty}</option>
                        </#list>
                    </select>
                </div>

                <div class="input-group mt-2">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="group"><@s.message 'sign.up.student.group'/></span>
                    </div>
                    <input type="text" class="form-control" name="group" aria-describedby="group" required>
                </div>

                <div class="input-group mt-2">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="link"><@s.message 'sign.contact.link.kfu'/></span>
                    </div>
                    <input type="text" class="form-control" name="link" aria-describedby="link" required>
                </div>

                <div class="input-group mt-2">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="average"><@s.message 'sign.up.student.average'/></span>
                    </div>
                    <input type="text" class="form-control" name="average" aria-describedby="average">
                </div>

                <button type="submit" class="btn btn-outline-light w-100 mt-2"><@s.message 'sign.up.student.submit'/></button>
            </form>
        </div>
    </div>
</@p.page>