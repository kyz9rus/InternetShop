<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>

<div id="logoDiv">
    <a href="/">
        <img class="logo" alt="AVON" src="/resources/images/logo.png">
    </a>
</div>

<div id="basketDiv">
    <a href="/clientProfile/issueOrder">
        <img id="basket" src="/resources/images/basket.png" alt="AVON"><br/>
    </a>
    <div id="basketInfo">
        <p class="emptyBasket">Your bag is empty</p>
    </div>
</div>

<script type="text/javascript" src="<c:url value="/resources/js/routingFromImages.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/displayMobileMenu.js"/>"></script>