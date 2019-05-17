<#-- @ftlvariable name="current_user" type="com.example.emailer.db.entities.Account" -->
<#-- @ftlvariable name="message" type="com.example.emailer.db.entities.Message" -->
<#include "_html.ftl">

<#macro imports>
    <script src="/static/message.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/marked/marked.min.js"></script>
</#macro>

<#macro page_body>
    <div class="card" <#--style="width: 18rem;"-->>
        <div class="card-body">
            <h5 class="card-title">${message.subject}</h5>
            <h6 class="card-subtitle mb-2 text-muted">from ${message.sender.email}</h6>
            <p class="card-text" id="content">${message.content}</p>
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
            <button
                    rel="nofollow"
                    class="btn btn-danger"
                    onclick="deleteRequest('/inbox/messages/${message.messageId}')">
                Delete
            </button>
            <div class="btn-group" role="group">
                <button id="btnGroupDrop1" type="button" class="btn btn-secondary dropdown-toggle"
                        data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Add to folder
                </button>
                <div class="dropdown-menu" aria-labelledby="btnGroupDrop1">
                    <#list folders as folder>
                        <a class="dropdown-item"
                           onclick="moveMessageToFolder(${message.messageId}, '${folder.name}')">${folder.name}</a>
                    </#list>
                </div>
            </div>
        </div>
    </div>

    <script>
        document.getElementById('content').innerHTML = marked(`${message.content}`);
    </script>
</#macro>

<@page></@page>
