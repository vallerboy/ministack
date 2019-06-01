package pl.oskarpolak.ministack.controller.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.oskarpolak.ministack.model.entity.UserEntity;
import pl.oskarpolak.ministack.model.form.PostForm;
import pl.oskarpolak.ministack.model.service.PostService;
import pl.oskarpolak.ministack.model.service.SessionService;

@Controller
public class PostController {

    @Autowired
    PostService postService;

    @Autowired
    SessionService sessionService;

    @GetMapping("/post/add")
    public String addPost(Model model) {
        if(!sessionService.isLogin()){
            return "redirect:/user/login";
        }

        model.addAttribute("postForm", new PostForm());
        return "post/add_post";
    }

    @PostMapping("/post/add")
    public String addPost(@ModelAttribute PostForm postForm,
                          RedirectAttributes redirectAttributes){
        postService.addPost(postForm);

        redirectAttributes.addFlashAttribute("info", "Dodano nowy post");
        return "redirect:/user/dashboard";
    }

    @GetMapping("/post/delete/{id}")
    public String deletePost(@PathVariable("id") int id){
        if(sessionService.getAccountType() != UserEntity.AccountType.ADMIN){
            return "redirect:/user/dashboard";
        }

        postService.deletePost(id);
        return "redirect:/user/dashboard";
    }

}
