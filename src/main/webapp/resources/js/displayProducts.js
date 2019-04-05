$(function () {
    if ($(window).width() <= 577) {
        $('.productImage').attr('align', 'center');
        $('.productInfo').attr('align', 'center');
    }
    else {
        $('.productImage').removeAttr('align');
        $('.productInfo').removeAttr('align');
    }
});