$('.buyButton').click(function () {
    var data = {};
    data["productId"] = $('.productInfo2 input[name="id"]').val();

    $.ajax({
        url: '/put-product',
        type: "GET",
        data: data,
        dataType: 'json',
        success: function (basket) {
            console.log(basket);

            $('.basketBlock .numberOfProductsText').text('Number of products: ' + basket.numberOfProducts);
            $('.basketBlock .summaryPrice').text('Summary price: ' + basket.summaryPrice + ' rub.');

            $('.emptyBasketBlock').hide();
            $('.basketBlock').show();
            $('.basketInfo')
                .fadeIn()
                .delay(2000)
                .fadeOut();
        },
        error: function (basket) {
            console.log('ERROR: ' + basket);
        }
    });
});