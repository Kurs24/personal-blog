package com.farhan.blog.service;

import com.farhan.blog.entity.Comment;
import com.farhan.blog.entity.Post;
import com.farhan.blog.exception.ApiException;
import com.farhan.blog.mapper.CommentMapper;
import com.farhan.blog.repository.CommentRepository;
import com.farhan.blog.repository.PostRepository;
import com.farhan.blog.request.comment.CreateCommentRequest;
import com.farhan.blog.response.comment.CreateCommentResponse;
import com.farhan.blog.response.comment.DeleteCommentResponse;
import com.farhan.blog.response.comment.GetCommentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public Iterable<GetCommentResponse> getComments(String postSlug, Integer pageNo, Integer limit) {
        Post foundPost = postRepository.findFirstBySlugAndIsDeleted(postSlug, false).orElseThrow(
                () -> new ApiException("Post not found", HttpStatus.NOT_FOUND)
        );
        PageRequest pageRequest = PageRequest.of(pageNo, limit);
        ArrayList<GetCommentResponse> response = new ArrayList<>();
        commentRepository.findAllByPostId(foundPost.getId(), pageRequest)
                .forEach(comment -> response.add(CommentMapper.INSTANCE.mapToGetCommentResponse(comment)));
        return response;
    }

    public GetCommentResponse getComment(Integer id) {
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new ApiException("Comment not found", HttpStatus.NOT_FOUND)
        );
        return CommentMapper.INSTANCE.mapToGetCommentResponse(comment);
    }

    @Transactional
    public CreateCommentResponse createComment(CreateCommentRequest comment) {
        Post relatedPost = postRepository.findFirstBySlugAndIsDeleted(comment.getPost().getSlug(), false)
                .orElseThrow(() -> new ApiException("Post not found", HttpStatus.NOT_FOUND));

        Comment newComment = CommentMapper.INSTANCE.mapToCommentFromCreateCommentRequest(comment);
        newComment.setCreatedAt(Instant.now().getEpochSecond());
        newComment.getPost().setId(relatedPost.getId());
        commentRepository.save(newComment);

        relatedPost.setCommentCount(relatedPost.getCommentCount() + 1);
        postRepository.save(relatedPost);

        return CommentMapper.INSTANCE.mapToCreateCommentResponse(newComment);
    }

    public DeleteCommentResponse deleteComment(Integer id) {
        Comment targetComment = commentRepository.findById(id).orElseThrow(
                () -> new ApiException("Comment not found", HttpStatus.NOT_FOUND)
        );
        commentRepository.delete(targetComment);
        return DeleteCommentResponse.builder().isDeleted(true).build();
    }
}
