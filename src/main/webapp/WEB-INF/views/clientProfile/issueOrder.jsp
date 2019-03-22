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

  <link rel="stylesheet" href='<c:url value="/resources/css/clientProfile.css" />'>

  <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.10/jquery.mask.js"></script>
</head>

<body>
  <div class="wrapper">
    <div class="wrapperForFooter">

      <div id="header">
        <j:import url="../common/header.jsp"/>
      </div>

      <div class="content">
        <div class="row">
          <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3 leftPanel">
            <j:import url="../common/leftClientPanel.jsp"/>
          </div>

          <div class="col-xs-1 col-sm-1 col-md-1 col-lg-1"></div>
          <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6 mainPanel">
            <div class="messageBlock">
              <label class="successMessage">${successMessage}</label>
              <label class="errorMessage">${errorMessage}</label>
            </div>
            <div class="variant issueOrderBlock">
              <form action="issue-order" method="post">
                <j:choose>
                  <j:when test="${basket.products.size() == 0}">
                    <p>You have not chosen anything yet.</p>
                    <p>Choose category above and do shopping right now!</p>
                  </j:when>
                  <j:otherwise>
                    <p>Product list:</p>
                    <ul class="productList">
                      <j:forEach items="${basket.products}" var="product" varStatus="tagStatus">
                        <li>
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
                              </div>
                            </div>
                          </div>
                        </li>
                      </j:forEach>
                    </ul>

                    <div class="clientAddress">
                      <p>Enter your address</p>
                      <div class="form-group">
                        <label>Country</label>
                        <input name="country" required />
                      </div>
                      <div class="form-group">
                        <label>City</label>
                        <input name="city" required />
                      </div>
                      <div class="form-group">
                        <label>Postal code</label>
                        <input name="postalCode" required />
                      </div>
                      <div class="form-group">
                        <label>Street</label>
                        <input name="street" required />
                      </div>
                      <div class="form-group">
                        <label>House</label>
                        <input name="house" required />
                      </div>
                      <div class="form-group">
                        <label>Room</label>
                        <input name="room" required />
                      </div>
                    </div>

                    <div class="orderInfo">
                      <div class="form-group">
                        <label>Enter delivery method</label>
                        <input name="deliveryMethod" required />
                      </div>
                      <div class="form-group">
                        <label>Enter payment method</label>
                        <input name="paymentMethod" required />
                      </div>
                    </div>
                  </j:otherwise>
                </j:choose>
              </form>
            </div>
          </div>

          </div>

          <div class="col-xs-2 col-sm-2 col-md-2 col-lg-2"></div>
        </div>

      <div id="footer">
        <j:import url="../common/footer.jsp"/>
      </div>
    </div>
  </div>

  <script type="text/javascript" src="<c:url value="/resources/js/routingFromImages.js"/>"></script>
  <script type="text/javascript" src="<c:url value="/resources/js/checkForms.js"/>"></script>
  <script>
      // Маска для полей
      $('.editProfileBlock input[name="birthday"]').mask('99-99-9999');
  </script>
  <script src="https://unpkg.com/ionicons@4.4.4/dist/ionicons.js"></script>
</body>

</html>