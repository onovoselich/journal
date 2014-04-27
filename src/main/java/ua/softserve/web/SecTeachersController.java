package ua.softserve.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.softserve.db.*;
import ua.softserve.entities.Teacher;
import ua.softserve.exceptions.UpdateException;

import static ua.softserve.db.UserDao.ROLE_TEACHER;
import static ua.softserve.web.Messages.FAIL;
import static ua.softserve.web.Messages.SUCCESS;

/**
 * Created by troll on 03.03.14.
 */
@Controller
@RequestMapping({"/secretary/teachers" ,"/secretary/"+ROLE_TEACHER})
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

    @RequestMapping({"", "/"})
    public String teachers(
                           ModelMap model) {
        model.put("teachers_list", teacherDao.getAllTeachers());
        return TEACHERS_PAGE;
    }

    @RequestMapping("alter_teacher")
    public String alterTeacher(ModelMap model,
                               @ModelAttribute Teacher teac) throws UpdateException {

            if (!teacherDao.updTeacher(teac))
                throw new UpdateException();
            model.put("message", SUCCESS);

        return "redirect: ";
    }

    @RequestMapping("add_teacher")
    public String addTeacher(ModelMap model,
                               @ModelAttribute Teacher teac) throws UpdateException {

            if (!teacherDao.newTeacher(teac))
                throw new UpdateException();
            model.put("message", SUCCESS);



        return "redirect: ";
    }


    @RequestMapping("upd_user")
    public String user(ModelMap model,
                       @RequestParam(value = "login")String login){

        model.put("login",login);
        model.put("role",UserDao.ROLE_TEACHER);
        return UPDATE_USER_PAGE;
    }


    @RequestMapping("add_user")
    public String user(ModelMap model,
                       @RequestParam("id")Integer id){

        model.put("id",id);
        model.put("role",UserDao.ROLE_TEACHER);

        return ADD_USER_PAGE;
    }

    @RequestMapping("appointments")
    public String teacherTgs(
                             @RequestParam("id")Integer teacId,
                             ModelMap model) {

        model.put("teacher",teacherDao.getTeacherInfo(teacId));
        model.put("tgs_list",tgsDao.getTeacherTgs(teacId));
        model.put("grp_list",groupDao.getAllGroups());
        model.put("subj_list",subjectDao.getAllSubjects());
        return TGS_PAGE;
    }

    @RequestMapping("upd_tgs")
    public String updTgs(ModelMap model,
                         @RequestParam(value = "id", required = false)Integer id,
                         @RequestParam("gr_id")Integer grId,
                         @RequestParam("teac_id")Integer teacId,
                         @RequestParam("subj_id")Integer subjectId) throws UpdateException {



            if (!tgsDao.updTgs(id,teacId,grId,subjectId))
               throw new UpdateException();
            model.put("message", SUCCESS);



        model.put("id",teacId);
        return"redirect: appointments";
    }

    @RequestMapping("add_tgs")
         public String addTgs(ModelMap model,
                              @RequestParam(value = "id", required = false)Integer id,
                              @RequestParam("gr_id")Integer grId,
                              @RequestParam("teac_id")Integer teacId,
                              @RequestParam("subj_id")Integer subjectId) throws UpdateException {




            if (!tgsDao.addTgs(teacId, grId, subjectId))
                throw new UpdateException();
            model.put("message", SUCCESS);



        model.put("id",teacId);
        return"redirect: appointments";
    }

}
