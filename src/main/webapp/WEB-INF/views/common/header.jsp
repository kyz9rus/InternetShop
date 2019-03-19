<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="j" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="J" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="firstHeader">
    <div id="largeHeader">
        <div class="becomeRepresentativeHeaderBlock">
            <a href="http://smartavon.ru/Registration.html">BECOME A REPRESENTATIVE</a>
        </div>

        <div class="signInBlock">
            <div class="registrationHeaderBlock">
                <j:choose>
                    <j:when test="${authenticationRole == 'ROLE_CLIENT'}">
                        <a href="/clientProfile">
                            <img src="/resources/images/profile.png">
                        </a>
                    </j:when>
                    <j:when test="${authenticationRole == 'ROLE_EMPLOYEE'}">
                        <a href="/employeeProfile">
                            <img src="/resources/images/profile.png">
                        </a>
                    </j:when>
                    <j:otherwise>
                        <a href="/registration">
                            <img src="/resources/images/register.png">
                        </a>
                    </j:otherwise>
                </j:choose>
            </div>
            <div class="loginHeaderBlock">
                <j:choose>
                    <j:when test="${authenticationRole != 'AnonymousUser'}">
                        <a href="/logout">
                            <img src="/resources/images/logout.png">
                        </a>
                    </j:when>
                    <j:otherwise>
                        <a href="login">
                            <img src="/resources/images/login.png">
                        </a>
                    </j:otherwise>
                </j:choose>
            </div>
        </div>

    </div>

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
                    <j:choose>
                        <j:when test="${authenticationRole == 'ROLE_CLIENT'}">
                            <li><a class="register" href="/clientProfile">PROFILE</a></li>
                        </j:when>
                        <j:when test="${authenticationRole == 'ROLE_EMPLOYEE'}">
                            <li><a class="register" href="/employeeProfile">PROFILE</a></li>
                        </j:when>
                        <j:otherwise>
                            <li><a class="register" href="/registration">REGISTER</a></li>
                        </j:otherwise>
                    </j:choose>
                    <li><a href="http://smartavon.ru/Registration.html">BECOME A REPRESENTATIVE</a></li>
                </ul>
            </nav>
        </div>
        <div class="logoBlock">
            <a href="/">
                <img class="logo" alt="AVON" src="/resources/images/logo.png" width="500px">
            </a>
        </div>
        <div class="lastBlock">
            <div>
                <img class="basket" src="/resources/images/basket.png" alt="AVON"><br/>
                <div class="basketInfo">
                    <p class="emptyBasket">Your bag is empty</p>
                </div>
            </div>
            <div>
                <j:choose>
                    <j:when test="${authenticationRole != 'AnonymousUser'}">
                        <a href="/logout">
                            <img src="/resources/images/logout.png">
                        </a>
                    </j:when>
                    <j:otherwise>
                        <a href="login">
                            <img src="/resources/images/login.png">
                        </a>
                    </j:otherwise>
                </j:choose>
            </div>
        </div>
    </div>
</div>

<div id="secondHeader">
    <div id="logoDiv">
        <a href="/">
            <img class="logo" alt="AVON" src="/resources/images/logo.png">
        </a>
    </div>

    <div id="basketDiv">
        <a href="/clientProfile/issueOrder">
            <img class="basket" src="/resources/images/basket.png" alt="AVON"><br/>
        </a>
        <div class="basketInfo">
            <p class="emptyBasket">Your bag is empty</p>
        </div>
    </div>

</div>

<hr class="hr">
<div id="thirdHeader">
    <ul class="menu">
        <j:forEach items="${categories}" var="category" varStatus="tagStatus">
            <li><a href="/category/${category.name}">${category.name}</a></li>
        </j:forEach>
        </li>
    </ul>
</div>
<hr class="hr">

<script type="text/javascript" src="<c:url value="/resources/js/displayUsingAuthority.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/routingFromImages.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/displayMobileMenu.js"/>"></script>