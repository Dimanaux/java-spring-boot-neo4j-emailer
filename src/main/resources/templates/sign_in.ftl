<#include "_application.ftl">

<#macro page_title>Sign in</#macro>

<#macro page_body>
    <form method="post">
        <fieldset>
            <legend>Sign In</legend>
            <#if RequestParameters.error??>
                <h1>Неправильный логин или пароль</h1>
            </#if>

            <label>
                Email *
                <input type="email" class="form-control" name="email"/>
            </label>

            <label>
                Password *
                <input type="password" class="form-control" name="password">
            </label>
        </fieldset>

        <fieldset>
            <input type="submit" class="btn btn-primary">
        </fieldset>
    </form>
</#macro>

<@page></@page>
