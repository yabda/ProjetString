package controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

@org.springframework.stereotype.Controller
@RequestMapping("")
public class Controller {

    //  "/index"
    @RequestMapping(value="/",method = RequestMethod.GET)
    public String index(Locale locale, Model model) {
        return "index";
    }
}
