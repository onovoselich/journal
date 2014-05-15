
var namedChbxs = new Object();

$(function(){
    var chbxs = $(':checkbox');
    chbxs.each(function(){
        var name = $(this).attr('id').substr(5);
        namedChbxs[name] = (namedChbxs[name] || $()).add(this);
    });
    chbxs.change(chbxCheck);
    chbxs.change();
});

var chbxCheck = function(){
    var name = $(this).attr('id').substr(5);
    var cbx = namedChbxs[name];
    if(cbx.filter(':checked').length>0){
        cbx.removeAttr('required');
    }else{
        cbx.attr('required','required');
    }
}