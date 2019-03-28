<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<ul>
    <li><h5>Orders</h5></li>
    <li class="employeeOperation">
        <a href="<c:url value="/employeeProfile/get-orders"/>">
            <label>View orders</label>
        </a>
    </li>
    <li class="divider"></li>

    <li><h5>Products</h5></li>
    <li class="employeeOperation">
        <a href="<c:url value="/employeeProfile/addProduct"/>">
            <label>Add product</label>
        </a>
    </li>
    <li class="employeeOperation">
        <a href="<c:url value="/employeeProfile/manageCategories"/>">
            <label>Creating and managing categories of the directory</label>
        </a>
    </li>
    <li class="employeeOperation">
        <form method="post" action="/employeeProfile/import-products-from-file">
            <label>Import from file</label>
        </form>
    </li>

    <li class="divider"></li>
    <li><h5>Statistics</h5></li>

    <li class="employeeOperation">
        <a href="<c:url value="/employeeProfile/clientStatistic"/>">
            <label>Client statistics</label>
        </a>
    </li>
    <li class="employeeOperation">
        <a href="<c:url value="/employeeProfile/productStatistic"/>">
            <label>Product statistics</label>
        </a>
    </li>
    <li class="employeeOperation">
        <a href="<c:url value="/employeeProfile/revenue"/>">
            <label>Monthly/weekly revenue</label>
        </a>
    </li>
</ul>