package ua.softserve.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.softserve.logic.*;

@Controller
@RequestMapping({"/", "/main", "/home"})
public class ApplicationController {

    public static final String MAIN_PAGE = "mainPage";
    public static final String LOGIN_PAGE = "loginPage";
    private static final String ERROR_PAGE = "errorPage";


    @RequestMapping(method = RequestMethod.GET)
    public String main(ModelMap model) {

        int[] a = {1,2,3,4,5,6,7,8,9,10};
        for(int i:a)
            System.out.println(ua.softserve.logic.Number.arabic2roman(i)+"--");
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
    public String errorHandler() {

        return ERROR_PAGE;
    }

}