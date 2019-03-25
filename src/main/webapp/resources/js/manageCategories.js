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
