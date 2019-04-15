<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="j" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8"%>

<html>

<head>
  <title>Registration</title>
  <link rel="icon" href='<c:url value="/resources/images/favicon.ico" />' type="image/x-icon">

  <link rel="stylesheet" href='<c:url value="/resources/css/bootstrap.min.css" />'>
  <link rel="stylesheet" href='<c:url value="/resources/css/index.css" />'>
  <link rel="stylesheet" href='<c:url value="/resources/css/submenu.css" />'>
  <link rel="stylesheet" href='<c:url value="/resources/css/registration.css" />'>

  <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.10/jquery.mask.js"></script>
</head>

<body>
  <div class="wrapper">
    <div class="wrapperForFooter">

      <div id="header">
        <j:import url="common/header.jsp"/>
      </div>

      <div class="content" align="center">
        <div class="registrationBlock" align="left">
          <spring:form method="post" action="client" modelAttribute="client">
            <div class="form-group">
              <label>Last name</label>
              <input name="lastName" required />
            </div>
            <div class="form-group">
              <label>First name</label>
              <input name="firstName" required/>
            </div>
            <div class="form-group">
              <label>Birthday</label>
              <input name="birthday"/>
            </div>
            <div class="form-group">
              <label>Email</label>
              <input name="email" type="email" required/>
            </div>
            <div class="form-group">
              <label>Password</label>
              <input type="password" name="password" required/>
            </div>
            <div class="form-group">
              <label>Repeat password</label>
              <input type="password" name="repeatPassword" required/>
            </div>
            <button class="btn formButton">REGISTER</button>
          </spring:form>

          <label class="formMessage"></label>
          <label class="successMessage">${successMessage}</label>
          <label class="errorMessage">${errorMessage}</label>
        </div>
      </div>

      <div id="footer">
        <j:import url="common/footer.jsp"/>
      </div>

      <script src="/resources/js/checkForms.js"></script>
      <script>
          // Маска для полей
          $('.registrationBlock input[name="birthday"]').mask('99-99-9999');
      </script>
    </div>
  </div>
  <script src="https://unpkg.com/ionicons@4.4.4/dist/ionicons.js"></script>
  <script type="text/javascript" src="<c:url value="resources/js/displayBlocks.js"/>"></script>
</body>

</html>