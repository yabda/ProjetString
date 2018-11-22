package controllers;

import beans.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.w3c.dom.css.Counter;
import services.*;


import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@org.springframework.stereotype.Controller
@RequestMapping("")
public class Controller {

    private User user;

    @Resource(name = "projectService")
    private IProjectService pS;

    @Resource(name = "userService")
    private UserServiceInterface uS;
    @Resource(name = "counterpartService")
    private CounterpartServiceInterface cS;


    @Resource(name = "messageService")
    private IMessageService mS;
    @Resource(name = "categoryService")
    private CategoryServiceInterface catS;


    @RequestMapping(value = "/AnswerMsg", method = RequestMethod.POST)
    public String answerMsg(@RequestParam("mId") int mId,@RequestParam("pId") int pId,@RequestParam("content") String content,HttpSession session, Locale locale, Model model){
        Project p = pS.getFromId(pId);
        User u = (User)session.getAttribute("user");
        Message m = mS.getFromId(mId);
        mS.answerMsg(p,content,m);
        model.addAttribute("project",p);

        return project(p.getId(),session,locale,model);
    }
    @RequestMapping(value = "/sendMsg", method = RequestMethod.POST)
    public String sendMsg(@RequestParam("pId") int pId,@RequestParam("content") String content,HttpSession session, Locale locale, Model model){
        Project p = pS.getFromId(pId);
        User u = (User)session.getAttribute("user");
        mS.sendMsg(u,p,content);
        model.addAttribute("project",p);
        return project(p.getId(),session,locale,model);
    }


    @RequestMapping(value = "/search", method=RequestMethod.POST)
    public  String search(@RequestParam("terms") String terms, HttpSession session, Locale locale, Model model){

        List<Project> result = pS.search(terms);
        if (result.isEmpty()){
            model.addAttribute("searchResult",pS.findAll());
            model.addAttribute("find",false);
        }
        else {
            model.addAttribute("searchResult", result);
            model.addAttribute("find", true);
        }
        model.addAttribute("terms",terms);
        return "project/search";
    }

    @RequestMapping(value="/",method = RequestMethod.GET)
    public String index(HttpSession session, Locale locale, Model model) {

        List<Project> frontProjects = pS.findAll(3);
        model.addAttribute("frontProjects", frontProjects);
        return "index";
    }


    @RequestMapping(value="/project/{projectId}")
    public String project(@PathVariable int projectId, HttpSession session, Locale locale, Model model){
        Project p = pS.getFromId(projectId);
        List<Counterpart> ret = new ArrayList<>();
        List<Counterpart> tmp= cS.getFromProject(p);
        for (Counterpart cp : tmp) {
            ret.add(cp);
        }
        p.setCounterparts(ret);
        model.addAttribute("project",p);
        return "project/view";
    }

    @RequestMapping(value="/donation",method = RequestMethod.POST)
    public String donation(@RequestParam("pId") int pId,@RequestParam("donationValue") int donation, HttpSession session, Locale locale, Model model){
        Project p = pS.getFromId(pId);
        User uSess = (User)session.getAttribute("user");
        if (uSess !=null && p.getGoal() != p.getCurrent()){
            if (p.getGoal()-p.getCurrent() < donation){
                donation = p.getGoal()-p.getCurrent();
            }
            User u = uS.getFromId(uSess.getId());
            pS.donation(u,p,donation);
        }
        model.addAttribute("project",p);
        return "redirect:/project/"+p.getId();
    }

    @RequestMapping(value="/login",method = RequestMethod.GET )
    public String loginGet(HttpSession session, Locale locale, Model model) {
        if (session.getAttribute("user") == null)
            return "user/login";
        return "redirect:/";
    }

    @RequestMapping(value="/newProjet",method = RequestMethod.GET)
    public String newProjet(HttpSession session, Locale locale, Model model) {
        List<String> categories=new ArrayList<>();
        for (Category cat : catS.findAll())
        {
            categories.add(cat.getName());
            System.out.println(cat.getName());
        }
        model.addAttribute("c",categories);
        return "newProjet";
    }

    @RequestMapping(value="/myProject",method = RequestMethod.GET)
    public String myProject(HttpSession session, Locale locale, Model model) {
        return "myProject";
    }


    @RequestMapping(value="/modifyProjet/{projectId}")
    public String myproject(@PathVariable int projectId, HttpSession session, Locale locale, Model model){

        Project p = pS.getFromId(projectId);
        List<Counterpart> ret = new ArrayList<>();
        List<Counterpart> tmp= cS.getFromProject(p);
        for (Counterpart cp : tmp) {
            ret.add(cp);
        }
        p.setCounterparts(ret);
        model.addAttribute("project",p);

        return "modifyProjet";
    }

    @RequestMapping(value="/login", method = RequestMethod.POST )
    public String login(@RequestParam("name") String name, @RequestParam("password") String password, HttpSession session, Locale locale, Model model) {
        if (uS.isValid(name, password) != null) {
            user = uS.isValid(name, password);
            session.setAttribute("user", user);
            return "redirect:/";
        }
        else {
            model.addAttribute("badLogin", true);
            return "user/login";
        }
    }

    @RequestMapping(value="/logout",method = RequestMethod.GET )
    public String logout(HttpSession session, Locale locale, Model model) {
        session.removeAttribute("user");
        user = null;
        return "redirect:/";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerGet(HttpSession session, Locale locale, Model model) {
        if (session.getAttribute("user") != null)
            return "redirect:/";
        return "user/register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@RequestParam("name") String name, @RequestParam("password") String password, @RequestParam("repeat-password") String repeat_password, HttpSession session, Locale locale, Model model) {
        if (session.getAttribute("user") != null)
            return "redirect:/";

        if (!repeat_password.equals(password)) {
            model.addAttribute("badRegister", "Password must be the same");
            model.addAttribute("name", name);
            return "user/register";
        }
        String test = uS.testName(name);
        if (test != null) {
            model.addAttribute("badRegister", test);
            model.addAttribute("name", name);
            return "user/register";
        }

        User u = new User();
        u.setName(name);
        u.setPassword(password);
        u.setCreatedAt(new Date());
        u.setUpdatedAt(new Date());
        uS.insert(u);
        return this.login(name, password, session, locale, model);
    }

    @RequestMapping(value = "/users/{userId}", method = RequestMethod.GET)
    public String userView(@PathVariable String userId, HttpSession session, Locale locale, Model model) {
        if (userId.equals("me") && session.getAttribute("user") == null)
            return "redirect:/login";
        else if (userId.equals("me") && session.getAttribute("user") != null)
            return "/user/me";
        else {
            User u = uS.getFromId(Integer.parseInt(userId));
            if (u == null)
                return "/errors/404";
            else {
                model.addAttribute("user", u);
                return "/user/view";
            }
        }
    }

    @RequestMapping(value = "/users/me/editName", method = RequestMethod.POST)
    public String changeName(@RequestParam("name") String name, HttpSession session, Locale locale, Model model) {
        String test = uS.testName(name);
        if (test != null) {
            model.addAttribute("badChange", test);
            model.addAttribute("name", name);
            return "user/me";
        }
        if (session.getAttribute("user") == null)
            return "redirect:/login";
        else {
            User u = (User)session.getAttribute("user");
            u.setName(name);
            uS.update(u);
            user = u;
            return "redirect:/users/me";
        }
    }

    @RequestMapping(value = "newProjet", method = RequestMethod.POST)
    public String addProject(@RequestParam("projectName") String projectName,@RequestParam("description") String description,@RequestParam("deadline") String deadline,@RequestParam("goal") int goal, HttpSession session, Locale locale, Model model) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
        Date d = sdf.parse(deadline);
        Project p = new Project(projectName,description,goal,d);
        p.setBelongUser(user);
        pS.insert(p);

        System.out.println(p.getTitle() + p.getDescription()+p.getGoal()+p.getDeadLine()+p.getBelongUser().getName());

        //System.out.println(deadline);
        return "redirect: /newProjet";
    }
}
