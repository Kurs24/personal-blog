package com.farhan.blog.repository;

import com.farhan.blog.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Integer> {
    Page<Comment> findAllByPostId(Integer postId, Pageable pageable);
}
