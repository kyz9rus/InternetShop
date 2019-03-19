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

          <div class="col-xs-8 col-sm-8 col-md-8 col-lg-8 mainPanel">
            <div class="messageBlock">
              <label class="successMessage">${successMessage}</label>
              <label class="errorMessage">${errorMessage}</label>
              <label>${emptyListMessage}</label>
            </div>

            <div class="variant orderHistory">
              <table class="orderTable">
                <tr align="center">
                  <td>ID</td>
                  <td>Adress</td>
                  <td>Delivery method</td>
                  <td>Payment method</td>
                  <td>Order status</td>
                  <td>Payment status</td>
                  <td>price</td>
                </tr>
                <j:forEach items="${orders}" var="order" varStatus="tagStatus">
                  <tr align="center">
                    <td>${order.id}</td>
                    <td>ID: ${order.clientAddress.id}, ${order.clientAddress.postalCode}, ${order.clientAddress.country}, ${order.clientAddress.city}, ${order.clientAddress.street}, ${order.clientAddress.house}, ${order.clientAddress.room}</td>
                    <td>${order.deliveryMethod}</td>
                    <td>${order.paymentMethod}</td>
                    <j:choose>
                      <j:when test="${order.orderStatus == 'waitingForPayment'}">
                        <td class="text-danger">waiting for payment</td>
                      </j:when>
                      <j:when test="${order.orderStatus == 'waitingForShipment'}">
                        <td class="text-warning">waiting for shipment</td>
                      </j:when>
                      <j:when test="${order.orderStatus == 'shipped'}">
                        <td class="text-primary">shipped</td>
                      </j:when>
                      <j:when test="${order.orderStatus == 'delivered'}">
                        <td class="text-success">delivered</td>
                      </j:when>
                      <j:otherwise>
                        <td>${order.orderStatus}</td>
                      </j:otherwise>
                    </j:choose>
                    <j:choose>
                      <j:when test="${order.paymentStatus == 'waitingForPayment'}">
                        <td class="text-warning">waiting for payment</td>
                      </j:when>
                      <j:when test="${order.paymentStatus == 'paid'}">
                        <td class="text-success">paid</td>
                      </j:when>
                      <j:otherwise>
                        <td>${order.paymentStatus}</td>
                      </j:otherwise>
                    </j:choose>
                    <td>${order.price}</td>
                  </tr>
                </j:forEach>
              </table>
            </div>

          </div>
        </div>

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