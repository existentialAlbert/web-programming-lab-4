package com.despair.controller;

import com.despair.model.DatabaseManagerBean;
import com.despair.model.Point;
import com.despair.model.User;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

@RestController
public class Service {
    @Resource(name = "databaseManager")
    private DatabaseManagerBean databaseManager;

    @PostMapping("/check")
    private Map<Integer, String> check(@RequestParam(name = "x[]") String[] x,
                                       @RequestParam(name = "y") String y,
                                       @RequestParam(name = "r[]") String[] r,
                                       @RequestParam(name = "offset") String offset,
                                       @RequestParam(name = "saveable") boolean saveable) {
        try {
            Gson gson = new Gson();
            Map<Integer, String> response = new HashMap<>();
            int id = 0;
            for (String x_ : x)
                for (String r_ : r) {
                    Point p = new Point(x_, y, r_, offset);
                    if (saveable)
                        databaseManager.add(p);
                    String answer = gson.toJson(p);
                    response.put(id++, answer);
                }
            return response;
        } catch (Exception e) {
            return new HashMap<>();
        }
    }

    @GetMapping("/getHistory")
    private Map<Integer, String> getHistory() {
        List<Point> points = databaseManager.getPoints();
        Map<Integer, String> response = new HashMap<>();
        int id = 0;
        Gson gson = new Gson();
        for (Point p : points) {
            p.setStringTime();
            response.put(id++, gson.toJson(p));
        }
        return response;
    }

    @GetMapping("/checkText")
    private boolean checkText(@RequestParam(name = "y") String Y) {
        try {
            BigDecimal y = new BigDecimal(Y);
            return y.compareTo(new BigDecimal(3.0)) < 0 && y.compareTo(new BigDecimal(-5.0)) > 0;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping("/clearHistory")
    private void clearHistory() {
        if (databaseManager.getUser() != null)
            databaseManager.deleteAllByUsername();
    }

    @GetMapping("/getUsername")
    private String getUsername() {
        return databaseManager.getUser().getUsername();
    }

    @PostMapping("/register")
    private String register(@RequestParam(name = "name") String username,
                            @RequestParam(name = "password") String password) {
        User user = new User();
        user.setPassword(password);
        user.setUsername(username);
        return databaseManager.register(user);
    }

    @PostMapping("/login")
    private String login(@RequestParam(name = "name") String username,
                         @RequestParam(name = "password") String password) {
        User user = new User();
        user.setPassword(password);
        user.setUsername(username);
        return databaseManager.login(user);
    }
}
