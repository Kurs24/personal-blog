package com.farhan.blog.controller;

import com.farhan.blog.request.comment.CreateCommentRequest;
import com.farhan.blog.response.comment.CreateCommentResponse;
import com.farhan.blog.response.comment.DeleteCommentResponse;
import com.farhan.blog.response.comment.GetCommentResponse;
import com.farhan.blog.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    @GetMapping
    public Iterable<GetCommentResponse> getComments(@RequestParam(required = false) String postSlug,
                                         @RequestParam(required = false, defaultValue = "0" ) Integer pageNo,
                                         @RequestParam(required = false, defaultValue = "5") Integer limit) {
        return commentService.getComments(postSlug, pageNo, limit);
    }

    @GetMapping("/{id}")
    public GetCommentResponse getComment(@PathVariable Integer id) {
        return commentService.getComment(id);
    }

    @PostMapping
    public CreateCommentResponse createComment(@RequestBody @Valid CreateCommentRequest comment) {
        return commentService.createComment(comment);
    }

    @DeleteMapping("/{id}")
    public DeleteCommentResponse deleteComment(@PathVariable Integer id) {
        return commentService.deleteComment(id);
    }
}
