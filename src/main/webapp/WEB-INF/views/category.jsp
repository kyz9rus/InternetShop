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
</head>

<body>
  <div class="wrapper">
    <div class="wrapperForFooter">

      <div id="header">
        <j:import url="common/header.jsp"/>
      </div>

      <div class="content" align="left">

        <p class="categoryName">${categoryName}</p>

        <div class="productsBlock">
          <p>${emptyListMessage}</p>

          <ul class="productList">
            <j:forEach items="${products}" var="product" varStatus="tagStatus">
              <li>
                <%--<form action="/busket/put-product" method="post">--%>
                <%--<div class="product">
                      <div class="productImage">
                        <img src="https://avatars.mds.yandex.net/get-pdb/1620281/5b427da9-dd9e-4385-9fff-45c78d96f07e/s1200" alt="NO IMAGE">

                    </div><div class="productInfo1">
                      <div class="productInfo">
                      <p class="productName">Fragrancy #1</p>
                      <p class="productWeight">Weight: 100.0г.</p>
                      <p class="productSize">Volume: 120x12x129</p>
                    </div>
                    <div class="productInfo2">
                      <p class="productPrice">Price: 123 руб.</p>
                      <p class="quantityInStock">Quantity in stock: 12</p>
                      <button class="btn buyButton">BUY</button>
                    </div>
                    </div></div>--%>

                  <div class="product">
                    <div class="productImage">
                      <img src="${product.imgSrc}" alt="NO IMAGE"/>
                    </div>
                    <div class="productInfo">
                      <div class="productInfo1">
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
                  </div>
                <%--</form>--%>
              </li>
            </j:forEach>
          </ul>
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