$('.buyButton').click(function () {
    var imgClass = $(this).attr('class');
    var index = imgClass.substr(24, imgClass.length);

    var data = {};
    data["productId"] = $('.product-' + index + ' input[name="id"]').val();

    $.post({
        url: document.URL + 'put-product',
        data: data,
        dataType: 'json',
        success: function (basketInfo) {
            $('.basketInfo .numberOfProductsText').text('Number of products: ' + basketInfo.basket.numberOfProducts);
            $('.basketInfo .summaryPrice').text('Summary price: ' + basketInfo.basket.summaryPrice + ' rub.');

            $('.emptyBasket').hide();
            $('.numberOfProductsText').show();
            $('.summaryPrice').show();
            $('.issue').show();

            $('.basketInfo')
                .fadeIn()
                .delay(2000)
                .fadeOut();
        },
        error: function (basketInfo) {
            console.log('ERROR: ' + basketInfo);
        }
    });
});

function showCouponWindow() {
    $('.couponWindow').show();
    $('body').css({'background-color': 'rgba(0,0,0,0.4)'});
}

function hideCouponWindow() {
    $('.couponWindow').hide();
    $('body').css({'background-color': 'rgba(0,0,0,0)'});
}

$('.couponBlock button').click(function (e) {
    var imgClass = $(this).attr('class');
    if (!imgClass.includes('ignoreScripts'))
        showCouponWindow();
});

$('.couponWindow img').click(function () {
    hideCouponWindow();
});

$('.couponWindow button').click(function () {
    hideCouponWindow();
});

$('.getCouponButton').click(function () {
    const urlParts = document.URL.split('/');
    const url = urlParts[0] + '/' + urlParts[1] + '/' + urlParts[2] + '/' + urlParts[3];

    var data = {};
    data["couponName"] = $(this).parent().find("input[name='couponName']").val();

    $.post({
        url: url + '/send-coupon',
        data: data,
        dataType: 'json',
        success: function (responseInfo) {
            if (responseInfo.statusCode === 200)
                $('.couponWindow p').attr('class', 'text-success');
            else if (responseInfo.statusCode === 404)
                $('.couponWindow p').attr('class', 'text-danger');

            $('.couponWindow p').text(responseInfo.message);
        },
        error: function (e) {
            console.log("ERROR: " + e);
        }
    });
});

$('#couponsBlock ul').anoSlide({
    items: 1,
    speed: 500,
    prev: 'a.prev',
    next: 'a.next',
    lazy: true,
    auto: 4000
});