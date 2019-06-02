package pl.oskarpolak.ministack.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.oskarpolak.ministack.model.entity.PostEntity;
import pl.oskarpolak.ministack.model.service.PostService;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PostControllerApi {

    @Autowired
    PostService postService;

    @GetMapping("/post")
    public ResponseEntity getAllPosts(){
        return ResponseEntity.ok(
                postService.getAllPosts()
        );
    }

    @GetMapping("/post/{id}")
    public ResponseEntity getOnePost(@PathVariable("id") int id){
        Optional<PostEntity> post = postService.getPostOptional(id);
        if(!post.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post with this id not exist");
        }
        return ResponseEntity.ok(post);
    }
}
