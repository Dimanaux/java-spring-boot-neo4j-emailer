<#-- @ftlvariable name="current_user" type="com.example.emailer.db.entities.Account" -->
<#assign security=JspTaglibs["http://www.springframework.org/security/tags"]/>

<#macro navbar>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="/inbox">Emailer</a>

        <ul class="navbar-nav mr-auto">

            <li class="nav-item active">
                <a class="nav-link" href="/inbox">Inbox</a>
            </li>

            <li class="nav-item">
                <a href="/inbox/sent" class="nav-link">Sent</a>
            </li>

            <li class="nav-item">
                <a href="/inbox/drafts" class="nav-link">Drafts</a>
            </li>

            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown"
                   aria-haspopup="true" aria-expanded="false">
                    Folders
                </a>

                <div class="dropdown-menu" id="folder_list" aria-labelledby="navbarDropdown">
                    <#if current_user?? && current_user.folders??>
                        <#list current_user.folders as folder>
                            <a class="dropdown-item" href="/inbox/folders/${folder.name}">${folder.name}</a>
                        </#list>
                        <div class="dropdown-divider"></div>
                    </#if>
                    <a class="dropdown-item" onclick="createFolder()">New folder</a>
                </div>
            </li>

            <li class="nav-item">
                <a href="/inbox/compose" class="nav-link">Compose</a>
            </li>

            <li class="nav-item">
                <a href="/groups" class="nav-link">Groups</a>
            </li>

            <li class="nav-item">
                <a href="/signature" class="nav-link">Default signature</a>
            </li>
        </ul>

        <ul class="navbar-nav">
            <li class="nav-item">
                <a href="/inbox/search" class="nav-link">Search</a>
            </li>

            <@security.authorize access="isAuthenticated()">
                <li class="nav-item">
                    <a class="nav-link disabled">
                        signed in as <@security.authentication property="principal.username"/>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/sign_out">Sign out</a>
                </li>
            </@security.authorize>
        </ul>
    </nav>
</#macro>
