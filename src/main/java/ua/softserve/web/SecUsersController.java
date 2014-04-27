package ua.softserve.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.softserve.db.TeacherDao;
import ua.softserve.db.UserDao;
import ua.softserve.exceptions.UpdateException;
import ua.softserve.logic.MD5;

import java.io.IOException;

import static ua.softserve.web.Messages.SUCCESS;

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
    public ModelAndView newUser(ModelMap model,
                                @RequestParam("login") String login,
                                @RequestParam("password") String password,
                                @RequestParam("id") Integer teacId,
                                @RequestParam("role") String role) throws UpdateException, IOException {

        for (String testLogin : userDao.getAllUsers()) {
            if (login.equals(testLogin))
                throw new IOException("Користувач із таким ім'ям уже існує");
        }
        if (!userDao.addUser(teacId, role, login, MD5.shufr(password)))
            throw new UpdateException();

        model.put("message", SUCCESS);


        return new ModelAndView("redirect:/secretary/" + role);

    }

    @RequestMapping(value = "upd_user", method = RequestMethod.POST)
    public ModelAndView updUser(ModelMap model,
                                @RequestParam("login") String login,
                                @RequestParam("password") String password,
                                @RequestParam("role") String role

    ) throws UpdateException {

        if (!userDao.changePass(login, MD5.shufr(password)))
            throw new UpdateException();
        model.put("message", SUCCESS);


        return new ModelAndView("redirect:/secretary/" + role);

    }


}
