package ua.journal.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.journal.db.TeacherDao;
import ua.journal.db.UserDao;
import ua.journal.exceptions.UpdateException;
import ua.journal.logic.MD5;

import java.io.IOException;

import static ua.journal.web.Messages.SUCCESS;

/**
 * Created by troll on 03.03.14.
 */
@Controller
@RequestMapping("/secretary/users")
public class SecUsersController {

    @Autowired
    UserDao userDao;
    @Autowired
    TeacherDao teacherDao;


    @RequestMapping(value = "add_user", method = RequestMethod.POST)
    public String newUser(ModelMap model,
                          @RequestParam("login") String login,
                          @RequestParam("password") String password,
                          @RequestParam("id") Integer teacId,
                          @RequestParam("role") String role,
                          @RequestParam("referer") String referer) throws UpdateException, IOException {

        for (String testLogin : userDao.getAllUsers()) {
            if (login.equals(testLogin))
                throw new IOException("Користувач із таким ім'ям уже існує");
        }
        if (!userDao.addUser(teacId, role, login, MD5.shufr(password)))
            throw new UpdateException();

        model.put("message", SUCCESS);


        return "redirect:" + referer;

    }

    @RequestMapping(value = "upd_user", method = RequestMethod.POST)
    public String updUser(ModelMap model,
                          @RequestParam("login") String login,
                          @RequestParam("password") String password,
                          @RequestParam("referer") String referer

    ) throws UpdateException {

        if (!userDao.changePass(login, MD5.shufr(password)))
            throw new UpdateException();
        model.put("message", SUCCESS);


        return "redirect:" + referer;

    }


}
