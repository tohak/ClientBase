<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">

<@c.page>
    <#if isAdmin>
        <a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
            Add new Family
        </a>
    </#if>

    <div class="collapse <#if family??>show</#if>" id="collapseExample">
        <div class="form-group mt-3">
            <form method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <input type="text" class="form-control ${(loginError??)?string('is-invalid', '')}"
                           value="<#if family??>${family.id}</#if>" name="login" placeholder="Input first login in new family" />
                    <#if loginError??>
                        <div class="invalid-feedback">
                            ${loginError}
                        </div>
                    </#if>
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                <div class="form-group">
                    <button type="submit" class="btn btn-primary">Добавить</button>
                </div>
            </form>
        </div>
    </div>

    <table class="table">
        <thead>
        <tr>
            <th>Id family</th>
            <th>User to family</th>

        </tr>
        </thead>
        <tbody>
        <#if familys??>
        <#list familys as family>
            <tr>
                <td>${family.id}</td>
                <td><#list family.users as user>${user.username}<#sep>, </#list></td>
                <#if isAdmin><td> <a href="/family/${family.id}" >edit</a> </td></#if>
            </tr>
        </#list>
        </#if>
        </tbody>
    </table>
</@c.page>