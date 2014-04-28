package ua.softserve.web;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;
import ua.softserve.db.*;
import ua.softserve.entities.Student;
import ua.softserve.exceptions.UpdateException;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;

import static ua.softserve.db.UserDao.ROLE_STUDENT;
import static ua.softserve.web.Messages.SUCCESS;

/**
 * Created by troll on 03.03.14.
 */
@Controller
@RequestMapping({"/secretary/students", "/secretary/" + ROLE_STUDENT})
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

    private ServletContext servletContext;

    @RequestMapping({"/", ""})
    public String studentsView(ModelMap model
    ) {

        model.put("spec_list",specDao.getAllSpecs());
        model.put("groups_list", groupDao.getAllGroups());
            model.put("students_list", studentDao.getAllStudents());

        return STUDENTS_PAGE;
    }
    @RequestMapping("spec{specId}")
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
    }
    @RequestMapping(value = "add_student", method = RequestMethod.POST)
    public String addStudent(ModelMap model,
                             @ModelAttribute Student stud) throws UpdateException, IOException {

        if (stud.getbDate().equals("")) stud.setbDate(null);
        for(Student st:studentDao.getAllStudents())

            if(st.getGradebook()==stud.getGradebook())
                throw new IOException("Цей номер залікової книжки уже зайнятий!");

        if (!studentDao.newStudent(stud))
            throw new UpdateException();
        model.put("message", SUCCESS);


        model.put("group_id", stud.getGroupId());
        return "redirect: ";
    }

    @RequestMapping(value = "alter_student", method = RequestMethod.POST)
    public String alterStudent(ModelMap model,
                               @ModelAttribute Student stud,
                               @RequestParam(value = "image", required = false) MultipartFile image) throws UpdateException, IOException {

        if (stud.getbDate().equals("")) stud.setbDate(null);

        if (!studentDao.updStudent(stud))
            throw new UpdateException();
        model.put("message", SUCCESS);


        if (!image.isEmpty()) {

            validateImage(image);


            saveImage(stud.getId() + ".jpg", image);

        }
        model.put("group_id", stud.getGroupId());
        return "redirect: ";

    }


    @RequestMapping("upd_user")
    public String user(ModelMap model,
                       @RequestParam(value = "login") String login) {

        model.put("login", login);
        model.put("role", UserDao.ROLE_STUDENT);
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
