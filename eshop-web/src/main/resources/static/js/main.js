var curPageUrl;
var prePageUrl;

function navi2page(newpage) {
    prePageUrl = curPageUrl;
    curPageUrl = newpage;
    $("#bodymain").load(newpage);
}

function back2page() {
    curPageUrl = prePageUrl;
    $("#bodymain").load(curPageUrl);
}
