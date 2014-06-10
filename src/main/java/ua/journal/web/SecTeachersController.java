package ua.journal.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.journal.db.*;
import ua.journal.entities.Spetiality;
import ua.journal.entities.Subject;
import ua.journal.entities.Teacher;
import ua.journal.exceptions.UpdateException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import static ua.journal.web.Messages.SUCCESS;

/**
 * Created by troll on 03.03.14.
 */
@Controller
@RequestMapping("/secretary/teachers")
public class SecTeachersController {
    private static final String TEACHERS_PAGE = "secTeachersPage";
    private static final String ADD_USER_PAGE = "addUserPage";
    private static final String TGS_PAGE = "teacherTgsPage";
    private static final String UPDATE_USER_PAGE = "updUserPage";
    @Autowired
    TeacherDao teacherDao;
    @Autowired
    UserDao userDao;
    @Autowired
    GroupDao groupDao;
    @Autowired
    SubjectDao subjectDao;
    @Autowired
    TgsDao tgsDao;
    @Autowired
    SpecDao specDao;


    @RequestMapping({"", "/"})
    public String teachers(
            ModelMap model) {
        model.put("teachers_list", teacherDao.getAllTeachers());
        return TEACHERS_PAGE;
    }

    @RequestMapping("alter_teacher")
    public String alterTeacher(ModelMap model,
                               @ModelAttribute Teacher teac,
                               HttpServletRequest request) throws UpdateException {

        if (!teacherDao.updTeacher(teac))
            throw new UpdateException();
        model.put("message", SUCCESS);

        return "redirect:" + request.getHeader("referer");
    }

    @RequestMapping("add_teacher")
    public String addTeacher(ModelMap model,
                             @ModelAttribute Teacher teac,
                             HttpServletRequest request) throws UpdateException {

        if (!teacherDao.newTeacher(teac))
            throw new UpdateException();
        model.put("message", SUCCESS);

        return "redirect:" + request.getHeader("referer");

    }


    @RequestMapping("upd_user")
    public String user(ModelMap model,
                       @RequestParam(value = "login") String login) {

        model.put("login", login);
        return UPDATE_USER_PAGE;
    }


    @RequestMapping("add_user")
    public String user(ModelMap model,
                       @RequestParam("id") Integer id) {

        model.put("id", id);
        model.put("role", UserDao.ROLE_TEACHER);

        return ADD_USER_PAGE;
    }

    @RequestMapping("appointments")
    public String teacherTgs(
            @RequestParam("id") Integer teacId,
            ModelMap model) {
        Map<Spetiality, List<Subject>> subjList = new TreeMap<Spetiality, List<Subject>>();
        for (Spetiality sp : specDao.getAllSpecs()) {
            List<Subject> sl = subjectDao.getSpecSubjects(sp.getId());
            if (sl != null && !sl.isEmpty())
                subjList.put(sp, sl);
        }

        model.put("subj_list", subjList);
        model.put("teacher", teacherDao.getTeacherInfo(teacId));
        model.put("tgs_list", tgsDao.getTeacherTgs(teacId));
        model.put("grp_list", groupDao.getAllGroups());

        return TGS_PAGE;
    }

    @RequestMapping("upd_tgs")
    public String updTgs(ModelMap model,
                         @RequestParam("id") Integer id,
                         @RequestParam("gr_id") Integer grId,
                         @RequestParam("teac_id") Integer teacId,
                         @RequestParam("subj_id") Integer subjectId,
                         //     @RequestParam List<Integer> sum,
                         HttpServletRequest request) throws UpdateException {


        if (groupDao.getGroup(grId).getSpec().getId() != subjectDao.getSubject(subjectId).getSpetiality().getId())
            throw new RuntimeException("Оберіть предмет з категорії відповідної спеціальності");

        try {
            Set<Integer> sum = subjectDao.getSubject(subjectId).getSums().keySet();
            if (!tgsDao.updTgs(id, teacId, grId, subjectId))
                throw new UpdateException();

            List<Integer> sumList = tgsDao.getSumesters(teacId, grId, subjectId);
            if (sumList != null && !sumList.isEmpty()) {
                for (Integer i : sum) {
                    if (!sumList.contains(i))
                        tgsDao.addTgs(teacId, grId, subjectId, i);
                }
                for (Integer i : sumList) {
                    if (!sum.contains(i))
                        tgsDao.delTgs(teacId, grId, subjectId, i);
                }
            }


            model.put("message", SUCCESS);
        } catch (NullPointerException e) {
            throw new RuntimeException("Спочатку визначте симестри, у яких читатиметься цей предмет");
        }

        return "redirect:" + request.getHeader("referer");
    }

    @RequestMapping("add_tgs")
    public String addTgs(ModelMap model,
                         @RequestParam("gr_id") Integer grId,
                         @RequestParam("teac_id") Integer teacId,
                         @RequestParam("subj_id") Integer subjectId,
                         //     @RequestParam Integer[] sum,
                         HttpServletRequest request) throws UpdateException {


        try {
            Set<Integer> sum = subjectDao.getSubject(subjectId).getSums().keySet();
            for (Integer i : sum)
                if (!tgsDao.addTgs(teacId, grId, subjectId, i))
                    throw new UpdateException();
            model.put("message", SUCCESS);

        } catch (NullPointerException e) {
            throw new RuntimeException("Спочатку визначте симестри, у яких читатиметься цей предмет");
        }

        return "redirect:" + request.getHeader("referer");
    }

}

