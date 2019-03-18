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
          $("#leftEmployeePanel").load("<c:url value="/resources/jsp/leftEmployeePanel.jsp"/>");
          $("#logoAndLastBlock").load("<c:url value="/resources/jsp/logoAndLastBlock.jsp"/>");
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
                              <li><a class="register" href="/registration">REGISTER</a></li>
                              <li><a href="http://smartavon.ru/Registration.html">BECOME A REPRESENTATIVE</a></li>
                          </ul>
                      </nav>
                  </div>
                  <div id="logoAndLastBlock"></div>
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
            <div id="leftEmployeePanel" class="col-xs-3 col-sm-3 col-md-3 col-lg-3 leftPanel"></div>

          <div class="col-xs-1 col-sm-1 col-md-1 col-lg-1"></div>
          <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6 mainPanel">
            <div class="messageBlock">
                <label class="successMessage">${successMessage}</label>
                <label class="errorMessage">${errorMessage}</label>
            </div>
              <div class="variant manageCategoriesBlock">
                <p>Category list:</p>
                <ul>
                    <j:forEach items="${categories}" var="category" varStatus="tagStatus">
                        <div class="category">
                            <div class="categoryTextBlock categoryTextBlock-${tagStatus.index}">
                                <h5 class="categoryText">${category.name}</h5>
                                <div class="categoryOperations">
                                    <img class="img-${tagStatus.index}" src="/resources/images/writingHand.png" alt="pen"/>
                                    <form action="remove-category" method="post">
                                        <button>
                                            <img src="/resources/images/cross.png" alt="cross"/>
                                        </button>
                                        <input name="oldName" style="display: none" value="${category.name}"/>
                                    </form>
                                </div>
                            </div>
                            <spring:form action="update-category" method="post">
                            <div class="categoryFormBlock categoryFormBlock-${tagStatus.index}">
                                <input name="name" value="${category.name}">
                                <input name="oldName" style="display: none" value="${category.name}"/>
                                <button class="btn formButton">EDIT</button>
                            </div>
                            </spring:form>
                        </div>
                    </j:forEach>
                </ul>
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
  <script type="text/javascript" src="<c:url value="/resources/js/manageCategories.js"/>"></script>
  <script src="https://unpkg.com/ionicons@4.4.4/dist/ionicons.js"></script>
  <script>
      // Маска для полей
      $('.addProductBlock input[name="volume"]').mask('999x999x999');
  </script>
</body>

</html>