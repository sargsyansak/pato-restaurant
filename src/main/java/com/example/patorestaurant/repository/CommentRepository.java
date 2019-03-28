package com.example.patorestaurant.repository;

import com.example.patorestaurant.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Integer> {
}
