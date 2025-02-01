package com.farhan.blog.mapper;

import com.farhan.blog.entity.Post;
import com.farhan.blog.response.post.GetPostResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostMapper {
    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    GetPostResponse mapToPostResponse(Post post);
}
