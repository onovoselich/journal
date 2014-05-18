$(document).ready(function(){
    $(".list-student a").click(function(e){
        e.preventDefault();
        $(".student-card-left").hide();
        $("#d"+this.id.substr(1)).show();


    });

    $(".student-card-left a").click(function(e){
        e.preventDefault();
        $(this).parent().parent().hide();

    });

    $(".student-card-left").hide();


});