package ua.softserve.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.softserve.db.SubjectDao;
import ua.softserve.entities.Subject;
import ua.softserve.exceptions.UpdateException;

import static ua.softserve.web.Messages.FAIL;
import static ua.softserve.web.Messages.SUCCESS;

/**
 * Created by troll on 03.03.14.
 */
@Controller
@RequestMapping("/secretary/subjects")
public class SecSubjectsController {
    private static final String SUBJECTS_PAGE = "secSubjectsPage";
    @Autowired
    SubjectDao subjectDao;

    @RequestMapping({"", "/"})
    public String subjects(
                           ModelMap model) {

        model.put("subj_list", subjectDao.getAllSubjects());
        return SUBJECTS_PAGE;
    }

    @RequestMapping(value = "alter_subject", method = RequestMethod.POST)
    public String alterSubject(ModelMap model,
                               @ModelAttribute Subject subj) throws UpdateException {
            if (!subjectDao.updSubject(subj))
                throw new UpdateException();
            model.put("message", SUCCESS);


        return "redirect: ";
    }

    @RequestMapping(value = "add_subject" , method = RequestMethod.POST)
    public String addSubject(ModelMap model,
                               @ModelAttribute Subject subj) throws UpdateException {


           if (!subjectDao.addSubject(subj))
               throw new UpdateException();
           model.put("message", SUCCESS);


        return "redirect: ";
    }
}
