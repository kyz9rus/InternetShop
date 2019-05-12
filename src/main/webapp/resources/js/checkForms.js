$('.registrationBlock button').click(function (e) {
    var formMessage = $('.formMessage');
    formMessage.text('');

    if ($('.registrationBlock input[name="lastName"]').val().replace(/\s+/g, '').length === 0) {
        formMessage.text('Enter your last name');
        formMessage.addClass('text-danger');
        e.preventDefault()
    } else if ($('.registrationBlock input[name="firstName"]').replace(/\s+/g, '').length === 0) {
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

$('.changePasswordBlock .formButton').click(function (e) {
    var newPassword = $('input[name="newPassword"]').val();
    var repeatNewPassword = $('input[name="repeatNewPassword"]').val();

    if (!newPassword.match(/(?=.*[0-9])[0-9a-zA-Z!@#$%^&*]{7,}/)) {
        $('.errorMessage').text('The password must consist of at least 7 characters and contain one digit');
        e.preventDefault()
    } else if (newPassword !== repeatNewPassword) {
        $('.errorMessage').text('Entered passwords do not match');
        e.preventDefault();
    } else
        $('.errorMessage').text('');

});

$('.addProductBlock button').click(function (e) {
    var volume = $('.addProductBlock input[name="volume"]').val();

    console.log($('#price').val() + ' | ' + /^[0-9]+$/.test($('#price').val()));

    if (volume.length !== 11) {
        $('.errorMessage').text('Enter volume following the pattern (999x999x999)');
        e.preventDefault()
    } else if (!/^[0-9]+$/.test($('#price').val())) {
        $('.errorMessage').text('Price must consist of numbers only');
        e.preventDefault()
    } else if (!/^[0-9]+$/.test($('#weight').val())) {
        $('.errorMessage').text('Weight must consist of numbers only');
        e.preventDefault()
    } else if (!/^[0-9]+$/.test($('#quantityInStock').val()) && parseInt($('#quantityInStock').val()) <= 0) {
        $('.errorMessage').text('Quantity in stock must consist of numbers only and cannot be negative');
        e.preventDefault()
    }
});

$('.editProfileBlock button').click(function (e) {
    var formMessage = $('.formMessage');
    formMessage.text('');

    if ($('.editProfileBlock input[name="lastName"]').val().length === 0) {
        formMessage.text('Enter your last name');
        formMessage.addClass('text-danger');
        e.preventDefault()
    } else if ($('.editProfileBlock input[name="firstName"]').val().length === 0) {
        formMessage.text('Enter your first name');
        formMessage.addClass('text-danger');
        e.preventDefault()
    } else if ($('.editProfileBlock input[name="birthday"]').val().length === 0) {
        formMessage.text('Enter your date of birth');
        formMessage.addClass('text-danger');
        e.preventDefault()
    } else if ($('.editProfileBlock input[name="email"]').val().length === 0) {
        formMessage.text('Enter your email address');
        formMessage.addClass('text-danger');
        e.preventDefault()
    }

    formMessage.show();
});
