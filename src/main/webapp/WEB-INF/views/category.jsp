<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="j"%>
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
        <div id="thirdHeader">
          <ul class="menu">
            <li><a href="/category/fragrances">FRAGRANCES</a></li>
            <li><a href="/category/for_face">FOR FACE</a></li>
          </ul>
        </div>
        <hr>

      </div>

      <div class="content" align="left">

        <p class="categoryName">${categoryName}</p>

        <div class="productsBlock">
          <p>${emptyListMessage}</p>

          <ul class="productList">
            <j:forEach items="${products}" var="product" varStatus="tagStatus">
              <li>
                <div class="product">
                  <div class="productInfo">
                    <p class="productName">${product.name}</p>
                    <p class="productWeight">Weight: ${product.weight}г.</p>
                    <p class="productSize">Volume: ${product.volume}</p>
                  </div>
                  <div class="productInfo2">
                    <p class="productPrice">Price: ${product.price} руб.</p>
                    <p class="quantityInStock">Quantity in stock: ${product.quantityInStock}</p>
                    <button class="btn buyButton">BUY</button>
                  </div>
                </div>
              </li>
            </j:forEach>
          </ul>
        </div>
      </div>

      <div id="footer"></div>
    </div>
  </div>
  <script src="https://unpkg.com/ionicons@4.4.4/dist/ionicons.js"></script>
</body>
</html>