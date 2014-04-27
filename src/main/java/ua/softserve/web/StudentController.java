package ua.softserve.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.softserve.db.MarkDao;
import ua.softserve.db.StudentDao;
import ua.softserve.db.SubjectDao;
import ua.softserve.db.TeacherDao;
import ua.softserve.entities.Mark;
import ua.softserve.entities.Student;
import ua.softserve.entities.Subject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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

    @RequestMapping({"/", ""})
    public String main(ModelMap model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Student student =  studentDao.getStudentInfo(auth.getName());
        model.put("student", student);
        model.put("subjectList", subjectDao.getGroupSubjects(student.getGroupId()));
        return STUDENT_PAGE;
    }

    @RequestMapping(value = "/vidomist", method = RequestMethod.GET)
    public String view(@RequestParam(value = "subj", required = false) String[] subjectsId,
                       ModelMap model) {


        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Student stud = studentDao.getStudentInfo(auth.getName());
        Map<String, Map<Subject, Mark>> markList = new HashMap<String, Map<Subject, Mark>>();
            markList.put(Subject.EXAM, new TreeMap<Subject, Mark>());
            markList.put(Subject.ZALIK, new TreeMap<Subject, Mark>());
            markList.put(Subject.DUF_ZALIK, new TreeMap<Subject, Mark>());

        if (subjectsId == null ){
            List<Subject> grlst = subjectDao.getGroupSubjects(stud.getGroupId());

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


        model.put("student", stud);
        model.put("marks", markList);

        return STUDENT_VID_PAGE;
    }
}
