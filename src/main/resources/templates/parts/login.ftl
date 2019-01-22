<#macro login path isRegisterForm>
    <#include "security.ftl">

    <form action="${path}" method="post">
        <div class="form-group row">
        <label class="col-sm-2 col-form-label">User Name :</label>
        <div class="col-sm-3">
            <input type="text" name="username" value="<#if user??>${user.username}</#if>"
                   class="form-control ${(usernameError??)?string('is-invalid', '')}"
                   placeholder="User name" />
            <#if usernameError??>
                    <div class="invalid-feedback">
                        ${usernameError}
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
                    <input type="text" name="name"
                           class="form-control ${(nameError??)?string('is-invalid', '')}"
                           placeholder="Retype username" />
                    <#if nameError??>
                        <div class="invalid-feedback">
                            ${nameError}
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
            <label class="col-sm-2 col-form-label">user INN:</label>
            <div class="col-sm-3">
                <input type="number" name="inn"
                       class="form-control ${(innError??)?string('is-invalid', '')}"
                       placeholder="Retype inn" />
                    <#if innError??>
                        <div class="invalid-feedback">
                            ${innError}
                        </div>
                    </#if>
            </div>
        </div>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">user date:</label>
                <div class="col-sm-3">
                    <input type="date" name="dateusr"
                           class="form-control ${(dateError??)?string('is-invalid', '')}"
                           placeholder="Retype patronymic" />
                </div>
            </div>

              <div class="form-group row">
      <label class="col-sm-2 col-form-label" for="married">State</label>
                  <div class="col-sm-3">
      <select id="married" name="married" class="form-control">
          <option selected>married</option>
          <option>unmarried</option>
      </select>
              </div>
              </div>

             <div class="form-group row">
      <label class="col-sm-2 col-form-label" for="educations">education:</label>
                  <div class="col-sm-3">
      <select id="educations" name="educations" class="form-control">
          <option selected>ELEMENTARY</option>
          <option>SECONDARY</option>
          <option>HIGHER</option>
      </select>
                  </div>
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