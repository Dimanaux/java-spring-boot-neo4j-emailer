<#-- @ftlvariable name="signature" type="java.lang.String" -->
<#include "_html.ftl">

<#macro imports>
    <!-- development version, includes helpful console warnings -->
    <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>

    <!-- production version, optimized for size and speed -->
<#--    <script src="https://cdn.jsdelivr.net/npm/vue"></script>-->

</#macro>

<#macro page_body>
    <form method="post" id="app">
        <div class="form-group">
            <label>Email address</label>
            <div class="form-row">
                <ul class="list-group list-group-horizontal">
                    <recipient-email-item
                            v-for="email in recipientsEmails"
                            v-bind:field="email"
                            v-bind:key="email.id"
                    ></recipient-email-item>
                </ul>
                <input type="text" class="form-control " v-model="newEmail" v-on:blur="newEmailField()">
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

    <script src="/static/compose_message.js"></script>
</#macro>

<@page></@page>
