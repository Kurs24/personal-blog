package com.farhan.blog.mapper;

import com.farhan.blog.entity.Comment;
import com.farhan.blog.request.comment.CreateCommentRequest;
import com.farhan.blog.response.comment.CreateCommentResponse;
import com.farhan.blog.response.comment.GetCommentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CommentMapper {
    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    GetCommentResponse mapToGetCommentResponse(Comment comment);
    Comment mapToCommentFromCreateCommentRequest(CreateCommentRequest createCommentRequest);
    CreateCommentResponse mapToCreateCommentResponse(Comment comment);
}
