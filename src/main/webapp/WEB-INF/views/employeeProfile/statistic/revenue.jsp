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

  <link rel="stylesheet" href='<c:url value="/resources/css/employeeProfile.css" />'>

  <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.10/jquery.mask.js"></script>
</head>

<body>
  <div class="wrapper">
    <div class="wrapperForFooter">

     <div id="header">
        <j:import url="../../common/header.jsp"/>
    </div>

        <div class="content row">
            <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3 leftPanel">
                <j:import url="../../common/leftEmployeePanel.jsp"/>
            </div>
            <div class="col-xs-8 col-sm-8 col-md-8 col-lg-8 mainPanel" align="center">
                <div class="variant clientsStatistic" align="left">
                    <j:choose>
                        <j:when test="${clients.size() == 0}">
                            <p>Clients list is empty</p>
                        </j:when>
                        <j:otherwise>
                            <ul class="topClientsList">
                                <j:forEach items="${clients}" var="client" varStatus="tagStatus">
                                    <li><p class="client">${tagStatus.count}. ${client.lastName} ${client.firstName} (${client.email}): ${client.summaryOrdersPrice} rubles</p></li>
                                </j:forEach>
                            </ul>
                        </j:otherwise>
                    </j:choose>
                </div>
            </div>
        </div>

        <div id="footer">
            <j:import url="../../common/footer.jsp"/>
        </div>
     </div>
  </div>

  <script type="text/javascript" src="<c:url value="/resources/js/checkForms.js"/>"></script>
  <script type="text/javascript" src="<c:url value="/resources/js/orders.js"/>"></script>
  <script src="https://unpkg.com/ionicons@4.4.4/dist/ionicons.js"></script>
  <script>
      // Маска для полей
      $('.addProductBlock input[name="volume"]').mask('999x999x999');
  </script>
</body>

</html>