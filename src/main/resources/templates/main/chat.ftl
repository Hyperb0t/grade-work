<#import "*/page.ftl" as p>
<#import "/spring.ftl" as s>
<#assign title>
    <@s.message 'chat.title'/> - Grade&Work
</#assign>
<@p.page title=title>
    <@p.navbar exit=true/>
    <script src="${rc.getContextPath()}/static/js/message-chat.js"></script>

    <div class="d-flex flex-column justify-content-center my-auto mx-auto card-width">
        <div class="card">

            <input id="csrf" type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

            <h3 class="regular text-center"><@s.message 'chat.header'/> <a href="/user/${user.id}" class="link bold">
                        <#if user.role == "ADMINISTRATION">
                            <@s.message 'chat.header.administration'/></a></h3>
                        <#else>
                            ${user.surname?upper_case} ${user.name?upper_case} ${user.middleName?upper_case}</a></h3>
                        </#if>
            <hr class="mb-2 mt-0">

            <div id="chat_window" class="my-2">
                <#if !messages?? || messages?size == 0>
                    <h4 class="mb-2" id="chat_empty"><@s.message 'chat.empty'/></h4>
                <#else>
                    <#list messages as message>
                        <div id="chat_message" class="mb-1">
                            <#if me.id == message.author.id>
                                <@p.message message=message author=me me=me/>
                            <#else>
                                <@p.message message=message author=user me=me/>
                            </#if>
                        </div>
                    </#list>
                </#if>
            </div>

            <form class="input-group d-inline-flex align-self-end">
                <input tabindex="1" type="text" class="form-control" id="message_content" name="content" placeholder="<@s.message 'chat.input.placeholder'/>">
                <input type="hidden" value="${pageId}" id="page_id">
                <div class="input-group-append">
                    <button tabindex="2" class="btn btn-outline-light ml-2" type="button" onclick="sendMessage()"><@s.message 'chat.button.send'/></button>
                </div>
            </form>

        </div>
    </div>
</@p.page>