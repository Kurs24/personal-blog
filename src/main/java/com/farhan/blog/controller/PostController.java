package com.farhan.blog.controller;

import com.farhan.blog.entity.Post;
import com.farhan.blog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/")
    public List<Post> getPosts() {
        return postService.getPosts();
    }

    @GetMapping("/{slugs}")
    public Post getPosts(@PathVariable String slugs) {
        return postService.getPostBySlug(slugs);
    }

    @PostMapping()
    public Post createPost(@RequestBody Post post) {
        return postService.createPost(post);
    }

    @PutMapping("/{id}")
    public boolean updatePost(@PathVariable Integer id, @RequestBody Post post) {
        return postService.updatePost(id, post.getTitle(), post.getSlug());
    }

    @DeleteMapping("/{id}")
    public boolean deletePost(@PathVariable Integer id) {
        return postService.deletePost(id);
    }
}
