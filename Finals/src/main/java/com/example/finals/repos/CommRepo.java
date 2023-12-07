package com.example.finals.repos;

import com.example.finals.entities.Comment;
import com.example.finals.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommRepo  extends JpaRepository<Comment, Long> {
}
