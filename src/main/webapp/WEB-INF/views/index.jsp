<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="j" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="J" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="auth" uri="http://www.springframework.org/security/tags" %>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>

<html>

<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <title>Avon shop</title>
  <link rel="icon" href='<c:url value="/resources/images/favicon.ico" />' type="image/x-icon">

  <link rel="stylesheet" href='<c:url value="/resources/css/bootstrap.min.css" />'>
  <link rel="stylesheet" href='<c:url value="/resources/css/index.css" />'>
  <link rel="stylesheet" href='<c:url value="/resources/css/submenu.css" />'>
  <link rel="stylesheet" href='<c:url value="/resources/css/products.css" />'>

  <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
</head>

<body>
  <div class="wrapper">
    <div class="wrapperForFooter">

      <div id="header">
        <j:import url="common/header.jsp"/>
      </div>

      <div class="content" align="center">
        <div class="couponWindow">
          <img src="<j:url value="/resources/images/cross.png"/>" alt="X">
          <p class="text-danger">Waiting...</p>
          <button class="btn formButton">OK</button>
        </div>

        <div class="becomeRepresentativeBlock">
          <div class="row">
            <div class="col-xs-2 col-sm-2 col-md-2 col-lg-2 presentImage">
              <img src="<c:url value="/resources/images/present.png" />"/>
            </div>
            <div class="col-xs-7 col-sm-7 col-md-7 col-lg-7">
              <p class="article">Do you want a 30% discount on a first order?</p>
              <p class=>Get a promo code right now!</p>
            </div>
            <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3 buttonBlock">
              <auth:authorize access="hasRole('EMPLOYEE') || isAnonymous()">
                <a href="<j:url value="/login" />">
                  <button class="btn formButton ignoreScripts">GET IT</button>
                </a>
              </auth:authorize>
              <auth:authorize access="hasRole('CLIENT')">
                <button class="btn formButton getCouponButton">GET IT</button>
                <input name="email" value="${client.email}" hidden/>
              </auth:authorize>
            </div>
          </div>
        </div>

        <div class="mainContent row">
          <div class="col-xs-1 col-sm-1 col-md-1 col-lg-1"></div>
          <div class="clientStatistic col-xs-4 col-sm-4 col-md-4 col-lg-4">
            <p class="topClientsLabel">TOP clients</p>
            <div class="topClientsBlock" align="left">
              <j:choose>
                <j:when test="${clients.size() == 0}">
                  <p>Clients list is empty</p>
                </j:when>
                <j:otherwise>
                  <ul class="topClientsList">
                    <j:forEach items="${clients}" var="client" varStatus="tagStatus">
                      <j:if test="${client.summaryOrdersPrice > 0}">
                        <li><p class="client">${tagStatus.count}. ${client.lastName} ${client.firstName}: ${client.summaryOrdersPrice} rubles</p></li>
                      </j:if>
                    </j:forEach>
                  </ul>
                </j:otherwise>
              </j:choose>
            </div>
          </div>
          <div class="col-xs-2 col-sm-2 col-md-2 col-lg-2"></div>
          <div class="newsBlock col-xs-4 col-sm-4 col-md-4 col-lg-4">
            <a href="http://localhost:8080/news">
              <p class="newsLabel">NEWS</p>
            </a>
            <j:choose>
              <j:when test="${newsList.size() != 0}">
                <ul class="newsList">
                  <j:forEach items="${newsList}" var="news" varStatus="tagStatus">
                    <li>
                      <div class="postBlock" align="left">
                        <a href="<j:url value="news/${news.id}"/> ">
                          <div class="articleBlock">
                            <label class="postArticle">${news.article}</label>
                          </div>
                        </a>
                        <div class="textBlock">
                          <j:choose>
                            <j:when test="${news.text.length() > 150}">
                              <p class="text">${fn:substring(news.text, 0, 150)}...</p>
                            </j:when>
                            <j:otherwise>
                              <p class="text">${news.text}</p>
                            </j:otherwise>
                          </j:choose>
                        </div>
                        <div class="writingDateBlock">
                          <label class="writing_date">${news.writingDate}</label>
                        </div>
                      </div>
                    </li>
                  </j:forEach>
                </ul>
              </j:when>
              <j:otherwise>
                <p>Here is no news yet</p>
              </j:otherwise>
            </j:choose>
          </div>
        </div>
      </div>

      <div id="footer">
        <j:import url="common/footer.jsp"/>
      </div>
    </div>
  </div>
  <script src="https://unpkg.com/ionicons@4.4.4/dist/ionicons.js"></script>
  <script src="<c:url value="/resources/js/index.js"/>"></script>
</body>
</html>