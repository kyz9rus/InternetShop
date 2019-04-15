<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<ul>
    <li><h5>Profile</h5></li>
    <li class="clientOperation">
        <a href="<c:url value="/clientProfile/editProfile"/>">
            <label>Edit profile data</label>
        </a>
    </li>
    <li class="clientOperation">
        <a href="<c:url value="/clientProfile/changePassword"/>">
            <label>Change password</label>
        </a>
    </li>
    <li class="divider"></li>
    <li><h5>Orders</h5></li>
    <li class="clientOperation">
        <a href="<c:url value="/clientProfile/issueOrder"/>">
            <label>Issue order</label>
        </a>
    </li>
    <li class="clientOperation">
        <a href="<c:url value="/clientProfile/orderHistory"/>">
            <label>View order history</label>
        </a>
    </li>
</ul>