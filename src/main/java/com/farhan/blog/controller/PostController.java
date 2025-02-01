package com.farhan.blog.controller;

import com.farhan.blog.entity.Post;
import com.farhan.blog.request.post.CreatePostRequest;
import com.farhan.blog.response.post.*;
import com.farhan.blog.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping
    public Iterable<GetPostResponse> getPosts() {
        return postService.getPosts();
    }

    @GetMapping("/{slugs}")
    public GetPostResponse getPosts(@PathVariable String slugs) {
        return postService.getPostBySlug(slugs);
    }

    @PostMapping
    public CreatePostResponse createPost(@Valid @RequestBody CreatePostRequest post) {
        return postService.createPost(post);
    }

    @PutMapping("/{slug}")
    public UpdatePostResponse updatePost(@PathVariable String slug,
                                         @Valid @RequestBody CreatePostRequest post) {
        return postService.updatePost(slug, post);
    }

    @PutMapping("/publish/{id}")
    public PublishPostResponse publishPost(@PathVariable Integer id) {
        return postService.publishPost(id);
    }

    @DeleteMapping("/{id}")
    public DeletePostResponse deletePost(@PathVariable Integer id) {
        return postService.deletePost(id);
    }
}
