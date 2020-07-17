<#import "*/page.ftl" as p>
<#import "/spring.ftl" as s>
<#assign title>
    <@s.message 'sign.up.student.file.title'/> - Grade&Work
</#assign>
<@p.page title=title>
    <@p.navbar exit=true/>
    <div class="d-flex flex-column justify-content-center my-auto mx-auto card-width">
        <div class="card">

            <h3 class="m-0 regular text-center mb-2"><@s.message 'sign.up.student.file.header'/></h3>
            <form method="POST" action="/signUp/student/file?${_csrf.parameterName}=${_csrf.token}" enctype="multipart/form-data">

                <span class="text-center"><@s.message 'sign.up.student.file.hint'/></span>

                <table class="m-0 mb-2 w-100">
                    <tr>
                        <th scope="col" class="main-table"><@s.message 'sign.name'/></th>
                        <th scope="col" class="main-table"><@s.message 'sign.surname'/></th>
                        <th scope="col" class="main-table"><@s.message 'sign.middle.name'/></th>
                        <th scope="col" class="main-table"><@s.message 'sign.login'/></th>
                        <th scope="col" class="main-table"><@s.message 'sign.contact.email'/></th>
                        <th scope="col" class="main-table"><@s.message 'sign.contact.phone'/></th>
                        <th scope="col" class="main-table"><@s.message 'sign.contact.link'/></th>
                        <th scope="col" class="main-table"><@s.message 'sign.birth'/></th>
                    </tr>
                    <tr>
                        <th scope="col" class="main-table"><@s.message 'sign.up.student.file.bio'/></th>
                        <th scope="col" class="main-table"><@s.message 'sign.institute'/></th>
                        <th scope="col" class="main-table"><@s.message 'sign.up.student.faculty'/></th>
                        <th scope="col" class="main-table"><@s.message 'sign.up.student.file.year.start'/></th>
                        <th scope="col" class="main-table"><@s.message 'sign.up.student.file.year.graduate'/></th>
                        <th scope="col" class="main-table"><@s.message 'sign.up.student.course'/></th>
                        <th scope="col" class="main-table"><@s.message 'sign.up.student.group'/></th>
                        <th scope="col" class="main-table"><@s.message 'sign.up.student.average'/></th>
                    </tr>
                </table>

                <div class="input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="file"><@s.message 'sign.file'/></span>
                    </div>

                    <input type="file" class="form-control" name="file" accept=".xls, .xlsx" aria-describedby="file" required>
                </div>

                <button type="submit" class="btn btn-outline-light w-100 mt-2"><@s.message 'sign.up.student.file.button'/></button>
            </form>
        </div>
    </div>
</@p.page>