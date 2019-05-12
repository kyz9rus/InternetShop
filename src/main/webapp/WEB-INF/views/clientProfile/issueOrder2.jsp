<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="j" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

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
            <form action="issue-order" method="post">
                <div class="row">
                    <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3">
                        <div class="leftPanel">
                            <j:import url="../common/leftClientPanel.jsp"/>
                        </div>
                    </div>

                    <div class="col-xs-8 col-sm-8 col-md-8 col-lg-8 mainPanel">
                        <div class="variant issueOrderBlock">
                            <form action="issue-order" method="post">
                                <j:if test="${basket.products.size() != 0}">
                                    <div class="orderInfoPanel" align="center">
                                        <div class="clientAddress">
                                            <div class="form-group">
                                                <label>Choose your address</label>
                                                <j:choose>
                                                    <j:when test="${client.addresses.size() > 0}">
                                                        <select name="addressId">
                                                            <j:forEach items="${client.addresses}" var="address"
                                                                       varStatus="tagStatus">
                                                                <option value="${address.id}">${address.postalCode}, ${address.country}, ${address.city}, ${address.street}, ${address.house}, ${address.room}</option>
                                                            </j:forEach>
                                                        </select>
                                                    </j:when>
                                                </j:choose>
                                            </div>
                                        </div>

                                        <div class="orderInfo">
                                            <div class="form-group">
                                                <label>Enter delivery method</label>
                                                <select name="deliveryMethod" required="">
                                                    <option>Post of Russia</option>
                                                    <option>Avon service centers</option>
                                                    <option>Home delivery</option>
                                                </select>
                                            </div>
                                            <div class="form-group">
                                                <label>Enter payment method</label>
                                                <select name="paymentMethod" required="">
                                                    <option>By cash</option>
                                                    <option>By card</option>
                                                </select>
                                                <div class="byCardBlock">
                                                    <div class="headerCardBlock">
                                                        <div class="cardsImage">
                                                            <img src="<j:url value="/resources/images/cards.jpg"/>"/>
                                                        </div>
                                                        <div class="orderInfoInCardBlock">
                                                            <div class="form-group">
                                                                <label class="orderInfoPoint form-group">Order
                                                                    cost:</label>
                                                                <label class="orderInfoValue">${basket.summaryPrice}
                                                                    rubles</label><br>
                                                            </div>
                                                            <div class="form-group">
                                                                <label class="orderInfoPoint">Order description:</label>
                                                                <label class="orderInfoValue">AVON_SHOP</label><br>
                                                            </div>
                                                            <div class="priceOrderInfo">
                                                                <label class="orderInfoPoint">Total for payment:</label>
                                                                <label class="text-success totalPrice">${basket.summaryPrice}
                                                                    rubles</label>
                                                            </div>
                                                        </div>
                                                        <div class="cardInfo">
                                                            <div class="cardInfoContent">
                                                                <div class="form-group">
                                                                    <label>Card number:</label>
                                                                    <input name="cardNumber"/>
                                                                </div>
                                                                <div class="form-group">
                                                                    <label>The name on the card:</label>
                                                                    <input name="cardName"/>
                                                                </div>
                                                                <div class="cardDate">
                                                                    <label>Valid until:</label>
                                                                    <input placeholder="01" name="validUntilMonth"/>
                                                                    <label>/</label>
                                                                    <input placeholder="2014" name="validUntilYear"/>
                                                                </div>
                                                                <div class="cvcCodeBlock">
                                                                    <div class="form-group">
                                                                        <label>CVC/CVV:</label><input name="cvc"/>
                                                                        <p>CVC/CVV is written on the back of the
                                                                            card</p>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="confirmPaymentBlock">
                                                            <button class="btn formButton">PAY</button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div align="left">
                                            <p>Total price <b class="text-success">${basket.summaryPrice}</b> rubles</p>
                                        </div>

                                        <input class="btn formButton issueOrderButton" type="submit"
                                               value="ISSUE ORDER"/>
                                    </div>
                                </j:if>
                            </form>
                        </div>
                    </div>

                </div>
            </form>

        </div>

        <div id="footer">
            <j:import url="../common/footer.jsp"/>
        </div>
    </div>
</div>

<script type="text/javascript" src="<c:url value="/resources/js/issueOrder2.js"/>"></script>
<script>
    // Masks for fields
    $('.editProfileBlock input[name="birthday"]').mask('99-99-9999');
</script>
<script src="https://unpkg.com/ionicons@4.4.4/dist/ionicons.js"></script>
</body>

</html>