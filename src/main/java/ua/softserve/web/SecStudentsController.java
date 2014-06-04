package ua.softserve.web;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;
import ua.softserve.db.*;
import ua.softserve.entities.Student;
import ua.softserve.exceptions.UpdateException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static ua.softserve.web.Messages.SUCCESS;

/**
 * Created by troll on 03.03.14.
 */
@Controller
@RequestMapping("/secretary/students")
public class SecStudentsController implements ServletContextAware {
    private static final String STUDENTS_PAGE = "secStudsPage";
    private static final String ADD_USER_PAGE = "addUserPage";
    private static final String UPDATE_USER_PAGE = "updUserPage";
    @Autowired
    GroupDao groupDao;
    @Autowired
    TeacherDao teacherDao;
    @Autowired
    StudentDao studentDao;
    @Autowired
    UserDao userDao;
    @Autowired
    SpecDao specDao;
    @Autowired
    MarkDao markDao;

    private ServletContext servletContext;

    @RequestMapping({"/", ""})
    public String studentsView(ModelMap model
    ) {

        model.put("spec_list", specDao.getAllSpecs());
        model.put("groups_list", groupDao.getAllGroups());
        List<Student> studLst = studentDao.getAllStudents();
        for (Student st : studLst)
            if (markDao.getNegMarksCount(st.getId()) >= 2)
                st.setAlarm(true);
        model.put("students_list", studLst);

        return STUDENTS_PAGE;
    }

    /*   @RequestMapping("spec{specId}")
       public String studentsSpecView(ModelMap model
               ,@PathVariable Integer specId
       ) {

           model.put("spec_list",specDao.getAllSpecs());
           model.put("groups_list", groupDao.getSpecGrops(specId));
           model.put("students_list", studentDao.getSpecStudents(specId));

           return STUDENTS_PAGE;
       }
       @RequestMapping("group{groupId}")
       public String studentsGroupView(ModelMap model
                                       ,@PathVariable Integer groupId
       ) {

           model.put("spec_list",specDao.getAllSpecs());
           model.put("groups_list", groupDao.getAllGroups());
           model.put("students_list", studentDao.getGroupStudentsInfo(groupId));

           return STUDENTS_PAGE;
       }*/
    @RequestMapping(value = "add_student", method = RequestMethod.POST)
    public String addStudent(ModelMap model,
                             @ModelAttribute Student stud,
                             HttpServletRequest request) throws UpdateException, IOException {

        if (stud.getbDate().equals("")) stud.setbDate(null);
        for (Student st : studentDao.getAllStudents())

            if (st.getGradebook() == stud.getGradebook())
                throw new IOException("Цей номер залікової книжки уже зайнятий!");

        if (!studentDao.newStudent(stud))
            throw new UpdateException();
        model.put("message", SUCCESS);


        return "redirect:" + request.getHeader("referer");
    }

    @RequestMapping(value = "alter_student", method = RequestMethod.POST)
    public String alterStudent(ModelMap model,
                               @ModelAttribute Student stud,
                               @RequestParam(value = "image", required = false) MultipartFile image,
                               HttpServletRequest request) throws UpdateException, IOException {

        if (stud.getbDate().equals("")) stud.setbDate(null);

        if (!studentDao.updStudent(stud))
            throw new UpdateException();
        model.put("message", SUCCESS);


        if (!image.isEmpty()) {

            validateImage(image);


            saveImage(stud.getId() + ".jpg", image);

        }
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
        model.put("role", UserDao.ROLE_STUDENT);

        return ADD_USER_PAGE;
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    private void validateImage(MultipartFile image) {
        if (!image.getContentType().equals("image/jpeg")) {
            throw new RuntimeException("Оберіть JPG файл");
        }
        if (image.getSize() > 5242880)
            throw new RuntimeException("Фотографія повинна важити не більше 5 МБ");
    }

    private void saveImage(String filename, MultipartFile image)
            throws RuntimeException, IOException {

        try {
            File file = new File(servletContext.getRealPath("/") + "/resources/photoes/"
                    + filename);

            FileUtils.writeByteArrayToFile(file, image.getBytes());
        } catch (IOException e) {
            throw new IOException("Невдалося завантажити зображення. Спробуйте ще раз.");
        }
    }
}
