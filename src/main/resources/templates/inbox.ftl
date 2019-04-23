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
                <td>${m.getSubject()}</td>
                <td>${m.getSender().getEmail()}</td>
                <td>${m.getRecipientsSummary()}</td>
                <td>${m.getStatus()}</td>
                <td>${m.getSentAt()}</td>
                <td>${m.getContent()}</td>
            </tr>
        </#list>
        </tbody>
    </table>
</#macro>

<@page></@page>