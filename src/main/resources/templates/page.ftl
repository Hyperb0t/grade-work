<#import "/spring.ftl" as s>
<#macro page title>
    <!doctype html>
    <html lang="ru">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">

        <link rel="stylesheet" href="${rc.getContextPath()}/static/css/bootstrap.min.css">
        <link rel="stylesheet" href="${rc.getContextPath()}/static/css/fontawesome/css/all.css">
        <link rel="stylesheet" href="${rc.getContextPath()}/static/css/every.css" type="text/css">

        <title>${title}</title>
    </head>
    <body>
    <script src="${rc.getContextPath()}/static/js/jquery-3.4.1.min.js"></script>
    <script src="${rc.getContextPath()}/static/js/popper.min.js"></script>
    <script src="${rc.getContextPath()}/static/js/bootstrap.min.js"></script>
    <#nested/>
    </body>
    </html>
</#macro>

<#macro navbar exit=false account=true>
    <nav class="navbar sticky-top p-0 d-flex">
        <div class="navbar-brand d-inline-flex m-0">
            <a class="d-inline-flex nav-logo" href="/">
                <img class="ml-2 kfu-logo align-self-center" src="${rc.getContextPath()}/static/defaults/kfu-logo-true.png" alt="<@s.message 'img.alt.kfu.logo'/>">
                <span>Grade&Work</span>
            </a>
            <div class="align-self-center">
                <a class="pl-2 nav-part" href="/institutes"><i class="fas fa-university icon"></i><@s.message 'navbar.institutes'/></a>
                <a class="pl-2 nav-part" href="/search"><i class="fas fa-search act-white icon"></i><@s.message 'navbar.search'/></a>
                <a class="pl-2 nav-part" href="/employers"><i class="fas fa-briefcase act-white icon"></i><@s.message 'navbar.employers'/></a>
            </div>
        </div>
        <#if account>
            <div class="d-inline-flex m-2">
                <#if exit == false>
                    <a class="btn btn-light nav-sign-in" href="/signIn"><@s.message 'navbar.sign.in'/></a>
                    <span class="nav-or mx-2"><@s.message 'navbar.or'/></span>
                    <a class="btn btn-outline-light nav-employee" href="/signUp"><@s.message 'navbar.sign.up'/></a>
                <#else>
                    <a class="btn btn-light mr-2" href="/user"><@s.message 'navbar.user'/></a>
                    <a class="btn btn-outline-light nav-employee" href="/signOut"><@s.message 'navbar.sign.out'/></a>
                </#if>
            </div>
        </#if>
    </nav>
</#macro>

<#macro message message author me>
    <div class="d-flex">

        <div class="d-inline-flex justify-content-between w-100">
            <div class="d-flex flex-column w-90 mr-2">
                <a href="/user/${message.author.id}" class="link regular">
                    <#if me.id == author.id>
                        Вы</a>
                    <#else>
                        <#if author.role == "ADMINISTRATION">
                            Деканат</a>
                        <#else>
                            ${author.surname} ${author.name} ${author.middleName}</a>
                        </#if>
                    </#if>
                <span class="m-0">${message.content}</span>
            </div>
            <span class="w-10 add-grey align-self-center">${message.createdAt}</span>
        </div>
    </div>
</#macro>