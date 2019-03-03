<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8"%>

<html>

<head>
  <title>Login Page</title>
  <link rel="icon" href='<c:url value="/resources/images/favicon.ico" />' type="image/x-icon">

  <link rel="stylesheet" href='<c:url value="/resources/css/bootstrap.min.css" />'>
  <link rel="stylesheet" href='<c:url value="/resources/css/index.css" />'>
  <link rel="stylesheet" href='<c:url value="/resources/css/login.css" />'>
</head>

<body>
  <div class="wrapper">
    <div class="wrapperForFooter">

      <div class="header1 row">
        <div class="col-md-6">
          <a href="http://smartavon.ru/Registration.html">СТАТЬ ПРЕДСТАВИТЕЛЕМ</a>
        </div>
        <div class="col-md-6">

        </div>
      </div>

      <div class="content row" align="center">
        <%--<spring:form method="post" modelAttribute="clientDtoJSP" action="create-client">
          <p>First name</p> <spring:input path="firstName"/>
          <p>Last name</p> <spring:input path="lastName"/>
          <p>Birthday</p> <spring:input  path="birthday"/>
          <p>Email</p> <spring:input type="email" path="email"/>
          <p>Password</p> <spring:input path="password"/>
          <spring:button>POST</spring:button>
        </spring:form>--%>
      </div>

      <div class="footer"></div>

    </div>
  </div>
</body>

</html>