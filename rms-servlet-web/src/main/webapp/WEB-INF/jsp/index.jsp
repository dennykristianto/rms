<%@ page language="java" pageEncoding="UTF-8" session="false"%>
<%@ taglib prefix="rms" uri="/WEB-INF/tags/link.tld" %>
<!doctype html>
<html lang="en">
<head>
  <meta charset="utf-8">

  <title>Resources Management System</title>
  <meta name="description" content="Index">
  <meta name="author" content="Mitrais">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
  <link rel="stylesheet" href="https://code.getmdl.io/1.3.0/material.indigo-blue.min.css">
  <script defer src="https://code.getmdl.io/1.3.0/material.min.js"></script>
  <link rel="stylesheet" href="../css/styles.css?v=1.0">
  <rms:link href="https://cdnjs.cloudflare.com/ajax/libs/dialog-polyfill/0.5.0/dialog-polyfill.min.css" type="stylesheet"/>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/dialog-polyfill/0.5.0/dialog-polyfill.min.js"></script>
  <script
          src="https://code.jquery.com/jquery-3.4.0.min.js"
          integrity="sha256-BJeo0qm959uMBGb65z40ejJYGSgR7REI4+CW1fNKwOg="
          crossorigin="anonymous"></script>
  <!--[if lt IE 9]>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.js"></script>
  <![endif]-->
</head>

<body>
    <div class="demo-layout-transparent mdl-layout mdl-js-layout">
      <header class="mdl-layout__header mdl-layout__header--transparent">
        <div class="mdl-layout__header-row">
          <!-- Title -->
          <span class="mdl-layout-title">Resources Management System</span>
          <!-- Add spacer, to align navigation to the right -->
          <div class="mdl-layout-spacer"></div>
          <!-- Navigation -->
          <nav class="mdl-navigation">
            <rms:href href="users/list" className="mdl-navigation__link" uriLink="true" value="Users"/>
            <rms:href href="account/logout" className="mdl-navigation__link" value="Logout"/>
          </nav>
        </div>
      </header>

    <div class="mdl-layout__drawer">
        <span class="mdl-layout-title">RMS</span>
        <nav class="mdl-navigation">
          <a class="mdl-navigation__link" href="users/list">Users</a>
        </nav>
      </div>
      <main class="mdl-layout__content" style="height: 100%;">
        <jsp:include page='${router}'/>
      </main>
    </div>
</body>
</html>
