package com.chatbotuni.app.model;

import jakarta.persistence.*;

@Entity
@Table(name = "knowledge_sources")
public class KnowledgeSource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 1000)
    private String url;

    @Column(nullable = false, length = 1000)
    private String keywords;

    @Column(nullable = false, length = 2000)
    private String summary;

    public KnowledgeSource() {
    }

    public KnowledgeSource(String category, String title, String url, String keywords, String summary) {
        this.category = category;
        this.title = title;
        this.url = url;
        this.keywords = keywords;
        this.summary = summary;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }
    public String getKeywords() { return keywords; }
    public void setKeywords(String keywords) { this.keywords = keywords; }
    public String getSummary() { return summary; }
    public void setSummary(String summary) { this.summary = summary; }
}
