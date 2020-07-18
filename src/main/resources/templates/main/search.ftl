<#import "*/page.ftl" as p>
<#import "/spring.ftl" as s>
<#assign title>
    <@s.message 'search.title'/> - Grade&Work
</#assign>
<@p.page title=title>
    <#if me??>
        <@p.navbar exit=true/>
    <#else>
        <@p.navbar/>
    </#if>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/js/select2.min.js"></script>
    <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/css/select2.min.css">

    <div class="d-flex flex-column justify-content-center my-auto mx-auto card-width">
        <div class="card">
            <h3 class="m-0 regular text-center mb-2"><@s.message 'search.header'/></h3>

            <form method="GET">

                <div class="d-inline-flex w-100">
                    <div class="d-flex flex-column">
                        <div class="input-group d-inline-flex">
                            <div class="input-group-prepend">
                                <span class="input-group-text"><@s.message 'resume.competences'/></span>
                            </div>

                            <select id="search-multi" class="js-states form-control" multiple="multiple" name="c">
                                <#list competences as comp>
                                    <option <#if selectedComp?seq_contains(comp.getId())>selected</#if> value="${comp.getId()}">${comp.getName()}</option>
                                </#list>
                            </select>
                        </div>

                        <div class="input-group d-inline-flex">
                            <div class="input-group-prepend">
                                <span class="input-group-text border-top-none"><@s.message 'sign.institute'/></span>
                            </div>

                            <select id="institute" class="js-states form-control input-group" name="i">
                                <option <#if selectedInst == -1>selected</#if> value="-1"><@s.message 'search.any'/></option>
                                <#list institutes as inst>
                                    <option <#if selectedInst == inst.getId()>selected</#if> value="${inst.getId()}">${inst.getName()}</option>
                                </#list>
                            </select>

                            <div class="input-group-prepend">
                                <span class="input-group-text border-top-none border-left-none"><@s.message 'sign.up.student.faculty'/></span>
                            </div>

                            <select id="faculty" class="js-states form-control input-group" name="f">
                                <option <#if selectedFac == -1>selected</#if> value="-1"><@s.message 'search.any'/></option>
                                <#list faculties as fac>
                                    <option <#if selectedFac == fac.getId()>selected</#if>value="${fac.getId()}">${fac.getName()}</option>
                                </#list>
                            </select>
                        </div>
                    </div>

                    <button type="submit" class="ml-2 btn btn-outline-light"><@s.message 'search.find'/></button>
                </div>

            </form>
        </div>

        <#if students?? && students?size gt 0>
            <div class="card">

                <#list students as st>
                    <a href="/user/${st.id}" class="search-block link d-flex flex-column w-100<#if !st?is_last> mb-2</#if>">
                        <div class="bold act-black">${st.surname} ${st.name} ${st.middleName}, ${st.group}</div>
                        <div class="regular act-black">${st.institute.name}, ${st.faculty.name}</div>
                        <div class="bold main-blue">
                            <#list st.competences as comp>
                                <#if comp.confirmed>
                                    ${comp.competence.name}<#if !comp?has_next>, </#if>
                                </#if>
                            </#list>
                        </div>
                    </a>
                </#list>

            </div>
        </#if>

    </div>

    <script type="text/javascript">
        $('#search-multi').select2();
        $('#search-multi').on('select2:opening select2:closing', function (event) {
            var $searchfield = $(this).parent().find('.select2-search__field');
            $searchfield.prop('disabled', true);
        });

        $('#institute').select2();
        $('#institute').on('select2:opening select2:closing', function (event) {
            var $searchfield = $(this).parent().find('.select2-search__field');
            $searchfield.prop('disabled', true);
        });

        $('#faculty').select2();
        $('#faculty').on('select2:opening select2:closing', function (event) {
            var $searchfield = $(this).parent().find('.select2-search__field');
            $searchfield.prop('disabled', true);
        });
    </script>
    <style type="text/css">
        .select2-container .select2-selection--single {
            height: 44.8px;
            border-radius: 0;
            border-width: 3px;
            border-color: var(--main-blue);
            border-top-style: none;
        }
        .select2-container .select2-selection--multiple {
            height: 44.8px;
            border-radius: 0;
            border-width: 3px;
            border-color: var(--main-blue);
        }
        .select2-container--open {
            border-width: 3px;
            border-color: #43b5e5;
        }
        .select2-container *:focus {
            border-width: 3px;
            border-color: #43b5e5;
        }
    </style>
</@p.page>