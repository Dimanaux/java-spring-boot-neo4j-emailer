# java-spring-boot-neo4j-emailer

## How to set up srping security tags for freemarker
1. Create these [2 classes](/src/main/java/com/example/emailer/security/config/) somewhere in your project.

2. Insert `<#assign security=JspTaglibs["http://www.springframework.org/security/tags"]/>` into the top of your Freemarker template.

3. Use as
```
<@security.authorize access="isAuthenticated()">
    logged in as <@security.authentication property="principal.username" />
    <li class="nav-item">
        <a class="nav-link" href="/sign_out">Sign out</a>
    </li>
</@security.authorize>
```
