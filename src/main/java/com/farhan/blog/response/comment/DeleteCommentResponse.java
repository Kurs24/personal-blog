package com.farhan.blog.response.comment;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DeleteCommentResponse {
    private Boolean isDeleted;
}
