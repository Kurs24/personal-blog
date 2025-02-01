package com.farhan.blog.service;

import com.farhan.blog.entity.Post;
import com.farhan.blog.exception.ApiException;
import com.farhan.blog.mapper.PostMapper;
import com.farhan.blog.repository.PostRepository;
import com.farhan.blog.request.post.CreatePostRequest;
import com.farhan.blog.response.post.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    private static final String POST_NOT_FOUND_MESSAGE = "Post not found";

    public Iterable<GetPostResponse> getPosts() {
        Iterable<Post> foundPosts = postRepository.findAllByIsDeleted(false)
                .orElseThrow(() -> new ApiException(POST_NOT_FOUND_MESSAGE, HttpStatus.NOT_FOUND));
        List<GetPostResponse> response = new ArrayList<>();
        foundPosts.forEach(post -> response.add(PostMapper.INSTANCE.mapToGetPostResponse(post)));
        return response;
    }

    public GetPostResponse getPostBySlug(String slug) {
        Post foundPost = postRepository.findFirstBySlugAndIsDeleted(slug, false)
                .orElseThrow(
                        () -> new ApiException(POST_NOT_FOUND_MESSAGE, HttpStatus.NOT_FOUND));
        return PostMapper.INSTANCE.mapToGetPostResponse(foundPost);
    }

    public CreatePostResponse createPost(CreatePostRequest request) {
        Post newPost = PostMapper.INSTANCE.mapToPost(request);
        newPost.setCreatedAt(Instant.now().getEpochSecond());
        newPost.setCommentCount(0L);
        return PostMapper.INSTANCE.mapToCreatePostResponse(postRepository.save(newPost));
    }

    public UpdatePostResponse updatePost(String slug, CreatePostRequest updatedPostByUser) {
        Post targetPost = postRepository.findFirstBySlugAndIsDeleted(slug, false).orElseThrow(
                () -> new ApiException(POST_NOT_FOUND_MESSAGE, HttpStatus.NOT_FOUND)
        );
        targetPost.setTitle(updatedPostByUser.getTitle());
        targetPost.setBody(updatedPostByUser.getBody());
        targetPost.setSlug(updatedPostByUser.getSlug());
        return PostMapper.INSTANCE.mapToUpdatePostResponse(postRepository.save(targetPost));
    }

    public DeletePostResponse deletePost(Integer id) {
        Post targetPost = postRepository.findById(id).orElseThrow(
                () -> new ApiException(POST_NOT_FOUND_MESSAGE, HttpStatus.NOT_FOUND)
        );
        targetPost.setDeleted(true);
        postRepository.save(targetPost);
        return DeletePostResponse.builder().id(targetPost.getId()).build();
    }

    public PublishPostResponse publishPost(Integer id) {
        Post targetPost = postRepository.findFirstByIdAndIsDeleted(id, false).orElseThrow(
                () -> new ApiException(POST_NOT_FOUND_MESSAGE, HttpStatus.NOT_FOUND)
        );
        targetPost.setPublishedAt(Instant.now().getEpochSecond());
        targetPost.setPublished(true);
        postRepository.save(targetPost);
        return PublishPostResponse.builder().publishedAt(targetPost.getPublishedAt()).build();
    }
}
