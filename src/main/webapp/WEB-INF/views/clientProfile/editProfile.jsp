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

  <link rel="stylesheet" href='<c:url value="/resources/css/profile.css" />'>

  <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.10/jquery.mask.js"></script>
  <script>
      $(function(){
          $("#leftClientPanel").load("<c:url value="/resources/jsp/leftClientPanel.jsp"/>");
      });
  </script>
</head>

<body>
  <div class="wrapper">
    <div class="wrapperForFooter">

      <div id="header">
        <j:import url="../common/header.jsp"/>
      </div>

      <div class="content">
        <div class="row">
          <div id="leftClientPanel" class="col-xs-3 col-sm-3 col-md-3 col-lg-3 leftPanel"></div>

          <div class="col-xs-1 col-sm-1 col-md-1 col-lg-1"></div>
          <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6 mainPanel">
            <div class="messageBlock">
              <label class="successMessage">${successMessage}</label>
              <label class="errorMessage">${errorMessage}</label>
            </div>

            <div class="variant editProfileBlock">
              <spring:form action="update-client" method="post" modelAttribute="client" >
                <div class="mainPanelBlock">
                  <p class="headerText">Main</p>
                  <div class="subBlock">
                    <div class="form-group">
                      <label>Last name</label>
                      <spring:input path="lastName"/>
                    </div>

                    <div class="form-group">
                      <label>First name</label>
                      <spring:input path="firstName"/>
                    </div>
                  </div>
                </div>

                <div class="mainPanelBlock">
                  <p class="headerText">Additionally</p>
                  <div class="subBlock">
                    <div class="form-group">
                      <label>Birthday</label>
                      <spring:input path="birthday"/>
                    </div>

                    <div class="form-group">
                      <label>Email</label>
                      <spring:input type="email" path="email"/>
                    </div>
                  </div>
                </div>

                <div class="mainPanelBlock">
                  <button class="btn formButton">CHANGE</button>
                </div>
              </spring:form>
            </div>

          </div>

          <div class="col-xs-2 col-sm-2 col-md-2 col-lg-2"></div>
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