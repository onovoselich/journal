package ua.softserve.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.softserve.db.*;
import ua.softserve.entities.Group;
import ua.softserve.entities.Spetiality;
import ua.softserve.entities.Teacher;
import ua.softserve.exceptions.UpdateException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

import static ua.softserve.web.Messages.SUCCESS;

/**
 * Created by troll on 03.03.14.
 */
@Controller
@RequestMapping("/secretary/groups")
public class SecGroupsController {
    private static final String TGS_PAGE = "groupTgsPage";
    public static final String GROUPS_PAGE = "secGroupsPage";

    @Autowired
    GroupDao groupDao;
    @Autowired
    TeacherDao teacherDao;
    @Autowired
    StudentDao studentDao;
    @Autowired
    SpecDao specDao;
    @Autowired
    SubjectDao subjectDao;
    @Autowired
    TgsDao tgsDao;


    @RequestMapping({"", "/"})
    public String students(@RequestParam(value = "vid", required = false) Integer degree,
                           ModelMap model ) {

        List<Spetiality> specs = specDao.getAllSpecs();
        for (Spetiality spec : specs) {
            spec.setZavViddil(teacherDao.getTeacherInfo(spec.getZavViddil().getId()));
        }
        List<Group> grpList;
        if (degree == null)
            grpList = groupDao.getAllGroups();
        else
            grpList = groupDao.getSpecGrops(degree);
        if (grpList != null)
            for (Group grp : grpList)
                grp.setCurator(teacherDao.getTeacherInfo(grp.getCurator().getId()));


        model.put("teacher_list", teacherDao.getAllTeachers());
        model.put("groups_list", grpList);
        model.put("specs_list", specs);

        return GROUPS_PAGE;
    }


    @RequestMapping(value = "add_spec", method = RequestMethod.POST)
    public String addSpec(ModelMap model,
                          @ModelAttribute Spetiality spec,
                          @RequestParam(value = "zavViddilId") Integer zavViddilId,
                          HttpServletRequest request) throws UpdateException {

        spec.setZavViddil(new Teacher(zavViddilId));
        if (!specDao.addSpec(spec))
            throw new UpdateException();
        model.put("message", SUCCESS);


        return "redirect:"+request.getHeader("referer");
    }

    @RequestMapping(value = "alter_spec", method = RequestMethod.POST)
    public String alterSpec(ModelMap model,
                            @ModelAttribute Spetiality spec,
                            @RequestParam(value = "zavViddilId") Integer zavViddilId,
                            HttpServletRequest request) throws UpdateException {
        spec.setZavViddil(new Teacher(zavViddilId));
        if (!specDao.alterSpec(spec))
            throw new UpdateException();
        model.put("message", SUCCESS);


        return "redirect:"+request.getHeader("referer");
    }

    @RequestMapping(value = "alter_group", method = RequestMethod.POST)
    public String alterGroup(
            @RequestParam("degree_id") Integer degId
            , @RequestParam("curator_id") Integer curId,
            ModelMap model,
            @ModelAttribute Group group,
            HttpServletRequest request) throws UpdateException {

        if (group.getStartDate().equals("")) group.setStartDate(null);
        if (group.getFinishDate().equals("")) group.setFinishDate(null);
        group.setSpec(new Spetiality(degId));
        group.setCurator(new Teacher(curId));
        if (!groupDao.updGroup(group))
            throw new UpdateException();
        model.put("message", SUCCESS);


        return "redirect:"+request.getHeader("referer");
    }

    @RequestMapping(value = "add_group", method = RequestMethod.POST)
    public String addGroup(
            @RequestParam("degree_id") Integer degId
            , @RequestParam("curator_id") Integer curId,
            ModelMap model,
            @ModelAttribute Group group,
            HttpServletRequest request) throws UpdateException {
        if (group.getStartDate().equals("")) group.setStartDate(null);
        if (group.getFinishDate().equals("")) group.setFinishDate(null);
        group.setSpec(new Spetiality(degId));
        group.setCurator(new Teacher(curId));
        if (!groupDao.addGroup(group))
            throw new UpdateException();
        model.put("message", SUCCESS);


        return "redirect:"+request.getHeader("referer");
    }


    @RequestMapping("appointments")
    public String groupTgs(ModelMap model,
                           @RequestParam("id") Integer grpId
    ) {

        Group group = groupDao.getGroup(grpId);
        model.put("group", group);
        model.put("tgs_list", tgsDao.getGroupTgs(grpId));
        model.put("teac_list", teacherDao.getAllTeachers());
        model.put("subj_list", subjectDao.getSpecSubjects(group.getSpec().getId()));
        return TGS_PAGE;
    }


    @RequestMapping("upd_tgs")
    public String updTgs(

            @RequestParam(value = "id", required = false) Integer id,
            @RequestParam("gr_id") Integer grId,
            @RequestParam("teac_id") Integer teacId,
            @RequestParam("subj_id") Integer subjectId,
 //           @RequestParam("sum") List<Integer> sum,
            ModelMap model,
            HttpServletRequest request) throws UpdateException {


        if (!tgsDao.updTgs(id, teacId, grId, subjectId))
            throw new UpdateException();

        try{
            Set<Integer> sum =  subjectDao.getSubject(subjectId).getSums().keySet();

        List<Integer> sumList = tgsDao.getSumesters(teacId,grId,subjectId);
        if(sumList!=null && !sumList.isEmpty()){
            for(Integer i:sum){
                if(!sumList.contains(i))
                    tgsDao.addTgs(teacId,grId,subjectId,i);
            }
            for(Integer i:sumList){
                if(!sum.contains(i))
                    tgsDao.delTgs(teacId,grId,subjectId,i);
            }
        }
        model.put("message", SUCCESS);
    }catch (NullPointerException e){
        throw new RuntimeException("Спочатку визначте симестри, у яких читатиметься цей предмет");
    }

        return "redirect:"+request.getHeader("referer");
    }

    @RequestMapping("add_tgs")
    public String addTgs(

            @RequestParam(value = "id", required = false) Integer id,
            @RequestParam("gr_id") Integer grId,
            @RequestParam("teac_id") Integer teacId,
            @RequestParam("subj_id") Integer subjectId,
  //          @RequestParam("sum")Integer[] sum,
            ModelMap model,
            HttpServletRequest request) throws UpdateException {

        try{
            Set<Integer> sum =  subjectDao.getSubject(subjectId).getSums().keySet();
        for(Integer i:sum)
            if (!tgsDao.addTgs(teacId, grId, subjectId, i))
                throw new UpdateException();
        model.put("message", SUCCESS);
        }catch (NullPointerException e){
            throw new RuntimeException("Спочатку визначте симестри, у яких читатиметься цей предмет");
        }

        return "redirect:"+request.getHeader("referer");
    }
}
