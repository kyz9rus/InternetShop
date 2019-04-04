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
                $('.priceBlock').hide();
            } else {
                $('.emptyBasket').hide();
                $('.numberOfProductsText').show();
                $('.summaryPrice').show();
                $('.issue').show();

                $('.emptyProductsListLabel').hide();
                $('.headerText').show();
                $('.productsBlock').show();
                $('.orderPageButton').show();
                $('.priceBlock').show();
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
    var products = $('.productBlock');

    var valid = true;
    $.each(products, function (index, product) {
        var imgClass = $(product).attr('class');
        var productIndex = imgClass.split(' ')[1].substr(13, imgClass.length);

        var numberOfProduct = $(product).find('.numberOfProduct-' + productIndex);
        numberOfProduct.removeClass('text-danger');

        var numberOfProductValue = numberOfProduct.text();
        var quantityInStockValue = $(product).find('.quantityInStock-' + productIndex).text().split(' ')[3];

        if (numberOfProductValue > quantityInStockValue) {
            numberOfProduct.addClass('text-danger');
            e.preventDefault();
            return valid = false;
        }
    });

    if (valid) {
        showCouponBlock();
        e.preventDefault();
    }
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
            if (responseInfo.statusCode === 200) {
                $('#checkCouponMessage').attr('class', 'text-success');

                $('.checkCouponButton').hide();
                $('.continueCouponButton').show();
            }
            else if (responseInfo.statusCode === 404)
                $('#checkCouponMessage').attr('class', 'text-danger');

            $('#checkCouponMessage').text(responseInfo.message);
        },
        error: function (e) {
            console.log("ERROR: " + e);
        }
    });
});
