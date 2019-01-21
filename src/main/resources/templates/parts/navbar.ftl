<#include "security.ftl">
<#import "login.ftl" as l>


<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
    <a class="navbar-brand" href="/">Client Base</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/user">Users</a>
            </li>
            <#if isAdmin>
                <li class="nav-item">
                    <a class="nav-link" href="/user">List users</a>
                </li>
        </#if>
        </ul>
        <div class="navbar-text mr-3"><#if user??>${name}, <@l.logout /><#else>Please, login
                <form action="/main" method="Get">
                    <button class="btn btn-primary" type="submit">Sing in</button>
                </form></#if></div>

    </div>
</nav>