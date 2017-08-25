$(document).ready(function () {
    $('.login-form input[type="text"], .login-form input[type="password"], .login-form textarea').on('focus', function () {
        $(this).removeClass('input-error');
    });

    //$(".login-form").bind("submit", function (e) {
    //$(this).find('input[type="text"], input[type="password"], textarea').each(function () {
    //    if ($(this).val() == "") {
    //        e.preventDefault();
    //        $(this).addClass('input-error');
    //        return;
    //    }
    //    else {
    //        $(this).removeClass('input-error');
    //    }
    //});

    $(".btn").click(function () {
        $.post($(".login-form").attr("action"), $(".login-form").serialize(), function (res) {
            if (res.code == 200) {
                navi2page("/cart.html");
            }
        });
    });
    //});
});
