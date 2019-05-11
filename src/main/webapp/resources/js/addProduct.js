// import Cropper from "./cropper2/cropper.js";

$('.addProductBlock input[name="imgSrc"]').on('input', function () {
    var imgSrc = $('.addProductBlock input[name="imgSrc"]');

    $('#cropImage').html('<img id="image" src="' + imgSrc.val() + '" />');

    var $image = $('#cropImage img');
    // var $image = document.getElementById('image');
    //
    // const cropper = new Cropper($image, {
    //     aspectRatio: 16 / 9,
    //     crop(event) {
    //         console.log(event.detail.x);
    //         console.log(event.detail.y);
    //         console.log(event.detail.width);
    //         console.log(event.detail.height);
    //         console.log(event.detail.rotate);
    //         console.log(event.detail.scaleX);
    //         console.log(event.detail.scaleY);
    //     },
    // });
    //
    // const containerData = cropper.getCroppedCanvas(
    //     {
    //         width: 150,
    //         height: 150,
    //     }
    // );
    //
    // console.log(containerData);
    //
    // $('#cropImage2').html(containerData);

    // $image.cropper({
    //     aspectRatio: 16 / 9,
    //     crop: function(event) {
    //         console.log(event.detail.x);
    //         console.log(event.detail.y);
    //         console.log(event.detail.width);
    //         console.log(event.detail.height);
    //         console.log(event.detail.rotate);
    //         console.log(event.detail.scaleX);
    //         console.log(event.detail.scaleY);
    //     }
    // });
    //
    // var cropper = $image.data('cropper');
    //
    // console.log(cropper);
    //
    // $image.cropper("getCroppedCanvas", {height: 150});
    //
    // $().cropper("getCroppedCanvas", {height: 150});


    $('#cropImage img').cropbox({
        width: 148,
        height: 148,
        controls: false,
        label: ''
    }, function() {
        //on load

        console.log('Url: ' + this.getDataURL());
    }).on('cropbox', function(e, data) {
        console.log('crop window: ' + data);
    });


});