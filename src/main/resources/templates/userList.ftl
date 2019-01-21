<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">

<@c.page>
<div class="form-row">
    <div class="form-group col-md-6">
        <form method="get" action="/user" class="form-inline">
            <input type="text" name="filter" class="form-control" value="${filter?ifExists}" placeholder="Search by name, last name, patronymic">
            <button type="submit" class="btn btn-primary ml-2">Search</button>
        </form>
    </div>
</div>

List of User:
<br>
    <#if message??> ${message}<#else>

<table class="table">
    <thead>
    <tr>
        <th>Name</th>
        <th>Last Name</th>
        <th>Patronymic</th>
        <th>Date of Birth</th>
        <th>Married</th>
        <th>INN</th>
        <th>Education</th>
        <th>User status</th>
        <th>Family</th>
        <th>Role</th>
    </tr>
    </thead>
    <tbody>
    <#list users as user>
    <tr>
        <td>${user.name}</td>
        <td>${user.userLastName}</td>
        <td>${user.patronymic}</td>
        <td>${user.date.toString()}</td>
       <#if user.familyStatus??> <td>yes</td><#else><td>no</td></#if>
        <td>${user.inn}</td>
        <td>${user.state}</td>
        <#if user.userStatus??><td>${user.userStatus.getStatusUser()}</td><#else><td>no vip status</td></#if>
        <#if user.family??><td>${user.family.getId()}</td><#else><td> no family on base</td></#if>
        <td><#list user.roles as role>${role}<#sep>, </#list></td>
        <#if isAdmin><td> <a href="/user/${user.id}" >edit</a> </td></#if>
    </tr>
    </#list>
    </tbody>
</table>
    </#if>
</@c.page>