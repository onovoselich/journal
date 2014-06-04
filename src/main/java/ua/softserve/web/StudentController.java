package ua.softserve.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.softserve.db.*;
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
    @Autowired
    private GroupDao groupDao;
    @Autowired
    private TgsDao tgsDao;

    @RequestMapping({"/", ""})
    public String main(ModelMap model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Student student = studentDao.getStudentInfo(auth.getName());
        int sum;
        try {
            sum = groupDao.getGroup(student.getGroupId()).getSumestr();
        } catch (Exception e) {
            sum = 1;
        }

        model.put("sum", sum);
        model.put("student", student);
        Map<Integer, List<Subject>> subjLstBySum = new TreeMap<Integer, List<Subject>>();
        for (Integer i = 1; i <= 8; i++)
            subjLstBySum.put(i, subjectDao.getGroupSubjects(student.getGroupId(), i));
        model.put("subjectList", subjLstBySum);

        if (markDao.getNegMarksCount(student.getId()) >= 2)
            model.put("alarm", true);
        return STUDENT_PAGE;
    }

    @RequestMapping(value = "/vidomist", method = RequestMethod.POST)
    public String view(@RequestParam(value = "subj", required = false) String[] subjectsIdLst,
                       @RequestParam(value = "sum") Integer sum,
                       ModelMap model) {


        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Student stud = studentDao.getStudentInfo(auth.getName());
        Map<Integer, Map<String, Map<Subject, Mark>>> markListSum = new HashMap<Integer, Map<String, Map<Subject, Mark>>>();
        Map<Integer, Float> saLst = new TreeMap<Integer, Float>();

        String[] subjectsId = new String[0];
        for (int i = 1; i <= 8; i++) {
            Map<String, Map<Subject, Mark>> markList = new HashMap<String, Map<Subject, Mark>>();

            markList.put(Subject.EXAM, new TreeMap<Subject, Mark>());
            markList.put(Subject.ZALIK, new TreeMap<Subject, Mark>());
            markList.put(Subject.DUF_ZALIK, new TreeMap<Subject, Mark>());
            markList.put(Subject.OTHER, new TreeMap<Subject, Mark>());

            if (sum == i && subjectsIdLst != null) {
                subjectsId = subjectsIdLst;

            } else {
                List<Subject> grlst = subjectDao.getGroupSubjects(stud.getGroupId(), i);
                if (grlst != null) {

                    subjectsId = new String[grlst.size()];
                    for (int j = 0; j < grlst.size(); j++) {
                        subjectsId[j] = new String(Integer.toString(grlst.get(j).getId()));
                    }
                }
            }
            int s = 0;
            int k = 0;
            for (int j = 0; j < subjectsId.length; j++) {
                Subject subj = subjectDao.getSubject(Integer.parseInt(subjectsId[j]));
                subj.setTeacher(teacherDao.getTeacher(subj.getId(), stud.getGroupId()));
                Mark mark = markDao.getMark(stud.getId(), subj.getId(), i);
                if (mark != null && subj.getControlForm() != Subject.ZALIK) {
                    s += mark.getMark();
                    k++;
                }
                markList.get(subj.getControlForm()).put(subj, mark);
            }
            markListSum.put(i, markList);
            if (k != 0) {
                float sa = s / (float) k;
                saLst.put(i, Math.round(sa * 100) / 100f);
            }
        }




        model.put("sum", sum);
        model.put("student", stud);
        model.put("marks", markListSum);
        model.put("sa", saLst);

        return STUDENT_VID_PAGE;
    }
}
