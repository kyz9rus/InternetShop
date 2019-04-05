<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="j" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="J" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="auth" uri="http://www.springframework.org/security/tags" %>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>

<html>

<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <title>Avon shop</title>
  <link rel="icon" href='<c:url value="/resources/images/favicon.ico" />' type="image/x-icon">

  <link rel="stylesheet" href='<c:url value="/resources/css/bootstrap.min.css" />'>
  <link rel="stylesheet" href='<c:url value="/resources/css/index.css" />'>
  <link rel="stylesheet" href='<c:url value="/resources/css/submenu.css" />'>
  <link rel="stylesheet" href='<c:url value="/resources/css/products.css" />'>

  <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
</head>

<body>
  <div class="wrapper">
    <div class="wrapperForFooter">

      <div id="header">
        <j:import url="common/header.jsp"/>
      </div>

      <div class="content" align="center">
        <div class="couponWindow">
          <img src="/resources/images/cross.png" alt="X">
          <p class="text-danger">Waiting...</p>
          <button class="btn formButton">OK</button>
        </div>

        <div class="becomeRepresentativeBlock">
          <div class="row">
            <div class="col-xs-2 col-sm-2 col-md-2 col-lg-2 presentImage">
              <img src="<c:url value="/resources/images/present.png" />"/>
            </div>
            <div class="col-xs-7 col-sm-7 col-md-7 col-lg-7">
              <p class="article">Do you want a 30% discount on a first order?</p>
              <p class=>Get a promo code right now!</p>
            </div>
            <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3 buttonBlock">
              <auth:authorize access="hasRole('EMPLOYEE') || isAnonymous()">
                <a href="<j:url value="/login" />">
                  <button class="btn formButton ignoreScripts">GET IT</button>
                </a>
              </auth:authorize>
              <auth:authorize access="hasRole('CLIENT')">
                <button class="btn formButton getCouponButton">GET IT</button>
                <input name="email" value="${client.email}" hidden/>
              </auth:authorize>
            </div>
          </div>
        </div>

        <div class="topProductsBlock">
              <j:if test="${products.size() != 0}">
                <p>Top-10 products:</p>
                <ul class="topClientsList">
                  <j:forEach items="${products}" var="product" varStatus="tagStatus">
                    <li>
                      <div class="productBlock">
                        <div class="product product-${tagStatus.count}">
                          <div class="productImage" align="center">
                            <img src="${product.imgSrc}" alt="NO IMAGE"/>
                          </div>
                          <div class="productInfo">
                            <div class="productInfo1">
                              <p class="productName">${product.name}</p>
                              <p class="productWeight">Weight: ${product.weight}г.</p>
                              <p class="productSize">Volume: ${product.volume}</p>
                            </div>
                            <div class="productInfo2">
                              <p class="productPrice">Price: ${product.price} rubles.</p>
                              <j:choose>
                                <j:when test="${product.quantityInStock != 0}">
                                  <p class="quantityInStock">Quantity in stock: ${product.quantityInStock}</p>
                                  <button class="btn buyButton buyButton-${tagStatus.count}">BUY</button>
                                </j:when>
                                <j:otherwise>
                                  <p class="quantityInStock">Product is temporarily out of stock</p>
                                </j:otherwise>
                              </j:choose>
                              <input name="id" value="${product.id}" hidden/>
                            </div>
                          </div>
                        </div>
                      </div>
                    </li>
                  </j:forEach>
                </ul>
              </j:if>
        </div>
      </div>

      <div id="footer">
        <j:import url="common/footer.jsp"/>
      </div>
    </div>
  </div>
  <script src="https://unpkg.com/ionicons@4.4.4/dist/ionicons.js"></script>
  <script src="<c:url value="/resources/js/index.js"/>"></script>
</body>
</html>