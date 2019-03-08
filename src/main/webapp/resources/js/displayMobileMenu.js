var isActive = false;

$('.menuButton').click(function(e) {
    isActive = !isActive;

    e.preventDefault();

    $('.mobileMenu').toggleClass('menu_active');

    if (isActive)
        $('.content').css({'background-color': 'rgba(0,0,0,0.4)'});
    else
        $('.content').css({'background-color': 'rgba(0,0,0,0)'});
});
