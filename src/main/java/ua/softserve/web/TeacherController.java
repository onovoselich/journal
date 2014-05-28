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
import ua.softserve.db.*;
import ua.softserve.entities.*;
import ua.softserve.exceptions.UpdateException;
import ua.softserve.logic.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
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
    public String main(
                       ModelMap model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Teacher teac = teacherDao.getTeacherInfo(auth.getName());
        Group grp = groupDao.getGroupByCurator(teac.getId());

        List<Integer>spList = groupDao.getSpecsByZavvidd(teac.getId());
        Map<Group,List<Subject>> gsl = new TreeMap<Group, List<Subject>>();
        if(spList!=null || !spList.isEmpty()){

            model.put("i_am_zavvidd",1);
            List<Group>grLst = new ArrayList<Group>();
            for(Integer i:spList){
                grLst.addAll(groupDao.getSpecGrops(i));
            }
            for(Group g : grLst)
                gsl.put(g,subjectDao.getGroupSubjects(g.getId()));

        }

        if (grp != null){

            model.put("i_am_curator",1);
            model.put("my_group", grp);
            model.put("grp_students", studentDao.getGroupStudents(grp.getId()));
            model.put("group_list",groupDao.getTeacherGroups(teac.getId()));


            gsl.put(grp,subjectDao.getGroupSubjects(grp.getId()));
        }

        model.put("group_list_for_vid",JSONObject.groupListToJson(gsl));
        Map<Group,  List<Subject>>  groupListMap = new TreeMap<Group, List<Subject>>();
        List<Group> groups = groupDao.getTeacherGroups(teac.getId());
        for(Group g : groups)
            groupListMap.put(g,subjectDao.getTeacherGroupSubjects(teac.getId(),g.getId()));


        model.put("group_json",JSONObject.groupListToJson(groupListMap));

        model.put("teacher", teac);


/*
            model.put("group_list", groupDao.getTeacherGroups(teac.getId()));
            model.put("subject_list", subjectDao.getTeacherSubjects(teac.getId()));*/


        return TEACHER_PAGE;
    }

    @RequestMapping({ "/vidomist","/vidomist.pdf","/vidomist.xls"})
    public ModelAndView vid(@RequestParam("group_id") Integer groupId,
                       @RequestParam("subject_id") Integer subjectId,
                       @RequestParam(required=false) Integer sum,
                       @RequestParam(required = false)String format,
                       HttpServletRequest request,
                       ModelMap model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Teacher teac = teacherDao.getTeacherInfo(auth.getName());
        if (sum==null){
            sum = groupDao.getGroup(groupId).getSumestr();

            List<Integer> sumLst = tgsDao.getSumesters(groupId,subjectId);
            while(!sumLst.contains(sum))
                sum--;
        }
        Integer tgsId = tgsDao.getTeacherGroupSubject(teac.getId(), groupId, subjectId,sum);

        if (tgsId == null)
            throw new RuntimeException("Ви не викладаєте цей предмет у цієї групи або неправильний симестр!");

        Map<Student, Mark> studMarkList = new TreeMap<Student, Mark>();
        List<Student> studList = studentDao.getGroupStudents(groupId);
        if (studList != null)
            for (Student st : studList) {
               studMarkList.put(st, markDao.getMark(st.getId(), subjectId,sum));
            }

        model.put("cur_sum",sum);
        model.put("sum_lst",tgsDao.getSumesters(teac.getId(),groupId,subjectId));
        model.put("group", groupDao.getGroup(groupId));
        model.put("subject", subjectDao.getSubject(subjectId));
        model.put("teac_subj_grp_id", tgsId);
        model.put("stud_mark_list", studMarkList);

        if(format!=null)
            return new ModelAndView(TEACHER_VID_PAGE+format,model);
        else
            return new ModelAndView(getViewName(request,TEACHER_VID_PAGE),model);
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



    private String getViewName(HttpServletRequest request, String viewName){
        String requestUri = request.getRequestURI();
        if(requestUri.indexOf(".") != -1){
            String extension =
                    requestUri.substring(requestUri.lastIndexOf("."));
            return viewName+extension;
        }else
        return viewName;
    }
}
