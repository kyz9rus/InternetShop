$('.orderInfo select[name="paymentMethod"]').click(function () {
    var paymentMethod = $('.orderInfo select[name="paymentMethod"]').val();

    if (paymentMethod === 'By card') {
        $('.byCardBlock').show();
        $('.issueOrderButton').hide();
    } else {
        $('.byCardBlock').hide();
        $('.issueOrderButton').show();
    }
});

$('.confirmPaymentBlock button').click(function (e) {
    e.preventDefault();

    if ($('.orderInfo select[name="paymentMethod"]').val() === 'By card') {
        var cardName = $('.cardInfo input[name="cardName"]');
        var cardNumber = $('.cardInfo input[name="cardNumber"]');
        var validUntilMonth = $('.cardInfo input[name="validUntilMonth"]');
        var validUntilYear = $('.cardInfo input[name="validUntilYear"]');
        var cvc = $('.cardInfo input[name="cvc"]');

        cardName.attr('style', 'outline: none');
        cardNumber.attr('style', 'outline: none');
        validUntilMonth.attr('style', 'outline: none');
        validUntilYear.attr('style', 'outline: none');
        cvc.attr('style', 'outline: none');

        if (cardName.val() === '') {
            cardName.attr('style', 'outline: 1px solid red');
            return;
        } else if (cardNumber.val() === '') {
            cardNumber.attr('style', 'outline: 1px solid red');
            return;
        } else if (validUntilMonth.val() === '') {
            validUntilMonth.attr('style', 'outline: 1px solid red');
            return;
        } else if (validUntilYear.val() === '') {
            validUntilYear.attr('style', 'outline: 1px solid red');
            return;
        } else if (cvc.val() === '') {
            cvc.attr('style', 'outline: 1px solid red');
            return;
        }
    }

    alert("Payment passed successfully");
    $('.byCardBlock').hide();
    $('.issueOrderButton').show();
    $('.paymentMethodBlock').hide();
});
