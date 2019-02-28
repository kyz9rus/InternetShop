<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8"%>

<html>

<head>
  <title>Index Page</title>
</head>

<body>
  <spring:form method="post"  modelAttribute="userJSP" action="check-user">

    <p>Name:</p> <spring:input path="name"/><br/>
    <p>Password:</p> <spring:input path="password"/><br/>
    <spring:button>Next Page</spring:button>

  </spring:form>

</body>

</html>