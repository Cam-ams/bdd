package com.example.jpa.adapter;

import com.example.domain.model.Article;
import com.example.domain.port.out.ArticleRepository;
import com.example.jpa.entity.ArticleEntity;
import com.example.jpa.repository.JpaArticleRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class JpaArticleAdapter implements ArticleRepository {

    private final JpaArticleRepository jpaRepo;

    public JpaArticleAdapter(JpaArticleRepository jpaRepo) {
        this.jpaRepo = jpaRepo;
    }

    private Article toDomain(ArticleEntity e) {
        return new Article(e.getId(), e.getTitle(), e.getDescription());
    }

    private ArticleEntity toEntity(Article a) {
        return new ArticleEntity(a.getId(), a.getTitle(), a.getDescription());
    }

    @Override
    public List<Article> findAll() {
        return jpaRepo.findAll().stream().map(this::toDomain).collect(Collectors.toList());
    }

    @Override
    public Optional<Article> findById(String id) {
        return jpaRepo.findById(id).map(this::toDomain);
    }

    @Override
    public Optional<Article> findByTitle(String title) {
        return jpaRepo.findByTitle(title).map(this::toDomain);
    }

    @Override
    public Article save(Article article) {
        return toDomain(jpaRepo.save(toEntity(article)));
    }

    @Override
    public void deleteById(String id) {
        jpaRepo.deleteById(id);
    }

    @Override
    public boolean existsById(String id) {
        return jpaRepo.existsById(id);
    }
}
