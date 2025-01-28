package com.farhan.blog.service;

import com.farhan.blog.entity.Comment;
import com.farhan.blog.entity.Post;
import com.farhan.blog.repository.CommentRepository;
import com.farhan.blog.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public Iterable<Comment> getComments(String postSlug, Integer pageNo, Integer limit) {
        Post foundPost = postRepository.findFirstBySlugAndIsDeleted(postSlug, false).orElse(null);
        if (foundPost == null) {
            return null;
        }
        PageRequest pageRequest = PageRequest.of(pageNo, limit);
        return commentRepository.findAllByPostId(foundPost.getId(), pageRequest);
    }

    public Comment getComment(Integer id) {
        return commentRepository.findById(id).orElse(null);
    }

    public Comment createComment(Comment comment) {
        comment.setCreatedAt(Instant.now().getEpochSecond());
        Comment newComment = commentRepository.save(comment);

        Post relatedPost = postRepository.findById(comment.getPost().getId()).orElse(null);
        if (relatedPost == null) {
            return null;
        }
        relatedPost.setCommentCount(relatedPost.getCommentCount() + 1);
        postRepository.save(relatedPost);

        return newComment;
    }

    public boolean deleteComment(Integer id) {
        Comment targetComment = commentRepository.findById(id).orElse(null);
        if (targetComment == null) {
            return false;
        }
        commentRepository.delete(targetComment);
        return true;
    }
}
