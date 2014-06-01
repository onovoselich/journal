function grLstInit(gr_sub_v) {
    subLs = $("#sub");
    grLs = $("#group");
    grLs.empty();
    for (var i in gr_sub_v) {
        newOpt = $("<option></option>").text(gr_sub_v[i].gr_name).val(gr_sub_v[i].gr_id);
        grLs.append(newOpt);
    }
    grLs.children().click(selectGrp);
    grLs.children(":selected").click();

}
function selectGrp() {
    subLs.empty();
    for (var i in gr_sub_v) {
        if (gr_sub_v[i].gr_id == parseInt($(this).val()))
            for (var j in gr_sub_v[i].subj_list) {
                newOpt = $("<option></option>").text(gr_sub_v[i].subj_list[j].subj_name).val(gr_sub_v[i].subj_list[j].subj_id);
                subLs.append(newOpt);
            }
    }

}