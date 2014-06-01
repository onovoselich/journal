function createGroupList(gr_sub) {

    for (var i in gr_sub) {
        var li = $('<li></li>').html(
            "<input id=gr_id" + gr_sub[i].gr_id + " type='radio' value=" + gr_sub[i].gr_id + " name='group_id'/><label for=gr_id" + gr_sub[i].gr_id + ">" + gr_sub[i].gr_name + "</label>"
        )
        $('#gr_list').append(li);
    }
    $('#gr_list li').click(onGroupClick);
}
function onGroupClick() {
    $('#subj_list').empty();
    for (var i in gr_sub)
        if (gr_sub[i].gr_id == parseInt($(this).find('input').attr('id').substr(5)))
            for (var j in gr_sub[i].subj_list) {
                var li = $('<li></li>').html(
                    "<input  id=subject_id" + gr_sub[i].subj_list[j].subj_id + " type='radio' value=" + gr_sub[i].subj_list[j].subj_id + " name='subject_id'/><label for=subject_id" + gr_sub[i].subj_list[j].subj_id + ">" + gr_sub[i].subj_list[j].subj_name + "</label>"
                );
                $('#subj_list').append(li);
            }


}

