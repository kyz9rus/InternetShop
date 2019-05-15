$(function () {
    createPriceFilter();
});

function createPriceFilter() {
    var elem = document.querySelector('.js-range');
    var minPrice = parseInt(findMinPrice(), 10);
    var maxPrice = parseInt(findMaxPrice(), 10);

    if (minPrice === 9999999 || maxPrice === 0) {
        var init = new Powerange(elem, {
            min: 0,
            max: 500,
            start: 0,
            decimal: true,
            klass: 'priceFilter',
            callback: function () {
                filterProductsByPrice();
            }
        });
    } else {
        var init = new Powerange(elem, {
            min: minPrice,
            max: maxPrice,
            start: minPrice,
            decimal: true,
            klass: 'priceFilter',
            callback: function () {
                filterProductsByPrice();
            }
        });

        $('.priceFilter .range-min').text(minPrice);
        $('.priceFilter .range-max').text(maxPrice);
    }
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