<%@ page import="com.mitrais.rms.helper.Helper" %>
<%@include file="../include/header.jsp" %>
<body>
<div class="mdl-layout mdl-js-layout mdl-color--grey-100">
    <main class="mdl-layout__content"
          style="display: flex;align-items: center;flex-direction: column;justify-content: center;height: 100%">
        <div class="mdl-card mdl-shadow--6dp">
            <div class="mdl-card__title mdl-color--primary mdl-color-text--white">
                <h2 class="mdl-card__title-text" style="text-align: center">Resources Management System</h2>
            </div>
            <form action="<%=Helper.getRouteLink(request,"account/login")%>" method="post">
                <div class="mdl-card__supporting-text">

                    <div class="mdl-color-text--accent" style="text-align: center">
                        <h5><%=request.getAttribute("error")!=null?request.getAttribute("error"):""%></h5>
                    </div>
                    <div class="mdl-textfield mdl-js-textfield">
                        <input class="mdl-textfield__input" type="text" id="username" name="username"/>
                        <label class="mdl-textfield__label" for="username">Username</label>
                    </div>
                    <div class="mdl-textfield mdl-js-textfield">
                        <input class="mdl-textfield__input" type="password" id="password" name="password"/>
                        <label class="mdl-textfield__label" >Password</label>
                    </div>

                </div>
                <div class="mdl-card__actions mdl-card--border">
                    <button class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect"
                            style="float:right">
                        Log in
                    </button>
                </div>
            </form>
        </div>
    </main>
</div>
</body>
</html>
