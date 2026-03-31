package com.example.jpa.repository;

import com.example.jpa.entity.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface JpaArticleRepository extends JpaRepository<ArticleEntity, String> {
    Optional<ArticleEntity> findByTitle(String title);
}
