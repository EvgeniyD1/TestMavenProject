package com.htp.controller;

import com.htp.controller.request.UserRequest;
import com.htp.domain.User;
import com.htp.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable("id") Long userId) {
        User user = userService.findOne(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<User>> searchUser(@RequestParam("query") String query) {
        List<User> searchResult = userService.search(query);
        return new ResponseEntity<>(searchResult, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody UserRequest request) {
        User userForCreate = new User();
        userForCreate.setUsername(request.getUsername());
        userForCreate.setSurname(request.getSurname());
        userForCreate.setPatronymic(request.getPatronymic());
        userForCreate.setPhoneNumber(request.getPhoneNumber());
        userForCreate.setLogin(request.getLogin());
        userForCreate.setPassword(request.getPassword());
        userForCreate.setCreated(new Timestamp(new Date().getTime()));
        userForCreate.setChanged(new Timestamp(new Date().getTime()));
        userForCreate.setBirthDate(request.getBirthDate());
        userForCreate.setBlocked(request.isBlocked());
        userForCreate.setMail(request.getMail());
        userForCreate.setCountryLocation(request.getCountryLocation());
        return new ResponseEntity<>(userService.save(userForCreate), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long userId, @RequestBody UserRequest request){
        User userForUpdate = userService.findOne(userId);
        userForUpdate.setUsername(request.getUsername());
        userForUpdate.setSurname(request.getSurname());
        userForUpdate.setPatronymic(request.getPatronymic());
        userForUpdate.setPhoneNumber(request.getPhoneNumber());
        userForUpdate.setLogin(request.getLogin());
        userForUpdate.setPassword(request.getPassword());
        userForUpdate.setChanged(new Timestamp(new Date().getTime()));
        userForUpdate.setBirthDate(request.getBirthDate());
        userForUpdate.setBlocked(request.isBlocked());
        userForUpdate.setMail(request.getMail());
        userForUpdate.setCountryLocation(request.getCountryLocation());
        return new ResponseEntity<>(userService.update(userForUpdate), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> deleteUser(@PathVariable("id") Long userId) {
        User userForDelete = userService.findOne(userId);
        return new ResponseEntity<>(userService.delete(userForDelete), HttpStatus.OK);
    }

//    @GetMapping
//    public String findAll(ModelMap modelMap){
//        modelMap.addAttribute("users", userService.findAll());
//        return "user/users";
//    }
//
//    @GetMapping("/{id}")
//    public String findById(@PathVariable("id") Long userId, ModelMap modelMap) {
//        modelMap.addAttribute("user", userService.findOne(userId));
//        return "user/user";
//    }
//
//    @GetMapping("/search")
//    public String searchUser(@RequestParam("query") String query, ModelMap modelMap) {
//        modelMap.addAttribute("users", userService.search(query));
//        return "user/users";
//    }
//
//    @GetMapping("/addUser")
//    public ModelAndView addUser(){
//        return new ModelAndView("user/userForm","command",new User());
//    }
//
//    @PostMapping("/save")
//    public String saveUser(@ModelAttribute("command") User user, ModelMap modelMap){
//        user.setCreated(new Timestamp(new Date().getTime()));
//        user.setChanged(new Timestamp(new Date().getTime()));
//        modelMap.addAttribute("id", user.getId());
//        modelMap.addAttribute("username", user.getUsername());
//        modelMap.addAttribute("surname", user.getSurname());
//        modelMap.addAttribute("patronymic", user.getPatronymic());
//        modelMap.addAttribute("phoneNumber", user.getPhoneNumber());
//        modelMap.addAttribute("login", user.getLogin());
//        modelMap.addAttribute("password", user.getPassword());
//        modelMap.addAttribute("created", user.getCreated());
//        modelMap.addAttribute("changed", user.getCreated());
//        modelMap.addAttribute("birthDate", user.getBirthDate());
//        modelMap.addAttribute("mail", user.getMail());
//        modelMap.addAttribute("isBlocked", user.isBlocked());
//        modelMap.addAttribute("countryLocation", user.getCountryLocation());
//        modelMap.addAttribute("user",userService.save(user));
//        return "user/user";
//        return "user/returnNewUser";
//    }

}
