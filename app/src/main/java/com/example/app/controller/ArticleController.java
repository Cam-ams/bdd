package com.example.app.controller;

import com.example.app.dto.ApiResponse;
import com.example.domain.model.Article;
import com.example.domain.port.in.ArticleUseCase;
import com.example.domain.service.TitleAlreadyUsedException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleUseCase articleUseCase;

    public ArticleController(ArticleUseCase articleUseCase) {
        this.articleUseCase = articleUseCase;
    }

    @GetMapping
    public ApiResponse<List<Article>> getAll() {
        return new ApiResponse<>(2002, "OK", articleUseCase.getAll());
    }

    @GetMapping("/{id}")
    public ApiResponse<Article> getById(@PathVariable String id) {
        Article article = articleUseCase.getById(id);
        if (article == null) {
            return new ApiResponse<>(7001, "Not found", null);
        }
        return new ApiResponse<>(2002, "OK", article);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Boolean> delete(@PathVariable String id) {
        boolean deleted = articleUseCase.delete(id);
        if (!deleted) {
            return new ApiResponse<>(7001, "Not found", false);
        }
        return new ApiResponse<>(2002, "Deleted", true);
    }

    @PostMapping
    public ApiResponse<Article> save(@RequestBody Article article) {
        try {
            return new ApiResponse<>(2002, "Saved", articleUseCase.save(article));
        } catch (TitleAlreadyUsedException e) {
            return new ApiResponse<>(7006, "Title already used", null);
        }
    }
}
