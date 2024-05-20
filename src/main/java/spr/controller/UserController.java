package spr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import spr.entity.User;
import spr.service.UserService;

import javax.validation.Valid;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/users")
    public String index(Model model) {
        model.addAttribute("users", userService.index());
        return "users";
    }
    @GetMapping(value = "/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "new";
    }
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String create(@ModelAttribute("user") @Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "new";
        } else {
            userService.save(user);
            return "redirect:/users";
        }
    }
    @GetMapping("/edit")
    public String editUser(@RequestParam("id") Long id, Model model) {
        model.addAttribute("user", userService.show(id));
        return "edit";
    }

    @PostMapping("/edit")
    public String updateUser(@RequestParam("id") Long id,@Valid @ModelAttribute("user") User user, BindingResult result) {
        if (result.hasErrors()) {
            return "edit";
        } else {
            userService.update(id, user);
            return "redirect:/users";
        }
    }
    @DeleteMapping(value = "/del")
    public String deleteUser(@RequestParam("id") Long id, @ModelAttribute("user") User user) {
        userService.delete(user.getId());
        return "redirect:/users";
    }
}
