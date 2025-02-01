package com.farhan.blog.response.post;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreatePostResponse {
    private String title;
    private String body;
    private String slug;
    private Long createdAt;
}
