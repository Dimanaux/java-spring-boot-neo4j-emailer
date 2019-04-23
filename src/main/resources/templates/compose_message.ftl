<#-- @ftlvariable name="signature" type="java.lang.String" -->
<#include "_html.ftl">

<#macro page_body>
    <form method="post">
        <div class="form-group">
            <label>Email address</label>
            <div class="form-row">
                <input type="text" class="form-control" name="recipients[0]">
            </div>
        </div>
        <div class="form-group">
            <label>
                Subject
                <input type="text" class="form-control" name="subject">
            </label>
        </div>
        <div class="form-group">
            <label>
                Content
                <textarea class="form-control" name="content" cols="100" rows="10">
                    <#if signature??>
                        ${signature}
                    </#if>
                </textarea>
            </label>
        </div>

        <input type="submit" class="btn btn-primary" value="Send">
    </form>
</#macro>

<@page></@page>
