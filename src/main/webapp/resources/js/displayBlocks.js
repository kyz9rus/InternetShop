$('.clientOperation').click(function () {
    $('.messageBlock').hide();

    var operationName = $(this).find("label").text();

    $('.variant').hide();

    if (operationName === 'View profile')
        $('.profileBlock').show();
    else if (operationName === 'Edit profile data')
        $('.editProfileBlock').show();
    else if (operationName === 'Change password')
        $('.changePasswordBlock').show();
    else if (operationName === 'Issue order')
        $('.issueOrderBlock').show();
    else if (operationName === 'View order history')
        $('.orderHistoryBlock').show();
    else if (operationName === 'Repeat order')
        $('.repeatOrderBlock').show();
});

$('.employeeOperation').click(function () {
    $('.messageBlock').hide();

    var operationName = $(this).find("label").text();

    $('.variant').hide();

    if (operationName === 'View orders')
        $('.ordersBlock').show();
    else if (operationName === 'Change order status')
        $('.changeOrderStatusBlock').show();
    else if (operationName === 'Sales statistics')
        $('.salesStatisticsBlock').show();
    else if (operationName === 'Add product')
        $('.addProductBlock').show();
    else if (operationName === 'Creating and managing categories of the directory')
        $('.categoriesManagingBlock').show();
    else if (operationName === 'Import from file')
        $('.importFromFileBlock').show();
});

$('.formButton').click( function () {
   $('.messageBlock').show();
});