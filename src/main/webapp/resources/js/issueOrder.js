$('.productOperations .plus').click(function(){
    var imgClass = $(this).attr('class');
    var index = imgClass.substr(10, imgClass.length);

    var data = {};
    data["productId"] = $('.productBlock-' + index + ' input[name="id"]').val();

    $.post({
        url: '/put-product',
        data: data,
        dataType: 'json',
        success: function (basketInfo) {
            console.log(basketInfo);

            $('.basketInfo .numberOfProductsText').text('Number of products: ' + basketInfo.basket.numberOfProducts);
            $('.basketInfo .summaryPrice').text('Summary price: ' + basketInfo.basket.summaryPrice + ' rub.');
            $('.priceBlock b').text(basketInfo.basket.summaryPrice);

            $('.emptyBasketBlock').hide();
            $('.basketInfo')
                .fadeIn()
                .delay(2000)
                .fadeOut();

            $('.numberOfProduct-' + index).text(basketInfo.numberOfProduct);
        },
        error: function (basketInfo) {
            console.log('ERROR: ' + basketInfo);
        }
    });
});

$('.productOperations .minus').click(function(){
    var imgClass = $(this).attr('class');
    var index = imgClass.substr(12, imgClass.length);

    var data = {};
    data["productId"] = $('.productBlock-' + index + ' input[name="id"]').val();

    $.post({
        url: '/increase-product',
        data: data,
        dataType: 'json',
        success: function (basketInfo) {
            console.log(basketInfo);

            if (basketInfo.basket.numberOfProducts === 0) {
                $('.emptyBasket').show();
                $('.numberOfProductsText').hide();
                $('.summaryPrice').hide();
                $('.issue').hide();

                $('.emptyProductsListLabel').show();
                $('.headerText').hide();
                $('.productsBlock').hide();
                $('.orderPageButton').hide();
            } else {
                $('.emptyBasket').hide();
                $('.numberOfProductsText').show();
                $('.summaryPrice').show();
                $('.issue').show();

                $('.emptyProductsListLabel').hide();
                $('.headerText').show();
                $('.productsBlock').show();
                $('.orderPageButton').show();
            }

            $('.basketInfo .numberOfProductsText').text('Number of products: ' + basketInfo.basket.numberOfProducts);
            $('.basketInfo .summaryPrice').text('Summary price: ' + basketInfo.basket.summaryPrice + ' rub.');
            $('.priceBlock b').text(basketInfo.basket.summaryPrice);

            $('.emptyBasketBlock').hide();
            $('.basketInfo')
                .fadeIn()
                .delay(2000)
                .fadeOut();

            if (basketInfo.numberOfProduct === null)
                $('.productBlock-' + index).hide();
            else
                $('.numberOfProduct-' + index).text(basketInfo.numberOfProduct);
        },
        error: function (basketInfo) {
            console.log('ERROR: ' + basketInfo);
        }
    });
});

$('.productOperations .remove').click(function(){
    var imgClass = $(this).attr('class');
    var index = imgClass.substr(14, imgClass.length);

    var data = {};
    data["productId"] = $('.productBlock-' + index + ' input[name="id"]').val();

    $.post({
        url: '/remove-product',
        data: data,
        dataType: 'json',
        success: function (basketInfo) {
            console.log(basketInfo);

            if (basketInfo.basket.numberOfProducts === 0) {
                $('.emptyBasket').show();
                $('.numberOfProductsText').hide();
                $('.summaryPrice').hide();
                $('.issue').hide();

                $('.emptyProductsListLabel').show();
                $('.headerText').hide();
                $('.productsBlock').hide();
                $('.orderPageButton').hide();
            } else {
                $('.emptyBasket').hide();
                $('.numberOfProductsText').show();
                $('.summaryPrice').show();
                $('.issue').show();

                $('.emptyProductsListLabel').hide();
                $('.headerText').show();
                $('.productsBlock').show();
                $('.orderPageButton').show();
            }

            $('.basketInfo .numberOfProductsText').text('Number of products: ' + basketInfo.basket.numberOfProducts);
            $('.basketInfo .summaryPrice').text('Summary price: ' + basketInfo.basket.summaryPrice + ' rub.');
            $('.priceBlock b').text(basketInfo.basket.summaryPrice);

            $('.emptyBasketBlock').hide();
            $('.basketInfo')
                .fadeIn()
                .delay(2000)
                .fadeOut();

            $('.productBlock-' + index).hide();
        },
        error: function (basketInfo) {
            console.log('ERROR: ' + basketInfo);
        }
    });
});


function showCouponBlock() {
    $('.couponBlock').show();
    $('body').css({'background-color': 'rgba(0,0,0,0.4)'});
}

$('.orderPageButton button').click(function(e) {
    showCouponBlock();
    e.preventDefault();
});

$('.checkCouponButton').click(function(e) {
    e.preventDefault();
    var data = {};
    var couponName = $('.couponBlock input[name="couponValue"]').val();
    data["couponValue"] = couponName;

    if (couponName.length === 0) {
        $('#checkCouponMessage').text('Enter your coupon.');
        $('#checkCouponMessage').attr('class', 'text-danger');
        return;
    }

    $.post({
        url: '/clientProfile/issueOrder/check-coupon',
        data: data,
        dataType: 'json',
        success: function (responseInfo) {
            $('#checkCouponMessage').attr('class', 'text-success');
            $('#checkCouponMessage').text(responseInfo.message);

            $('.checkCouponButton').hide();
            $('.continueCouponButton').show();
        },
        error: function (responseInfo) {
            $('#checkCouponMessage').attr('class', 'text-danger');
            $('#checkCouponMessage').text(responseInfo.message);
        }
    });
});
