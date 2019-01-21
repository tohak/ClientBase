<#macro login path isRegisterForm>
    <#include "security.ftl">

    <form action="${path}" method="post">
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">User login :</label>
            <div class="col-sm-3">
                <input type="text" name="login" value="<#if user??>${user.login}</#if>"
                       class="form-control ${(loginError??)?string('is-invalid', '')}"
                       placeholder="User login" />
                <#if loginError??>
                    <div class="invalid-feedback">
                        ${loginError}
                    </div>
                </#if>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Password:</label>
            <div class="col-sm-3">
                <input type="password" name="password"
                       class="form-control ${(passwordError??)?string('is-invalid', '')}"
                       placeholder="Password" />
                <#if passwordError??>
                    <div class="invalid-feedback">
                        ${passwordError}
                    </div>
                </#if>
            </div>
        </div>
        <#if isRegisterForm>
             <div class="form-group row">
            <label class="col-sm-2 col-form-label">Password2:</label>
            <div class="col-sm-3">
                <input type="password" name="password2"
                       class="form-control ${(password2Error??)?string('is-invalid', '')}"
                       placeholder="Password" />
                <#if passwordError2??>
                    <div class="invalid-feedback">
                        ${passwordError2}
                    </div>
                </#if>
            </div>
        </div>
         <div class="form-group row">
                <label class="col-sm-2 col-form-label">user name:</label>
                <div class="col-sm-3">
                    <input type="text" name="userName"
                           class="form-control ${(userNameError??)?string('is-invalid', '')}"
                           placeholder="Retype userName" />
                    <#if userNameError??>
                        <div class="invalid-feedback">
                            ${userNameError}
                        </div>
                    </#if>
                </div>
            </div>
             <div class="form-group row">
                <label class="col-sm-2 col-form-label">user last name:</label>
                <div class="col-sm-3">
                    <input type="text" name="userLastName"
                           class="form-control ${(userLastNameError??)?string('is-invalid', '')}"
                           placeholder="Retype userLastName" />
                    <#if userLastNameError??>
                        <div class="invalid-feedback">
                            ${userLastNameError}
                        </div>
                    </#if>
                </div>
            </div>
             <div class="form-group row">
                <label class="col-sm-2 col-form-label">user patronymic:</label>
                <div class="col-sm-3">
                    <input type="text" name="patronymic"
                           class="form-control ${(patronymicError??)?string('is-invalid', '')}"
                           placeholder="Retype patronymic" />
                    <#if patronymicError??>
                        <div class="invalid-feedback">
                            ${patronymicError}
                        </div>
                    </#if>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">user date:</label>
                <div class="col-sm-3">
                    <input type="date" name="date"
                           class="form-control ${(dateError??)?string('is-invalid', '')}"
                           placeholder="Retype patronymic" />
                </div>

              <div class="form-group">
  <label class="col-sm-2 col-form-label" for="sel1">Select married:</label>
  <label for="married"></label><select class="form-control" id="married">
    <option>married</option>
    <option>unmarried</option>
  </select>
</div>
             <div class="form-group row">
                <label class="col-sm-2 col-form-label">user INN:</label>
                <div class="col-sm-3">
                    <input type="number" name="inn"
                           class="form-control ${(innError??)?string('is-invalid', '')}"
                           placeholder="Retype patronymic" />
                </div>
            </div>
            <div class="form-group">
  <label class="col-sm-2 col-form-label" for="sel1">Select education:</label>
  <select class="form-control" id="educations">
    <option>ELEMENTARY</option>
    <option>SECONDARY</option>
    <option>HIGHER</option>
  </select>
</div>
</#if>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <#if !isRegisterForm><a href="/registration">Add new user</a>
         </#if>
        <button class="btn btn-primary" type="submit"><#if isRegisterForm>Create<#else>Sign In</#if></button>

</#macro>

<#macro logout>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button class="btn btn-primary" type="submit">Sing out</button>
    </form>
</#macro>

<#macro loginn>
    <form action="/login" method="Get">
        <button class="btn btn-primary" type="submit">Sing in</button>
    </form>
</#macro>