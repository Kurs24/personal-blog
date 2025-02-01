package com.farhan.blog.response.comment;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GetCommentResponse {
    private Integer id;
    private String name;
    private String email;
    private String body;
    private Long createdAt;
}
