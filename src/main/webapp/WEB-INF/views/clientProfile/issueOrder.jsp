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
       <form action="issue-order" method="post">
        <div class="row">
          <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3">
            <div class="leftPanel">
                <j:import url="../common/leftClientPanel.jsp"/>
            </div>
                <j:if test="${basket.products.size() != 0}">
                    <div class="orderInfoPanel">
                        <div class="clientAddress">
                            <div class="form-group">
                                <label>Choose your address</label>
                                <j:choose>
                                    <j:when test="${client.addresses.size() > 0}">
                                        <select name="addressId">
                                            <j:forEach items="${client.addresses}" var="address" varStatus="tagStatus">
                                                <option value="${address.id}">${address.postalCode}, ${address.country}, ${address.city}, ${address.street}, ${address.house}, ${address.room}</option>
                                            </j:forEach>
                                        </select>
                                    </j:when>
                                </j:choose>
                            </div>
                        </div>

                        <div class="orderInfo">
                            <div class="form-group">
                                <label>Enter delivery method</label>
                                <select name="deliveryMethod" required>
                                    <option>Post of Russia</option>
                                    <option>Avon service centers</option>
                                    <option>Home delivery</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label>Enter payment method</label>
                                <select name="paymentMethod" required>
                                    <option>By cash</option>
                                    <option>By card</option>
                                </select>
                                <%--by CARD block--%>
                            </div>
                        </div>
                        <input class="btn formButton" type="submit" value="ISSUE ORDER"/>
                    </div>
                </j:if>
          </div>

          <div class="col-xs-8 col-sm-8 col-md-8 col-lg-8 mainPanel">
            <div class="messageBlock">
              <label class="successMessage">${successMessage}</label>
              <label class="errorMessage">${errorMessage}</label>
            </div>
            <div class="variant issueOrderBlock">
              <form action="issue-order" method="post">
                <j:choose>
                  <j:when test="${basket.products.size() == 0}">
                    <p class="emptyProductsListLabel">You have not chosen anything yet.</p>
                    <p class="emptyProductsListLabel">Choose category above and do shopping right now!</p>
                  </j:when>
                  <j:otherwise>
                    <p class="emptyProductsListLabel" style="display: none">You have not chosen anything yet.</p>
                    <p class="emptyProductsListLabel" style="display: none">Choose category above and do shopping right now!</p>
                    <p class="headerText">Product list:</p>
                    <div class="productsBlock">
                      <ul class="productList">
                          <j:forEach items="${basket.products}" var="product" varStatus="tagStatus">
                            <li>
                                <div class="productBlock productBlock-${tagStatus.count}">
                                    <div class="product">
                                        <div class="productImage">
                                            <img src="${product.key.imgSrc}" alt="NO IMAGE"/>
                                        </div>
                                        <div class="productInfo">
                                            <div class="productInfo1">
                                                <p class="productName">${product.key.name}</p>
                                                <p class="productWeight">Weight: ${product.key.weight}г.</p>
                                                <p class="productSize">Volume: ${product.key.volume}</p>
                                            </div>
                                            <div class="productInfo2">
                                                <p class="productPrice">Price: ${product.key.price} руб.</p>
                                                <p class="quantityInStock">Quantity in stock: ${product.key.quantityInStock}</p>
                                                <input name="id" value="${product.key.id}" hidden/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="productOperations">
                                        <img class="remove remove-${tagStatus.count}" src="<c:url value="/resources/images/cross.jpeg"/>" alt="X"/>
                                        <img class="plus plus-${tagStatus.count}" src="<c:url value="/resources/images/plus.jpeg"/>" alt="+"/>
                                        <img class="minus minus-${tagStatus.count}" src="<c:url value="/resources/images/minus.jpeg"/>" alt="-"/>
                                        <label class="numberOfProduct numberOfProduct-${tagStatus.count}">${product.value}</label>
                                    </div>
                                </div>
                            </li>
                          </j:forEach>
                      </ul>
                    </div>
                  </j:otherwise>
                </j:choose>
              </form>
            </div>
          </div>

        </div>
       </form>

        </div>

      <div id="footer">
        <j:import url="../common/footer.jsp"/>
      </div>
    </div>
  </div>

  <script type="text/javascript" src="<c:url value="/resources/js/checkForms.js"/>"></script>
  <script type="text/javascript" src="<c:url value="/resources/js/issueOrder.js"/>"></script>
  <script>
      // Маска для полей
      $('.editProfileBlock input[name="birthday"]').mask('99-99-9999');
  </script>
  <script src="https://unpkg.com/ionicons@4.4.4/dist/ionicons.js"></script>
</body>

</html>