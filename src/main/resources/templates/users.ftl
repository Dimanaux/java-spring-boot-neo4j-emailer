<#-- @ftlvariable name="accounts" type="java.util.List<com.example.emailer.db.entities.Account>" -->
<#include "_html.ftl">

<#macro page_title>Users in system</#macro>

<#macro page_body>
    <table>
        <thead>
        <tr>
            <td>id</td>
            <td>email</td>
            <td>first name</td>
            <td>last name</td>
        </tr>
        </thead>

        <tbody>
        <#list accounts as a>
            <tr>
                <td>${a.getId()}</td>
                <td>${a.getEmail()}</td>
                <td>${a.getFirstName()}</td>
                <td>${a.getLastName()}</td>
            </tr>
        </#list>
        </tbody>
    </table>
</#macro>

<@page></@page>
