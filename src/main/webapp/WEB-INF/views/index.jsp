<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="j" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>

<html>

<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <title>Avon shop</title>
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

        <div class="becomeRepresentativeBlock">
          <div class="row">
            <div class="col-xs-2 col-sm-2 col-md-2 col-lg-2 presentImage">
              <img src="/resources/images/present.png"/>
            </div>
            <div class="col-xs-7 col-sm-7 col-md-7 col-lg-7">
              <p class="article">Do you want a 30% discount and a prize?</p>
              <p class=>Become a representative right now!</p>
            </div>
            <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3 buttonBlock">
              <a href="http://smartavon.ru/Registration.html">
                <button class="btn formButton">More details</button>
              </a>
            </div>
          </div>
        </div>

        <div>
          <%-- product top --%>
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