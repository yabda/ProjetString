package controllers;

import beans.Counterpart;
import beans.Message;
import beans.Project;
import beans.User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import services.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@org.springframework.stereotype.Controller
@RequestMapping("/project/")
public class ProjectController {

    @Resource(name = "projectService")
    private IProjectService pS;

    @Resource(name = "userService")
    private IUserService uS;

    @Resource(name = "counterpartService")
    private ICounterpartService cS;

    @Resource(name = "messageService")
    private IMessageService mS;

    @Resource(name = "categoryService")
    private ICategoryService catS;

    @RequestMapping(value="{projectId}")
    public String project(@PathVariable int projectId, HttpSession session, Locale locale, Model model){
        Project p = pS.getFromId(projectId);
        p.setCounterparts(cS.getFromProject(p));
        model.addAttribute("project",p);
        return "project/view";
    }

    @RequestMapping(value = "my", method = RequestMethod.GET)
    public String myProject(HttpSession session, Locale locale, Model model) {
        return "project/my";
    }

    @RequestMapping(value="modify/{projectId}", method = RequestMethod.GET)
    public String myproject(@PathVariable int projectId, HttpSession session, Locale locale, Model model){
        Project p = pS.getFromId(projectId);

        List<Counterpart> ret = new ArrayList<>();
        List<Counterpart> tmp= cS.getFromProject(p);
        for (Counterpart cp : tmp) {
            ret.add(cp);
        }
        p.setCounterparts(ret);

        model.addAttribute("project",p);
        model.addAttribute("categories",catS.findAll());

        return "project/modify";
    }

    @RequestMapping(value = "new", method = RequestMethod.GET)
    public String newProjet(HttpSession session, Locale locale, Model model) {
        model.addAttribute("categories",catS.findAll());
        return "project/new";
    }

    @RequestMapping(value = "new", method = RequestMethod.POST)
    public String addProject(@RequestParam("projectName") String projectName, @RequestParam("description") String description, @RequestParam("deadline") String deadline, @RequestParam("goal") int goal, @RequestParam("category") int category, HttpSession session, Locale locale, Model model) throws ParseException {

        Date d = new SimpleDateFormat("yy-MM-dd").parse(deadline);
        Project p = new Project(projectName,description,goal,d);
        p.setBelongUser((User)session.getAttribute("user"));
        p.setCategory(catS.getFromId(category));
        pS.insert(p);
        uS.updateUserSession(session);

        return "redirect: /project/new";
    }

    @RequestMapping(value = "answerMsg", method = RequestMethod.POST)
    public String answerMsg(@RequestParam("mId") int mId,@RequestParam("pId") int pId,@RequestParam("content") String content,HttpSession session, Locale locale, Model model){
        Project p = pS.getFromId(pId);
        Message m = mS.getFromId(mId);
        mS.answerMsg(p,content,m);
        model.addAttribute("project",p);

        return project(p.getId(),session,locale,model);
    }

    @RequestMapping(value = "sendMsg", method = RequestMethod.POST)
    public String sendMsg(@RequestParam("pId") int pId,@RequestParam("content") String content,HttpSession session, Locale locale, Model model){
        Project p = pS.getFromId(pId);
        User u = (User)session.getAttribute("user");
        mS.sendMsg(u,p,content);
        model.addAttribute("project",p);
        return project(p.getId(),session,locale,model);
    }

    @RequestMapping(value="donation",method = RequestMethod.POST)
    public String donation(@RequestParam("pId") int pId,@RequestParam("donationValue") int donation, HttpSession session, Locale locale, Model model){
        Project p = pS.getFromId(pId);
        User uSess = (User)session.getAttribute("user");
        if (uSess !=null){
            User u = uS.getFromId(uSess.getId());
            pS.donation(u,p,donation);
        }
        model.addAttribute("project",p);
        return "redirect:/project/"+p.getId();
    }

    @RequestMapping(value = "addCounterPart", method = RequestMethod.POST)
    public String addCounterPart(@RequestParam("IDProjet") int IDProjet,@RequestParam("cpName") String cpName,@RequestParam("cpDescription") String cpDescription, @RequestParam("price") int price, HttpSession session, Locale locale, Model model) throws ParseException {
        Project p = pS.getFromId(IDProjet);
        System.out.println(cpName+cpDescription+price);
        Counterpart c=new Counterpart(price,cpName,cpDescription);
        c.setBelongProjet(p);
        cS.insert(c);

        return "redirect: /project/modify/" + p.getId();
    }

    @RequestMapping(value = "removeCounterPart", method = RequestMethod.POST)
    public String removeCounterPart(@RequestParam("IDProjet") int IDProjet,@RequestParam("CP") int CPid, HttpSession session, Locale locale, Model model) throws ParseException {
        Project p = pS.getFromId(IDProjet);

        cS.destroy(CPid);

        List<Counterpart> ret = new ArrayList<>();
        List<Counterpart> tmp= cS.getFromProject(p);
        for (Counterpart cp : tmp) {
            ret.add(cp);
        }
        p.setCounterparts(ret);

        pS.update(p);
        return "redirect: /project/modify/" + p.getId();
    }
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String modifyProject(@RequestParam("IDProjet") int IDProjet, @RequestParam("projectName") String projectName,@RequestParam("description") String description,@RequestParam("deadline") String deadline, @RequestParam("goal") int goal, @RequestParam("category") int category, HttpSession session, Locale locale, Model model) throws ParseException {

        Project p = pS.getFromId(IDProjet);

        System.out.println("AVANT:"+p.getId()+p.getTitle() + p.getDescription()+p.getGoal()+p.getDeadLine()+p.getBelongUser().getName()+p.getCategory().getName());

        SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
        Date d = sdf.parse(deadline);

        p.setTitle(projectName);
        p.setCategory(catS.getFromId(category));
        p.setDeadLine(d);
        p.setDescription(description);
        p.setGoal(goal);

        pS.update(p);

        p = pS.getFromId(IDProjet);
        System.out.println("APRES:"+p.getId()+p.getTitle() + p.getDescription()+p.getGoal()+p.getDeadLine()+p.getBelongUser().getName()+p.getCategory().getName());

        return "redirect: /project/modify/" + p.getId();
    }



}
