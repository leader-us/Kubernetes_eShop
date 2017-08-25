function oper_detail(value, row) {
    var href = "/detail.html?id=" + row.id;
    return "<a href='#' onclick=navi2page('" + href + "')>商品详情</a>";
}

function oper_cart(value, row) {
    var json = {
        productId: row.id,
        productName: row.name,
        productPrice: row.price,
        count: 1
    };
    return "<a href='#' onclick='addToCart(" + JSON.stringify(json) + ")'>加入购物车</a>";
}

function addToCart(row) {
    var reqUrl = "/cart/add-cart";
    $.ajax({
        type: 'POST',
        url: reqUrl,
        data: JSON.stringify(row),
        dataType: 'json',
        contentType: "application/json ; charset=utf-8",
        success: function (data) {
            var code = data.code;
            if (code != undefined) {
                if (code == 200) {
                    navi2page("/cart.html");
                } else if (code == 503) {
                    navi2page("/login.html");
                } else {
                    alert("add to cart error");
                }
                return;
            }
            var directUrl = data.redirect;
            if (directUrl != undefined) {
                navi2page(directUrl);
                return;
            }
        }
    });
};
