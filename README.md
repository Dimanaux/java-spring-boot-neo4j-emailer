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
