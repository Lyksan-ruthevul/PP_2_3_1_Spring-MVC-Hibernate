package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

@Controller
@RequestMapping("/")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String displayAllUsers(Model model) {
        model.addAttribute("allUsers", userService.displayAllUsers());
        return "all-users";
    }

    @GetMapping("/showAddNewUserForm")
    public String showAddNewUserForm(Model model) {
        model.addAttribute("addUser", new User());
        return "add-user";
    }

    @PostMapping("/addUser")
    public String addUser(@ModelAttribute("addUser") User user) {
        userService.saveUser(user);
        return "redirect:/";
    }

    @GetMapping(value = "/showEditUserForm")
    public String showEditUserForm(@RequestParam("id") int id, Model model) {
        User user = userService.getUser(id);
        model.addAttribute("user", user);
        return "edit-user";
    }

    @PostMapping("/saveEditUser")
    public String saveEditUser(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/";
    }

    @GetMapping("/deleteUser")
    public String deleteUser(@RequestParam("id") int id) {
        User user = userService.getUser(id);
        userService.deleteUser(user);
        return "redirect:/";
    }
}
