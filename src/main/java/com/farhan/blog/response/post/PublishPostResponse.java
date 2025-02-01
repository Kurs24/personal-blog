package com.farhan.blog.response.post;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PublishPostResponse {
    private Long publishedAt;
}
