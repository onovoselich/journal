$(document).ready(function(){
    showContent(sum);
    $(".tabs a").click(function(e){
        e.preventDefault();
        sum = this.id.substr(4);
        showContent(sum);
    });

});

function showContent(sum){
    $(".tabcontent").hide()/*.removeClass("current")*/;
    $(".tabs a").removeClass("current")

    $("#tab_"+sum).addClass("current");
    $("#content_"+sum).show();


}