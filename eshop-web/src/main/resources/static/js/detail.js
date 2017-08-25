var id = getQueryString(curPageUrl, 'id');
var dataUrl = "/products/" + id;

$('.product_detail_table').bootstrapTable({
    url: dataUrl,
    method: "GET",
    columns: [{
        field: 'id',
        title: 'ID'
    }, {
        field: 'name',
        title: '商品名称'
    }, {
        field: 'price',
        title: '商品价格'
    }, {
        field: 'desc',
        title: '商品描述'
    }],
     responseHandler: handler
});
function handler(res) {
    return res;
     
}