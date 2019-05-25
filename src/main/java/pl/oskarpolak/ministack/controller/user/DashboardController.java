package pl.oskarpolak.ministack.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/user/dashboard")
    public String dashboard() {
        return "user/dashboard";
    }
}
