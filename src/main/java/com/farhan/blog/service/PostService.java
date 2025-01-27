package com.farhan.blog.service;

import com.farhan.blog.entity.Post;
import com.farhan.blog.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public Iterable<Post> getPosts() {
        return postRepository.findAll();
    }

    public Post getPostBySlug(String slug) {
        return postRepository.findFirstBySlugAndIsDeleted(slug, false).orElse(null);
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
