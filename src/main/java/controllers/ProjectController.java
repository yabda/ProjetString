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
import java.util.*;

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
        if (session.getAttribute("user")!=null)
            return "project/my";
        return "redirect: /";
    }

    @RequestMapping(value="modify/{projectId}", method = RequestMethod.GET)
    public String myproject(@PathVariable int projectId, HttpSession session, Locale locale, Model model){
        Project p = pS.getFromId(projectId);
        User u = (User)session.getAttribute("user");
        if (u!=null && u.getId()==p.getBelongUser().getId()) {
            List<Counterpart> ret = new ArrayList<>();
            List<Counterpart> tmp = cS.getFromProject(p);
            for (Counterpart cp : tmp) {
                ret.add(cp);
            }
            p.setCounterparts(ret);

            model.addAttribute("project", p);
            model.addAttribute("categories", catS.findAll());
            Date d = new Date();
            Date d2 = new Date();
            Calendar c = Calendar.getInstance();
            c.add(Calendar.DATE, 1);
            d = c.getTime();
            c = Calendar.getInstance();
            c.add(Calendar.MONTH, 2);
            d2 = c.getTime();
            model.addAttribute("today", new SimpleDateFormat("yyyy-MM-dd").format(d));
            model.addAttribute("todayPlus2Month", new SimpleDateFormat("yyyy-MM-dd").format(d2));
            return "project/modify";
        }

        return  "redirect: /project/"+projectId;
    }

    @RequestMapping(value = "new", method = RequestMethod.GET)
    public String newProjet(HttpSession session, Locale locale, Model model) {

        if(session.getAttribute("user")!=null) {
            model.addAttribute("categories", catS.findAll());
            Date d = new Date();
            Date d2 = new Date();
            Calendar c = Calendar.getInstance();
            c.add(Calendar.DATE, 1);
            d = c.getTime();
            c = Calendar.getInstance();
            c.add(Calendar.MONTH, 2);
            d2 = c.getTime();
            model.addAttribute("today", new SimpleDateFormat("yyyy-MM-dd").format(d));
            model.addAttribute("todayPlus2Month", new SimpleDateFormat("yyyy-MM-dd").format(d2));
            return "project/new";
        }
        return "redirect: /";
    }

    @RequestMapping(value = "new", method = RequestMethod.POST)
    public String addProject(@RequestParam("projectName") String projectName, @RequestParam("description") String description, @RequestParam("deadline") String deadline, @RequestParam("goal") int goal, @RequestParam("category") int category, HttpSession session, Locale locale, Model model) throws ParseException {

        if(session.getAttribute("user")!=null) {
            Date d = new SimpleDateFormat("yy-MM-dd").parse(deadline);
            Project p = new Project(projectName, description, goal, d);
            p.setBelongUser((User) session.getAttribute("user"));
            p.setCategory(catS.getFromId(category));
            pS.insert(p);
            uS.updateUserSession(session);

            return "redirect: /project/" + p.getId();
        }
        return "redirect: /";
    }

    @RequestMapping(value = "answerMsg", method = RequestMethod.POST)
    public String answerMsg(@RequestParam("mId") int mId,@RequestParam("pId") int pId,@RequestParam("content") String content,HttpSession session, Locale locale, Model model){
        if(session.getAttribute("user")!=null) {
            Project p = pS.getFromId(pId);
            Message m = mS.getFromId(mId);
            mS.answerMsg(p, content, m);
            model.addAttribute("project", p);

            return project(p.getId(), session, locale, model);
        }
        return "redirect: /project/"+pId;
    }

    @RequestMapping(value = "sendMsg", method = RequestMethod.POST)
    public String sendMsg(@RequestParam("pId") int pId,@RequestParam("content") String content,HttpSession session, Locale locale, Model model){
        if(session.getAttribute("user")!=null) {
            Project p = pS.getFromId(pId);
            User u = (User) session.getAttribute("user");
            mS.sendMsg(u, p, content);
            model.addAttribute("project", p);
            return project(p.getId(), session, locale, model);
        }
        return "redirect: /project/"+pId;
    }

    @RequestMapping(value="donation",method = RequestMethod.POST)
    public String donation(@RequestParam("pId") int pId,@RequestParam("donationValue") int donation, HttpSession session, Locale locale, Model model){
        Project p = pS.getFromId(pId);
        if(session.getAttribute("user")!=null) {
            User uSess = (User) session.getAttribute("user");
            if (uSess != null) {
                User u = uS.getFromId(uSess.getId());
                pS.donation(u, p, donation);
            }
            model.addAttribute("project", p);
        }
        return "redirect:/project/"+p.getId();
    }

    @RequestMapping(value = "addCounterPart", method = RequestMethod.POST)
    public String addCounterPart(@RequestParam("IDProjet") int IDProjet,@RequestParam("cpName") String cpName,@RequestParam("cpDescription") String cpDescription, @RequestParam("price") int price, HttpSession session, Locale locale, Model model) throws ParseException {
        if(session.getAttribute("user")!=null) {
            Project p = pS.getFromId(IDProjet);
            Counterpart c=new Counterpart(price,cpName,cpDescription);
            c.setBelongProjet(p);
            cS.insert(c);

            return "redirect: /project/modify/" + p.getId();
        }
        return "redirect: /project/"+IDProjet;
    }

    @RequestMapping(value = "removeCounterPart", method = RequestMethod.POST)
    public String removeCounterPart(@RequestParam("IDProjet") int IDProjet,@RequestParam("CP") int CPid, HttpSession session, Locale locale, Model model) throws ParseException {
        Project p = pS.getFromId(IDProjet);
        if(session.getAttribute("user")!=null) {

            cS.destroy(CPid);

            List<Counterpart> ret = new ArrayList<>();
            List<Counterpart> tmp = cS.getFromProject(p);
            for (Counterpart cp : tmp) {
                ret.add(cp);
            }
            p.setCounterparts(ret);

            pS.update(p);
            return "redirect: /project/modify/" + p.getId();
        }
        return "redirect: /project/"+IDProjet;
    }
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String modifyProject(@RequestParam("IDProjet") int IDProjet, @RequestParam("projectName") String projectName,@RequestParam("description") String description,@RequestParam("deadline") String deadline, @RequestParam("goal") int goal, @RequestParam("category") int category, HttpSession session, Locale locale, Model model) throws ParseException {
        if(session.getAttribute("user")!=null) {
            Project p = pS.getFromId(IDProjet);

            SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
            Date d = sdf.parse(deadline);

            p.setTitle(projectName);
            p.setCategory(catS.getFromId(category));
            p.setDeadLine(d);
            p.setDescription(description);
            p.setGoal(goal);

            pS.update(p);

            p = pS.getFromId(IDProjet);

            return "redirect: /project/modify/" + p.getId();
        }
        return "redirect: /project/"+IDProjet;
    }



}
