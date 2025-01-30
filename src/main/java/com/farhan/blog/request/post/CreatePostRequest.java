package com.farhan.blog.request.post;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class CreatePostRequest {
    @NotBlank
    @Size(max = 50, message = "Title must be less than 50 characters")
    private String title;
    @NotBlank
    @Size(min = 10, message = "Body must be at least 10 characters")
    private String body;
    @NotBlank
    @Size(min = 3, message = "Slug must at least 3 characters")
    private String slug;
}
