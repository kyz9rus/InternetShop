$('.clientOperation').click(function () {
    var operationName = $(this).find("label").text();

    $('.variant').hide();

    if (operationName === 'View profile')
        $('.showProfile').show();
    else if (operationName === 'Edit profile data')
        $('.editProfile').show();
    else if (operationName === 'Change password')
        $('.changePassword').show();
    else if (operationName === 'Issue order')
        $('.orderRegistration').show();
    else if (operationName === 'View order history')
        $('.showOrdersHistory').show();
    else if (operationName === 'Repeat order')
        $('.repeatOrder').show();
});

$('.employeeOperation').click(function () {
    var operationName = $(this).find("label").text();

    $('.variant').hide();

    if (operationName === 'View orders')
        $('.showProfile').show();
    else if (operationName === 'Change order status')
        $('.editProfile').show();
    else if (operationName === 'Sales statistics')
        $('.changePassword').show();
    else if (operationName === 'Add product')
        $('.orderRegistration').show();
    else if (operationName === 'Creating and managing categories of the directory')
        $('.showOrdersHistory').show();
    else if (operationName === 'Import from file')
        $('.repeatOrder').show();
});
