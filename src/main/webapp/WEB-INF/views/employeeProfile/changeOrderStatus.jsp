<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="j" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>

<html>

<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <title>Employee profile</title>
  <link rel="icon" href='<c:url value="/resources/images/favicon.ico" />' type="image/x-icon">

  <link rel="stylesheet" href='<c:url value="/resources/css/bootstrap.min.css" />'>
  <link rel="stylesheet" href='<c:url value="/resources/css/index.css" />'>
  <link rel="stylesheet" href='<c:url value="/resources/css/submenu.css" />'>

  <link rel="stylesheet" href='<c:url value="/resources/css/profile.css" />'>

  <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.10/jquery.mask.js"></script>
  <script>
      $(function(){
          $("#footer").load("<c:url value="/resources/jsp/footer.jsp"/>");
          $("#largeHeader").load("<c:url value="/resources/jsp/largeHeader.jsp"/>");
          $("#secondHeader").load("<c:url value="/resources/jsp/secondHeader.jsp"/>");
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
          <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3 leftPanel">
              <ul>
                  <li><h5>Orders</h5></li>
                  <li class="employeeOperation">
                      <a href="/employeeProfile/get-orders">
                          <label>View orders</label>
                      </a>
                  </li>
                  <li class="employeeOperation">
                      <a href="/employeeProfile/changeOrderStatus">
                          <label>Change order status</label>
                      </a>
                  </li>
                  <li class="divider"></li>

                  <li class="employeeOperation">
                      <a href="/employeeProfile/saleStatistic">
                          <label>Sales statistics</label>
                      </a>
                  </li>
                  <li class="divider"></li>

                  <li><h5>Products</h5></li>
                  <li class="employeeOperation">
                      <a href="/employeeProfile/addProduct">
                          <label>Add product</label>
                      </a>
                  </li>
                  <li class="employeeOperation">
                      <a href="/employeeProfile/manageCategories">
                          <label>Creating and managing categories of the directory</label>
                      </a>
                  </li>
                  <li class="employeeOperation">
                      <a href="/employeeProfile/import-from-file">
                          <label>Import from file</label>
                      </a>
                  </li>
              </ul>
          </div>

          <div class="col-xs-1 col-sm-1 col-md-1 col-lg-1"></div>
          <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6 mainPanel">
            <div class="messageBlock">
                <label class="successMessage">${successMessage}</label>
                <label class="errorMessage">${errorMessage}</label>
            </div>

              <div class="variant changeOrderStatusBlock">
                  <form action="change-order-status" method="post">
                      <div class="form-group">
                          <label>Order id (you can find on the "View orders page"):</label>
                          <input name="id" required/>
                      </div>
                      <div class="form-group">
                          <label>Category:</label>
                          <select name="orderStatus" required>
                              <option value="waitingForPayment">Waiting for payment</option>
                              <option value="waitingForShipment">Waiting for shipment</option>
                              <option value="shipped">Shipped</option>
                              <option value="delivered">Delivered</option>
                          </select>
                      </div>
                      <button class="btn formButton">Change order status</button>
                  </form>
              </div>
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
  <script>
      // Маска для полей
      $('.addProductBlock input[name="volume"]').mask('999x999x999');
  </script>
</body>

</html>