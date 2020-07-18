<#import "*/page.ftl" as p>
<#import "/spring.ftl" as s>
<@p.page title="Заявки">
    <#if me??>
        <@p.navbar exit=true/>
    <#else>
        <@p.navbar/>
    </#if>
    <form class="justify-content-center" method="post">
        <input id="csrf" type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <h4>Непрочитанные</h4>
        <#list unreadApplications as appl>
            <div class="card border m-2 px-2 col-8 justify-content-between row">
                <a href="/user/${appl.getStudent().getId()}">
                    <p>${appl.getStudent().getSurname()} ${appl.getStudent().getName()}</p>
                </a>
                <label for="${appl.getId()}">Прочитано</label>
                <input type="checkbox" name="${appl.getId()}">
            </div>
        </#list>
        <h4>Прочитанные</h4>
        <#list readApplications as appl>
            <div class="card border m-2 px-2 col-8 justify-content-between">
                <a href="/user/${appl.getStudent().getId()}">
                    <p>${appl.getStudent().getSurname()} ${appl.getStudent().getName()}</p>
                </a>
<#--                <label for="${appl.getId()}">Прочитано</label>-->
<#--                <input type="checkbox" name="${appl.getId()}">-->
            </div>
        </#list>
        <input type="submit" value="Отметить прочитанные" class="btn btn-outline-light">
    </form>
</@p.page>