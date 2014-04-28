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

$(function(){
    var chbxs = $(':checkbox[required]');
    var namedChbxs = {};
    chbxs.each(function(){
        var name = $(this).attr('name');
        namedChbxs[name] = (namedChbxs[name] || $()).add(this);
    });
    chbxs.change(function(){
        var name = $(this).attr('name');
        var cbx = namedChbxs[name];
        if(cbx.filter(':checked').length>0){
            cbx.removeAttr('required');
        }else{
            cbx.attr('required','required');
        }
    });
});