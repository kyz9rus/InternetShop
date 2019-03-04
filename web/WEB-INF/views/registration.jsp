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
        <div class="firstHeader"></div>

        <div class="secondHeader"></div>

        <hr>
        <div class="thirdHeader row">
          <ul class="menu">
            <li><a href=#>FRAGRANCES</a>
              <ul class="submenu">
                <li><h5 class="categoryText">CATEGORIES</h5></li>
                <li><a href=#>Men's fragrances</a></li>
                <li><a href=#>Women's fragrances</a></li>
              </ul>
            </li>
            <li><a href=#>FOR FACE</a>
              <ul class="submenu">
                <li><h5 class="categoryText">CATEGORIES</h5></li>
                <li><a href=#>Masks</a></li>
                <li><a href=#>Sera</a></li>
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
              <label>Last name</label>
              <spring:input path="lastName"/>
            </div>
            <div class="form-group">
              <label>First name</label>
              <spring:input path="firstName"/>
            </div>
            <div class="form-group">
              <label>Birthday</label>
              <spring:input path="birthday"/>
            </div>
            <div class="form-group">
              <label>Email</label>
              <spring:input path="email"/>
            </div>
            <div class="form-group">
              <label>Password</label>
              <spring:input type="password" path="password"/>
            </div>
            <div class="form-group">
              <label>Repeat password</label>
              <input type="password" name="repeatPassword"/>
              <%--<spring:input type="password" path="repeatPassword"/>--%>
            </div>
            <spring:button class="btn btn-primary formButton">REGISTER</spring:button>
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
  <script src="https://unpkg.com/ionicons@4.4.4/dist/ionicons.js"></script>
</body>

</html>