<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>

<div id="logoDiv">
    <img class="logo" alt="AVON" src="/resources/images/logo.png">
</div>

<div id="basketDiv">
    <img id="basket" src="/resources/images/basket.png" alt="AVON"><br/>
    <div id="basketInfo">
        <p class="emptyBasket">Your bag is empty</p>
    </div>
</div>

<script type="text/javascript" src="<c:url value="resources/js/routingFromImages.js"/>"></script>
<script type="text/javascript" src="<c:url value="resources/js/basketDisplay.js"/>"></script>
<script type="text/javascript" src="<c:url value="resources/js/displayMobileMenu.js"/>"></script>