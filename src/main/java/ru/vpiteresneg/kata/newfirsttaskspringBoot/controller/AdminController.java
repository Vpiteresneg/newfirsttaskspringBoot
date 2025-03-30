package ru.vpiteresneg.kata.newfirsttaskspringBoot.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.vpiteresneg.kata.newfirsttaskspringBoot.model.User;
import ru.vpiteresneg.kata.newfirsttaskspringBoot.service.UserService;


@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String adminPanel(Model model) {
        model.addAttribute("users", userService.getAll());
        return "admin/admin";
    }

    @GetMapping("/new")
    public String newUserForm(Model model) {
        model.addAttribute("user", new User());
        return "admin/new";
    }

    @PostMapping()
    public String createUser(@ModelAttribute User user) {
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/edit/{id}")
    public String editUserForm(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "admin/edit";
    }

//    @PostMapping("/update")
//    public String updateUser(@ModelAttribute User user) {
//        userService.saveUser(user);
//        return "redirect:/admin";
//    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute User user, @RequestParam(required = false) String newPassword) {
        if (newPassword != null && !newPassword.isEmpty()) {
            user.setPassword(newPassword); // Устанавливаем новый пароль
        } else {
            // Пароль остается прежним, ничего не делаем
            User existingUser = userService.findById(user.getId());
            user.setPassword(existingUser.getPassword()); // Оставляем старый пароль
        }
        userService.saveUser(user); // Сохраняем пользователя
        return "redirect:/admin";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}