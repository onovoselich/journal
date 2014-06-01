package ua.softserve.logic;

import ua.softserve.entities.Group;
import ua.softserve.entities.Subject;

import java.util.List;
import java.util.Map;

/**
 * Created by troll on 12.05.14.
 */
public class JSONObject {
    public static String groupListToJson(Map<Group, List<Subject>> groupListMap) {
        StringBuilder res = new StringBuilder();
        res.append("[");
        for (Map.Entry<Group, List<Subject>> entry : groupListMap.entrySet()) {
            if ("}".equals(res.substring(res.length() - 1))) res.append(",");
            res.append("{");
            res.append("\"gr_id\":" + entry.getKey().getId()
                    + ", \"gr_name\":\"" + entry.getKey().getName() + "\", \"subj_list\":[");
            if (entry.getValue() != null)
                for (Subject subj : entry.getValue()) {
                    if ("}".equals(res.substring(res.length() - 1))) res.append(",");
                    res.append("{");
                    res.append("\"subj_id\":" + subj.getId() + ", \"subj_name\":\"" + subj.getName() + "\"");
                    res.append("}");
                }
            res.append("]");
            res.append("}");
        }
        res.append("]");
        return res.toString();
    }
}
