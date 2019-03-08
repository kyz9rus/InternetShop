$('.registrationBlock button').click(function (e) {
    var formMessage = $('.formMessage');
    formMessage.text('');

    if ($('.registrationBlock input[name="lastName"]').val().length === 0) {
        formMessage.text('Enter your last name');
        formMessage.addClass('text-danger');
        e.preventDefault()
    } else if ($('.registrationBlock input[name="firstName"]').val().length === 0) {
        formMessage.text('Enter your first name');
        formMessage.addClass('text-danger');
        e.preventDefault()
    } else if ($('.registrationBlock input[name="birthday"]').val().length === 0) {
        formMessage.text('Enter your date of birth');
        formMessage.addClass('text-danger');
        e.preventDefault()
    } else if ($('.registrationBlock input[name="email"]').val().length === 0) {
        formMessage.text('Enter your email address');
        formMessage.addClass('text-danger');
        e.preventDefault()
    } else if ($('.registrationBlock input[name="password"]').val().length === 0) {
        formMessage.text('Enter password');
        formMessage.addClass('text-danger');
        e.preventDefault()
    } else if ($('.registrationBlock input[name="password"]').val().length !== 0) {
        var password = $('.registrationBlock input[name="password"]').val();
        var repeatPassword = $('.registrationBlock input[name="repeatPassword"]').val();

        if (!password.match(/(?=.*[0-9])[0-9a-zA-Z!@#$%^&*]{7,}/)) {
            formMessage.addClass('text-danger');
            formMessage.text('The password must consist of at least 7 characters and contain one digit');
            e.preventDefault()
        } else if (password !== repeatPassword) {
            formMessage.text('Entered passwords do not match');
            formMessage.addClass('text-danger');
            e.preventDefault()
        } else {
            formMessage.addClass('text-success');
            formMessage.removeClass('text-danger');
        }
    }

    formMessage.show();
});

$('.changePassword .formButton').click(function (e) {
    var newPassword = $('input[name="newPassword"]').val();
    var repeatNewPassword = $('input[name="repeatNewPassword"]').val();

    if (!newPassword.match(/(?=.*[0-9])[0-9a-zA-Z!@#$%^&*]{7,}/)) {
        $('.changePassword .errorMessage').text('The password must consist of at least 7 characters and contain one digit');
        e.preventDefault()
    } else if (newPassword !== repeatNewPassword) {
        $('.changePassword .errorMessage').text('Entered passwords do not match');
        e.preventDefault();
    } else
        $('.changePassword .errorMessage').text('');

});