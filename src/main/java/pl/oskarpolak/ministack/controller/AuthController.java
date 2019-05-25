package pl.oskarpolak.ministack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.oskarpolak.ministack.model.form.RegisterForm;
import pl.oskarpolak.ministack.model.service.UserService;

@Controller
public class AuthController {

    @Autowired
    UserService userService;

    @GetMapping("/user/register")
    public String register(Model model) {
        model.addAttribute("registerForm", new RegisterForm());
        return "user/register";
    }

    @PostMapping("/user/register")
    public String register(@ModelAttribute("registerForm") RegisterForm registerForm,
                           Model model) {
        model.addAttribute("isRegistered", userService.registerUser(registerForm));
        return "user/register";
    }

}
