package controllers;

import beans.Counterpart;
import beans.Project;
import beans.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import services.CRUDService;
import services.CounterpartServiceInterface;
import services.UserServiceInterface;


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
    private CRUDService<Project> pS;
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


    @RequestMapping(value="/projectX",method = RequestMethod.GET)
    public String projectX(HttpSession session, Locale locale, Model model){
        Project test = pS.getFromId(1);
        model.addAttribute("project",test);
        return "projectX";
    }

    @RequestMapping(value="/donation",method = RequestMethod.POST)
    public String donation(@RequestParam("pId") int pId,@RequestParam("donationValue") int donation, HttpSession session, Locale locale, Model model){
        List<Project> frontProjects = pS.findAll(1);
        Project p = frontProjects.get(0);

        model.addAttribute("project",p);
        p.setCurrent(p.getCurrent()+donation);
        pS.update(p);
        return "redirect:/projectX";
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

    @RequestMapping(value="/login",method = RequestMethod.GET )
    public String loginGet(HttpSession session, Locale locale, Model model) {
        if (session.getAttribute("user") == null)
            return "user/login";
        return "redirect:/";
    }

    @RequestMapping(value="/logout",method = RequestMethod.GET )
    public String logout(HttpSession session, Locale locale, Model model) {
        session.removeAttribute("user");
        user = null;
        return "redirect:/";
    }

}
