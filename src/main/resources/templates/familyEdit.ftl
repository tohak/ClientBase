<#import "parts/common.ftl" as c>

<@c.page>
Family Editor :
<form action="familyAddUser" method="post">
   Add user from Family:
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">User login :</label>
        <div class="col-sm-3">
            <input type="text" name="login"
                   class="form-control ${(usernameError??)?string('is-invalid', '')}"
                   placeholder="login" />
            <#if loginError??>
                    <div class="invalid-feedback">
                        ${loginError}
                    </div>
            </#if>
        </div>
    </div>
    <input type="hidden" value="${family.id}" name="familyId">
    <input type="hidden" value="${_csrf.token}" name="_csrf">
    <button type="submit">Add user</button>
</form>
<br>
<form action="familyEdit" method="post">
    Tick whom to remove:
    <#list family.users as user>
        <div>
            <input type="checkbox" name="toDelete[]" value="${user.id}" id="checkbox_${user.id}" >  ${user.username}</>
        </div>
    </#list>
    <input type="hidden" value="${family.id}" name="familyId">
    <input type="hidden" value="${_csrf.token}" name="_csrf">
    <button type="submit">Delete</button>
</form>
<br>
<form action="familyDelete" method="post">
    <input type="hidden" value="${family.id}" name="familyId">
    <input type="hidden" value="${_csrf.token}" name="_csrf">
    <button type="submit">delete Family</button>
</form>

</@c.page>