<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="j"%>--%>
<%@ page contentType="text/html;charset=UTF-8"%>

<html>

<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <title>Avon shop</title>
  <link rel="icon" href='<c:url value="/resources/images/favicon.ico" />' type="image/x-icon">

  <link rel="stylesheet" href='<c:url value="/resources/css/bootstrap.min.css" />'>
  <link rel="stylesheet" href='<c:url value="/resources/css/index.css" />'>
  <link rel="stylesheet" href='<c:url value="/resources/css/submenu.css" />'>
  <link rel="stylesheet" href='<c:url value="/resources/css/category.css" />'>

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

      <div class="content" align="left">

        <p class="categoryName">${categoryName}</p>

        <%--"Fragrances №1", 345, new Category(categoryName), null, 40, "40x20x10", 100L--%>

        <ul class="productList">
          <li>
            <div class="product">
              <div class="productInfo">
                <p class="productName">Fragrances №1</p>
                <p class="productWeight">Вес: 40г.</p>
                <p class="productSize">Размеры: 40x20x10</p>
              </div>
              <div class="productInfo2">
                <p class="productPrice">Цена: 345 руб.</p>
                <p class="quantityInStock">Количество на складе: 100</p>
                <button class="btn buyButton">КУПИТЬ</button>
              </div>
            </div>
          </li>
          <li>
            <div class="product">
              <div class="productInfo">
                <p class="productName">Fragrances №2</p>
                <p class="productWeight">Вес: 40г.</p>
                <p class="productSize">Размеры: 40x20x10</p>
              </div>
              <div class="productInfo2">
                <p class="productPrice">Цена: 345 руб.</p>
                <p class="quantityInStock">Количество на складе: 100</p>
                <button class="btn buyButton">КУПИТЬ</button>
              </div>
            </div>
          </li>
        </ul>
        <%--<j:forEach items="${Clients.clients}" var="client" varStatus="tagStatus">--%>
          <%--<tr>--%>
            <%--<td>${client.firstName}</td>--%>
            <%--<td>${client.lastName}</td>--%>
            <%--<td>${client.email}</td>--%>
          <%--</tr>--%>
        <%--</j:forEach>--%>
      </div>

      <div id="footer"></div>
    </div>
  </div>
  <script src="https://unpkg.com/ionicons@4.4.4/dist/ionicons.js"></script>
</body>
</html>