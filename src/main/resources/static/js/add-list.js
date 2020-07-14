jQuery.fn.outerHTML = function () {
    return jQuery('<div />').append(this.eq(0).clone()).html();
};

function addList(elementPrefix) {
    let element = $("div[id^=" + elementPrefix + "]").last();
    let index = element.attr("id").substring(element.attr("id").indexOf(elementPrefix) + elementPrefix.length);
    element.after(element.outerHTML().replace(new RegExp( elementPrefix + index, 'g'), elementPrefix + (parseInt(index) + 1)));
}

function removeList(button, elementPrefix) {
    let n = $("div[id^=" + elementPrefix + "]").length;
    if (n > 1) {
        $(button).closest("div[id^=" + elementPrefix + "]").remove();
    }
}