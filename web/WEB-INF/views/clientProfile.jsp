<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8"%>

<html>

<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <title>Client profile</title>
  <link rel="icon" href='<c:url value="/resources/images/favicon.ico" />' type="image/x-icon">

  <link rel="stylesheet" href='<c:url value="/resources/css/bootstrap.min.css" />'>
  <link rel="stylesheet" href='<c:url value="/resources/css/index.css" />'>
  <link rel="stylesheet" href='<c:url value="/resources/css/submenu.css" />'>

  <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
</head>

<body>
  <div class="wrapper">
    <div class="wrapperForFooter">

      <div class="headers">
        <div class="firstHeader row">
          <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
            <a href="http://smartavon.ru/Registration.html">СТАТЬ ПРЕДСТАВИТЕЛЕМ</a>
          </div>
          <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6"></div>
          <div class="col-xs-2 col-sm-2 col-md-2 col-lg-2">
            <a href="login">
              <img src="resources/images/login.png"/>
            </a>
          </div>
        </div>

        <div class="secondHeader">
          <div id="logoDiv">
            <img id="logo" alt="AVON" src="resources/images/logo.png">
          </div>

          <div id="busketDiv">
            <img id="busket" src="resources/images/busket.png" alt="AVON">
          </div>
        </div>

        <hr>
        <div class="thirdHeader row">
          <ul class="menu">
            <li><a href=#>АРОМАТЫ</a>
              <ul class="submenu">
                <li><h5 class="categoryText">КАТЕГОРИИ</h5></li>
                <li><a href=#>Мужские ароматы</a></li>
                <li><a href=#>Женские ароматы</a></li>
              </ul>
            </li>
            <li><a href=#>ДЛЯ ЛИЦА</a>
              <ul class="submenu">
                <li><h5 class="categoryText">КАТЕГОРИИ</h5></li>
                <li><a href=#>Маски</a></li>
                <li><a href=#>Сыворотки</a></li>
              </ul>
            </li>
          </ul>
        </div>
        <hr>

      </div>

      <div class="content" align="center">
        <spring:form action="change-password" method="post">
          <div class="form-group">
            <label>Текущий пароль:</label>
            <spring:input path="password"/>
          </div>
          <div class="form-group">
            <label>Новый пароль:</label>
            <spring:input path="newPassword"/>
          </div>
          <div class="form-group">
            <label>Повторите новый пароль:</label>
            <spring:input path="repeatNewPassword"/>
          </div>
          <spring:button>Сменить пароль</spring:button>
        </spring:form>
      </div>

      <div class="footer">
        <p class="footerText">2019</p>
      </div>
    </div>
  </div>

  <script type="text/javascript" src="<c:url value="resources/js/routingFromImages.js"/>"></script>
</body>

</html>