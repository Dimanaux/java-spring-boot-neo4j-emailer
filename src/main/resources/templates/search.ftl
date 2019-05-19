<#include "_html.ftl">

<#macro imports>
    <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js" defer></script>
    <script src="/static/search.js" defer></script>
</#macro>

<#macro page_body>
    <div class="container-fluid" id="app">
        <nav class="navbar navbar-expand-lg navbar-light" style="background-color: #e3f2fd;">

            <a class="navbar-brand" href="#">Message filter</a>

            <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
                <li class="form-inline my-2 my-lg-0">
                    <span class="nav-link disabled">from:</span>
                    <input class="form-control" type="search" v-model="filter.from">
                </li>
                <li class="form-inline my-2 my-lg-0">
                    <span class="nav-link disabled">subject:</span>
                    <input class="form-control" type="search" v-model="filter.subject">
                </li>
                <li class="form-inline my-2 my-lg-0">
                    <span class="nav-link disabled">content:</span>
                    <input class="form-control" type="search" v-model="filter.content">
                </li>
                <li class="form-inline my-2 my-lg-0">
                    <a class="nav-link disabled">to:</a>
                    <input class="form-control" type="search" v-model="filter.to">
                </li>
                <button class="btn btn-outline-success my-2 my-sm-0" v-on:click="search()">Search</button>
            </ul>
        </nav>

        <message-item
                v-for="message in messages"
                v-bind:key="message.messageId"
                v-bind:message="message"
        ></message-item>
    </div>
</#macro>

<@page></@page>
