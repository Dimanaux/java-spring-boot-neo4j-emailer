<#include "_html.ftl">

<#macro page_title>Sign up</#macro>

<#macro page_body>
    <form method="post">
        <fieldset>
            <legend>Sign Up</legend>

            <div class="form-group">
                <label>
                    First Name *
                    <input type="text" class="form-control" name="firstName" required>
                </label>
            </div>

            <div class="form-group">
                <label>
                    Last Name *
                    <input type="text" class="form-control" name="lastName" required>
                </label>
            </div>

            <div class="form-group">
                <label>
                    Email *
                    <input type="email" class="form-control" name="email" required>
                </label>
            </div>

            <div class="form-group">
                <label>
                    Password *
                    <input type="password" class="form-control" name="password" required>
                </label>
            </div>
        </fieldset>
        <fieldset>
            <div class="form-group">
                <input type="submit" class="btn btn-primary">
            </div>
        </fieldset>
    </form>
</#macro>

<@page></@page>
