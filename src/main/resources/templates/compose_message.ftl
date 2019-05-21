<#-- @ftlvariable name="contacts" type="java.util.Set<com.example.emailer.db.entities.Account>" -->
<#-- @ftlvariable name="recipient_email" type="java.lang.String" -->
<#-- @ftlvariable name="signature" type="java.lang.String" -->
<#include "_html.ftl">

<#macro imports>
    <!-- development version, includes helpful console warnings -->
    <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>

    <!-- production version, optimized for size and speed -->
<#--    <script src="https://cdn.jsdelivr.net/npm/vue"></script>-->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/simplemde/latest/simplemde.min.css">
    <script src="https://cdn.jsdelivr.net/simplemde/latest/simplemde.min.js"></script>
</#macro>

<#macro page_body>

    <datalist id="contacts-list">
        <#list contacts as contact>
            <option value="${contact.email}">${contact.getFullName()}</option>
        </#list>
    </datalist>

    <form method="post" id="app" action="/inbox/messages">
        <div class="form-group">
            <label>Recipients:</label>
            <div class="form-row">
                <ul class="list-group list-group-horizontal">
                    <recipient-email-item
                            v-for="email in recipientsEmails"
                            v-bind:field="email"
                            v-bind:key="email.id"
                    ></recipient-email-item>
                </ul>
                <div class="form-group mx-sm-3 mb-2">
                    <input type="text" class="form-control" list="contacts-list"
                           v-model="newRecipient"
                           v-on:blur="createRecipient()">
                </div>
            </div>
            <label>Copy recipients:</label>
            <div class="form-row">
                <ul class="list-group list-group-horizontal">
                    <recipient-email-item
                            v-for="email in copyRecipientsEmails"
                            v-bind:field="email"
                            v-bind:key="email.id"
                    ></recipient-email-item>
                </ul>
                <div class="form-group mx-sm-3 mb-2">
                    <input type="text" class="form-control col" list="contacts-list"
                           v-model="newCopyRecipient"
                           v-on:blur="createCopyRecipient()">
                </div>
            </div>
            <label>Secret copy recipients:</label>
            <div class="form-row">
                <ul class="list-group list-group-horizontal">
                    <recipient-email-item
                            v-for="email in secretCopyRecipientsEmails"
                            v-bind:field="email"
                            v-bind:key="email.id"
                    ></recipient-email-item>
                </ul>
                <div class="form-group mx-sm-3 mb-2">
                    <input type="text" class="form-control" list="contacts-list"
                           v-model="newSecretCopyRecipient"
                           v-on:blur="createSecretCopyRecipient()">
                </div>
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
                <#--class="form-control" id="message_body_field"-->
                <textarea name="content" cols="100" rows="10">
                    <#if signature??>
                        ${signature}
                    </#if>
                </textarea>
            </label>
        </div>

        <input type="submit" class="btn btn-primary" onclick="changeAction('/inbox/messages')" value="Send">
        <input type="submit" class="btn btn-success" onclick="changeAction('/inbox/messages/drafts')"
               value="Save to drafts">
    </form>
    <script src="/static/compose_message.js"></script>

    <script>
        const simplemde = new SimpleMDE();

        <#if RequestParameters.recipient_email??>
        app.recipientsEmails.push({id: 0, email: "${RequestParameters.recipient_email}"});
        </#if>
    </script>

</#macro>

<@page></@page>
