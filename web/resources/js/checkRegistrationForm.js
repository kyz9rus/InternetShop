$('.registrationBlock button').click(function (e){
    var formMessage = $('.formMessage');
    formMessage.text('');

    if ($('.registrationBlock input[name="lastName"]').val().length === 0) {
        formMessage.text('Введите вашу фамилию');
        formMessage.addClass('text-danger');
        e.preventDefault()
    } else if ($('.registrationBlock input[name="firstName"]').val().length === 0) {
        formMessage.text('Введите ваше имя');
        formMessage.addClass('text-danger');
        e.preventDefault()
    } else if ($('.registrationBlock input[name="birthday"]').val().length === 0) {
        formMessage.text('Введите вашу дату рождения');
        formMessage.addClass('text-danger');
        e.preventDefault()
    } else if ($('.registrationBlock input[name="email"]').val().length === 0) {
        formMessage.text('Введите ваш адрес электронной почты');
        formMessage.addClass('text-danger');
        e.preventDefault()
    } else if ($('.registrationBlock input[name="password"]').val().length === 0) {
        formMessage.text('Введите пароль');
        formMessage.addClass('text-danger');
        e.preventDefault()
    } else if ($('.registrationBlock input[name="password"]').val().length !== 0) {
        var password = $('.registrationBlock input[name="password"]').val();
        var repeatPassword = $('.registrationBlock input[name="repeatPassword"]').val();

        if (!password.match(/(?=.*[0-9])[0-9a-zA-Z!@#$%^&*]{7,}/)) {
            formMessage.addClass('text-danger');
            formMessage.text('Пароль должен состоять не менее чем из 7 символов и содержать зотя бы одну цифру');
            e.preventDefault()
        } else if (password !== repeatPassword) {
            formMessage.text('Введенные пароли не совпадают');
            formMessage.addClass('text-danger');
            e.preventDefault()
        } else {
            formMessage.addClass('text-success');
            formMessage.removeClass('text-danger');
        }
    }

    formMessage.show();
});