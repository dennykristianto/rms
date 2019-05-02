<%@ page import="com.mitrais.rms.helper.Helper" %>
<%@ page language="java" pageEncoding="UTF-8" session="false" %>
<%@ taglib prefix="rms" uri="/WEB-INF/tags/link.tld" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="mdl-card mdl-shadow--6dp fullwidth">
    <div class="mdl-card__title mdl-color--primary mdl-color-text--white">
        <h2 class="mdl-card__title-text" style="text-align: center">Lists of available users in the system</h2>
    </div>

    <table class="mdl-data-table mdl-js-data-table mdl-shadow--2dp fullwidth">
        <thead>
        <tr>
            <th>No</th>
            <th class="mdl-data-table__cell--non-numeric">Picture</th>
            <th class="mdl-data-table__cell--non-numeric">User Name</th>
            <th class="mdl-data-table__cell--non-numeric">Name</th>
            <th class="mdl-data-table__cell--non-numeric">Address</th>
            <th class="mdl-data-table__cell--non-numeric">Email</th>
            <th class="mdl-data-table__cell--non-numeric">Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${users}" var="user" varStatus="loop">
            <tr>
                <td>${loop.index+1}</td>
                <td><img src="${user.picture}" style="width:75px;height: auto;"/></td>
                <td class="mdl-data-table__cell--non-numeric"><c:out value="${user.userName}"/></td>
                <td class="mdl-data-table__cell--non-numeric"><c:out value="${user.name}"/></td>
                <td class="mdl-data-table__cell--non-numeric"><c:out value="${user.address}"/></td>
                <td class="mdl-data-table__cell--non-numeric"><c:out value="${user.email}"/></td>
                <td>
                    <div>
                        <a class="mdl-button mdl-js-button mdl-button--raised
                        mdl-js-ripple-effect mdl-button--primary show-modal"
                            id="edit${user.id}">Edit
                        </a>
                    </div>
                    <div><a class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent" href="<%=Helper.getRouteLink(request,"users/delete/")%>${user.id}">Delete</a>
                    </div>
                </td>

                <dialog class="mdl-dialog" id="dialog_edit${user.id}">
                    <h4 class="mdl-dialog__title">Edit user ${user.name}</h4>
                    <form action="<%=Helper.getRouteLink(request,"users")%>/${user.id}" method="post" enctype="multipart/form-data">
                        <div class="mdl-dialog__content">
                            <input type="hidden" name="_method" value="put"/>
                            <div class="mdl-textfield mdl-js-textfield">
                                <input class="mdl-textfield__input" type="text" name="username"
                                       value="${user.userName}" required="required">
                                <label class="mdl-textfield__label">Username</label>
                            </div>
                            <div class="mdl-textfield mdl-js-textfield">
                                <input class="mdl-textfield__input" type="text" name="name" value="${user.name}" required="required">
                                <label class="mdl-textfield__label">Full Name</label>
                            </div>
                            <div class="mdl-textfield mdl-js-textfield">
                                <input class="mdl-textfield__input" type="text" name="address" value="${user.address}" required="required">
                                <label class="mdl-textfield__label">Address</label>
                            </div>
                            <div class="mdl-textfield mdl-js-textfield">
                                <input class="mdl-textfield__input" type="password" name="password" required="required" minlength="6" maxlength="12"
                                       value="${user.password}">
                                <label class="mdl-textfield__label">Password</label>
                            </div>
                            <div class="mdl-textfield mdl-js-textfield">
                                <input class="mdl-textfield__input" type="email" name="email" value="${user.email}" required="required">
                                <label class="mdl-textfield__label">Email Address</label>
                            </div>
                            <div class="mdl-textfield mdl-js-textfield">
                                <input class="mdl-textfield__input" type="file" name="picture" value="${user.picture}">
                                <label class="mdl-textfield__label">Picture</label>
                            </div>
                        </div>
                        <div class="mdl-dialog__actions">
                            <button class="mdl-button mdl-button--colored">Submit</button>
                            <button type="button" class="mdl-button close">Cancel</button>
                        </div>
                    </form>
                </dialog>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<dialog class="mdl-dialog" id="create_user_dialog">
    <h4 class="mdl-dialog__title">Add New User</h4>
    <form action="${requestScope['javax.servlet.forward.request_uri']}" method="post" enctype="multipart/form-data">
        <div class="mdl-dialog__content">
            <div class="mdl-textfield mdl-js-textfield">
                <input class="mdl-textfield__input" type="text" name="username" required="required">
                <label class="mdl-textfield__label">Username</label>
            </div>
            <div class="mdl-textfield mdl-js-textfield">
                <input class="mdl-textfield__input" type="text" name="name" required="required">
                <label class="mdl-textfield__label">Full Name</label>
            </div>
            <div class="mdl-textfield mdl-js-textfield">
                <input class="mdl-textfield__input" type="text" name="address" required="required">
                <label class="mdl-textfield__label">Address</label>
            </div>
            <div class="mdl-textfield mdl-js-textfield">
                <input class="mdl-textfield__input" type="password" name="password" required="required" minlength="6" maxlength="12">
                <label class="mdl-textfield__label">Password</label>
            </div>
            <div class="mdl-textfield mdl-js-textfield">
                <input class="mdl-textfield__input" type="email" name="email" required="required">
                <label class="mdl-textfield__label">Email Address</label>
            </div>
            <div class="mdl-textfield mdl-js-textfield">
                <input class="mdl-textfield__input" type="file" name="picture" required="required">
                <label class="mdl-textfield__label">Picture</label>
            </div>
        </div>
        <div class="mdl-dialog__actions">
            <button class="mdl-button mdl-button--colored">Submit</button>
            <button type="button" class="mdl-button close">Cancel</button>
        </div>
    </form>
</dialog>

<div class="fixed">
    <button class="mdl-button mdl-js-button mdl-button--fab mdl-js-ripple-effect mdl-button--colored show-modal"
            id="create_user_button">
        <i class="material-icons">add</i>
    </button>
</div>

<script>
    var dialog = "";
    $(document).ready(function () {
        $(".show-modal").click(function (event) {
            console.log(event.currentTarget.id)
            if(event.currentTarget.id==='create_user_button')
                setterDialog(document.querySelector("#create_user_dialog"))
            else
                setterDialog(document.querySelector("#dialog_"+event.currentTarget.id))
            dialog.showModal();
        });
        $(".close").click(function () {
            dialog.close();
        })
    })

    function showIndividualDialog() {
        setterDialog(document.querySelector(id))
        dialog.showModal();
    }

    function setterDialog(val) {
        dialog = val;
    }
</script>
