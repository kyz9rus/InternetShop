var displayBasket = false;

$('.basket').click(function (){
    displayBasket = !displayBasket;

    if (displayBasket)
        $('.basketInfo').show();
    else
        $('.basketInfo').hide();
});