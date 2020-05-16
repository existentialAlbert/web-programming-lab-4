package com.despair.controller;

import com.despair.model.DatabaseManagerBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
public class NavigationController {
    @Resource(name = "databaseManager")
    private DatabaseManagerBean databaseManager;

    @RequestMapping(value = {"/", "/autorization"})
    public String loginPage() {
        return "autorization.html";
    }

    @RequestMapping(value = {"/main"})
    public String mainPage() {
        if (databaseManager.getUser() != null)
            return "redirect:/index.html";
        else return "redirect:/";
    }

    @RequestMapping("/exit")
    public String exit() {
        databaseManager.setUser(null);
        return "redirect:/";
    }
//    @RequestMapping("/error")
//    public String errorPage(){
//        return "error.html";
//    }
}
