$(document).ready(function (){
    $('.none').css('display', 'none');
    $( ".data" ).datepicker();
})



function isNumberKey(evt) {
    var charCode = (evt.which) ? evt.which : event.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
        return false;
    }
    return true;
}
function isNumberPointKey(evt) {
    var charCode = (evt.which) ? evt.which : event.keyCode;
    if (charCode === 46) {
        return true;
    } else if (charCode > 31 && (charCode < 48 || charCode > 57)) {
        return false;
    }
    return true;
}

function down(event) {
    $(event.target).parents('tr').next('tr').children('td.none').slideToggle(1000);
};
