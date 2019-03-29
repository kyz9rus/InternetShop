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
  <link rel="stylesheet" href='<c:url value="/resources/css/powerange.css" />' />

  <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
  <script src="<c:url value="/resources/js/powerange.js" />"></script>
</head>

<body>
  <div class="wrapper">
    <div class="wrapperForFooter">

      <div id="header">
        <j:import url="common/header.jsp"/>
      </div>

      <div class="content" align="left">
        <div class="contentHeader">
          <p class="categoryName">FRANGRANCES</p>

          <div class="filters">
            <div class="searchBlock">
              <input class="searchProduct" placeholder="Search">
            </div>
            <div class="form-group priceFilterBlock">
              <label class="filterName">Price:</label>
              <input class="js-range"/>
            </div>
            <label class="filterValue">93</label>
            <div class="inStockInfo">
              <label>In stock</label>
              <input type="checkbox" class="inStockCheckbox">
            </div>
          </div>

          <div class="sorts">

          </div>
        </div>


        <div class="productsBlock">
          <p>${emptyListMessage}</p>

          <ul class="productList">
            <j:forEach items="${products}" var="product" varStatus="tagStatus">
              <li>
                  <div class="product product-${tagStatus.count}">
                    <div class="productImage" align="center">
                      <img src="${product.imgSrc}" alt="NO IMAGE"/>
                    </div>
                    <div class="productInfo">
                      <div class="productInfo1">
                        <p class="productName">${product.name}</p>
                        <p class="productWeight">Weight: ${product.weight}g.</p>
                        <p class="productSize">Volume: ${product.volume}</p>
                      </div>
                      <div class="productInfo2">
                        <p class="productPrice">Price: ${product.price} rubles.</p>
                        <p class="quantityInStock">Quantity in stock: ${product.quantityInStock}</p>
                        <button class="btn buyButton buyButton-${tagStatus.count}">BUY</button>

                        <input name="id" value="${product.id}" hidden/>
                        <input name="price" class="productPriceValue" value="${product.price}" hidden/>
                        <input name="quantityInStock" class="quantityInStockValue" value="${product.quantityInStock}" hidden/>
                      </div>
                    </div>
                  </div>
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
  <script src="<c:url value="/resources/js/category.js"/>"></script>
</body>
</html>