$(document).ready(function () {


    $(".popup").click(function (e) {
        e.preventDefault();
        switch ($(this).text()) {
            case "↑":
                $(this).parent().next().slideUp(1000);
                $(this).text("↓");
                setCookie(this.id + " hidden", "true");
                break;
            case "↓":
                $(this).parent().next().slideDown(1000);
                $(this).text("↑");
                setCookie(this.id + " hidden", "");
                break;
        }


    });

    $(".popup").each(function () {
        if (getCookie(this.id + " hidden") == "true") {
            $(this).parent().next().hide();
            $(this).text("↓");
        }
        ;

    });


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