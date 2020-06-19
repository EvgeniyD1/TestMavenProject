package com.htp.controller;

import com.htp.controller.request.UserRequest;
import com.htp.domain.User;
import com.htp.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;
import java.util.Date;


@Controller
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String findAll(ModelMap modelMap){
        modelMap.addAttribute("users", userService.findAll());
        return "user/users";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long userId, ModelMap modelMap) {
        modelMap.addAttribute("user", userService.findOne(userId));
        return "user/user";
    }

    @GetMapping("/search")
    public String searchUser(@RequestParam("query") String query, ModelMap modelMap) {
        modelMap.addAttribute("users", userService.search(query));
        return "user/users";
    }

    @GetMapping("/addUser")
    public ModelAndView addUser(){
        return new ModelAndView("user/userForm","command",new User());
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute("command") User user, ModelMap modelMap){
        user.setCreated(new Timestamp(new Date().getTime()));
        user.setChanged(new Timestamp(new Date().getTime()));
        modelMap.addAttribute("id", user.getId());
        modelMap.addAttribute("username", user.getUsername());
        modelMap.addAttribute("surname", user.getSurname());
        modelMap.addAttribute("patronymic", user.getPatronymic());
        modelMap.addAttribute("phoneNumber", user.getPhoneNumber());
        modelMap.addAttribute("login", user.getLogin());
        modelMap.addAttribute("password", user.getPassword());
        modelMap.addAttribute("created", user.getCreated());
        modelMap.addAttribute("changed", user.getCreated());
        modelMap.addAttribute("birthDate", user.getBirthDate());
        modelMap.addAttribute("mail", user.getMail());
        modelMap.addAttribute("isBlocked", user.isBlocked());
        modelMap.addAttribute("countryLocation", user.getCountryLocation());
//        modelMap.addAttribute("user",userService.save(user));
//        return "user/user";
        return "user/returnNewUser";
    }

}
