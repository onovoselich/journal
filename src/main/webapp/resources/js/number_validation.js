$(document).ready(function(){
	$('.float').keypress(floatNumCheck);
	$('.integer').keypress(intNumCheck);
});

var floatNumCheck = function (evt){
	var value = evt.currentTarget.value;
	var charCode = (evt.which) ? evt.which : event.keyCode;
    if (charCode === 46) {
		if(value.indexOf('.')==-1)
			return true;
		else
			return false;
    } else if (charCode > 31 && (charCode < 48 || charCode > 57)) {
        return false;
    }
    return true;
}

var intNumCheck = function(evt){
	var charCode = (evt.which) ? evt.which : event.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
        return false;
    }
    return true;
}