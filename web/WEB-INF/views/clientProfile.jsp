<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
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
        <div class="firstHeader"></div>

        <div class="secondHeader"></div>

        <hr>
        <div class="thirdHeader row">
          <ul class="menu">
            <li><a href=#>FRAGRANCES</a>
              <ul class="submenu">
                <li><h5 class="categoryText">CATEGORIES</h5></li>
                <li><a href=#>Men's fragrances</a></li>
                <li><a href=#>Women's fragrances</a></li>
              </ul>
            </li>
            <li><a href=#>FOR FACE</a>
              <ul class="submenu">
                <li><h5 class="categoryText">CATEGORIES</h5></li>
                <li><a href=#>Masks</a></li>
                <li><a href=#>Sera</a></li>
              </ul>
            </li>
          </ul>
        </div>
        <hr>

      </div>

      <div class="content">
        <div class="row">
          <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3 leftPanel">
            <ul>
              <li><h5>Profile</h5></li>
              <%--<li class="clientOperation"><label>View profile</label></li>--%>
              <li class="clientOperation"><label>Edit profile data</label></li>
              <li class="clientOperation"><label>Change password</label></li>
              <li class="divider"></li>
              <li><h5>Orders</h5></li>
              <li class="clientOperation"><label>Issue order</label></li>
              <li class="clientOperation"><label>View order history</label></li>
              <li class="clientOperation"><label>Repeat order</label></li>
            </ul>
          </div>

          <div class="col-xs-1 col-sm-1 col-md-1 col-lg-1"></div>
          <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6 mainPanel">
            <%--<div class="variant showProfile">--%>

              <%--<div class="mainPanelBlock">--%>
                <%--<p class="headerText">Main</p>--%>
                <%--<div class="subBlock">--%>
                  <%--<ul>--%>
                    <%--<li><label>Last name:</label> <label>${clientDto.lastName}</label></li>--%>
                    <%--<li><label>First name:</label> <label>${clientDto.firstName}</label></li>--%>
                  <%--</ul>--%>
                <%--</div>--%>
              <%--</div>--%>

              <%--<div class="mainPanelBlock">--%>
                <%--<p class="headerText">Additionally</p>--%>
                <%--<div class="subBlock">--%>
                  <%--<ul>--%>
                    <%--<li><label>Birthday:</label> <label>${clientDto.birthday}</label></li>--%>
                    <%--<li><label>Email:</label> <label>${clientDto.email}</label></li>--%>
                  <%--</ul>--%>
                <%--</div>--%>
              <%--</div>--%>
            <%--</div>--%>

            <div class="variant editProfile">
              <spring:form action="change-client" method="post" modelAttribute="clientDto">
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
                  <spring:button class="btn btn-primary formButton">CHANGE</spring:button>
                </div>
              </spring:form>
            </div>
          </div>

          <div class="changePassword">
            <%--<spring:form action="change-password" method="post">--%>
            <%--<div class="form-group">--%>
            <%--<label>Текущий пароль:</label>--%>
            <%--<spring:input path="password"/>--%>
            <%--</div>--%>
            <%--<div class="form-group">--%>
            <%--<label>Новый пароль:</label>--%>
            <%--<spring:input path="newPassword"/>--%>
            <%--</div>--%>
            <%--<div class="form-group">--%>
            <%--<label>Повторите новый пароль:</label>--%>
            <%--<spring:input path="repeatNewPassword"/>--%>
            <%--</div>--%>
            <%--<spring:button>Сменить пароль</spring:button>--%>
            <%--</spring:form>--%>
          </div>

          <div class="col-xs-2 col-sm-2 col-md-2 col-lg-2"></div>
        </div>


      </div>

      <div class="footer"></div>
    </div>
  </div>

  <script type="text/javascript" src="<c:url value="resources/js/routingFromImages.js"/>"></script>
  <script type="text/javascript" src="<c:url value="resources/js/displayBlocks.js"/>"></script>
  <script src="https://unpkg.com/ionicons@4.4.4/dist/ionicons.js"></script>
</body>

</html>