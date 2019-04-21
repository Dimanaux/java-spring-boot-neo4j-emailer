<#include "_application.ftl">

<#macro page_title>Sign up</#macro>

<#macro page_body>
    <form method="post">
        <fieldset>
            <legend>Sign Up</legend>
            <label>
                First Name *
                <input type="text" class="form-control" name="firstName">
            </label>
            <label>
                Last Name *
                <input type="text" class="form-control" name="lastName">
            </label>
            <label>
                Email *
                <input type="email" class="form-control" name="email">
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
