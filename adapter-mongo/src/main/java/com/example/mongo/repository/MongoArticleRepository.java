package com.example.mongo.repository;

import com.example.mongo.document.ArticleDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface MongoArticleRepository extends MongoRepository<ArticleDocument, String> {
    Optional<ArticleDocument> findByTitle(String title);
}
