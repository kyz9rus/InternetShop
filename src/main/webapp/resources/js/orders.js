$('.orderStatus').click(function () {
    var imgClass = $(this).attr('class');
    var index = imgClass.split(' ')[0].substr(12, imgClass.length);

    $('.orderStatus-' + index).hide();
    $('.editOrderStatus-' + index).show();
});

$('.paymentStatus').click(function () {
    var imgClass = $(this).attr('class');
    var index = imgClass.split(' ')[0].substr(14, imgClass.length);

    $('.paymentStatus-' + index).hide();
    $('.editPaymentStatus-' + index).show();
});