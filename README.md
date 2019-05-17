# java-spring-boot-neo4j-emailer

## How to set up Spring Security tags for Freemarker?
0. Add maven dependencies
```xml
<dependency>
    <groupId>javax.servlet</groupId>
    <artifactId>jstl</artifactId>
    <scope>provided</scope>
</dependency>
<dependency>
    <groupId>org.apache.tomcat.embed</groupId>
    <artifactId>tomcat-embed-jasper</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.security</groupId>
    <artifactId>spring-security-taglibs</artifactId>
    <version>5.1.5.RELEASE</version>
    <scope>${defaultScope}</scope>
</dependency>
```

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

### Jump to:
1. [Aspects](/src/main/java/com/example/emailer/aspects/)
2. [validation](/src/main/java/com/example/emailer/forms/validation/) TODO
3. [ajax](/src/main/resources/static/message.js)
4. TODO chat plugin
5. [spring data jpa NEO4J!](/src/main/java/com/example/emailer/db/repositories/)
6. Docker TODO
7. [Vue.js JS](/src/main/resources/static/compose_message.js), [Vue.js FTL](/src/main/resources/templates/compose_message.ftl)
8. [FreeMarker + spring security tags](/src/main/java/com/example/emailer/security/config/)
9. [postgresql](/src/main/java/com/example/emailer/db/entities/id/) + [neo4j](/src/main/java/com/example/emailer/db/)
10. [spring security config](/src/main/java/com/example/emailer/security/)
11. oauth TODO

