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
  <script>
      $(function(){
          $("#footer").load("<c:url value="/resources/jsp/footer.jsp"/>");
          $("#largeHeader").load("<c:url value="/resources/jsp/largeHeader.jsp"/>");
          $("#secondHeader").load("<c:url value="/resources/jsp/secondHeader.jsp"/>");
      });
  </script>
</head>

<body>
  <div class="wrapper">
    <div class="wrapperForFooter">

      <div class="headers">
        <div id="firstHeader">
          <div id="largeHeader"></div>

          <div class="mobileHeader">
            <div class="menuButtonBlock">
              <button class="menuButton">
                <ion-icon class="ion-navicon" name="menu"></ion-icon>
              </button>
              <nav class="mobileMenu">
                <ul>
                  <j:forEach items="${categories}" var="category" varStatus="tagStatus">
                    <li><a href="/category/${category.name}">${category.name}</a></li>
                  </j:forEach>
                  <li class="divider"></li>
                  <hr>
                  <li><a href="/registration">REGISTER</a></li>
                  <li><a href="http://smartavon.ru/Registration.html">BECOME A REPRESENTATIVE</a></li>
                </ul>
              </nav>
            </div>
            <div class="logoBlock">
              <a href="#">
                <img class="logo" alt="AVON" src="/resources/images/logo.png" width="500px">
              </a>
            </div>
            <div class="lastBlock">
              <div>
                <img id="basket" src="/resources/images/basket.png" alt="AVON"><br/>
                <div id="basketInfo">
                  <p class="emptyBasket">Your bag is empty</p>
                </div>
              </div>
              <div>
                <a href="login">
                  <img id="login" src="/resources/images/login.png"/>
                </a>
              </div>
            </div>
          </div>
        </div>

        <div id="secondHeader"></div>

        <hr>
        <div id="thirdHeader">
          <ul class="menu">
            <j:forEach items="${categories}" var="category" varStatus="tagStatus">
              <li><a href="/category/${category.name}">${category.name}</a></li>
            </j:forEach>
            </li>
          </ul>
        </div>
        <hr>

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

      <div id="footer"></div>
    </div>
  </div>
  <script src="https://unpkg.com/ionicons@4.4.4/dist/ionicons.js"></script>
</body>
</html>