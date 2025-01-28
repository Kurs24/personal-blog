package com.farhan.blog.controller;

import com.farhan.blog.entity.Comment;
import com.farhan.blog.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    @GetMapping
    public Iterable<Comment> getComments(@RequestParam(required = false) String postSlug,
                                         @RequestParam(required = false) Integer pageNo,
                                         @RequestParam(required = false) Integer limit) {
        return commentService.getComments(postSlug, pageNo, limit);
    }

    @GetMapping("/{id}")
    public Comment getComment(@PathVariable Integer id) {
        return commentService.getComment(id);
    }

    @PostMapping
    public Comment createComment(@RequestBody Comment comment) {
        return commentService.createComment(comment);
    }

    @DeleteMapping("/{id}")
    public boolean deleteComment(@PathVariable Integer id) {
        return commentService.deleteComment(id);
    }
}
