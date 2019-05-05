<#-- @ftlvariable name="current_user" type="com.example.emailer.db.entities.Account" -->
<#-- @ftlvariable name="message" type="com.example.emailer.db.entities.Message" -->
<#include "_html.ftl">

<#macro page_body>
    <div class="card" <#--style="width: 18rem;"-->>
        <div class="card-body">
            <h5 class="card-title">${message.subject}</h5>
            <h6 class="card-subtitle mb-2 text-muted">from ${message.sender.email}</h6>
            <p class="card-text">${message.content}</p>
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
            <#if current_user?? && current_user.email == message.sender.email>
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
            <a href="#" class="card-link">Card link</a>
            <a href="#" class="card-link">Another link</a>
        </div>
    </div>
</#macro>

<@page></@page>
