package com.farhan.blog.controller;

import com.farhan.blog.entity.Post;
import com.farhan.blog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/")
    public Iterable<Post> getPosts() {
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

    @PutMapping("/{slug}")
    public boolean updatePost(@PathVariable String slug, @RequestBody Post post) {
        return postService.updatePost(slug, post);
    }

    @PutMapping("/publish/{id}")
    public Post publishPost(@PathVariable Integer id) {
        return postService.publishPost(id);
    }

    @DeleteMapping("/{id}")
    public boolean deletePost(@PathVariable Integer id) {
        return postService.deletePost(id);
    }
}
