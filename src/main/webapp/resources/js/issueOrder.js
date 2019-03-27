$('.productOperations .plus').click(function(){
    var imgClass = $(this).attr('class');
    var index = imgClass.substr(10, imgClass.length);

    var data = {};
    data["productId"] = $('.productBlock-' + index + ' input[name="id"]').val();

    $.post({
        url: '/put-product',
        data: data,
        dataType: 'json',
        success: function (basket) {
            console.log(basket);

            $('.basketInfo .numberOfProductsText').text('Number of products: ' + basket.numberOfProducts);
            $('.basketInfo .summaryPrice').text('Summary price: ' + basket.summaryPrice + ' rub.');

            $('.emptyBasketBlock').hide();
            $('.basketInfo')
                .fadeIn()
                .delay(2000)
                .fadeOut();

            // $('.numberOfProduct-' + index).text(basket.products); change basketInfo
        },
        error: function (basket) {
            console.log('ERROR: ' + basket);
        }
    });
});

$('.productOperations .minus').click(function(){
    // var imgClass = $(this).attr('class');
    // var index = imgClass.substr(14, imgClass.length);
    //
    // var data = {};
    // data["productId"] = $('.productBlock-' + index + ' input[name="id"]').val();
    //
    // $.post({
    //     url: '/remove-product',
    //     data: data,
    //     dataType: 'json',
    //     success: function (basket) {
    //         console.log(basket);
    //
    //         if (basket.numberOfProducts === 0) {
    //             $('.emptyBasket').show();
    //             $('.numberOfProductsText').hide();
    //             $('.summaryPrice').hide();
    //             $('.issue').hide();
    //
    //             $('.emptyProductsListLabel').show();
    //             $('.headerText').hide();
    //             $('.productsBlock').hide();
    //         } else {
    //             $('.emptyBasket').hide();
    //             $('.numberOfProductsText').show();
    //             $('.summaryPrice').show();
    //             $('.issue').show();
    //
    //             $('.emptyProductsListLabel').hide();
    //             $('.headerText').show();
    //             $('.productsBlock').show();
    //         }
    //
    //         $('.basketInfo .numberOfProductsText').text('Number of products: ' + basket.numberOfProducts);
    //         $('.basketInfo .summaryPrice').text('Summary price: ' + basket.summaryPrice + ' rub.');
    //
    //         $('.emptyBasketBlock').hide();
    //         $('.basketInfo')
    //             .fadeIn()
    //             .delay(2000)
    //             .fadeOut();
    //
    //         $('.product-' + index).hide();
    //     },
    //     error: function (basket) {
    //         console.log('ERROR: ' + basket);
    //     }
    // });
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
        success: function (basket) {
            console.log(basket);

            if (basket.numberOfProducts === 0) {
                $('.emptyBasket').show();
                $('.numberOfProductsText').hide();
                $('.summaryPrice').hide();
                $('.issue').hide();

                $('.emptyProductsListLabel').show();
                $('.headerText').hide();
                $('.productsBlock').hide();
            } else {
                $('.emptyBasket').hide();
                $('.numberOfProductsText').show();
                $('.summaryPrice').show();
                $('.issue').show();

                $('.emptyProductsListLabel').hide();
                $('.headerText').show();
                $('.productsBlock').show();
            }

            $('.basketInfo .numberOfProductsText').text('Number of products: ' + basket.numberOfProducts);
            $('.basketInfo .summaryPrice').text('Summary price: ' + basket.summaryPrice + ' rub.');

            $('.emptyBasketBlock').hide();
            $('.basketInfo')
                .fadeIn()
                .delay(2000)
                .fadeOut();

            $('.productBlock-' + index).hide();
        },
        error: function (basket) {
            console.log('ERROR: ' + basket);
        }
    });
});