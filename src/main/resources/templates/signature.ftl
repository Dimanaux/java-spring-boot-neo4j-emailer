<#-- @ftlvariable name="contacts" type="java.util.Set<com.example.emailer.db.entities.Account>" -->
<#-- @ftlvariable name="recipient_email" type="java.lang.String" -->
<#-- @ftlvariable name="signature" type="java.lang.String" -->
<#include "_html.ftl">

<#macro imports>
    <!-- production version, optimized for size and speed -->
<#--    <script src="https://cdn.jsdelivr.net/npm/vue"></script>-->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/simplemde/latest/simplemde.min.css">
    <script src="https://cdn.jsdelivr.net/simplemde/latest/simplemde.min.js"></script>
</#macro>

<#macro page_body>

    <form method="post" id="app" action="/signature">
        <div class="form-group">
            <label>
                Set up your signature:
                <#--class="form-control" id="message_body_field"-->
                <textarea name="content" cols="100"
                          rows="10"><#if current_user.signature??>${current_user.signature}</#if></textarea>
            </label>
        </div>

        <input type="submit" class="btn btn-primary" value="Send">
    </form>

    <br>

    <script>
        const simplemde = new SimpleMDE();
    </script>
</#macro>

<@page></@page>
