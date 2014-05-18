$(document).ready(function(){
    $(".popup").click(function(e){
        e.preventDefault();
        switch($(this).text()){
            case "↑": $(this).text("↓"); break;
            case "↓": $(this).text("↑"); break;
        }
        $(this).parent().next().slideToggle(1000,"swing");

    });
});