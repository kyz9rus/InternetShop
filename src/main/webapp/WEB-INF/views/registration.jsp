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
          $("#footer").load("<c:url value="/resources/jsp/footer.jsp"/>");
          $("#firstHeader").load("<c:url value="/resources/jsp/firstHeader.jsp"/>");
          $("#secondHeader").load("<c:url value="/resources/jsp/secondHeader.jsp"/>");
      });
  </script>
</head>

<body>
  <div class="wrapper">
    <div class="wrapperForFooter">

      <div class="headers">
          <div id="firstHeader"></div>

          <div id="secondHeader"></div>

        <hr>
        <div id="thirdHeader" class="row">
          <ul class="menu">
            <li><a href="category/fragrances">FRAGRANCES</a></li>
            <li><a href="category/forFace">FOR FACE</a></li>
          </ul>
        </div>
        <hr>

      </div>

      <div class="content" align="center">
        <div class="registrationBlock" align="left">
          <spring:form method="post" modelAttribute="client" action="create-client">
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
            <button class="btn btn-primary formButton">REGISTER</button>
          </spring:form>

          <label class="formMessage"></label>
          <label class="successMessage">${successMessage}</label>
          <label class="errorMessage">${errorMessage}</label>
        </div>
      </div>

      <div id="footer"></div>

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