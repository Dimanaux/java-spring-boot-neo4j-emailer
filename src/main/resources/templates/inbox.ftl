<#-- @ftlvariable name="messages" type="java.util.List<com.example.emailer.db.entities.Message>" -->
<#include "_html.ftl">

<#macro page_body>
    <table>
        <thead>
            <tr>
                <td>subject</td>
                <td>sent by</td>
                <td>sent to</td>
                <td>status</td>
                <td>sent at</td>
                <td>contents</td>
            </tr>
        </thead>

        <tbody>
        <#list messages as m>
            <tr>
                <td>${m.subject}</td>
                <td>${m.sender.email}</td>
                <td>${m.getRecipientsSummary()}</td>
                <td>${m.status}</td>
                <td>${m.sentAt?string('dd.MM.yyyy HH:mm:ss')}</td>
                <td>${m.content}</td>
            </tr>
        </#list>
        </tbody>
    </table>
</#macro>

<@page></@page>