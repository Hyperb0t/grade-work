<#import "*/page.ftl" as p>
<#import "/spring.ftl" as s>
<@p.page title="Поиск">
    <#if me??>
        <@p.navbar exit=true/>
    <#else>
        <@p.navbar/>
    </#if>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/js/select2.min.js"></script>
    <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/css/select2.min.css">
    <div class="">
        <div class="card mx-3">
        <h2 class="my-2 regular text-center">Поиск студентов</h2>
        <form class="my-3 row mx-3">
            <div class="col-4">
                <h5>Компетенции</h5>
                <select id="search-multi" class="js-states form-control pb-5 input-group" multiple="multiple" name="c">
                    <#list competences as comp>
                        <option <#if selectedComp?seq_contains(comp.getId())>selected</#if> value="${comp.getId()}">${comp.getName()}</option>
                    </#list>
                </select>
            </div>
            <div class="col-3">
                <h5>Институт</h5>
                <select id="institute" class="js-states form-control input-group" name="i">
                    <option <#if selectedInst == -1>selected</#if> value="-1">Любой</option>
                    <#list institutes as inst>
                        <option <#if selectedInst == inst.getId()>selected</#if> value="${inst.getId()}">${inst.getName()}</option>
                    </#list>
                </select>
            </div>
            <div class="col-3">
                <h5>Специальность</h5>
                <select id="faculty" class="js-states form-control input-group" name="f">
                    <option <#if selectedFac == -1>selected</#if> value="-1">Любой</option>
                    <#list faculties as fac>
                        <option <#if selectedFac == fac.getId()>selected</#if>value="${fac.getId()}">${fac.getName()}</option>
                    </#list>
                </select>
            </div>
            <input type="submit" value="Найти" class="btn btn-outline-light col-2 h-50 align-self-end">
        </form>
        </div>
        <div class=" mx-auto justify-content-between">
            <#list students as st>
                <div class="row border my-2 mx-3">
                    <div class="d-flex col-3 col justify-content-center ">
                        <p class="font-weight-bold">${st.getSurname()} ${st.getName()}</p>
                    </div>
                    <div class="col-3">
                        <div>${st.getInstitute().getName()}</div>
                        <div>${st.getFaculty().getName()}</div>
                        <div>${st.getGroup()}</div>
                        <div>${st.getCourse()} курс</div>
                    </div>
                    <div class="col-3">
                        <ul>
                            <#list st.competences as comp>
                                <#if comp.getConfirmed()>
                                <li>${comp.getCompetence().getName()}</li>
                                </#if>
                            </#list>
                        </ul>
                    </div>
                    <div class="col-3">
                        <ul class="list-group list-group-flush">
                            <#list st.projects as proj>
                            <li class="list-group-item">
                                <a href="${proj.getLink()}">${proj.getName()}</a>
                            </li>
                            </#list>
                        </ul>
                    </div>
                </div>
            </#list>
        </div>
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
        .select2-container--focus {
            border-width: 3px;
            border-color: #43b5e5;
        }
    </style>
</@p.page>