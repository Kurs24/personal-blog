package com.farhan.blog.service;

import com.farhan.blog.entity.Post;
import com.farhan.blog.exception.ApiException;
import com.farhan.blog.mapper.PostMapper;
import com.farhan.blog.repository.PostRepository;
import com.farhan.blog.response.post.GetPostResponse;
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

    public Iterable<GetPostResponse> getPosts() {
        Iterable<Post> foundPosts = postRepository.findAllByIsDeleted(false)
                .orElseThrow(() -> new ApiException("Posts not found", HttpStatus.NOT_FOUND));
        List<GetPostResponse> response = new ArrayList<>();
        foundPosts.forEach(post -> response.add(PostMapper.INSTANCE.mapToPostResponse(post)));
        return response;
    }

    public GetPostResponse getPostBySlug(String slug) {
        Post foundPost = postRepository.findFirstBySlugAndIsDeleted(slug, false)
                .orElseThrow(
                        () -> new ApiException("Post not found", HttpStatus.NOT_FOUND));
        return PostMapper.INSTANCE.mapToPostResponse(foundPost);
    }

    public Post createPost(Post post) {
        post.setCreatedAt(Instant.now().getEpochSecond());
        return postRepository.save(post);
    }

    public boolean updatePost(String slug, Post updatedPostByUser) {
        Post targetPost = postRepository.findFirstBySlugAndIsDeleted(slug, false).orElse(null);
        if (targetPost == null) {
            return false;
        }
        updatedPostByUser.setId(targetPost.getId());
        postRepository.save(updatedPostByUser);
        return true;
    }

    public boolean deletePost(Integer id) {
        Post targetPost = postRepository.findById(id).orElse(null);
        if (targetPost == null) {
            return false;
        }
        targetPost.setDeleted(true);
        postRepository.save(targetPost);
        return true;
    }

    public Post publishPost(Integer id) {
        Post targetPost = postRepository.findById(id).orElse(null);
        if (targetPost == null) {
            return null;
        }
        targetPost.setPublishedAt(Instant.now().getEpochSecond());
        targetPost.setPublished(true);
        return postRepository.save(targetPost);
    }
}
