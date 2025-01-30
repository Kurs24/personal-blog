package com.farhan.blog.response.comment;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class GetCommentResponse {
    private Integer id;
    private String name;
    private String email;
    private String body;
    private Long createdAt;
}
