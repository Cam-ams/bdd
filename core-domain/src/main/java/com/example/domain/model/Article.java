package com.example.domain.model;

import java.util.UUID;

public class Article {
    private String id;
    private String title;
    private String description;

    public Article() {}

    public Article(String id, String title, String description) {
        this.id = (id == null || id.isEmpty()) ? UUID.randomUUID().toString() : id;
        this.title = title;
        this.description = description;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
