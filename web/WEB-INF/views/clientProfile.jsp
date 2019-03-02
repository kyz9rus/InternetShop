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

  <link rel="stylesheet" href='<c:url value="/resources/css/profile.css" />'>

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
                <li></li>
              </ul>
            </li>
            <li><a href=#>ДЛЯ ЛИЦА</a>
              <ul class="submenu">
                <li><h5 class="categoryText">КАТЕГОРИИ</h5></li>
                <li><a href=#>Маски</a></li>
                <li><a href=#>Сыворотки</a></li>
                <li></li>
              </ul>
            </li>
          </ul>
        </div>
        <hr>

      </div>

      <div class="content">
        <div class="row">
          <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3 leftPanel">
            <ul>
              <li><h5>Профиль</h5></li>
              <li class="operation"><label>Посмотреть профиль</label></li>
              <li class="operation"><label>Изменить данные профиля</label></li>
              <li class="operation"><label>Сменить пароль</label></li>
              <li class="divider"></li>
              <hr>
              <li><h5>Заказы</h5></li>
              <li class="operation"><label>Оформление заказа</label></li>
              <li class="operation"><label>Просмотр истории заказов</label></li>
              <li class="operation"><label>Повторить заказ</label></li>
            </ul>
          </div>

          <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6 mainPanel">
            <div class="variant showProfile">

              <div class="mainPanelBlock">
                <p class="headerText">Основное</p>
                <div class="subBlock">
                  <ul>
                    <li><label>Фамилия:</label> <label>${clientDto.lastName}</label></li>
                    <li><label>Имя:</label> <label>${clientDto.firstName}</label></li>
                  </ul>
                </div>
              </div>

              <div class="mainPanelBlock">
                <p class="headerText">Дополнительно</p>
                <div class="subBlock">
                  <ul>
                    <li><label>Дата рождения:</label> <label>${clientDto.birthday}</label></li>
                    <li><label>Адрес электронной почты:</label> <label>${clientDto.email}</label></li>
                  </ul>
                </div>
              </div>
            </div>

            <div class="variant editProfile">
              <spring:form action="change-client" method="post" modelAttribute="clientDto">
                <div class="mainPanelBlock">
                  <p class="headerText">Основное</p>
                  <div class="subBlock">
                    <div class="form-group">
                      <label>Фамилия</label>
                      <spring:input path="lastName"/>
                    </div>

                    <div class="form-group">
                      <label>Имя</label>
                      <spring:input path="firstName"/>
                    </div>
                  </div>
                </div>

                <div class="mainPanelBlock">
                  <p class="headerText">Дополнительно</p>
                  <div class="subBlock">
                    <div class="form-group">
                      <label>Дата рождения</label>
                      <spring:input path="birthday"/>
                    </div>

                    <div class="form-group">
                      <label>Адрес электронной почты</label>
                      <spring:input type="email" path="email"/>
                    </div>
                  </div>
                </div>
              </spring:form>
            </div>
          </div>

          <div class="changePassword">
            <%--<spring:form action="change-password" method="post">--%>
            <%--<div class="form-group">--%>
            <%--<label>Текущий пароль:</label>--%>
            <%--<spring:input path="password"/>--%>
            <%--</div>--%>
            <%--<div class="form-group">--%>
            <%--<label>Новый пароль:</label>--%>
            <%--<spring:input path="newPassword"/>--%>
            <%--</div>--%>
            <%--<div class="form-group">--%>
            <%--<label>Повторите новый пароль:</label>--%>
            <%--<spring:input path="repeatNewPassword"/>--%>
            <%--</div>--%>
            <%--<spring:button>Сменить пароль</spring:button>--%>
            <%--</spring:form>--%>
          </div>

        <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3"></div>

        </div>


      </div>

      <div class="footer">
        <p class="footerText">2019</p>
      </div>
    </div>
  </div>

  <script type="text/javascript" src="<c:url value="resources/js/routingFromImages.js"/>"></script>
  <script type="text/javascript" src="<c:url value="resources/js/displayBlocks.js"/>"></script>
</body>

</html>