<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="j" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%request.setCharacterEncoding("UTF-8");%>

<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>Employee profile</title>
    <link rel="icon" href='<c:url value="/resources/images/favicon.ico" />' type="image/x-icon">

    <link rel="stylesheet" href='<c:url value="/resources/css/bootstrap.min.css" />'>
    <link rel="stylesheet" href='<c:url value="/resources/css/index.css" />'>
    <link rel="stylesheet" href='<c:url value="/resources/css/submenu.css" />'>
    <link rel="stylesheet" href="<c:url value="/resources/js/cropper2/cropper.css"/>"/>

    <link rel="stylesheet" href='<c:url value="/resources/css/employeeProfile.css" />'>

    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.10/jquery.mask.js"></script>
    <script src="<j:url value="/resources/js/jquery.cropbox.js" />"></script>
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
                    <j:import url="../common/leftEmployeePanel.jsp"/>
                </div>

                <div class="col-xs-1 col-sm-1 col-md-1 col-lg-1"></div>
                <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6 mainPanel">
                    <div class="messageBlock">
                        <label class="successMessage">${successMessage}</label>
                        <label class="errorMessage">${errorMessage}</label>
                    </div>

                    <div class="variant addProductBlock">
                        <form action="create-product" method="post">
                            <div class="form-group">
                                <label>Product name:</label>
                                <input id="name" name="name" required/>
                            </div>
                            <div class="form-group">
                                <label>Price:</label>
                                <input id="price" name="price" required/>
                            </div>
                            <div class="form-group">
                                <label>Category:</label>
                                <select name="category" required>
                                    <j:forEach items="${categories}" var="category" varStatus="tagStatus">
                                        <option value="${category.name}">
                                                ${fn:toUpperCase(category.name)}
                                        </option>
                                    </j:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                <label>Weight:</label>
                                <input id="weight" name="weight" placeholder="325"/>
                            </div>
                            <div class="form-group">
                                <label>Volume:</label>
                                <input name="volume" placeholder="999x999x999"/>
                            </div>
                            <div class="form-group">
                                <label>Quantity in stock:</label>
                                <input id="quantityInStock" name="quantityInStock" required/>
                            </div>
                            <div class="form-group">
                                <label>Link to the image:</label>
                                <input name="imgSrc" placeholder="https://site.ru/images/image.png">
                            </div>
                            <div id="cropImage">

                            </div>

                            <div id="cropImage2">

                            </div>
                            <button class="btn formButton">Add product</button>
                        </form>
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

<script type="text/javascript" src="<c:url value="/resources/js/checkForms.js"/>"></script>
<script src="<j:url value="/resources/js/addProduct.js" />"></script>
<script src="https://unpkg.com/ionicons@4.4.4/dist/ionicons.js"></script>
<script>
    // Masks for fields
    $('.addProductBlock input[name="volume"]').mask('999x999x999');
</script>
</body>

</html>