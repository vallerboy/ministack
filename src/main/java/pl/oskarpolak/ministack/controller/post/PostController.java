package pl.oskarpolak.ministack.controller.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.oskarpolak.ministack.model.form.PostForm;
import pl.oskarpolak.ministack.model.service.PostService;

@Controller
public class PostController {

    @Autowired
    PostService postService;

    @GetMapping("/post/add")
    public String addPost(Model model) {
        model.addAttribute("postForm", new PostForm());
        return "post/add_post";
    }

    @PostMapping("/post/add")
    public String addPost(@ModelAttribute PostForm postForm){
        System.out.println(postForm);
        return "redirect:/user/dashboard";
    }

}
