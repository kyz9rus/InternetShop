<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8"%>

<html>

<head>
  <title>Registration page</title>
  <link rel="icon" href='<c:url value="/resources/images/favicon.ico" />' type="image/x-icon">

  <link rel="stylesheet" href='<c:url value="/resources/css/bootstrap.min.css" />'>
  <link rel="stylesheet" href='<c:url value="/resources/css/index.css" />'>
  <link rel="stylesheet" href='<c:url value="/resources/css/submenu.css" />'>
  <link rel="stylesheet" href='<c:url value="/resources/css/registration.css" />'>

  <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.10/jquery.mask.js"></script>
  <script>
      $(function(){
          $(".footer").load("<c:url value="/resources/jsp/footer.jsp"/>");
          $(".firstHeader").load("<c:url value="/resources/jsp/firstHeader.jsp"/>");
          $(".secondHeader").load("<c:url value="/resources/jsp/secondHeader.jsp"/>");
      });
  </script>
</head>

<body>
  <div class="wrapper">
    <div class="wrapperForFooter">

      <div class="headers">
        <div class="firstHeader row"></div>

        <div class="secondHeader"></div>

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
        <div class="registrationBlock" align="left">
          <spring:form method="post" modelAttribute="client" action="create-client">
            <div class="form-group">
              <label>Фамилия</label>
              <spring:input path="lastName"/>
            </div>
            <div class="form-group">
              <label>Имя</label>
              <spring:input path="firstName"/>
            </div>
            <div class="form-group">
              <label>Дата рождения</label>
              <spring:input path="birthday"/>
            </div>
            <div class="form-group">
              <label>Адрес электронной почты</label>
              <spring:input path="email"/>
            </div>
            <div class="form-group">
              <label>Пароль</label>
              <spring:input type="password" path="password"/>
            </div>
            <div class="form-group">
              <label>Повторите пароль</label>
              <input type="password" name="repeatPassword"/>
              <%--<spring:input type="password" path="repeatPassword"/>--%>
            </div>
            <spring:button class="btn btn-primary">REGISTER</spring:button>
          </spring:form>

          <label class="formMessage"></label>
        </div>
      </div>

      <div class="footer"></div>

      <script src="/resources/js/checkRegistrationForm.js"></script>
      <script>
          // Маска для полей
          $('.registrationBlock input[name="birthday"]').mask('99-99-9999');
      </script>
    </div>
  </div>
</body>

</html>