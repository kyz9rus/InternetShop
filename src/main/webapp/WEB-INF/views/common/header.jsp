<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="j" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="J" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="auth" uri="http://www.springframework.org/security/tags" %>

<div id="firstHeader">
    <div id="largeHeader">
        <div class="becomeRepresentativeHeaderBlock">
            <a href="http://smartavon.ru/Registration.html">BECOME A REPRESENTATIVE</a>
        </div>

        <div class="signInBlock">
            <div class="registrationHeaderBlock">
                <auth:authorize access="hasRole('CLIENT')">
                    <a href="/clientProfile">
                        <img src="/resources/images/profile.png">
                    </a>
                </auth:authorize>
                <auth:authorize access="hasRole('EMPLOYEE')">
                    <a href="/employeeProfile">
                        <img src="/resources/images/profile.png">
                    </a>
                </auth:authorize>
                <auth:authorize access="isAnonymous()">
                <a href="/registration">
                    <img src="/resources/images/register.png">
                </a>
                </auth:authorize>
            </div>
            <div class="loginHeaderBlock">
                <auth:authorize access="!isAuthenticated()">
                    <a href="/login">
                        <img src="/resources/images/login.png">
                    </a>
                </auth:authorize>
                <auth:authorize access="isAuthenticated()">
                    <a href="/logout">
                        <img src="/resources/images/logout.png">
                    </a>
                </auth:authorize>
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
                    <auth:authorize access="hasRole('CLIENT')">
                        <li><a class="register" href="/clientProfile">PROFILE</a></li>
                    </auth:authorize>
                    <auth:authorize access="hasRole('EMPLOYEE')">
                        <li><a class="register" href="/employeeProfile">PROFILE</a></li>
                    </auth:authorize>
                    <auth:authorize access="isAnonymous()">
                        <li><a class="register" href="/registration">REGISTER</a></li>
                    </auth:authorize>
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
                <auth:authorize access="!isAuthenticated()">
                    <a href="/login">
                        <img class="login" src="/resources/images/login.png">
                    </a>
                </auth:authorize>
                <auth:authorize access="isAuthenticated()">
                    <a href="/logout">
                        <img class="logout" src="/resources/images/logout.png">
                    </a>
                </auth:authorize>
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
            <j:choose>
                <j:when test="${basket.numberOfProducts != 0}">
                    <div class="basketInfoBlock">
                        <p>Number of products: ${basket.numberOfProducts}</p>
                        <p>Summary price: ${basket.summaryPrice}</p>
                    </div>
                    <div class="issue">
                        <a href="/clientProfile/issueOrder">
                            <button class="btn formButton">ISSUE ORDER</button>
                        </a>
                    </div>
                </j:when>
                <j:otherwise>
                    <p class="emptyBasket">Your bag is empty</p>
                </j:otherwise>
            </j:choose>
        </div>
    </div>

</div>

<hr class="hr">
<div id="thirdHeader">
    <ul class="menu">
        <j:forEach items="${categories}" var="category" varStatus="tagStatus">
            <li><a href="/category/${fn:replace(fn:toUpperCase(category.name), '_', ' ')}">${fn:replace(fn:toUpperCase(category.name), '_', ' ')}</a></li>
        </j:forEach>
        </li>
    </ul>
</div>
<hr class="hr">

<script type="text/javascript" src="<c:url value="/resources/js/displayUsingAuthority.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/routingFromImages.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/displayMobileMenu.js"/>"></script>