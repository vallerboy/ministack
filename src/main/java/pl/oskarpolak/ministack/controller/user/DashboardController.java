package pl.oskarpolak.ministack.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import pl.oskarpolak.ministack.model.service.SessionService;

import javax.persistence.Access;

@Controller
public class DashboardController {

    @Autowired
    SessionService sessionService;

    @GetMapping("/user/dashboard")
    public String dashboard() {
        if(!sessionService.isLogin()){
            return "redirect:/user/login";
        }
        return "user/dashboard";
    }
}
