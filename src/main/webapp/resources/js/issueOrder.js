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