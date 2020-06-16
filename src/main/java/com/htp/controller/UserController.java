package com.htp.controller;

import com.htp.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;


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

//    @GetMapping("/save")
//    @ResponseStatus(HttpStatus.CREATED)
//    public ResponseEntity<User> save(@RequestBody UserRequest userRequest){
//        User user = new User();
//        user.setUsername(userRequest.getUsername());
//        user.setSurname(userRequest.getSurname());
//        user.setPatronymic(userRequest.getPatronymic());
//        user.setPhoneNumber(userRequest.getPhoneNumber());
//        user.setLogin(userRequest.getLogin());
//        user.setPassword(userRequest.getPassword());
//        user.setCreated(userRequest.getCreated());
//        user.setChanged(userRequest.getChanged());
//        user.setBirthDate(userRequest.getBirthDate());
//        user.setMail(userRequest.getMail());
//        user.setCountryLocation(userRequest.getCountryLocation());
//        userService.save(user);
//        return new ResponseEntity<>(user,HttpStatus.OK);
//    }

}
