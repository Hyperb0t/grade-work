<#import "*/page.ftl" as p>
<#import "/spring.ftl" as s>
<#assign title>
    <@s.message 'sign.in.title'/> - Grade&Work
</#assign>
<@p.page title=title>
    <@p.navbar/>
    <div class="d-flex flex-column justify-content-center my-auto mx-auto card-width">
        <div class="card">
            <h3 class="m-0 regular text-center mb-2"><@s.message 'sign.in.header'/></h3>
            <form method="POST">
                <input id="csrf" type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                <#if error?? && error == "USER_NOT_FOUND">
                    <div class="alert alert-danger p-0 m-0 mb-2" role="alert">
                        <h5><@s.message 'sign.in.error'/></h5>
                    </div>
                </#if>

                <div class="input-group mb-2">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="login"><@s.message 'sign.login'/></span>
                    </div>
                    <input type="text" tabindex="1" class="form-control" name="login" aria-describedby="login" required>
                </div>

                <div class="input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="password"><@s.message 'sign.password'/></span>
                    </div>
                    <input type="password" tabindex="2" class="form-control" name="password" aria-describedby="password" required>
                </div>

                <div class="btn-group-toggle mt-2" data-toggle="buttons">
                    <label class="btn btn-light w-100 agree-button">
                        <input type="checkbox" tabindex="3" name="remember-me" autocomplete="off" value="true"><@s.message 'sign.in.remember.me'/>
                    </label>
                </div>

<#--                <div class="d-inline-flex mt-2 w-100" id="competence-1">-->
<#--                    <div class="input-group">-->
<#--                        <div class="input-group-prepend">-->
<#--                            <span class="input-group-text">Компетенция</span>-->
<#--                        </div>-->

<#--                        <select class="custom-select" name="competence-1">-->
<#--                            <option>Java</option>-->
<#--                            <option>С#</option>-->
<#--                        </select>-->
<#--                    </div>-->

<#--                    <button type="button" onclick="removeList(this)" class="btn btn-icon ml-2 d-flex justify-content-center align-items-center"><i class="fas fa-times"></i></button>-->
<#--                </div>-->

<#--                <button type="button" id="competence-adder" class="mt-2 btn btn-light w-100">Добавить компетенцию</button>-->

                <button type="submit" tabindex="4" class="btn btn-outline-light w-100 mt-2"><@s.message 'sign.in.submit'/></button>
            </form>
        </div>
    </div>
</@p.page>