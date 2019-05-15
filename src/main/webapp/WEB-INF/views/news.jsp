<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="j" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="J" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="auth" uri="http://www.springframework.org/security/tags" %>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>Avon shop</title>
    <link rel="icon" href='<c:url value="/resources/images/favicon.ico" />' type="image/x-icon">

    <link rel="stylesheet" href='<c:url value="/resources/css/bootstrap.min.css" />'>
    <link rel="stylesheet" href='<c:url value="/resources/css/index.css" />'>
    <link rel="stylesheet" href='<c:url value="/resources/css/submenu.css" />'>
    <link rel="stylesheet" href='<c:url value="/resources/css/products.css" />'>

    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
</head>

<body>
<div class="wrapper">
    <div class="wrapperForFooter">

        <div id="header">
            <j:import url="common/header.jsp"/>
        </div>

        <div class="content">
            <div class="theNewsBlock">
                <div class="articleBlock">
                    <label class="postArticle">${news.article}</label>
                </div>
                <div class="writingDateBlock">
                    <label class="writing_date">${news.writingDate}</label>
                </div>
                <div class="textBlock">
                    <p class="text">${news.text}</p>
                </div>
                <a href="<j:url value="/"/>">
                    <button class="btn formButton">BACK</button>
                </a>
            </div>
        </div>

        <div id="footer">
            <j:import url="common/footer.jsp"/>
        </div>
    </div>
</div>
<script src="https://unpkg.com/ionicons@4.4.4/dist/ionicons.js"></script>
<script src="<c:url value="/resources/js/index.js"/>"></script>
</body>
</html>