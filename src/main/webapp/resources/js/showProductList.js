$('.showProducts').click(function () {
    var orderId = $(this).attr('class');
    var data = {};
    data["orderId"] = orderId.substr(41, orderId.length);

    var beginUrl = document.URL.split('/');
    var formUrl;

    if (beginUrl[4] === 'employeeProfile' || beginUrl[3] === 'employeeProfile')
        formUrl = 'employeeProfile/';
    else if (beginUrl[4] === 'clientProfile' || beginUrl[3] === 'clientProfile')
        formUrl = 'clientProfile/';

    $.post({
        url: beginUrl[0] + '/' + beginUrl[1] + '/' + beginUrl[2] + '/' + beginUrl[3] + '/' + formUrl + 'showOrderHistory/get-products',
        data: data,
        success: function (orderProducts) {
            showProductsWindow();
            $('.orderProductList').html(orderProducts.map(orderProduct => `<li><div class="productBlock"><div class="product"><div class="productImage" align="center"><img src="${orderProduct.product.imgSrc}" alt="NO IMAGE"/></div><div class="productInfo"><div class="productInfo1"><p class="productName">${orderProduct.product.name}</p><p class="productWeight">Weight: ${orderProduct.product.weight}г.</p><p class="productSize">Volume: ${orderProduct.product.volume}</p></div><div class="productInfo2"><p class="productPrice">Price: ${orderProduct.product.price} rubles.</p><p class="amount">Amount: ${orderProduct.amount}</p></div></div></div></div></li>`))
        },
        error: function (e) {
            console.log("ERROR" + e);
        }
    });
});

function showProductsWindow() {
    $('.productsWindow').show();
    $('body').css({'background-color': 'rgba(0,0,0,0.4)'});
}

function hideProductsWindow() {
    $('.productsWindow').hide();
    $('body').css({'background-color': 'rgba(0,0,0,0)'});
}

$('.close').click(function () {
    hideProductsWindow();
});