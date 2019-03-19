// $(function () {
//     var orderStatuses = $('.orderStatus');
//     var paymentStatus = $('.paymentStatus');
//
//     orderStatuses.forEach(function (orderStatus) {
//         orderStatus.removeClass();
//
//         if (orderStatus.text() === 'waitingForPayment')
//             orderStatus.addClass('text-danger');
//         else if (orderStatus.text() === 'waitingForShipment')
//             orderStatus.addClass('text-warning');
//         else if (orderStatus.text() === 'shipped')
//             orderStatus.addClass('text-primary');
//         else if (orderStatus.text() === 'delivered')
//             orderStatus.addClass('text-success');
//         paymentStatus.addClass('orderStatus');
//     });
//
//     paymentStatus.forEach(function (paymentStatus) {
//         paymentStatus.removeClass();
//         if (paymentStatus.text() === 'waitingForPayment')
//             paymentStatus.addClass('text-warning');
//         else if (paymentStatus.text() === 'paid')
//             paymentStatus.addClass('text-success');
//         paymentStatus.removeClass('paymentStatus');
//     });
// });