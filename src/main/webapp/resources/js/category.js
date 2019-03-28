$('.buyButton').click(function () {
    var imgClass = $(this).attr('class');
    var index = imgClass.substr(24, imgClass.length);

    var data = {};
    data["productId"] = $('.product-' + index + ' input[name="id"]').val();

    $.post({
        url: '/put-product',
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

$('.searchProduct').on('input', function () {
    filterProducts($('.productList'));
});

function filterProducts(data) {
    var filters = $('.searchProduct');
    var rows = data.find('.product');
    rows.each(function () {
        var valid = true;
        if ($(this).find('.productName').text().toLowerCase().indexOf(filters.val().toLowerCase()) === -1)
            valid = valid && false;

        if (valid === true)
            $(this).css('display', '');
        else
            $(this).css('display', 'none');
    });
}