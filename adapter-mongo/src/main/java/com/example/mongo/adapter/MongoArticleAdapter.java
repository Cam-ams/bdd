package com.example.mongo.adapter;

import com.example.domain.model.Article;
import com.example.domain.port.out.ArticleRepository;
import com.example.mongo.document.ArticleDocument;
import com.example.mongo.repository.MongoArticleRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class MongoArticleAdapter implements ArticleRepository {

    private final MongoArticleRepository mongoRepo;

    public MongoArticleAdapter(MongoArticleRepository mongoRepo) {
        this.mongoRepo = mongoRepo;
    }

    private Article toDomain(ArticleDocument d) {
        return new Article(d.getId(), d.getTitle(), d.getDescription());
    }

    private ArticleDocument toDocument(Article a) {
        return new ArticleDocument(a.getId(), a.getTitle(), a.getDescription());
    }

    @Override
    public List<Article> findAll() {
        return mongoRepo.findAll().stream().map(this::toDomain).collect(Collectors.toList());
    }

    @Override
    public Optional<Article> findById(String id) {
        return mongoRepo.findById(id).map(this::toDomain);
    }

    @Override
    public Optional<Article> findByTitle(String title) {
        return mongoRepo.findByTitle(title).map(this::toDomain);
    }

    @Override
    public Article save(Article article) {
        return toDomain(mongoRepo.save(toDocument(article)));
    }

    @Override
    public void deleteById(String id) {
        mongoRepo.deleteById(id);
    }

    @Override
    public boolean existsById(String id) {
        return mongoRepo.existsById(id);
    }
}
