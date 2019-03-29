let products = $('.product');

$('#sortSelect').on('change', () => {
    var sortSelectValue = $('#sortSelect').val();
    if (sortSelectValue === 'Alphabet (A-Z):')
        sortByNameASC();
    else if (sortSelectValue === 'Alphabet (Z-A):')
        sortByNameDESC();
    else if (sortSelectValue === 'Price (descending):')
        sortByPriceASC();
    else if (sortSelectValue === 'Price (ascending):')
        sortByPriceDESC();
});

function sortByNameASC() {
    products.sortElements(function (a, b) {
        return $(a).find('.productName').text() > $(b).find('.productName').text() ? 1 : -1;
    });
}

function sortByNameDESC() {
    products.sortElements(function (a, b) {
        return $(a).find('.productName').text() < $(b).find('.productName').text() ? 1 : -1;
    });
}

function sortByPriceASC() {
    products.sortElements(function (a, b) {
        return parseInt($(a).find('.productPriceValue').val()) > parseInt($(b).find('.productPriceValue').val()) ? 1 : -1;
    });
}

function sortByPriceDESC() {
    products.sortElements(function (a, b) {
        return parseInt($(a).find('.productPriceValue').val()) < parseInt($(b).find('.productPriceValue').val()) ? 1 : -1;
    });
}

$.fn.sortElements = (function () {
    var sort = [].sort;
    return function (comparator, getSortable) {
        getSortable = getSortable || function () {
            return this;
        };
        var placements = this.map(function () {
            var sortElement = getSortable.call(this),
                parentNode = sortElement.parentNode,
                nextSibling = parentNode.insertBefore(
                    document.createTextNode(''),
                    sortElement.nextSibling
                );
            return function () {
                if (parentNode === this) {
                    throw new Error(
                        "You can't sort elements if any one is a descendant of another."
                    );
                }
                parentNode.insertBefore(this, nextSibling);
                parentNode.removeChild(nextSibling);
            };
        });
        return sort.call(this, comparator).each(function (i) {
            placements[i].call(getSortable.call(this));
        });
    };
})();