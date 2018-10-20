package controllers;

import beans.Project;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import services.CRUDService;


import javax.annotation.Resource;
import java.util.*;

@org.springframework.stereotype.Controller
@RequestMapping("")
public class Controller {

    @Resource(name = "projectService")
    private CRUDService<Project> pS;

    @RequestMapping(value="/",method = RequestMethod.GET)
    public String index(Locale locale, Model model) {

        List<Project> frontProjects = pS.findAll(3);
        model.addAttribute("frontProjects", frontProjects);
        return "index";
    }


    @RequestMapping(value="/login",method = RequestMethod.POST )
    public String login(Locale locale, Model model) {
        return "login";
    }

    @RequestMapping(value="/login",method = RequestMethod.GET )
    public String loginGet(Locale locale, Model model) {
        return "login"
                ;
    }

}
