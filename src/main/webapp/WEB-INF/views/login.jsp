<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="j" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Please Sign in</title>
    <link rel="stylesheet" href='<c:url value="/resources/css/login.css" />'>
    <link rel="stylesheet" href='<c:url value="/resources/css/bootstrap.min.css" />'>
</head>
<body>
<div class="container">
    <div class="form-signin">
        <form method="post" action="perform_login">
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
        <div class="buttons">
            <a href="<j:url value="/"/> ">
                <button class="btn formButton">BACK</button>
            </a>
            <a href="<j:url value="/registration"/">
                <button class="btn formButton">REGISTER</button>
            </a>
        </div>
    </div>
</div>
</body>
</html>