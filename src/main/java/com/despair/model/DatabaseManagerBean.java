package com.despair.model;

import com.despair.repositories.PointRepository;
import com.despair.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import org.springframework.web.context.annotation.SessionScope;

import java.util.List;
import java.util.Optional;

@Component
public class DatabaseManagerBean {
    @Bean
    public DatabaseManagerBean databaseManager() {
        return new DatabaseManagerBean();
    }

    private User user;
    @Autowired
    private PointRepository pointRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Point> getPoints() {
        return pointRepository.findAllByUsernameOrderByTime(user.getUsername());
    }
    public List<Point> getPoints(String username) {
        return pointRepository.findAllByUsernameOrderByTime(username);
    }


    public void add(Point p) {
        p.setUsername(user.getUsername());
        pointRepository.save(p);
    }

    private String md5(String str) {
        return DigestUtils.md5DigestAsHex(str.getBytes());
    }

    public void delete(Point p) {
        pointRepository.deleteById(p.getTime());
    }

    public void deleteAllByUsername() {
        userRepository.delete(user);
        userRepository.save(user);
    }

    public String login(User user) {
        User us = findUser(user);
        if (us.getUsername() != null && us.getPassword().equals(md5(user.getPassword()))) {
            this.user = us;
            return "true";
        } else if (us.getUsername() == null)
            return "Такого пользователя не существует!";
        else
            return "Неправильный пароль!";
    }

    public String register(User user) {
        boolean found = findUser(user).getUsername() != null;
        if (!found) {
            this.user = user;
            this.user.setPassword(md5(user.getPassword()));
            userRepository.save(user);
            return "true";
        }
        return "Такой пользователь уже существует!";

    }

    private User findUser(User user) {
        Optional<User> s = userRepository.findById(user.getUsername());
        return s.orElseGet(User::new);
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
