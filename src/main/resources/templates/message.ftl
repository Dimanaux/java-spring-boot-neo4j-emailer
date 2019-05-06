<#-- @ftlvariable name="current_user" type="com.example.emailer.db.entities.Account" -->
<#-- @ftlvariable name="message" type="com.example.emailer.db.entities.Message" -->
<#include "_html.ftl">

<#macro imports>
    <script src="/static/delete.js"></script>
</#macro>

<#macro page_body>
    <div class="card" <#--style="width: 18rem;"-->>
        <div class="card-body">
            <h5 class="card-title">${message.subject}</h5>
            <h6 class="card-subtitle mb-2 text-muted">from ${message.senderEmail}</h6>
            <p class="card-text">${message.content}</p>
            <small>at ${message.sentAt?string('dd.MM.yyyy HH:mm')}</small>
        </div>
        <ul class="list-group list-group-flush">
            <li class="list-group-item">
                Sent to: ${message.getRecipientsSummary()}
            </li>
            <li class="list-group-item">
                Copied to:
                <#list message.copiesRecipients as recipient>
                    ${recipient.email}
                <#else>
                    No copies
                </#list>
            </li>
            <#if current_user?? && current_user.email == message.senderEmail>
                <li class="list-group-item">
                    Secretly copied to:
                    <#list message.secretCopiesRecipients as recipient>
                        ${recipient.email}
                    <#else>
                        No secret copies
                    </#list>
                </li>
            </#if>
        </ul>
        <div class="card-body">
            <button
                    rel="nofollow"
                    class="btn btn-danger"
                    onclick="deleteRequest('/inbox/messages/${message.messageId}')">
                Delete
            </button>
        </div>
    </div>
</#macro>

<@page></@page>
