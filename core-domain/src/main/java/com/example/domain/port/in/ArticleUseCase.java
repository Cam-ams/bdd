package com.example.domain.port.in;

import com.example.domain.model.Article;
import java.util.List;

public interface ArticleUseCase {
    List<Article> getAll();
    Article getById(String id);
    Article save(Article article);
    boolean delete(String id);
}
