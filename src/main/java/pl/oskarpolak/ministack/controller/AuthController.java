package pl.oskarpolak.ministack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.oskarpolak.ministack.model.form.LoginForm;
import pl.oskarpolak.ministack.model.form.RegisterForm;
import pl.oskarpolak.ministack.model.service.SessionService;
import pl.oskarpolak.ministack.model.service.UserService;

import javax.validation.Valid;

@Controller
public class AuthController {

    @Autowired
    UserService userService;

    @Autowired
    SessionService sessionService;

    @GetMapping("/user/register")
    public String register(Model model) {
        model.addAttribute("registerForm", new RegisterForm());
        return "user/register";
    }

    @PostMapping("/user/register")
    public String register(@ModelAttribute("registerForm") @Valid RegisterForm registerForm,
                           BindingResult bindingResult,
                           Model model) {
        if(bindingResult.hasErrors()){
            return "user/register";
        }

        boolean isRegistered = userService.registerUser(registerForm);
        if(isRegistered){
            return "redirect:/user/login";
        }

        registerForm.setPassword("");
        model.addAttribute("isRegistered", isRegistered);
        return "user/register";
    }

    @GetMapping("/user/login")
    public String login(Model model){
        model.addAttribute("loginForm", new LoginForm());
        return "user/login";
    }

    @PostMapping("/user/login")
    public String login(@ModelAttribute LoginForm loginForm,
                        Model model){
        if(userService.tryLogin(loginForm)){
            return "redirect:/user/dashboard";
        }
        model.addAttribute("isLogin", false);
        return "user/login";
    }

    @GetMapping("/user/logout")
    public String logout(RedirectAttributes redirectAttributes){
        sessionService.setLogin(false);

        redirectAttributes.addFlashAttribute("info", "Zostałeś wylogowany!");
        return "redirect:/user/login";
    }
}
