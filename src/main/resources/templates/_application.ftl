<#include "_navbar.ftl">

<#macro page_title></#macro>
<#macro imports></#macro>
<#macro page_body></#macro>

<#macro page>
    <!doctype html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">

        <title><@page_title></@page_title></title>

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <@imports></@imports>
    </head>
    <body>
    <@navbar></@navbar>
    <div class="container-fluid">
        <@page_body></@page_body>
    </div>
    </body>
    </html>
</#macro>
