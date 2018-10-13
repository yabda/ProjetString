package controllers;

import beans.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import services.ServiceImpl;


import java.text.DateFormat;
import java.util.*;

@org.springframework.stereotype.Controller
@RequestMapping("")
public class Controller {

    @Autowired
    ServiceImpl serviceImpl;

    @RequestMapping(value="/",method = RequestMethod.GET)
    public String index(Locale locale, Model model) {
        Set<Project> projects =  serviceImpl.getAllProj();
        ArrayList<Project> newestProj = new ArrayList<Project>(new TreeSet<Project>(projects));
        Collections.sort(newestProj);
        model.addAttribute("newestProj", newestProj.subList(0,2));
        return "index";
    }


    @RequestMapping(value="/login",method = RequestMethod.POST )
    public String login(Locale locale, Model model) {
        return "login";
    }

    @RequestMapping(value="/login",method = RequestMethod.GET )
    public String loginGet(Locale locale, Model model) {
        return login(locale,model);
    }

}
