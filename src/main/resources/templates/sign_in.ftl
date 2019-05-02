<#include "_html.ftl">

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

            <br>

            <label>
                Password *
                <input type="password" class="form-control" name="password">
            </label>
        </fieldset>

        <fieldset>
            <label>
                Remember me
                <input type="checkbox" name="remember-me">
            </label>

            <input type="submit" class="btn btn-primary">
        </fieldset>

        <p>
            Don't have an account?
            <a href="/sign_up">Create</a>
        </p>
    </form>
</#macro>

<@page></@page>
