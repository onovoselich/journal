package ua.softserve.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.softserve.db.*;
import ua.softserve.entities.Mark;
import ua.softserve.entities.Student;
import ua.softserve.entities.Subject;

import java.util.*;

/**
 * Created by troll on 13.01.14.
 */
@Controller
@RequestMapping("/student")
public class StudentController {
    private static final String STUDENT_PAGE = "studentPage";
    private static final String STUDENT_VID_PAGE = "studentVid";

    @Autowired
    private StudentDao studentDao;
    @Autowired
    private SubjectDao subjectDao;
    @Autowired
    private TeacherDao teacherDao;
    @Autowired
    private MarkDao markDao;
    @Autowired
    private GroupDao groupDao;

    @RequestMapping({"/", ""})
    public String main(ModelMap model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Student student = studentDao.getStudentInfo(auth.getName());
        int sum;
        try{
            sum = groupDao.getGroup(student.getGroupId()).getSumestr();
        }catch(Exception e){
            sum = 1;
        }
        return "redirect:student/"+sum+"sumestr";
    }

    @RequestMapping("{sum}sumestr")
    public String studRoom(@PathVariable Integer sum,
                           ModelMap model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Student student = studentDao.getStudentInfo(auth.getName());
        model.put("sum",sum);
        model.put("student", student);
        model.put("subjectList", subjectDao.getGroupSubjects(student.getGroupId(),sum));
        return STUDENT_PAGE;
    }

    @RequestMapping(value = "/vidomist", method = RequestMethod.GET)
    public String view(@RequestParam(value = "subj", required = false) String[] subjectsId,
                       @RequestParam(value = "sum") Integer sum,
                       ModelMap model) {


        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Student stud = studentDao.getStudentInfo(auth.getName());
        Map<String, Map<Subject, Mark>> markList = new HashMap<String, Map<Subject, Mark>>();
        markList.put(Subject.EXAM, new TreeMap<Subject, Mark>());
        markList.put(Subject.ZALIK, new TreeMap<Subject, Mark>());
        markList.put(Subject.DUF_ZALIK, new TreeMap<Subject, Mark>());

        if (subjectsId == null) {
            List<Subject> grlst = subjectDao.getGroupSubjects(stud.getGroupId(),sum);
        if (grlst==null)
            throw new RuntimeException("Немає даних по предметам");

            subjectsId = new String[grlst.size()];
            for (int i = 0; i < grlst.size(); i++) {
                subjectsId[i] = new String(Integer.toString(grlst.get(i).getId()));
            }
        }

        for (int i = 0; i < subjectsId.length; i++) {
            Subject subj = subjectDao.getSubject(Integer.parseInt(subjectsId[i]));
            subj.setTeacher(teacherDao.getTeacher(subj.getId(), stud.getGroupId()));
            Mark mark = markDao.getMark(stud.getId(), subj.getId());
            markList.get(subj.getControlForm()).put(subj, mark);
        }

        model.put("sum",sum);
        model.put("student", stud);
        model.put("marks", markList);

        return STUDENT_VID_PAGE;
    }
}
