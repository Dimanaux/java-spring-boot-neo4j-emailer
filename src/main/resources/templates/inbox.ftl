<#-- @ftlvariable name="current_user" type="com.example.emailer.db.entities.Account" -->
<#-- @ftlvariable name="messages" type="java.util.List<com.example.emailer.db.entities.Message>" -->
<#include "_html.ftl">

<#macro page_body>
    <br>
    <div class="container">
        <div class="list-group">
            <#list messages as m>
                <a href="/inbox/messages/${m.messageId}" class="list-group-item list-group-item-action">
                    <div class="d-flex w-100 justify-content-between">
                        <h5 class="mb-1">${m.subject}</h5>
                        <small>${m.sentAt?string('dd.MM.yyyy HH:mm')}</small>
                    </div>
                    <p class="mb-1">${m.content}</p>
                    <small>
                        ${m.status}
                        <#if current_user?? && current_user.equals(m.sender)>
                            to ${m.getRecipientsSummary()}
                        <#else>
                            from ${m.sender.email}
                        </#if>
                    </small>
                </a>
            <#else>
                <h2>You have no messages yet.</h2>
            </#list>
        </div>
    </div>
</#macro>

<@page></@page>
