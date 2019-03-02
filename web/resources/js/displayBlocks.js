$('.operation').click(function () {
    var operationName = $(this).find("label").text();

    $('.variant').hide();

    if (operationName === 'Посмотреть профиль')
        $('.showProfile').show();
    else if (operationName === 'Изменить данные профиля')
        $('.editProfile').show();
    else if (operationName === 'Сменить пароль')
        $('.changePassword').show();
    else if (operationName === 'Оформление заказа')
        $('.orderRegistration').show();
    else if (operationName === 'Просмотр истории заказов')
        $('.showOrdersHistory').show();
    else if (operationName === 'Повторить заказ')
        $('.repeatOrder').show();
});