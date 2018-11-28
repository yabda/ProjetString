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

import static java.lang.Integer.parseInt;

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

        if (session.getAttribute("user") == null)
            return "redirect:/login";
        else {
            if (userId.equals("me")) {
                User majU = (User)session.getAttribute("user");
                majU = uS.getFromId(majU.getId());
                session.setAttribute("user",majU);
                return "/user/me";
            } else {
                User majU = uS.getFromId(parseInt(userId));
                if (majU == null) {
                    return "/errors/404";
                }
                else {
                    session.setAttribute("user",majU);
                    return "/user/view";
                }
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
}
