package org.example.socialnetworkserver.controllers;

import org.example.socialnetworkserver.enteties.Post;
import org.example.socialnetworkserver.responses.BasicResponse;
import org.example.socialnetworkserver.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api")

public class PostController {




    @Autowired
    private PostService postService;

    @GetMapping("/FeedPosts")
    public BasicResponse<ArrayList<Post>> getMyFollowingsPosts(@CookieValue("token") String tokenCookie) {
        return  postService.getMyFollowingsPosts(tokenCookie);
    }

    @GetMapping("/MyPosts")
    public ResponseEntity<Boolean> getMyPosts(@CookieValue("token") String tokenCookie) {
        return ResponseEntity.ok(postService.getMyPosts(tokenCookie));
    }

    @PostMapping("/AddPost")
    public  BasicResponse<String> addPost(@RequestBody Post post , @CookieValue("token") String tokenCookie) {
        return postService.addPost(post,tokenCookie);
    }

}