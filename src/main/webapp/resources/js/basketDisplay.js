var displayBasket = false;

$('#basket').click(function (e){
    // e.preventDefault();
    //
    // $('.mobileMenu').toggleClass('menu_active');
    //
    displayBasket = !displayBasket;

    if (displayBasket)
        $('#basketInfo').show();
    else
        $('#basketInfo').hide();
});