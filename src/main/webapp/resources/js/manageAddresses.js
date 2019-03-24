$('.editAddress').click(function (e){
    var imgClass = $(this).attr('class');
    var index = imgClass.substr(45, imgClass.length);

    $('.addressTextBlock-' + index).hide();
    $('.addressFormBlock-' + index).show();

    e.preventDefault();
});

$('.addAddressButton').click(function() {
    $('.addAddressBlock').show();
    $(this).hide();
});
