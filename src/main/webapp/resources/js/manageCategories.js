$('.categoryTextBlock img').click(function (){
    var imgClass = $(this).attr('class');
    var index = imgClass.substr(4, imgClass.length);

    $('.categoryTextBlock-' + index).hide();
    $('.categoryFormBlock-' + index).show();
});

$('.addCategoryButton').click(function() {
    $('.addCategoryBlock').show();
    $(this).hide();
});

$('.addCategoryBlock .formButton').click(function (e) {
    if ($('.addCategoryBlock input[name="name"]').val().length === 0) {
        e.preventDefault();

        $('.errorMessage').text('Category can\'t be empty.');
    } else
        $('.errorMessage').text('');

});
