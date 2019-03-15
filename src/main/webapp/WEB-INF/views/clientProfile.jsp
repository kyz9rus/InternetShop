<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="j" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8"%>

<html>

<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <title>Client profile</title>
  <link rel="icon" href='<c:url value="/resources/images/favicon.ico" />' type="image/x-icon">

  <link rel="stylesheet" href='<c:url value="/resources/css/bootstrap.min.css" />'>
  <link rel="stylesheet" href='<c:url value="/resources/css/index.css" />'>
  <link rel="stylesheet" href='<c:url value="/resources/css/submenu.css" />'>

  <link rel="stylesheet" href='<c:url value="/resources/css/profile.css" />'>

  <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
  <script>
      $(function(){
          $("#footer").load("<c:url value="/resources/jsp/footer.jsp"/>");
          $("#largeHeader").load("<c:url value="/resources/jsp/largeHeader.jsp"/>");
          $("#secondHeader").load("<c:url value="/resources/jsp/secondHeader.jsp"/>");
          $("#leftClientPanel").load("<c:url value="/resources/jsp/leftClientPanel.jsp"/>");
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
          </ul>
        </div>
        <hr>

      </div>

      <div class="content">
        <div class="row">
          <div id="leftClientPanel" class="col-xs-3 col-sm-3 col-md-3 col-lg-3 leftPanel"></div>

          <div class="col-xs-1 col-sm-1 col-md-1 col-lg-1"></div>
          <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6 mainPanel">
            <div class="messageBlock">
              <label class="successMessage">${successMessage}</label>
              <label class="errorMessage">${errorMessage}</label>
            </div>

            <p>Choose action in left panel</p>
          </div>

          <div class="col-xs-2 col-sm-2 col-md-2 col-lg-2"></div>
        </div>


      </div>

      <div id="footer"></div>
    </div>
  </div>

  <script type="text/javascript" src="<c:url value="/resources/js/routingFromImages.js"/>"></script>
  <script type="text/javascript" src="<c:url value="/resources/js/checkForms.js"/>"></script>
  <script src="https://unpkg.com/ionicons@4.4.4/dist/ionicons.js"></script>
</body>

</html>