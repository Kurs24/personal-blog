package com.farhan.blog.repository;

import com.farhan.blog.entity.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends CrudRepository<Post, Integer> {
    Optional<Iterable<Post>> findAllByIsDeleted(boolean isDeleted);
    Optional<Post> findFirstBySlugAndIsDeleted(String slug, boolean isDeleted);
}
