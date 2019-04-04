$('.orderStatus').click(function () {
    var imgClass = $(this).attr('class');
    var index = imgClass.split(' ')[0].substr(12, imgClass.length);

    $('.orderStatus-' + index).hide();
    $('.editOrderStatus-' + index).show();
});

$('.editOrderStatus .formButton').click(function () {
    var imgClass = $(this).attr('class');
    var index = imgClass.split(' ')[2].substr(11, imgClass.length);

    var data = {};
    data["id"] = $('.editOrderStatus-' + index + ' input[name="id"]').val();
    data["orderStatus"] = $('.editOrderStatus-' + index + ' select[name="orderStatus"]').val();

    $.post({
        url: '/employeeProfile/change-order-status',
        data: data,
        dataType: 'json',
        success: function (responseInfo) {
            if (responseInfo.statusCode === 200) {
                $('.successMessage').text(responseInfo.message);
                $('.orderStatus-'+index+'').text(data["orderStatus"]);
            }
            else if (responseInfo.statusCode === 404)
                $('.errorMessage').text(responseInfo.message);

            $('.orderStatus-' + index).show();
            $('.editOrderStatus-' + index).hide();
        },
        error: function (basketInfo) {
            console.log('ERROR: ' + basketInfo);
        }
    });
});

$('.paymentStatus').click(function () {
    var imgClass = $(this).attr('class');
    var index = imgClass.split(' ')[0].substr(14, imgClass.length);

    $('.paymentStatus-' + index).hide();
    $('.editPaymentStatus-' + index).show();
});

$('.editPaymentStatus .formButton').click(function () {
    var imgClass = $(this).attr('class');
    var index = imgClass.split(' ')[2].substr(11, imgClass.length);

    var data = {};
    data["id"] = $('.editPaymentStatus-' + index + ' input[name="id"]').val();
    data["paymentStatus"] = $('.editPaymentStatus-' + index + ' select[name="paymentStatus"]').val();

    $.post({
        url: '/employeeProfile/change-payment-status',
        data: data,
        dataType: 'json',
        success: function (responseInfo) {
            if (responseInfo.statusCode === 200) {
                $('.successMessage').text(responseInfo.message);
                $('.paymentStatus-'+index+'').text(data["paymentStatus"]);
            }
            else if (responseInfo.statusCode === 404)
                $('.errorMessage').text(responseInfo.message);

            $('.paymentStatus-' + index).show();
            $('.editPaymentStatus-' + index).hide();
        },
        error: function (basketInfo) {
            console.log('ERROR: ' + basketInfo);
        }
    });
});