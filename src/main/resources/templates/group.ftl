<#-- @ftlvariable name="group" type="com.example.emailer.db.entities.Group" -->
<#-- @ftlvariable name="groups" type="java.util.List<com.example.emailer.db.entities.Group>" -->
<#include "_html.ftl">

<#macro page_title>Group ${group.name}</#macro>

<#macro page_body>
    <form method="post">
        <label>Join group ${group.name}?</label>
        <input type="submit" value="PUSH">
    </form>
</#macro>

<@page></@page>
