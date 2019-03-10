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
          $("#firstHeader").load("<c:url value="/resources/jsp/firstHeader.jsp"/>");
          $("#secondHeader").load("<c:url value="/resources/jsp/secondHeader.jsp"/>");
      });

      // $(document).ready(function() {
      //     $("#getOrders").click(function(){
      //         $.get("/getOrders",function(data,status){
      //             console.log("Data: " + data + "\nStatus: " + status);
      //         });
      //     });
      // });
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
            <li><a href="category/fragrances">FRAGRANCES</a></li>
            <li><a href="category/forFace">FOR FACE</a></li>
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
                <%--<a href="getOrders">--%>
                  <label>View orders</label>
                <%--</a>--%>
              </li>
              <li class="employeeOperation"><label>Change order status</label></li>
              <li class="divider"></li>

              <li class="employeeOperation"><label>Sales statistics</label></li>
              <li class="divider"></li>

              <li><h5>Products</h5></li>
              <li class="employeeOperation"><label>Add product</label></li>
              <li class="employeeOperation"><label>Creating and managing categories of the directory</label></li>
              <li class="employeeOperation"><label>Import from file</label></li>
            </ul>
          </div>

          <div class="messageBlock">
            <label class="successMessage">${successMessage}</label>
            <label class="errorMessage">${errorMessage}</label>
            <label>${emptyListMessage}</label>
          </div>

          <div class="col-xs-1 col-sm-1 col-md-1 col-lg-1"></div>
          <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6 mainPanel">
            <div class="variant ordersBlock">
              <form action="/employeeProfile/getOrders" method="get">
                <input class="btn formButton" type="submit" value="Show order list"/>
              </form>
                <%--<button id="getOrders">Show order list</button>--%>
              <ul class="orderList">
                <j:forEach items="${orders}" var="order" varStatus="tagStatus">
                  <li>${order.id}, ${order.paymentMethod}, ${order.paymentStatus}, ${order.orderStatus}</li>
                </j:forEach>
              </ul>
            </div>

            <div class="variant addProductBlock">
              <form action="employeeProfile/create-product" method="post">
                <div class="form-group">
                  <label>Product name:</label>
                  <input name="name" required/>
                </div>
                <div class="form-group">
                  <label>Price:</label>
                  <input name="price" required/>
                </div>
                <div class="form-group">
                  <label>Category:</label>
                  <select name="category" required>
                    <j:forEach items="${categories}" var="category" varStatus="tagStatus">
                      <option value="${category.name}">
                          ${category.name}
                      </option>
                    </j:forEach>
                  </select>
                </div>
                <div class="form-group">
                  <label>Weight:</label>
                  <input name="weight"/>
                </div>
                <div class="form-group">
                  <label>Volume:</label>
                  <input name="volume"/>
                </div>
                <div class="form-group">
                  <label>Quantity in stock:</label>
                  <input name="quantityInStock" required/>
                </div>
                <button class="btn formButton">Add product</button>
              </form>
            </div>

            <div class="variant changeOrderStatusBlock">
              <form action="employeeProfile/change-order-status" method="post">
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

          </div>

          <div class="col-xs-2 col-sm-2 col-md-2 col-lg-2"></div>
        </div>

        <div id="footer"></div>
      </div>
    </div>
  </div>

  <script type="text/javascript" src="<c:url value="resources/js/routingFromImages.js"/>"></script>
  <script type="text/javascript" src="<c:url value="resources/js/displayBlocks.js"/>"></script>
  <script src="https://unpkg.com/ionicons@4.4.4/dist/ionicons.js"></script>
  <script>
      // Маска для полей
      $('.addProductBlock input[name="volume"]').mask('999x999x999');
  </script>
</body>

</html>