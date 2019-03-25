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
                <form action="${categoryName}/put-product" method="post">
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

                        <input name="id" value="${product.id}" hidden/>
                      </div>
                    </div>
                  </div>

                  <input name="imgSrc" value="${product.imgSrc}" style="display: none"/>
                  <input name="name" value="${product.name}" style="display: none"/>
                  <input name="weight" value="${product.weight}" style="display: none"/>
                  <input name="volume" value="${product.volume}" style="display: none"/>
                  <input name="price" value="${product.price}" style="display: none"/>
                  <input name="quantityInStock" value="${product.quantityInStock}" style="display: none"/>
                  <input name="category" value="${categoryName}" style="display: none"/>

                </form>
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