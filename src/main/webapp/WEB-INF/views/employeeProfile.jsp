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
          $("#leftEmployeePanel").load("<c:url value="/resources/jsp/leftEmployeePanel.jsp"/>");
      });
  </script>
</head>

<body>
  <div class="wrapper">
    <div class="wrapperForFooter">

        <div id="header">
            <j:import url="common/header.jsp"/>
        </div>

      <div class="content">
        <div class="row">
          <div id="leftEmployeePanel" class="col-xs-3 col-sm-3 col-md-3 col-lg-3 leftPanel"></div>

          <div class="col-xs-1 col-sm-1 col-md-1 col-lg-1"></div>
          <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6 mainPanel">
              <div class="messageBlock">
                  <label class="successMessage">${successMessage}</label>
                  <label class="errorMessage">${errorMessage}</label>
              </div>

            <p>Choose action in left panel</p>
          </div>

            <div class="col-xs-2 col-sm-2 col-md-2 col-lg-2"></div>
          </div>
        </div>

        <div id="footer">
            <j:import url="common/footer.jsp"/>
        </div>
    </div>
  </div>

  <script type="text/javascript" src="<c:url value="/resources/js/checkForms.js"/>"></script>
  <script src="https://unpkg.com/ionicons@4.4.4/dist/ionicons.js"></script>
  <script>
      // Маска для полей
      $('.addProductBlock input[name="volume"]').mask('999x999x999');
  </script>
</body>

</html>