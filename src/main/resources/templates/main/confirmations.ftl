<h4>Список компетенций, подлежащий подтверждению преподавателем</h4>
<h3>${teacher.getSurname()} ${teacher.getName()} ${teacher.getMiddleName()}</h3>
<form method="POST">
    <input id="csrf" type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <table class="table">
        <thead>
        <th scope="col">Фамилия</th>
        <th scope="col">Имя</th>
        <th scope="col">Отчество</th>
        <th scope="col">Группа</th>
        <th scope="col">Компетенция</th>
        <th scope="col">Да/Нет</th>
        </thead>
        <#list competences! as comp>
        <tr>
            <td>${comp.getStudent().getSurname()}</td>
            <td>${comp.getStudent().getName()}</td>
            <td>${comp.getStudent().getMiddleName()}</td>
            <td>${comp.getStudent().getGroup()}</td>
            <td>${comp.getCompetence().getName()}</td>
            <td>
                <label for="yes${comp.getStudent().getId()}_${comp.getCompetence().getId()}">Да</label>
                <input type="radio" name="${comp.getStudent().getId()}_${comp.getCompetence().getId()}" value="yes" id="yes${comp.getStudent().getId()}_${comp.getCompetence().getId()}">
                <label for="no${comp.getStudent().getId()}_${comp.getCompetence().getId()}">Нет</label>
                <input type="radio" name="${comp.getStudent().getId()}_${comp.getCompetence().getId()}" value="no" id="no${comp.getStudent().getId()}_${comp.getCompetence().getId()}">
            </td>
        </tr>
        </#list>
    </table>
    <input type="submit">
</form>