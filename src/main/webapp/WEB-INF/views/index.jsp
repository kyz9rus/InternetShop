<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="j"%>
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
            <li><a href="/category/fragrances">FRAGRANCES</a>
              <ul class="submenu">
                <li><h5 class="categoryText">CATEGORIES</h5></li>
                <li><a href=#>Men's fragrances</a></li>
                <li><a href=#>Women's fragrances</a></li>
              </ul>
            </li>
            <li><a href="/category/forFace">FOR FACE</a>
              <ul class="submenu">
                <li><h5 class="categoryText">CATEGORIES</h5></li>
                <li><a href=#>Masks</a></li>
                <li><a href=#>Sera</a></li>
              </ul>
            </li>
          </ul>
        </div>
        <hr>

      </div>

      <div class="content row" align="center">

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
                <button class="btn btn-primary formButton">More details</button>
              </a>
            </div>
          </div>


        </div>

        <j:forEach items="${Clients.clients}" var="client" varStatus="tagStatus">
          <tr>
            <td>${client.firstName}</td>
            <td>${client.lastName}</td>
            <td>${client.email}</td>
          </tr>
        </j:forEach>
      </div>

      <div id="footer"></div>
    </div>
  </div>
  <script src="https://unpkg.com/ionicons@4.4.4/dist/ionicons.js"></script>
</body>
</html>