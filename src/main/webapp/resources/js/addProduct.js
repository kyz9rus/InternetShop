$('.addProductBlock input[name="imgSrc"]').on('input', function () {
    var imgSrc = $('.addProductBlock input[name="imgSrc"]');

    $('#cropImage').html('<img src="' + imgSrc.val() + '" />')

    $('#cropImage img').cropbox({
        width: 148,
        height: 148,
        controls: false
    }, function() {
        //on load

        console.log('Url: ' + this.getDataURL());
    }).on('cropbox', function(e, data) {
        console.log('crop window: ' + data);
    });
});