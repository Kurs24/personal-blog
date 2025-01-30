package com.farhan.blog.response.post;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class GetPostResponse {
    private Integer id;
    private String title;
    private String body;
    private String slug;
    private Long publishedAt;
    private Long commentCount;
}
