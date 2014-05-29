$(document).ready(function(){

    showContent(sum);
    $(".tabs a").click(function(e){
        e.preventDefault();
        sum = this.id.substr(4);
        showContent(sum);
    });

});

function showContent(sum){
    if (sum=="all")
        $(".tabcontent").show()
    else{
    $(".tabcontent").hide()

        $("#content_"+sum).show();
        $(".content_"+sum).show();
    }
    $(".tabs a").removeClass("current")
    $("#tab_"+sum).addClass("current");

}