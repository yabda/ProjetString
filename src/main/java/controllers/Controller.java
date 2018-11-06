package controllers;

import beans.Counterpart;
import beans.Project;
import beans.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import services.*;


import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

    @RequestMapping(value="/",method = RequestMethod.GET)
    public String index(HttpSession session, Locale locale, Model model) {

        List<Project> frontProjects = pS.findAll(3);
        model.addAttribute("frontProjects", frontProjects);
        return "index";
    }


    @RequestMapping(value="/Project/{projectId}")
    public String project(@PathVariable String projectId, HttpSession session, Locale locale, Model model){
        Project p = pS.getFromId(Integer.parseInt(projectId));
        model.addAttribute("project",p);
        return "projectX";
    }

    @RequestMapping(value="/donation",method = RequestMethod.POST)
    public String donation(@RequestParam("pId") int pId,@RequestParam("donationValue") int donation, HttpSession session, Locale locale, Model model){
        Project p = pS.getFromId(pId);
        User uSess = (User)session.getAttribute("user");
        if (uSess !=null){
            User u = uS.getFromId(uSess.getId());
            pS.donation(u,p,donation);
            System.out.println("back in donation\n");
        }
        model.addAttribute("project",p);

        return "redirect:/Project/"+p.getId();
    }

    @RequestMapping(value="/login",method = RequestMethod.GET )
    public String loginGet(HttpSession session, Locale locale, Model model) {
        if (session.getAttribute("user") == null)
            return "user/login";
        return "redirect:/";
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

        if (!repeat_password.equals(password) || name.length() <= 0)
            model.addAttribute("badRegister", true);

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
            else
                return "/user/view";
        }
    }

    @RequestMapping(value = "/users/me/editName", method = RequestMethod.POST)
    public String changeName(@RequestParam("name") String name, HttpSession session, Locale locale, Model model) {
        if (!uS.testName(name))
            return "redirect:/users/me";
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
}
