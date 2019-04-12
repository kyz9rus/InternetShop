<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="j" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>

<html>

<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <title>Exception</title>
  <link rel="icon" href='<c:url value="/resources/images/favicon.ico" />' type="image/x-icon">

  <link rel="stylesheet" href='<c:url value="/resources/css/bootstrap.min.css" />'>
  <link rel="stylesheet" href='<c:url value="/resources/css/index.css" />'>
  <link rel="stylesheet" href='<c:url value="/resources/css/submenu.css" />'>

  <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
</head>

<body>
  <div class="wrapper">
    <div class="wrapperForFooter">

      <div id="header">
        <j:import url="common/header.jsp"/>
      </div>

      <div class="content" align="center">
        <div class="errorBlock">
          <div class="errorInfoBlock">
            <div class="image404Block">
              <j:choose>
                <j:when test="${responseInfo.statusCode == 404}">
                  <img alt="404" src="<c:url value="/resources/images/404.png"/>">
                </j:when>
                <j:when test="${responseInfo.statusCode == 500}">
                  <img alt="500" src="<c:url value="/resources/images/500.png"/>">
                </j:when>
              </j:choose>
            </div>
            <div>
              <p>${fn:toUpperCase(responseInfo.message)}</p>
              <a href="<j:url value="/"/>">
                <button class="btn formButton">MAIN PAGE</button>
              </a>
            </div>
          </div>
          <div class="errorImageBlock">
            <img src="<c:url value="/resources/images/errorImage.png"/>">
          </div>
        </div>
      </div>

      <div id="footer">
        <j:import url="common/footer.jsp"/>
      </div>
    </div>
  </div>
  <script src="https://unpkg.com/ionicons@4.4.4/dist/ionicons.js"></script>
</body>
</html>