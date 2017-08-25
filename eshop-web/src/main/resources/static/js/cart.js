$('.cart_table').bootstrapTable({
    url: "/cart/records",
    method: "GET",
    columns: [{
        field: 'productId',
        title: 'ID'
    }, {
        field: 'productName',
        title: '商品名称'
    }, {
        field: 'productPrice',
        title: '商品价格'
    },{
        field: "count",
        title: '数量'
    }, {
        formatter: "amount_formatter",
        title: '总额'
    }],
    responseHandler: handler
});

function handler(res) {
    if (res == undefined) {
        return res;
    }
    var directUrl = res.redirect;
    if (directUrl != undefined) {
        navi2page(directUrl);
        return "[]";
    }
    return res;
}


function amount_formatter(value, row) {
    return row.productPrice * row.count;
}
