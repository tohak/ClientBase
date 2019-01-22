<#import "parts/common.ftl" as c>

<@c.page>
User Editor:
    <#if message??>
        ${message}
        <a href="/user/">repeat</a>
   <#else >
<form action="/user" method="post">
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Login :</label>
        <div class="col-sm-3">
    <input type="text" name="login" value="<#if user??>${user.username}</#if>">
        </div>
    </div>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Status id(1=Vip) :</label>
                <div class="col-sm-3">
    <input type="text" name="userstatus" value="<#if user.userStatus??>${user.userStatus.id}</#if>">
                </div>
            </div>
                    <div class="form-group row">
                        <label class="col-sm-2 col-form-label">Family id :</label>
                        <div class="col-sm-3">
    <input type="text" name="family"  value="<#if user.family??>${user.family.id}</#if>">
                        </div>
                    </div>
    <#list roles as role>
        <div>
            <label><input type="checkbox" name="${role}" ${user.roles?seq_contains(role)?string("checked", "")} > ${role}</label>
        </div>

    </#list>
    <input type="hidden" value="${user.id}" name="userId">
    <input type="hidden" value="${_csrf.token}" name="_csrf">
    <button type="submit">Save</button>
</form>
    </#if>
</@c.page>