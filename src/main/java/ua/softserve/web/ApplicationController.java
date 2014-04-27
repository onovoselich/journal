package ua.softserve.web;

import com.sun.deploy.net.HttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
@RequestMapping({"/", "/main", "/home"})
public class ApplicationController {

    public static final String MAIN_PAGE = "mainPage";
    public static final String LOGIN_PAGE = "loginPage";
    private static final String ERROR_PAGE = "errorPage";


    @RequestMapping(method = RequestMethod.GET)
    public String main(ModelMap model) {

           return MAIN_PAGE;
    }


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String autentification(@RequestParam(value = "login_error", required = false) String loginError, ModelMap model) {
        if (loginError != null) {
            model.put("error_message", "Помилка аутентифікації!");
        }
        return LOGIN_PAGE;
    }

    @RequestMapping("/error")
    public String errorHandler(){

        return ERROR_PAGE;
    }

}