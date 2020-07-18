<#import "*/page.ftl" as p>
<#import "/spring.ftl" as s>
<#assign title>
    <@s.message 'sign.up.employer.title'/> - Grade&Work
</#assign>
<@p.page title=title>
    <@p.navbar />
    <script src="${rc.getContextPath()}/static/js/add-list.js"></script>
    <script>
        window.onload = function () {
            $("#link-adder").click(function () {
                addList("link-");
            });
        }
    </script>
    <div class="d-flex flex-column justify-content-center my-auto mx-auto card-width">
        <div class="card">
            <h3 class="m-0 regular text-center mb-2"><@s.message 'sign.up.employer.header'/></h3>
            <form method="POST" action="/signUp">
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
                        <span class="input-group-text" id="organisation-name"><@s.message 'sign.up.employer.organisation.name'/></span>
                    </div>
                    <input type="text" class="form-control" name="organisationName" aria-describedby="organisation-name" placeholder="организации" required>
                </div>

                <div class="input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="psrn"><@s.message 'sign.up.employer.psrn'/></span>
                    </div>
                    <input type="number" min="1000000000000" max="9999999999999" class="form-control" name="psrn" aria-describedby="psrn" required>
                </div>

                <span class="text-center"><@s.message 'sign.up.employer.juridical.person'/></span>

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

                <div class="input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="middle-name"><@s.message 'sign.middle.name'/></span>
                    </div>
                    <input type="text" class="form-control" name="middleName" aria-describedby="middle-name" required>
                </div>

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

                <span class="text-center"><@s.message 'sign.contacts.social.links'/></span>

                <div class="input-group d-inline-flex mb-2 w-100" id="link-1">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="link-1"><@s.message 'sign.contact.link'/></span>
                    </div>
                    <input type="text" class="form-control" name="link-1" aria-describedby="link-1">

                    <button type="button" onclick="removeList(this, 'link-')" class="btn btn-icon ml-2 d-flex justify-content-center align-items-center"><i class="fas fa-times"></i></button>
                </div>

                <button type="button" id="link-adder" class="btn btn-light w-100"><@s.message 'sign.contact.link.button'/></button>

                <div class="btn-group-toggle mt-2" data-toggle="buttons">
                    <label class="btn btn-light w-100 agree-button">
                        <input type="checkbox" name="agreement" required autocomplete="off" value="true"><@s.message 'sign.agreement'/>
                    </label>
                </div>

                <button type="submit" class="btn btn-outline-light w-100 mt-2"><@s.message 'sign.up.submit'/></button>
            </form>
        </div>
    </div>
</@p.page>