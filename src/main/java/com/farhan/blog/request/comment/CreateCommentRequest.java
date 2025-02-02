package com.farhan.blog.request.comment;

import com.farhan.blog.entity.Post;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateCommentRequest {
    @NotBlank(message = "Name cannot be blank")
    private String name;
    @NotBlank(message = "Email cannot be blank")
    @Email
    private String email;
    @NotBlank
    @Size(min = 1, message = "Body must be at least 1 character")
    private String body;

    private Post post;
}
