package com.farhan.blog.mapper;

import com.farhan.blog.entity.Comment;
import com.farhan.blog.response.comment.GetCommentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CommentMapper {
    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    GetCommentResponse mapToCommentResponse(Comment comment);
}
