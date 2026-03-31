package com.example.domain.port.out;

import com.example.domain.model.Article;
import java.util.List;
import java.util.Optional;

public interface ArticleRepository {
    List<Article> findAll();
    Optional<Article> findById(String id);
    Optional<Article> findByTitle(String title);
    Article save(Article article);
    void deleteById(String id);
    boolean existsById(String id);
}
