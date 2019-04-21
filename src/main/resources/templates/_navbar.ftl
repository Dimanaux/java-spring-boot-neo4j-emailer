<#assign security=JspTaglibs["http://www.springframework.org/security/tags"]/>

<#macro navbar>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="/inbox">Emailer</a>

        <ul class="navbar-nav mr-auto">

            <li class="nav-item active">
                <a class="nav-link" href="/inbox">Inbox</a>
            </li>

            <li class="nav-item">
                <a class="nav-link" href="#">Link</a>
            </li>

            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown"
                   aria-haspopup="true" aria-expanded="false">
                    Folders
                </a>

                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="#">Action</a>
                    <a class="dropdown-item" href="#">Another action</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="#">Something else here</a>
                </div>
            </li>

        </ul>

        <ul class="navbar-nav">
            <@security.authorize access="isAuthenticated()">
                logged in as <@security.authentication property="principal.username" />
                <li class="nav-item">
                    <a class="nav-link" href="/sign_out">Sign out</a>
                </li>
            </@security.authorize>
        </ul>
    </nav>
</#macro>