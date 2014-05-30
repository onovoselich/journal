package ua.softserve.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.softserve.db.SpecDao;
import ua.softserve.db.SubjectDao;
import ua.softserve.entities.Spetiality;
import ua.softserve.entities.Subject;
import ua.softserve.exceptions.UpdateException;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    SpecDao specDao;

    @RequestMapping({"", "/"})
    public String subjects(
            ModelMap model) {

    //    model.put("tab_list",specDao.getAllSpecs());
        model.put("spec_list",specDao.getAllSpecs());
        model.put("subj_list", subjectDao.getAllSubjectsInfo());
        return SUBJECTS_PAGE;
    }
/*    @RequestMapping("spec{specId}")
    public String specSubjects(
            ModelMap model,
            @PathVariable Integer specId) {
        List spList = new ArrayList<Spetiality>();
        spList.add(specDao.getSpetiality(specId));
        model.put("spec_list",spList);

        model.put("tab_list",specDao.getAllSpecs());
        model.put("subj_list", subjectDao.getSpecSubjects(specId));
        return SUBJECTS_PAGE;


    }*/

    @RequestMapping(value = "alter_subject", method = RequestMethod.POST)
    public String alterSubject(ModelMap model,
                               @RequestParam Integer specId,
                               @RequestParam List<Integer> sum,
                               @ModelAttribute Subject subj,
                               HttpServletRequest request) throws UpdateException {
        subj.setSpetiality(new Spetiality(specId));

        Map<Integer,Integer> sums = new HashMap<Integer, Integer>();
        for(Integer s : sum){
            String hours = request.getParameter("sum" + s);
            if("".equals(hours) || hours== null)
                throw new RuntimeException("Введіть години для "+s+"-го симастра");
            sums.put(s, Integer.parseInt(hours));
        }

        subj.setSums(sums);

        if (!subjectDao.updSubject(subj))
            throw new UpdateException();
        model.put("message", SUCCESS);


        return "redirect:"+request.getHeader("referer");
    }

    @RequestMapping(value = "add_subject", method = RequestMethod.POST)
    public String addSubject(ModelMap model,
                             @RequestParam Integer specId,
                             @RequestParam List<Integer> sum,
                             @ModelAttribute Subject subj,

                             HttpServletRequest request) throws UpdateException {

        subj.setSpetiality(new Spetiality(specId));
        Map<Integer,Integer> sums = new HashMap<Integer, Integer>();
        for(Integer s : sum){
            Integer i =  Integer.parseInt(request.getParameter("sum" + s));
            if (i==null)
                i = 0;
            sums.put(s, i);
        }

        subj.setSums(sums);
        if (!subjectDao.addSubject(subj))
            throw new UpdateException();
        model.put("message", SUCCESS);


        return "redirect:"+request.getHeader("referer");
    }
}
