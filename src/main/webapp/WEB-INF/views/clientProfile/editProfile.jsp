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

                <div class="mainPanelBlock">
                  <j:choose>
                    <j:when test="${client.addresses.size() != 0}">
                      <p class="headerText">Addresses</p>
                      <div class="subBlock">
                        <ul>
                          <j:forEach items="${client.addresses}" var="address" varStatus="tagStatus">
                            <li>
                              <div class="address" align="float">
                                <div class="addressTextBlock addressTextBlock-${tagStatus.count}">
                                  <h5>${tagStatus.count}. ${address.postalCode}, ${address.country}</h5>
                                  <h5>${address.city}, ${address.street}, ${address.house}, ${address.room}</h5>
                                  <div class="addressOperations">
                                      <button class="btn formButton editAddress editAddressButton-${tagStatus.count}">EDIT</button>
                                    <form action="delete-address" method="post">
                                      <input name="addressId" style="display: none" value="${address.id}"/>
                                      <button class="btn formButton">REMOVE</button>
                                    </form>
                                  </div>
                                </div>
                                <spring:form action="update-address" method="post">
                                  <div class="addressFormBlock addressFormBlock-${tagStatus.count}">
                                    <div class="clientAddressData">
                                      <div class="form-group">
                                        <label>Country</label>
                                        <input name="country" required value="${address.country}"/>
                                      </div>
                                      <div class="form-group">
                                        <label>City</label>
                                        <input name="city" required value="${address.city}"/>
                                      </div>
                                      <div class="form-group">
                                        <label>Postal code</label>
                                        <input name="postalCode" required value="${address.postalCode}"/>
                                      </div>
                                      <div class="form-group">
                                        <label>Street</label>
                                        <input name="street" required value="${address.street}"/>
                                      </div>
                                      <div class="form-group">
                                        <label>House</label>
                                        <input name="house" required value="${address.house}"/>
                                      </div>
                                      <div class="form-group">
                                        <label>Room</label>
                                        <input name="room" required value="${address.room}"/>
                                      </div>
                                    </div>
                                    <input name="addressId" style="display: none" value="${address.id}"/>
                                    <button class="btn formButton">EDIT</button>
                                  </div>
                                </spring:form>
                              </div>
                            </li>
                          </j:forEach>
                        </ul>
                        <button class="btn formButton addAddressButton">ADD ADDRESS</button>

                  <%--<div style="display: block;" class="mainPanelBlock addAddressBlock">
                            <p class="headerText">Add address</p>
                          <form method="post" class="subBlock" action="create-address">
                            <div class="clientAddressData">
                              <div class="form-group">
                                <label>Country</label>
                                <input name="country" required="">
                              </div>
                              <div class="form-group">
                                <label>City</label>
                                <input name="city" required="">
                              </div>
                              <div class="form-group">
                                <label>Postal statusCode</label>
                                <input name="postalCode" required="">
                              </div>
                              <div class="form-group">
                                <label>Street</label>
                                <input name="street" required="">
                              </div>
                              <div class="form-group">
                                <label>House</label>
                                <input name="house" required="">
                              </div>
                              <div class="form-group">
                                <label>Room</label>
                                <input name="room" required="">
                              </div>
                            </div>
                            <input name="name">
                            <button class="btn formButton">ADD ADDRESS</button>
                          </form>
                        </div>--%>

                      </div>
                    </j:when>
                    <j:otherwise>
                        <p>Empty addresses list</p>
                        <button class="btn formButton addAddressButton">ADD ADDRESS</button>
                    </j:otherwise>
                  </j:choose>
                </div>

                <div class="mainPanelBlock addAddressBlock">
                    <p class="headerText">Add address</p>
                    <form action="create-address" method="post" class="subBlock">
                        <div class="clientAddressData">
                            <div class="form-group">
                                <label>Country</label>
                                <input name="country" required/>
                            </div>
                            <div class="form-group">
                                <label>City</label>
                                <input name="city" required/>
                            </div>
                            <div class="form-group">
                                <label>Postal code</label>
                                <input name="postalCode" required/>
                            </div>
                            <div class="form-group">
                                <label>Street</label>
                                <input name="street" required/>
                            </div>
                            <div class="form-group">
                                <label>House</label>
                                <input name="house" required/>
                            </div>
                            <div class="form-group">
                                <label>Room</label>
                                <input name="room" required/>
                            </div>
                        </div>
                        <button class="btn formButton">ADD ADDRESS</button>
                    </form>
                </div>
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

  <script type="text/javascript" src="<c:url value="/resources/js/manageAddresses.js"/>"></script>
  <script type="text/javascript" src="<c:url value="/resources/js/checkForms.js"/>"></script>
  <script>
      // Маска для полей
      $('.editProfileBlock input[name="birthday"]').mask('99-99-9999');
  </script>
  <script src="https://unpkg.com/ionicons@4.4.4/dist/ionicons.js"></script>
</body>

</html>