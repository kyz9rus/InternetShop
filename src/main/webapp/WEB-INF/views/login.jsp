<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Please Sign in</title>
    <link rel="stylesheet" href='<c:url value="/resources/css/login.css" />'>
    <link rel="stylesheet" href='<c:url value="/resources/css/bootstrap.min.css" />'>
</head>
<body>
<div class="container">
    <form class="form-signin" method="post" action="perform_login">
        <h2 class="form-signin-heading">Please sign in</h2>
        <p>
            <label for="username" class="sr-only">Username</label>
            <input type="text" id="username" name="username" class="form-control" placeholder="Username" required="" autofocus="">
        </p>
        <p>
            <label for="password" class="sr-only">Password</label>
            <input type="password" id="password" name="password" class="form-control" placeholder="Password" required="">
        </p>
        <button class="btn btn-lg formButton btn-block" type="submit">Sign in</button>
    </form>
</div>
</body>
</html>