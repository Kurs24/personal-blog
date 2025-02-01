package com.farhan.blog.mapper;

import com.farhan.blog.entity.Post;
import com.farhan.blog.request.post.CreatePostRequest;
import com.farhan.blog.response.post.CreatePostResponse;
import com.farhan.blog.response.post.GetPostResponse;
import com.farhan.blog.response.post.UpdatePostResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostMapper {
    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    GetPostResponse mapToGetPostResponse(Post post);
    Post mapToPost(CreatePostRequest createPostRequest);
    CreatePostResponse mapToCreatePostResponse(Post post);
    UpdatePostResponse mapToUpdatePostResponse(Post post);
}
