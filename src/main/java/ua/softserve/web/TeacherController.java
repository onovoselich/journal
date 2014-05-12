package ua.softserve.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import sun.org.mozilla.javascript.internal.json.JsonParser;
import ua.softserve.db.*;
import ua.softserve.entities.*;
import ua.softserve.exceptions.UpdateException;
import ua.softserve.logic.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.xml.transform.sax.SAXSource;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static ua.softserve.web.Messages.DELETED_SUCCESS;
import static ua.softserve.web.Messages.SUCCESS;

/**
 * Created by troll on 13.01.14.
 */
@Controller
@RequestMapping("/teacher")
public class TeacherController {

    private static final String TEACHER_PAGE = "teacherPage";
    private static final String TEACHER_VID_PAGE = "teacherVid";
    private static final String STUD_INFO_PAGE = "studInfo";

    @Autowired
    GroupDao groupDao;
    @Autowired
    TeacherDao teacherDao;
    @Autowired
    StudentDao studentDao;
    @Autowired
    SubjectDao subjectDao;
    @Autowired
    MarkDao markDao;

    @Autowired
    TgsDao tgsDao;

    @RequestMapping({"/", ""})
    public String main(@RequestParam(value = "stud_id", required = false) Integer studId,
                       ModelMap model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Teacher teac = teacherDao.getTeacherInfo(auth.getName());
        Group grp = groupDao.getGroupByCurator(teac.getId());

        Map<Group,  List<Subject>>  groupListMap = new TreeMap<Group, List<Subject>>();
        List<Group> groups = groupDao.getTeacherGroups(teac.getId());
        for(Group g : groups)
            groupListMap.put(g,subjectDao.getTeacherGroupSubjects(teac.getId(),g.getId()));


        model.put("group_map",groupListMap);
        model.put("group_json",JSONObject.groupListToJson(groupListMap));

        model.put("teacher", teac);
        model.put("my_group", grp);
        model.put("grp_students", studentDao.getGroupStudents(grp.getId()));
        if (studId != null)
            model.put("student", studentDao.getStudentInfo(studId));


            model.put("group_list", groupDao.getTeacherGroups(teac.getId()));
            model.put("subject_list", subjectDao.getTeacherSubjects(teac.getId()));


        return TEACHER_PAGE;
    }

    @RequestMapping(value = "/vidomist", method = RequestMethod.GET)
    public String view(@RequestParam("group_id") Integer groupId,
                       @RequestParam("subject_id") Integer subjectId,
                       @RequestParam(required=false) Integer sum,
                       ModelMap model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Teacher teac = teacherDao.getTeacherInfo(auth.getName());
        if (sum==null){
            sum = groupDao.getGroup(groupId).getSumestr();

            List<Integer> sumLst = tgsDao.getSumesters(teac.getId(),groupId,subjectId);
            while(!sumLst.contains(sum))
                sum--;
        }
        Integer tgsId = tgsDao.getTeacherGroupSubject(teac.getId(), groupId, subjectId,sum);

        if (tgsId == null)
            throw new RuntimeException("Ви не викладаєте цей предмет у цієї групи!");

        Map<Student, Mark> studMarkList = new TreeMap<Student, Mark>();
        List<Student> studList = studentDao.getGroupStudents(groupId);
        if (studList != null)
            for (Student st : studList) {
               studMarkList.put(st, markDao.getMark(st.getId(), subjectId,sum));
            }


        model.put("sum_lst",tgsDao.getSumesters(teac.getId(),groupId,subjectId));
        model.put("group", groupDao.getGroup(groupId));
        model.put("subject", subjectDao.getSubject(subjectId));
        model.put("teac_subj_grp_id", tgsId);
        model.put("stud_mark_list", studMarkList);


        return TEACHER_VID_PAGE;
    }

    @RequestMapping(value = "/delMark", method = RequestMethod.POST)
    public String delMark(@RequestParam("student_id") Integer studId,
                          @RequestParam("teac_subj_grp_id") Integer tsgId,
                          HttpServletRequest request,
                          ModelMap model) throws IOException {


        if (!markDao.insert(0, null, studId, tsgId))
            throw new IOException("Невдалося видалити оцінку!");

        model.put("message", DELETED_SUCCESS);


        return "redirect:"+request.getHeader("referer");


    }

    @RequestMapping(value = "/updMark", method = RequestMethod.POST)
    public String updMark(@RequestParam("student_id") Integer studId,
                          @RequestParam("teac_subj_grp_id") Integer tgsId,
                          @RequestParam("mark") Integer mark,
                          @RequestParam("date") String date,
                          HttpServletRequest request,
                          ModelMap model) throws UpdateException, IOException {
        if (date.equals("")) date = null;

        if (mark == null || mark > 12 || mark < 1)
            throw new IOException("Введіть значення від 1 до 12!");
        if (!markDao.insert(mark, date, studId, tgsId))
            throw new UpdateException();

        model.put("message", SUCCESS);
        return "redirect:"+request.getHeader("referer");


    }

    @RequestMapping("studentinfo")
    public String studCard(ModelMap model,
                           @RequestParam("stud_id") Integer studId) {
        model.put("student", studentDao.getStudentInfo(studId));
        return STUD_INFO_PAGE;
    }

    @RequestMapping("somePdf")
    public ModelAndView somePdf(ModelMap model)throws Exception{
        //dummy data
        Map<String,String> revenueData = new HashMap<String,String>();
        revenueData.put("1/20/2010", "$100,000");
        revenueData.put("1/21/2010", "$200,000");
        revenueData.put("1/22/2010", "$300,000");
        revenueData.put("1/23/2010", "$400,000");
        revenueData.put("1/24/2010", "$500,000");

        return new ModelAndView("PdfRevenueSummary","revenueData",revenueData);
    }


}
