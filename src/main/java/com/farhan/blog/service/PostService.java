package com.farhan.blog.service;

import com.farhan.blog.entity.Post;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class PostService {

    Post post1 = new Post(1, "slug1", "title1");
    Post post2 = new Post(2, "slug2", "title2");
    @Getter
    List<Post> posts = new ArrayList<>(Arrays.asList(post1, post2));

    public Post getPostBySlug(String slug) {
        return posts.stream().filter(p -> p.getSlug().equals(slug)).findFirst().orElse(null);
    }

    public Post createPost(Post post) {
        posts.add(post);
        return post;
    }

    public boolean updatePost(Integer id, String title, String slug) {
        Post targetPost = posts.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
        if (targetPost == null) {
            return false;
        }

        targetPost.setTitle(title);
        targetPost.setSlug(slug);
        return true;
    }

    public boolean deletePost(Integer id) {
        Post targetPost = posts.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
        if (targetPost == null) {
            return false;
        }

        posts.remove(targetPost);
        return true;
    }

    public boolean publishPost(Integer id) {
        Post targetPost = posts.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
        if (targetPost == null) {
            return false;
        }

        targetPost.setPublished(true);
        return true;
    }
}
