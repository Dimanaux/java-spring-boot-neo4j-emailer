<#-- @ftlvariable name="groups" type="java.util.List<com.example.emailer.db.entities.Group>" -->
<#macro contacts>
    <div class="accordion" id="accordionExample">
        <#list groups as group>
            <div class="card">
                <div class="card-header" id="headingOne">
                    <h2 class="mb-0">
                        <button class="btn btn-link" type="button" data-toggle="collapse" data-target="#collapseOne"
                                aria-expanded="true" aria-controls="collapseOne">
                            ${group.name}
                        </button>
                    </h2>
                </div>

                <div id="collapseOne" class="collapse show" aria-labelledby="headingOne"
                     data-parent="#accordionExample">
                    <div class="card-body">
                        <ul class="list-group">
                            <#list group.contacts as contact>
                                <a class="list-group-item" href="/inbox/compose?recipient_email=${contact.email}">
                                    ${contact.getFullName()}
                                </a>
                            </#list>
                        </ul>
                    </div>
                </div>
            </div>
        </#list>
    </div>
</#macro>
