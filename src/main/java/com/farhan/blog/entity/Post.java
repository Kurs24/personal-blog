package com.farhan.blog.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Post {
    public Post(Integer id, String slug, String title) {
        this.id = id;
        this.slug = slug;
        this.title = title;
    }

    private Integer id;
    private String title;
    private String body;
    private String slug;
    private boolean isPublished;
    private boolean isDeleted;
    private Integer categoryId;
    private Date createdAt;
    private Date updatedAt;
    private Date publishedAt;
}