<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="j" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="J" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="auth" uri="http://www.springframework.org/security/tags" %>

<div id="firstHeader">
    <div id="largeHeader">
        <div class="becomeRepresentativeHeaderBlock">
            <a href="http://smartavon.ru/Registration.html">BECOME A REPRESENTATIVE</a>
            <br>
            <a href="http://localhost:8081/news" target="_blank">NEWS</a>
        </div>

        <div class="signInBlock">
            <div class="registrationHeaderBlock">
                <auth:authorize access="hasRole('CLIENT')">
                    <a href="<c:url value="/clientProfile"/>">
                        <img src="<c:url value="/resources/images/profile.png" />">
                    </a>
                </auth:authorize>
                <auth:authorize access="hasRole('EMPLOYEE')">
                    <a href="<c:url value="/employeeProfile"/>">
                        <img src="<c:url value="/resources/images/profile.png" />">
                    </a>
                </auth:authorize>
                <auth:authorize access="isAnonymous()">
                    <a href="<c:url value="/registration"/>">
                        <img src="<c:url value="/resources/images/register.png" />">
                    </a>
                </auth:authorize>
            </div>
            <div class="loginHeaderBlock">
                <auth:authorize access="!isAuthenticated()">
                    <a href="<c:url value="/login"/>">
                        <img src="<c:url value="/resources/images/login.png" />">
                    </a>
                </auth:authorize>
                <auth:authorize access="isAuthenticated()">
                    <a href="<c:url value="/logout"/>">
                        <img src="<c:url value="/resources/images/logout.png" />">
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
                        <li>
                            <a href="<c:url value="/category/${fn:replace(fn:toUpperCase(category.name), '_', ' ')}"/>">${fn:replace(fn:toUpperCase(category.name), '_', ' ')}</a>
                        </li>
                    </j:forEach>
                    <li class="divider"></li>
                    <hr>
                    <auth:authorize access="hasRole('CLIENT')">
                        <li><a class="register" href="<c:url value="/clientProfile"/>">PROFILE</a></li>
                    </auth:authorize>
                    <auth:authorize access="hasRole('EMPLOYEE')">
                        <li><a class="register" href="<c:url value="/employeeProfile"/>">PROFILE</a></li>
                    </auth:authorize>
                    <auth:authorize access="isAnonymous()">
                        <li><a class="register" href="<c:url value="/registration"/>">REGISTER</a></li>
                    </auth:authorize>
                    <li><a href="http://smartavon.ru/Registration.html">BECOME A REPRESENTATIVE</a></li>
                    <li><a href="http://localhost:8081/news" target="_blank">NEWS</a></li>
                </ul>
            </nav>
        </div>
        <div class="logoBlock">
            <a href="<c:url value="/"/>">
                <img class="logo" alt="AVON" width="500px" src="<c:url value="/resources/images/logo.png" />">
            </a>
        </div>
        <div class="lastBlock">
            <div class="basketDiv">
                <a href="<c:url value="/clientProfile/issueOrder"/>">
                    <img class="basket" alt="BASKET" src="<c:url value="/resources/images/basket.png" />"><br/>
                </a>
                <J:choose>
                    <j:when test="${basket.products.size() == 0}">
                        <div class="basketInfo">
                            <div class="basketInfoBlock">
                                <p class="emptyBasket">Your bag is empty</p>
                                <p class="numberOfProductsText" style="display:none">Number of
                                    products: ${basket.numberOfProducts}</p>
                                <p class="summaryPrice" style="display:none">Summary price: ${basket.summaryPrice}
                                    rub.</p>
                            </div>
                            <div class="issue" style="display:none">
                                <a href="<c:url value="/clientProfile/issueOrder"/>">
                                    <button class="btn formButton">ISSUE ORDER</button>
                                </a>
                            </div>
                        </div>
                    </j:when>
                    <j:otherwise>
                        <div class="basketInfo">
                            <div class="basketInfoBlock">
                                <p class="emptyBasket" style="display:none">Your bag is empty</p>
                                <p class="numberOfProductsText">Number of products: ${basket.numberOfProducts}</p>
                                <p class="summaryPrice">Summary price: ${basket.summaryPrice} rub.</p>
                            </div>
                            <div class="issue">
                                <a href="<c:url value="/clientProfile/issueOrder"/>">
                                    <button class="btn formButton">ISSUE ORDER</button>
                                </a>
                            </div>
                        </div>
                    </j:otherwise>
                </J:choose>
            </div>
            <div>
                <auth:authorize access="!isAuthenticated()">
                    <a href="<c:url value="/login"/>">
                        <img class="login" src="<c:url value="/resources/images/login.png"/>">
                    </a>
                </auth:authorize>
                <auth:authorize access="isAuthenticated()">
                    <a href="<c:url value="/logout"/>">
                        <img class="logout" src="<c:url value="/resources/images/logout.png"/>">
                    </a>
                </auth:authorize>
            </div>
        </div>
    </div>
</div>

<div id="secondHeader">
    <div id="logoDiv">
        <a href="<c:url value="/"/>">
            <img class="logo" alt="AVON" src="<c:url value="/resources/images/logo.png" />">
        </a>
    </div>

    <div class="basketDiv">
        <a href="<c:url value="/clientProfile/issueOrder"/>">
            <img class="basket" src="<c:url value="/resources/images/basket.png" />" alt="AVON"><br/>
        </a>
        <J:choose>
            <j:when test="${basket.products.size() == 0}">
                <div class="basketInfo">
                    <div class="basketInfoBlock">
                        <p class="emptyBasket">Your bag is empty</p>
                        <p class="numberOfProductsText" style="display:none">Number of
                            products: ${basket.numberOfProducts}</p>
                        <p class="summaryPrice" style="display:none">Summary price: ${basket.summaryPrice} rub.</p>
                    </div>
                    <div class="issue" style="display:none">
                        <a href="<c:url value="/clientProfile/issueOrder"/>">
                            <button class="btn formButton">ISSUE ORDER</button>
                        </a>
                    </div>
                </div>
            </j:when>
            <j:otherwise>
                <div class="basketInfo">
                    <div class="basketInfoBlock">
                        <p class="emptyBasket" style="display:none">Your bag is empty</p>
                        <p class="numberOfProductsText">Number of products: ${basket.numberOfProducts}</p>
                        <p class="summaryPrice">Summary price: ${basket.summaryPrice} rub.</p>
                    </div>
                    <div class="issue">
                        <a href="<c:url value="/clientProfile/issueOrder"/>">
                            <button class="btn formButton">ISSUE ORDER</button>
                        </a>
                    </div>
                </div>
            </j:otherwise>
        </J:choose>
    </div>

</div>

<div id="thirdHeader">
    <ul class="menu">
        <j:forEach items="${categories}" var="category" varStatus="tagStatus">
            <li>
                <a href="<c:url value="/category/${fn:replace(fn:toUpperCase(category.name), '_', ' ')}"/>">${fn:replace(fn:toUpperCase(category.name), '_', ' ')}</a>
            </li>
        </j:forEach>
        </li>
    </ul>
</div>

<script type="text/javascript" src="<c:url value="/resources/js/displayMobileMenu.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/displayProducts.js"/>"></script>