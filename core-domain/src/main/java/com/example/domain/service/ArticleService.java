package com.example.domain.service;

import com.example.domain.model.Article;
import com.example.domain.port.in.ArticleUseCase;
import com.example.domain.port.out.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService implements ArticleUseCase {

    private final ArticleRepository repository;

    public ArticleService(ArticleRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Article> getAll() {
        return repository.findAll();
    }

    @Override
    public Article getById(String id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Article save(Article article) {
        repository.findByTitle(article.getTitle()).ifPresent(existing -> {
            if (!existing.getId().equals(article.getId())) {
                throw new TitleAlreadyUsedException("Le titre est déjà utilisé");
            }
        });

        if (article.getId() == null || article.getId().isEmpty()) {
            article.setId(java.util.UUID.randomUUID().toString());
        }

        return repository.save(article);
    }

    @Override
    public boolean delete(String id) {
        if (!repository.existsById(id)) return false;
        repository.deleteById(id);
        return true;
    }
}
