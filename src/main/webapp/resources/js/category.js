$('.buyButton').click(function () {
    var imgClass = $(this).attr('class');
    var index = imgClass.substr(24, imgClass.length);

    var data = {};
    data["productId"] = $('.product-' + index + ' input[name="id"]').val();

    $.post({
        url: '/put-product',
        data: data,
        dataType: 'json',
        success: function (basketInfo) {
            $('.basketInfo .numberOfProductsText').text('Number of products: ' + basketInfo.basket.numberOfProducts);
            $('.basketInfo .summaryPrice').text('Summary price: ' + basketInfo.basket.summaryPrice + ' rub.');

            $('.emptyBasket').hide();
            $('.numberOfProductsText').show();
            $('.summaryPrice').show();
            $('.issue').show();

            $('.basketInfo')
                .fadeIn()
                .delay(2000)
                .fadeOut();
        },
        error: function (basketInfo) {
            console.log('ERROR: ' + basketInfo);
        }
    });
});

function filterProductsBySearchText(index, product) {
    var filters = $('.searchProduct');
    var rows = data.find('.product');
    rows.each(function () {
        var valid = true;
        if ($(this).find('.productName').text().toLowerCase().includes(filters.val().toLowerCase()) === -1)
            valid = valid && false;

        if (valid === true)
            $(this).css('display', '');
        else
            $(this).css('display', 'none');
    });
}

function filterProductsByQuantity(data, checkbox) {
    var rows = data.find('.product');
    rows.each(function () {
        if (checkbox.is(":checked"))
            if ($(this).find('.quantityInStockValue').val() <= 0)
                $(this).css('display', 'none');
            else
                $(this).css('display', '');
        else
            $(this).css('display', '');
    });
}

function createPriceFilter() {
    var elem = document.querySelector('.js-range');
    var minPrice = parseInt(findMinPrice(), 10);
    var maxPrice = parseInt(findMaxPrice(), 10);

    var init = new Powerange(elem, {
        min: minPrice,
        max: maxPrice,
        start: (maxPrice - minPrice) / 2,
        decimal: true,
        klass: 'priceFilter',
        callback: function () {
            filterProductsByPrice();
        }
    });

    $('.priceFilter .range-min').text(minPrice);
    $('.priceFilter .range-max').text(maxPrice);
}

function findMinPrice() {
    var productPrices = $('.productList input[name="price"]');
    var minPrice = 9999999;

    $.each(productPrices, function (index, priceInput) {
        if (parseInt(priceInput.value, 10) < minPrice)
            minPrice = priceInput.value;
    });

    return minPrice;
}

function findMaxPrice() {
    var productPrices = $('.productList input[name="price"]');
    var maxPrice = 0;

    $.each(productPrices, function (index, priceInput) {
        if (parseInt(priceInput.value, 10) > maxPrice)
            maxPrice = priceInput.value;
    });

    return maxPrice;
}

$(function () {
    createPriceFilter();
});

var productList = $('.product');
var productListAfterSearchFilter = [];
var productListAfterCheckboxFilter = [];
var productListAfterPriceFilter = [];

var checkFilteringBySearching = false;
var checkFilteringByCheckbox = false;
var checkFilteringByPrice = false;

function filterProductsByPrice() {
    var input = document.querySelector('.js-range');

    var filterValue = $('.filterValue');
    if (input !== null) {
        filterValue.text(input.value);

        productListAfterPriceFilter =
        productList.filter(function () {
            return $(this).find('.productPriceValue').val() >= parseInt(filterValue.text(), 10);
        });
    }

    checkFilteringByPrice = true;

    checkProductList();
}

$('.searchProduct').on('input', function () {
    if ($('.searchProduct').val() === '')
        productListAfterSearchFilter = productList;
    else
        productListAfterSearchFilter =
            productList.filter(function () {
                console.log($(this).find('.productName').text().toLowerCase() + " | " + $('.searchProduct').val().toLowerCase() + " | " + $(this).find('.productName').text().toLowerCase().includes($('.searchProduct').val().toLowerCase()) !== -1);
                return $(this).find('.productName').text().toLowerCase().includes($('.searchProduct').val().toLowerCase());
            });

    checkFilteringBySearching = true;

    checkProductList();
});

$('.inStockCheckbox').on('change', function () {
    productListAfterCheckboxFilter =
        productList.filter(function () {
            console.log("isCheked: " + $('.inStockCheckbox').is(":checked") + " | quantity: " + $(this).find('.quantityInStockValue'));
            return $('.inStockCheckbox').is(":checked") ? ($(this).find('.quantityInStockValue').val() > 0) : true;
        });

    checkFilteringByCheckbox = true;

    checkProductList();
});

function checkProductList() {
    var productArrayAfterSearchFilter = [], productArrayAfterCheckboxFilter = [], productArrayAfterPriceFilter = [];

    $.each(productListAfterSearchFilter, function (index, product) {
        productArrayAfterSearchFilter[index] = product;
    });

    $.each(productListAfterCheckboxFilter, function (index, product) {
        productArrayAfterCheckboxFilter[index] = product;
    });

    $.each(productListAfterPriceFilter, function (index, product) {
        productArrayAfterPriceFilter[index] = product;
    });

    if (checkFilteringBySearching && checkFilteringByCheckbox && checkFilteringByPrice) {
        $.each(productList, function (index, product) {
            if (productArrayAfterSearchFilter.includes(product) && productArrayAfterCheckboxFilter.includes(product) && productArrayAfterPriceFilter.includes(product))
                $(product).css('display', '');
            else
                $(product).css('display', 'none');
        });
    } else if (checkFilteringBySearching && checkFilteringByCheckbox) {
        $.each(productList, function (index, product) {
            if (productArrayAfterSearchFilter.includes(product) && productArrayAfterCheckboxFilter.includes(product))
                $(product).css('display', '');
            else
                $(product).css('display', 'none');
        });
    } else if (checkFilteringBySearching && checkFilteringByPrice) {
        $.each(productList, function (index, product) {
            if (productArrayAfterSearchFilter.includes(product) && productArrayAfterPriceFilter.includes(product))
                $(product).css('display', '');
            else
                $(product).css('display', 'none');
        });
    } else if (checkFilteringByCheckbox && checkFilteringByPrice) {
        $.each(productList, function (index, product) {
            if (productArrayAfterCheckboxFilter.includes(product) && productArrayAfterPriceFilter.includes(product))
                $(product).css('display', '');
            else
                $(product).css('display', 'none');
        });
    } else if (checkFilteringBySearching) {
        $.each(productList, function (index, product) {
            if (productArrayAfterSearchFilter.includes(product))
                $(product).css('display', '');
            else
                $(product).css('display', 'none');
        });
    } else if (checkFilteringByCheckbox) {
        $.each(productList, function (index, product) {
            if (productArrayAfterCheckboxFilter.includes(product))
                $(product).css('display', '');
            else
                $(product).css('display', 'none');
        });
    } else if (checkFilteringByPrice) {
        $.each(productList, function (index, product) {
            if (productArrayAfterPriceFilter.includes(product))
                $(product).css('display', '');
            else
                $(product).css('display', 'none');
        });
    } else
        return;
}