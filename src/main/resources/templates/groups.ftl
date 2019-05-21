<#-- @ftlvariable name="groups" type="java.util.List<com.example.emailer.db.entities.Group>" -->
<#include "_html.ftl">

<#macro page_title>Groups</#macro>

<#macro page_body>
    <#list groups as group>
        <div>
            <h4>${group.name}</h4>
            <small>copy invite link:
                <button class="btn btn-link">/groups/${group.groupId}</button>
            </small>

            <div>
                <#list group.contacts as contact>
                    <a class="dropdown-item"
                       href="/inbox/compose?recipient_email=${contact.email}">${contact.getFullName()}</a>
                </#list>
            </div>
        </div>
    </#list>

    <details>
        <summary>Create group?</summary>
        <form method="post">
            <div class="form-group">

                <label>
                    Group name *
                    <input type="text" class="form-control" name="name" required>
                </label>
            </div>
            <input class="btn btn-primary" type="submit" value="create group">
        </form>
    </details>
</#macro>

<@page></@page>
