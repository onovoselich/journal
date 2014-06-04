$(document).ready(function () {

    showContent(sum);
    $(".tabs a").click(function (e) {
        e.preventDefault();
        sum = this.id.substr(4);
        showContent(sum);
    });

});

function showContent(sum) {
    if (sum == "all") {
        $(".tabcontent").show();
    }
    else {
        $(".tabcontent").hide()

        $("#content_" + sum).show();
        $(".content_" + sum).show();
    }

    if (sum != "noone") {
        $(".content_noone").hide();
        $("#content_noone").hide();
    }
    $(".tabs a").removeClass("current")
    $("#tab_" + sum).addClass("current");

}