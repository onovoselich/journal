$(document).ready(function () {
    $("form").submit(function () {
        setCookie("pagescroll", $(window).scrollTop());
    });

    $(window).scrollTop(getCookie("pagescroll"));
    setCookie("pagescroll", "");

});

function setCookie(cname, cvalue) {

    document.cookie = cname + "=" + cvalue + ";   path=/";
}

function getCookie(cname) {
    var name = cname + "=";
    var ca = document.cookie.split(';');
    for (var i = 0; i < ca.length; i++) {
        var c = ca[i].trim();
        if (c.indexOf(name) == 0) return c.substring(name.length, c.length);
    }
    return "";
}