$(document).ready(function () {
    navi2page($(".navbar-brand").attr("data-page"));

    $("ul.navbar-nav li").each(function () {
        $(this).on("click", function (e) {
            var newPage = $(this).attr("data-page");
            navi2page(newPage);
        })
    });

    $(".navbar-brand").on("click", function (e) {
        var newPage = $(this).attr("data-page");
        navi2page(newPage);
    });
});
