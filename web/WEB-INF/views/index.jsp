<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="j"%>--%>
<%@ page contentType="text/html;charset=UTF-8"%>

<html>

<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <title>Avon Shop</title>
  <link rel="icon" href='<c:url value="/resources/images/favicon.ico" />' type="image/x-icon">

  <link rel="stylesheet" href='<c:url value="/resources/css/bootstrap.min.css" />'>
  <link rel="stylesheet" href='<c:url value="/resources/css/index.css" />'>
  <link rel="stylesheet" href='<c:url value="/resources/css/submenu.css" />'>

  <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
  <script>
      $(function(){
          $(".footer").load("<c:url value="/resources/jsp/footer.jsp"/>");
          $(".firstHeader").load("<c:url value="/resources/jsp/firstHeader.jsp"/>");
          $(".secondHeader").load("<c:url value="/resources/jsp/secondHeader.jsp"/>");
      });
  </script>

</head>

<body>
  <div class="wrapper">
    <div class="wrapperForFooter">

      <div class="headers">
        <div class="firstHeader row"></div>

        <div class="secondHeader"></div>

        <hr>
        <div class="thirdHeader row">
          <ul class="menu">
            <li><a href=#>АРОМАТЫ</a>
              <ul class="submenu">
                <li><h5 class="categoryText">КАТЕГОРИИ</h5></li>
                <li><a href=#>Мужские ароматы</a></li>
                <li><a href=#>Женские ароматы</a></li>
              </ul>
            </li>
            <li><a href=#>ДЛЯ ЛИЦА</a>
              <ul class="submenu">
                <li><h5 class="categoryText">КАТЕГОРИИ</h5></li>
                <li><a href=#>Маски</a></li>
                <li><a href=#>Сыворотки</a></li>
              </ul>
            </li>
          </ul>
        </div>
        <hr>

      </div>

      <div class="content row" align="center">
        <%--<j:forEach items="${Clients.clients}" var="client" varStatus="tagStatus">--%>
          <%--<tr>--%>
            <%--<td>${client.firstName}</td>--%>
            <%--<td>${client.lastName}</td>--%>
            <%--<td>${client.email}</td>--%>
          <%--</tr>--%>
        <%--</j:forEach>--%>
      </div>

      <div class="footer"></div>
    </div>
  </div>
</body>

</html>